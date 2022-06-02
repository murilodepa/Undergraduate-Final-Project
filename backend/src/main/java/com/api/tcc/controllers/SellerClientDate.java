package com.api.tcc.controllers;

import com.api.tcc.services.ClientService;
import com.api.tcc.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellerClientDate {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SellerService sellerService;

    @GetMapping("/getIdsAndNames")
    public ResponseEntity<String[]> getSellerClientDate() {
        return ResponseEntity.status(HttpStatus.OK).body(getSellerClientIdsAndNames());
    }

    public String[] getSellerClientIdsAndNames() {
        String[] seller = sellerService.getClientIdsAndNames();
        String[] client = clientService.getClientIdsAndNames();
        String[] concatenateSellerClient = new String[200];

        int i = 0, j = 0;

        while (seller[i] != null) {
            concatenateSellerClient[i] = seller[i];
            i++;
        }

        while (client[j] != null) {
            concatenateSellerClient[i] = client[j];
            i++;
            j++;
        }

        return concatenateSellerClient;
    }
}
