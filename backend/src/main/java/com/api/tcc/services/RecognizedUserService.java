/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.services;

import com.api.tcc.utils.IndexAndName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Murilo de Paula Araujo
 */
@Service
public class RecognizedUserService {

    @Autowired
    ClientService clientService;

    @Autowired
    SellerService sellerService;

    public IndexAndName verifyRecognizedClient(long userId) {
        return clientService.getClientNames().stream().filter(clientId -> userId == clientId.getId()).findAny().orElse(null);
    }

    public IndexAndName verifyRecognizedSeller(long userId) {
        return sellerService.getSellerNames().stream().filter(sellerId -> userId == sellerId.getId()).findAny().orElse(null);
    }
}
