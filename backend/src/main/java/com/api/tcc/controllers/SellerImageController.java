package com.api.tcc.controllers;

import com.api.tcc.Utils.ManipulatingImage;

import com.api.tcc.services.SellerImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/seller")
public class SellerImageController {


    private final SellerImageService sellerImageService;

    public SellerImageController(SellerImageService sellerImageService) {
        this.sellerImageService = sellerImageService;
    }

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println("Imagens Recebidas: " + image.getInputStream());
        ManipulatingImage manipulatingImage = new ManipulatingImage();
        final byte[] encodeImage;
        long nextId = (sellerImageService.lastId());
        if (nextId > 0) {
            final String fileName = manipulatingImage.fileName(false, (int) (nextId));
            try {
                Files.write(manipulatingImage.fileNameAndPath(false, fileName), image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            encodeImage = manipulatingImage.encodeImage(false, fileName);
            sellerImageService.saveImage(encodeImage, nextId);
            return ResponseEntity.ok("Image was received successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
    }
}
