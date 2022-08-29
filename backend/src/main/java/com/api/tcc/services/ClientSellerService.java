/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientImageModel;
import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.ClientsWaitingAttendanceDTO;
import com.api.tcc.repositories.ClientImageRepository;
import com.api.tcc.repositories.ClientSellerRepository;
import com.api.tcc.repositories.SellerRepository;
import com.api.tcc.utils.FormattingDates;
import com.api.tcc.utils.ManipulatingImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private ClientImageRepository clientImageRepository;

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

    public Optional<SellerModel> getBestSeller() {
        List<SellerModel> sellerModelList = sellerRepository.findAll();
        if(sellerModelList.isEmpty()) {
            return Optional.empty();
        }
        if(sellerModelList.size() == 1) {
            return Optional.ofNullable(sellerModelList.get(0));
        }
        return sellerModelList.stream().min(Comparator.comparing(SellerModel::getAttendances));
    }

    public ClientsWaitingAttendanceDTO clientWaitingAttendance(long sellerId) throws IOException {
        List<ClientSellerModel> clientSellerModelList = clientSellerRepository.findAll();
        ClientsWaitingAttendanceDTO clientsWaitingAttendanceDTO = new ClientsWaitingAttendanceDTO();
        ManipulatingImage manipulatingImage = new ManipulatingImage();
        FormattingDates formattingDates = new FormattingDates();

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if(clientSellerModel.getSellerModel().getId() == sellerId && clientSellerModel.getEndTime() == null && clientSellerModel.getClientModel().getAvailable()) {
                clientsWaitingAttendanceDTO.setClientId(clientSellerModel.getClientModel().getId());
                clientsWaitingAttendanceDTO.setName(clientSellerModel.getClientModel().getName());
                clientsWaitingAttendanceDTO.setBirth(formattingDates.convertDateToAge(clientSellerModel.getClientModel().getBirth()));
                clientsWaitingAttendanceDTO.setGender(clientSellerModel.getClientModel().getGender());
                List<ClientImageModel> clientImageModelList = clientImageRepository.findClientImages(clientSellerModel.getClientModel().getId());
                if(!clientImageModelList.isEmpty()) {
                    clientsWaitingAttendanceDTO.setProfileImage(manipulatingImage.decodeImage(clientImageModelList.get(0).getImage()));
                }

                return clientsWaitingAttendanceDTO;
            }
        }

        return null;
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

    public void endAttendance(long clientId, long sellerId) {
        List<ClientSellerModel> clientSellerModelList = clientSellerRepository.findAll();

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if (clientSellerModel.getEndTime() == null
                    && !clientSellerModel.getClientModel().getAvailable()
                    && !clientSellerModel.getSellerModel().getAvailable()
                    && clientSellerModel.getClientModel().getId() == clientId
                    && clientSellerModel.getSellerModel().getId() == sellerId) {
                clientSellerModel.setEndTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                clientSellerModel.getClientModel().setAvailable(true);
                clientSellerModel.getSellerModel().setAvailable(true);
            }
        }

        //TO DO Verificar lista e indicar vendedor para algum cliente que não foi atendido ainda
        List<SellerModel> availableSellerModelList = verifySellersAvailable();
        for (ClientSellerModel clientSellerModel : clientSellerModelList) {
            if (clientSellerModel.getEndTime() == null && clientSellerModel.getSellerModel() == null && clientSellerModel.getClientModel().getAvailable() && !availableSellerModelList.isEmpty()) {
                if(availableSellerModelList.size() == 1) {
                    clientSellerModel.setSellerModel(availableSellerModelList.get(0));
                } else {
                    //TO DO MATCH MAKING
                }
            }
        }
    }

    @Transactional
    public void deleteAttendance(long clientId, long sellerId) {

        List<ClientSellerModel> clientSellerModelList = clientSellerRepository.findAll();

        for(ClientSellerModel clientSellerModel : clientSellerModelList) {
            if (clientSellerModel.getEndTime() == null
                    && clientSellerModel.getClientModel().getId() == clientId
                    && clientSellerModel.getSellerModel().getId() == sellerId) {
                clientSellerRepository.delete(clientSellerModel);
            }
        }
    }
}
