package com.api.tcc.controllers;

import com.api.tcc.Utils.ManipulatingImage;
import com.api.tcc.services.ClientImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/clientImage")
public class ClientImageController {

    private final ClientImageService clientImageService;

    public ClientImageController(ClientImageService clientImageService) {
        this.clientImageService = clientImageService;
    }

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        System.out.println("Imagens Recebidas: " + image.getInputStream());

        StringBuilder filesName = new StringBuilder();
        ManipulatingImage manipulatingImage = new ManipulatingImage();

        try {
            Files.write(manipulatingImage.fileNameAndPath(true), image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("image was received successfully!");
    }
}
