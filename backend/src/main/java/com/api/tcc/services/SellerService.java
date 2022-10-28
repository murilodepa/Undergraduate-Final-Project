/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.RegistrationSellerDTO;
import com.api.tcc.email.EmailMessages;
import com.api.tcc.email.SendEmail;
import com.api.tcc.repositories.ClientSellerRepository;
import com.api.tcc.repositories.SellerImageRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.utils.IndexAndName;
import com.api.tcc.utils.ManipulatingImage;
import com.api.tcc.utils.PostRequestApiPython;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private SellerImageRepository sellerImageRepository;

    @Autowired
    private ClientSellerRepository clientSellerRepository;

    private static final String URL_MATCH_MAKING = "http://127.0.0.1:9090/postBestSeller";

    @Transactional
    public SellerModel save(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitle(sellerModel), EmailMessages.messageToNewSeller(sellerModel));
        return sellerRepository.save(sellerModel);
    }

    @Transactional
    public SellerModel update(SellerModel sellerModel) {
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitleUpdate(sellerModel), EmailMessages.messageToUpdateSeller(sellerModel));
        return sellerRepository.save(sellerModel);
    }

    public List<SellerModel> findAll() {
        return sellerRepository.findAll();
    }

    public List<SellerModel> findAllSellerAvailable() {
        return sellerRepository.findAllSellerAvailable();
    }

    public Optional<SellerModel> findById(long id) {
        return sellerRepository.findById(id);
    }

    private void deletePhotos(long sellerId) {
        String sellerPhotosPath;
        int count;
        for (count = 1; count <= ManipulatingImage.QUANTITY_OF_PHOTOS; count++) {
            sellerPhotosPath = "src\\main\\resources\\photos\\sellers\\person." + sellerId + "." + count + ".jpg";
            File image = new File(sellerPhotosPath);
            if (image.exists()) {
                image.delete();
            }
        }
    }

    @Transactional
    public void delete(SellerModel sellerModel) {
        deletePhotos(sellerModel.getId());
        this.sendEmail.send(sellerModel.getEmail(),
                EmailMessages.createTitleToRemove(sellerModel), EmailMessages.messageToRemoveSeller(sellerModel));
        sellerRepository.delete((sellerModel));
    }

    public boolean existsByEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

    public List<IndexAndName> getSellerNames() {
        List<IndexAndName> sellerDate = new ArrayList<>();
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        for (SellerModel sellerModel : sellerModelList) {
            sellerDate.add(new IndexAndName(sellerModel.getId(), sellerModel.getName()));
        }
        return sellerDate;
    }

    public List<SellerModel> verifySellersAvailable() {
        List<SellerModel> sellerModelList = sellerRepository.findAll();
        List<SellerModel> availableSellerModelList = new ArrayList<>();

        for (SellerModel sellerModel: sellerModelList) {
            if(sellerModel.getAvailable()) {
                availableSellerModelList.add(sellerModel);
            }
        }
        return availableSellerModelList;
    }

    public RegistrationSellerDTO getRegisteredSeller(String email, String password) throws IOException {
        List<SellerModel> sellerModelList = sellerRepository.findAll();
        RegistrationSellerDTO registrationSellerDTO = new RegistrationSellerDTO();
        ManipulatingImage manipulatingImage = new ManipulatingImage();

        for (SellerModel sellerModel : sellerModelList) {
            if (Objects.equals(email, sellerModel.getEmail()) && Objects.equals(password, sellerModel.getPassword())) {
                sellerModel.setAvailable(true);
                sellerRepository.save(sellerModel);
                List<SellerImageModel> sellerImageModelList = sellerImageRepository.findSellerImages(sellerModel.getId());
                BeanUtils.copyProperties(sellerModel, registrationSellerDTO);
                if(!sellerImageModelList.isEmpty()) {
                    registrationSellerDTO.setProfileImage(manipulatingImage.decodeImage(sellerImageModelList.get(0).getImage()));
                }
                FormattingDates formattingDates = new FormattingDates();
                registrationSellerDTO.setBirth(formattingDates.convertDateToAge(sellerModel.getBirth()));

                List<ClientSellerModel> clientSellerModelListAux = clientSellerRepository.findAll();
                List<ClientSellerModel> clientSellerModelList = new ArrayList<>();

                for(ClientSellerModel clientSellerModel : clientSellerModelListAux) {
                    if(clientSellerModel.getEndTime() == null){
                        clientSellerModelList.add(clientSellerModel);
                    }
                }

                // Verify list of client - seller and seller available to indicate attendance
                if(!clientSellerModelList.isEmpty()) {
                    for (ClientSellerModel clientSellerModel : clientSellerModelList) {
                        List<SellerModel> availableSellerModelList = verifySellersAvailable();
                        if (!availableSellerModelList.isEmpty()) {
                            if (availableSellerModelList.size() == 1) {
                                sellerModel = availableSellerModelList.get(0);
                                sellerModel.setAvailable(false);
                                sellerRepository.save(sellerModel);
                                clientSellerModel.setSellerModel(sellerModel);
                            } else {
                                if (clientSellerModel.getClientModel() != null) {
                                    sellerModel = matchMakingGetBestSeller(clientSellerModel.getClientModel());
                                    if(sellerModel != null) {
                                        sellerModel.setAvailable(false);
                                        sellerRepository.save(sellerModel);
                                        clientSellerModel.setSellerModel(sellerModel);
                                    }
                                } else {
                                    availableSellerModelList.sort(Comparator.comparing(SellerModel::getAttendances));
                                    sellerModel = availableSellerModelList.get(0);
                                    sellerModel.setAvailable(false);
                                    sellerRepository.save(sellerModel);
                                    clientSellerModel.setSellerModel(sellerModel);
                                }
                            }
                            clientSellerRepository.save(clientSellerModel);
                        }
                    }
                }

                return registrationSellerDTO;
            }
        }
        return null;
    }

    public SellerModel matchMakingGetBestSeller(ClientModel clientModel) {
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        if (!sellerModelList.isEmpty()) {
            sellerModelList.removeIf(sellerModel -> !sellerModel.getAvailable());

            if (!sellerModelList.isEmpty()) {
                if (sellerModelList.size() == 1) {
                    return sellerModelList.get(0);
                }

                StringBuilder parametersModel = new StringBuilder();
                for (SellerModel sellerModel : sellerModelList) {
                    parametersModel.append(sellerModel.getId()).append(",");
                    parametersModel.append(sellerModel.getGender().toLowerCase()).append(",");
                    parametersModel.append(sellerModel.getSector().toLowerCase().replace('ç', 'c')).append(",");
                    parametersModel.append(sellerModel.getServicePreference().toLowerCase().replace('ç', 'c')).append("/");
                }
                parametersModel.append(clientModel.getId()).append(",");
                parametersModel.append(clientModel.getGender().toLowerCase()).append(",");
                if (clientModel.getPurchaseSuggestion() != null) {
                    parametersModel.append(clientModel.getPurchaseSuggestion().toLowerCase().replace('ç', 'c')).append(",");
                } else {
                    parametersModel.append(",");
                }
                FormattingDates formattingDates = new FormattingDates();
                parametersModel.append(formattingDates.getAgeGroup(clientModel.getBirth()).toLowerCase());

                long sellerId = Long.parseLong(PostRequestApiPython.requestApiPython(String.valueOf(parametersModel), URL_MATCH_MAKING));
                Optional<SellerModel> sellerModelOptional = sellerRepository.findById(sellerId);
                if (!sellerModelOptional.isPresent()) {
                    return null;
                }
                return sellerModelOptional.get();
            }
        }
        return null;
    }

    public SellerModel getSellerWithLessAttendance() {
        List<SellerModel> sellerModelList = sellerRepository.findAll();

        if (!sellerModelList.isEmpty()) {
            sellerModelList.removeIf(sellerModel -> !sellerModel.getAvailable());
            if (!sellerModelList.isEmpty()) {
                sellerModelList.sort(Comparator.comparing(SellerModel::getAttendances));
                        return sellerModelList.get(0);
            }
        }
        return null;
    }
}
