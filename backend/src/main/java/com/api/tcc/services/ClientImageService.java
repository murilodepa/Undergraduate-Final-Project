package com.api.tcc.services;

import com.api.tcc.repositories.ClientImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientImageService {

    private final ClientImageRepository clientImageRepository;

    public ClientImageService(ClientImageRepository clientImageRepository) {
        this.clientImageRepository = clientImageRepository;
    }

    public void saveImage(String image) {
        System.out.println("Imagem Recebida");
    }
}