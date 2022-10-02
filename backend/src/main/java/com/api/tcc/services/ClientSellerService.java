/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.ClientsWaitingAttendanceDTO;
import com.api.tcc.repositories.ClientImageRepository;
import com.api.tcc.repositories.ClientSellerRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.CategorySizeAndDate;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class ClientSellerService {

    private static final int MINIMUM_MINUTES_TO_ATTEND_AGAIN = 20;
    @Autowired
    private ClientSellerRepository clientSellerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ClientImageRepository clientImageRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Transactional
    public ClientSellerModel save(ClientSellerModel clientSellerModel) {
        return clientSellerRepository.save(clientSellerModel);
    }

    public List<ClientSellerModel> findAll() {
        return clientSellerRepository.findAll();
    }

    public Optional<ClientSellerModel> findById(UUID id) {
        return clientSellerRepository.findById(id);
    }

    public Optional<ClientSellerModel> findClientSellerModelBySellerModel_Id(long id) { return clientSellerRepository.findClientSellerModelBySellerModel_Id(id);}

    @Transactional
    public void delete(ClientSellerModel clientSellerModel) {
        clientSellerRepository.delete(clientSellerModel);
    }

    public boolean isBeingAttended(long clientId) {
        Optional<ClientSellerModel> clientSellerModelOptional = clientSellerRepository.findClientSellerModelByClientModel_IdAndEndTime(clientId, null);

        if (!clientSellerModelOptional.isEmpty()) {
            return true;
        } else {
            Optional<List<ClientSellerModel>> clientSellerModelOptionalList = clientSellerRepository.findClientSellerModelByClientModel_Id(clientId);
            if(!clientSellerModelOptionalList.isPresent()) {
                return false;
            } else {
                if(!clientSellerModelOptionalList.get().isEmpty()) {
                    ClientSellerModel clientSellerModel = clientSellerModelOptionalList.get().get(clientSellerModelOptionalList.get().size() - 1);
                    LocalDateTime lastAttendance = clientSellerModel.getEndTime(), localDateTimeNow = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
                    System.out.println("End: " + lastAttendance.getHour() + ":" + lastAttendance.getMinute());
                    System.out.println("Now: " + localDateTimeNow.getHour() + ":" + localDateTimeNow.getMinute());
                    int startTimeStamp = lastAttendance.getHour() * 60 + lastAttendance.getMinute();
                    int endTimeStamp = localDateTimeNow.getHour() * 60 + localDateTimeNow.getMinute();
                    return endTimeStamp - startTimeStamp < MINIMUM_MINUTES_TO_ATTEND_AGAIN;
                } else {
                    return false;
                }
            }
        }
    }

    public ClientsWaitingAttendanceDTO clientWaitingAttendance(long sellerId) throws IOException {
        List<ClientSellerModel> clientSellerModelList = clientSellerRepository.findAll();
        ClientsWaitingAttendanceDTO clientsWaitingAttendanceDTO = new ClientsWaitingAttendanceDTO();
        ManipulatingImage manipulatingImage = new ManipulatingImage();
        FormattingDates formattingDates = new FormattingDates();

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if(clientSellerModel.getSellerModel() != null && clientSellerModel.getSellerModel().getId() == sellerId && clientSellerModel.getEndTime() == null) {
                long clientId;
                if(clientSellerModel.getClientModel() != null) {
                    ClientModel clientModel = clientSellerModel.getClientModel();
                    clientId = clientModel.getId();
                    clientsWaitingAttendanceDTO.setClientId(clientId);
                    clientsWaitingAttendanceDTO.setName(clientModel.getName());
                    LocalDate date = clientModel.getBirth();
                    clientsWaitingAttendanceDTO.setBirth(formattingDates.convertDatabaseToDateComplete(date));
                    clientsWaitingAttendanceDTO.setGender(clientModel.getGender());
                    List<CategorySizeAndDate> categorySizeAndDateList = purchaseService.getPurchasedList(clientId, null, null);
                    clientsWaitingAttendanceDTO.setVisibleHappyBirthdayImage(formattingDates.getHappyBirthdayNear(date, categorySizeAndDateList));
                    List<ClientImageModel> clientImageModelList = clientImageRepository.findClientImages(clientId);
                    if(!clientImageModelList.isEmpty()) {
                        clientsWaitingAttendanceDTO.setProfileImage(manipulatingImage.decodeImage(clientImageModelList.get(0).getImage()));
                    }
                } else {
                    clientsWaitingAttendanceDTO.setClientId(0);
                    clientsWaitingAttendanceDTO.setName("Desconhecido");
                    clientsWaitingAttendanceDTO.setBirth("Nascimento não registrado");
                    clientsWaitingAttendanceDTO.setGender("Gênero não registrado");

                    String fileName = manipulatingImage.getFileNameUnknownClient();
                    if(fileName != null) {
                        final String photoPath = "src\\main\\resources\\photos\\Unknown_Client\\" + fileName;
                        Path source = Paths.get(photoPath);
                        BufferedImage bi = ImageIO.read(source.toFile());

                        // convert BufferedImage to byte[]
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(bi, "png", baos);
                        byte[] imageBytes = baos.toByteArray();
                        clientsWaitingAttendanceDTO.setProfileImage(manipulatingImage.decodeImage(imageBytes));
                        /* TO DO - Delete file after sending...
                        File photo = new File(photoPath);
                        if (photo.exists()) {
                            photo.delete();
                        }*/
                    }
                }

                return clientsWaitingAttendanceDTO;
            }
        }

        return null;
    }

    public void endAttendance(long sellerId) {
        List<ClientSellerModel> clientSellerModelListAux = clientSellerRepository.findAll();
        List<ClientSellerModel> clientSellerModelList = new ArrayList<>();
        List<ClientSellerModel> clientSellerModelListToAttendace = new ArrayList<>();

        for(ClientSellerModel clientSellerModel : clientSellerModelListAux) {
            if(clientSellerModel.getEndTime() == null){
                clientSellerModelList.add(clientSellerModel);
                clientSellerModelListToAttendace.add(clientSellerModel);
            }
        }

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if (clientSellerModel.getSellerModel() != null && !clientSellerModel.getSellerModel().getAvailable()
                    && clientSellerModel.getSellerModel().getId() == sellerId) {

                clientSellerModel.setEndTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                SellerModel sellerModel = clientSellerModel.getSellerModel();
                sellerModel.setAvailable(true);
                int attendance = sellerModel.getAttendances()+1;
                sellerModel.setAttendances(attendance);
                clientSellerModel.getSellerModel().setAvailable(true);
                clientSellerModel.getSellerModel().setAttendances(attendance);
                sellerRepository.save(sellerModel);
                clientSellerRepository.save(clientSellerModel);
                clientSellerModelListToAttendace.remove(clientSellerModel);
            }
        }

        // Verify list of client - seller and seller available to indicate attendance
        if(!clientSellerModelListToAttendace.isEmpty()) {
            SellerModel sellerModel;
            for (ClientSellerModel clientSellerModel : clientSellerModelListToAttendace) {
                List<SellerModel> availableSellerModelList = sellerService.verifySellersAvailable();
                if (!availableSellerModelList.isEmpty()) {
                    if (availableSellerModelList.size() == 1) {
                        sellerModel = availableSellerModelList.get(0);
                        sellerModel.setAvailable(false);
                        sellerRepository.save(sellerModel);
                        clientSellerModel.setSellerModel(sellerModel);
                    } else {
                        if (clientSellerModel.getClientModel() != null) {
                            sellerModel = sellerService.matchMakingGetBestSeller(clientSellerModel.getClientModel());
                            if(sellerModel != null) {
                                sellerModel.setAvailable(false);
                                sellerService.save(sellerModel);
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
    }

    @Transactional
    public void deleteAttendance(long sellerId) {

        List<ClientSellerModel> clientSellerModelList = clientSellerRepository.findAll();

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if (clientSellerModel.getEndTime() == null
                    && clientSellerModel.getSellerModel().getId() == sellerId) {
                clientSellerRepository.delete(clientSellerModel);
            }
        }
    }
}
