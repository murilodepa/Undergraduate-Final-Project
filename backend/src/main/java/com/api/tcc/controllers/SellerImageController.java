/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.controllers;

import com.api.tcc.database.Models.SellerImageModel;
import com.api.tcc.database.Models.SellerModel;
import com.api.tcc.database.dtos.IdImageNameDTO;
import com.api.tcc.database.dtos.ImageAndNamesDTO;
import com.api.tcc.faceRecognition.Training;
import com.api.tcc.services.SellerService;
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

/**
 * @author Murilo de Paula Araujo
 */
@RestController
@RequestMapping("/seller")
public class SellerImageController {

    @Autowired
    private SellerImageService sellerImageService;
    @Autowired
    private SellerService sellerService;
    private ManipulatingImage manipulatingImage = new ManipulatingImage();

    @PostMapping("/sendImage")
    public ResponseEntity<?> sendImage(@RequestPart(value = "image", required = false) MultipartFile image) throws Exception {
        System.out.println("Images Receives of a Seller: " + image.getInputStream());
        final byte[] encodeImage;
        long sellerId = (sellerImageService.getSellerId());
        if (sellerId > 0) {
            final String fileName = manipulatingImage.fileName(false, (int) (sellerId));
            try {
                Files.write(manipulatingImage.fileNameAndPath(false, fileName), image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(ManipulatingImage.photosIndex == ManipulatingImage.QUANTITY_OF_PHOTOS) {
                System.out.println("Generating an updated classifier file!");
                new Training();
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
        List<SellerImageModel> sellerImageModelList = sellerImageService.findSellerImages(id);
        ImageAndNamesDTO imageAndNamesDTO = new ImageAndNamesDTO();
        if(sellerImageModelList.isEmpty()) {
            imageAndNamesDTO.setProfileImage(null);
            imageAndNamesDTO.setName(null);
            return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
        }
        SellerImageModel sellerImageModel = sellerImageModelList.get(0);
        imageAndNamesDTO.setProfileImage(manipulatingImage.decodeImage(sellerImageModel.getImage()));
        imageAndNamesDTO.setName(sellerImageModel.getSellerModel().getName());
        return ResponseEntity.status(HttpStatus.OK).body(imageAndNamesDTO);
    }

    @GetMapping("/getSellerImageNameList")
    public ResponseEntity<List<IdImageNameDTO>> getSellerIdImageNameList() throws IOException {
        List<SellerModel> sellerModelList = sellerService.findAll();
        if(!sellerModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(sellerImageService.findSellerIdImagesNamesList(sellerModelList));
        }
        return null;
    }
}
