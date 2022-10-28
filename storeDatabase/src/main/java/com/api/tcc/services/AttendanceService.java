/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.database.Models.AttendanceModel;
import com.api.tcc.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class AttendanceService {

    private static final int MINIMUM_MINUTES_TO_ATTEND_AGAIN = 20;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Transactional
    public AttendanceModel save(AttendanceModel attendanceModel) {
        return attendanceRepository.save(attendanceModel);
    }

    public List<AttendanceModel> findAll() {
        return attendanceRepository.findAll();
    }

    public Optional<AttendanceModel> findById(UUID id) {
        return attendanceRepository.findById(id);
    }

    public Optional<AttendanceModel> findClientSellerModelBySellerModel_Id(long id) { return attendanceRepository.findClientSellerModelBySellerModel_Id(id);}

    @Transactional
    public void delete(AttendanceModel attendanceModel) {
        attendanceRepository.delete(attendanceModel);
    }

    public boolean isBeingAttended(long clientId) {
        Optional<AttendanceModel> clientSellerModelOptional = attendanceRepository.findClientSellerModelByClientModel_IdAndEndTime(clientId, null);

        if (!clientSellerModelOptional.isEmpty()) {
            return true;
        } else {
            Optional<List<AttendanceModel>> clientSellerModelOptionalList = attendanceRepository.findClientSellerModelByClientModel_Id(clientId);
            if(!clientSellerModelOptionalList.isPresent()) {
                return false;
            } else {
                if(!clientSellerModelOptionalList.get().isEmpty()) {
                    AttendanceModel attendanceModel = clientSellerModelOptionalList.get().get(clientSellerModelOptionalList.get().size() - 1);
                    LocalDateTime lastAttendance = attendanceModel.getEndTime(), localDateTimeNow = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
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

    @Transactional
    public void deleteAttendance(long sellerId) {

        List<AttendanceModel> attendanceModelList = attendanceRepository.findAll();

        for(AttendanceModel attendanceModel : attendanceModelList) {
            if (attendanceModel.getEndTime() == null) {
                attendanceRepository.delete(attendanceModel);
            }
        }
    }
}
