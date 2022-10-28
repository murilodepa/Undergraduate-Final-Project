/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Murilo de Paula Araujo
 */
public class ManipulatingImage {

    public static final int QUANTITY_OF_PHOTOS = 6;

    public static int photosIndex = 0;

    private static final String clientPhotosPath = "src\\main\\resources\\photos\\clients";
    private static final String sellerPhotosPath = "src\\main\\resources\\photos\\sellers";

    public double getQuantityOfFiles(final boolean isClient) {
        int count = 0;
        File directory;

        if (isClient) {
            directory = new File(clientPhotosPath);
        } else {
            directory = new File(sellerPhotosPath);
        }
        File[] list = directory.listFiles();

        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Path fileNameAndPath(final boolean isClient, final String fileName) {
        if (isClient) {
            return Paths.get(clientPhotosPath + "\\" + fileName);
        } else {
            return Paths.get(sellerPhotosPath + "\\" + fileName);
        }
    }

    public String fileName(final boolean isClient, int userId) {
        String fileName;
        double quantityOfFiles = getQuantityOfFiles(isClient) + 1;

        photosIndex = (int) (quantityOfFiles % QUANTITY_OF_PHOTOS);
        if (photosIndex == 0) {
            photosIndex = QUANTITY_OF_PHOTOS;
        }
        fileName = ("person." + userId + "." + photosIndex + ".jpg");
        return fileName;
    }

    public byte[] encodeImage(final boolean isClient, final String fileName) throws IOException {
        byte[] fileContent;
        if (isClient) {
            fileContent = FileUtils.readFileToByteArray(new File(clientPhotosPath + "\\" + fileName));
        } else {
            fileContent = FileUtils.readFileToByteArray(new File(sellerPhotosPath + "\\" + fileName));
        }
        return (fileContent);
    }

    public String decodeImage(byte[] inputEncodedImage) throws IOException {
        //String imagePath = "src\\main\\resources\\photos\\profileImage\\imageLast.jpg";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputEncodedImage);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        System.out.println("Image Created!");
        String data = DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray());
        return "data:image/jpeg;base64," + data;
    }

    private File[] getUnknownClientPhotosList() {
        File unknownClientPhotos = new File("src\\main\\resources\\photos\\Unknown_Client");

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith("jpeg");
            }
        };

        return unknownClientPhotos.listFiles(filenameFilter);
    }

    public int getLastIndexUnknownClientPhotos() {
        int index = 1;
        File[] unknownClientPhotosList = getUnknownClientPhotosList();

        if (unknownClientPhotosList != null) {
            index = (unknownClientPhotosList.length) + 1;
        }
        return index;
    }

    public String getFileNameUnknownClient() {
        File[] unknownClientPhotosList = getUnknownClientPhotosList();

        if (unknownClientPhotosList != null && unknownClientPhotosList.length > 0) {
            for (File file : unknownClientPhotosList) {
                return file.getName();
            }
        }
        return null;
    }
}
