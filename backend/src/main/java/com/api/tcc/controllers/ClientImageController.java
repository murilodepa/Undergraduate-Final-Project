package com.api.tcc.controllers;

import com.api.tcc.services.ClientImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/image")
public class ClientImageController {

    private final ClientImageService clientImageService;

    public ClientImageController(ClientImageService clientImageService) {
        this.clientImageService = clientImageService;
    }

    @PostMapping("/sendImage")
    public ResponseEntity<Object> sendImage(@RequestParam @NotNull String image) {
        System.out.println("Imagem Recebida");
        clientImageService.saveImage(image);
        return ResponseEntity.ok("image was received successfully!");
    }
}
