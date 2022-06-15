package com.api.tcc.services;

import com.api.tcc.database.Models.ClientSellerModel;
import com.api.tcc.repositories.ClientSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientSellerService {

    private static final int MINIMUM_MINUTES_TO_ATTEND_AGAIN = 20;
    @Autowired
    private ClientSellerRepository clientSellerRepository;

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

    @Transactional
    public void delete(ClientSellerModel clientSellerModel) {
        clientSellerRepository.delete(clientSellerModel);
    }

    public boolean isBeingAttended(long clientId) {
        ClientSellerModel clientSellerModel = clientSellerRepository.findClientAttendances(clientId);
        if (clientSellerModel.getEndTime() == null) {
            return true;
        }
        LocalDateTime lastAttendance = clientSellerModel.getEndTime(), localDateTimeNow = LocalDateTime.now();
        int startTimeStamp = lastAttendance.getHour() * 60 + lastAttendance.getMinute();
        int endTimeStamp = localDateTimeNow.getHour() * 60 + localDateTimeNow.getMinute();
        return endTimeStamp - startTimeStamp < MINIMUM_MINUTES_TO_ATTEND_AGAIN;
    }
}
