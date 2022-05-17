package com.api.tcc.controllers;

import com.api.tcc.Utils.ManipulatingImage;
import com.api.tcc.services.ClientImageService;
import com.api.tcc.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/client")
public class ClientImageController {

    private final ClientImageService clientImageService;

    public ClientImageController(ClientImageService clientImageService) {
        this.clientImageService = clientImageService;
    }

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println("Imagens Recebidas: " + image.getInputStream());

        ManipulatingImage manipulatingImage = new ManipulatingImage();

        final byte[] encodeImage;
        long nextId = (clientImageService.lastId());
        if (nextId > 0) {
            final String fileName = manipulatingImage.fileName(true, (int) (nextId));

            try {
                Files.write(manipulatingImage.fileNameAndPath(true, fileName), image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            encodeImage = manipulatingImage.encodeImage(true, fileName);

            System.out.println("nextId:" + nextId);

            clientImageService.saveImage(encodeImage, nextId);

            return ResponseEntity.ok("image was received successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found!");
        }
    }
}
