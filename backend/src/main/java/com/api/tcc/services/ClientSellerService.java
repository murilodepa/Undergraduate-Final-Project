/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.repositories.ClientSellerRepository;
import com.api.tcc.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        //ClientSellerModel clientSellerModel;

        if (!clientSellerModelOptional.isEmpty()) {
            return true;
        } else {
            Optional<List<ClientSellerModel>> clientSellerModelOptionalList = clientSellerRepository.findClientSellerModelByClientModel_Id(clientId);
            if(clientSellerModelOptionalList.get().isEmpty()) {
                return false;
            } else {
                ClientSellerModel clientSellerModel = clientSellerModelOptionalList.get().get(clientSellerModelOptionalList.get().size()-1);
                LocalDateTime lastAttendance = clientSellerModel.getEndTime(), localDateTimeNow = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
                System.out.println("End: " + lastAttendance.getHour() + ":" + lastAttendance.getMinute());
                System.out.println("Now: " + localDateTimeNow.getHour() + ":" + localDateTimeNow.getMinute());
                int startTimeStamp = lastAttendance.getHour() * 60 + lastAttendance.getMinute();
                int endTimeStamp = localDateTimeNow.getHour() * 60 + localDateTimeNow.getMinute();
                return endTimeStamp - startTimeStamp < MINIMUM_MINUTES_TO_ATTEND_AGAIN;
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
}
