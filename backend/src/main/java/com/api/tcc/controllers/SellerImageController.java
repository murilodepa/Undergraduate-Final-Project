package com.api.tcc.controllers;

import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.dtos.ImageAndNamesDTO;
import com.api.tcc.utils.ManipulatingImage;

import com.api.tcc.services.SellerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seller")
public class SellerImageController {

    @Autowired
    private SellerImageService sellerImageService;
    private ManipulatingImage manipulatingImage = new ManipulatingImage();

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println("Images Receives: " + image.getInputStream());
        final byte[] encodeImage;
        long sellerId = (sellerImageService.getSellerId());
        if (sellerId > 0) {
            final String fileName = manipulatingImage.fileName(false, (int) (sellerId));
            try {
                Files.write(manipulatingImage.fileNameAndPath(false, fileName), image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            encodeImage = manipulatingImage.encodeImage(false, fileName);
            sellerImageService.saveImage(encodeImage, sellerId);
            return ResponseEntity.ok("Image was received successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Seller not found!");
        }
    }

    @GetMapping("/getSellerImage/{id}")
    public ResponseEntity<ImageAndNamesDTO> getSellerImage(@PathVariable(value = "id") long id) throws IOException {
        Optional<List<SellerImageModel>> sellerImageModelOptional = sellerImageService.findSellerImages(id);
        if(!sellerImageModelOptional.isPresent()) {
            return null;
        }
        SellerImageModel sellerImageModel = sellerImageModelOptional.get().get(0);
        ImageAndNamesDTO imageAndNamesDTO = new ImageAndNamesDTO();
        imageAndNamesDTO.setProfileImage(manipulatingImage.decodeImage(sellerImageModel.getImage()));
        imageAndNamesDTO.setName(sellerImageModel.getSellerModel().getName());
        return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
    }
}
