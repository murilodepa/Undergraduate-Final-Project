package com.api.tcc.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ManipulatingImage {

    String clientPhotosPath = "src\\main\\resources\\photos\\clients";
    String sellerPhotosPath = "src\\main\\resources\\photos\\sellers";

    public double getQuantityOfFiles(final boolean isClient) {
        int count = 0;
        File directory;

        if(isClient) {
            directory = new File(clientPhotosPath);
        } else {
            directory = new File(sellerPhotosPath);
        }
        File[] list = directory.listFiles();

        if(list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    count++;
                }
            }
        }
        return (double) count;
    }

    public Path fileNameAndPath(final boolean isClient, final String fileName) {
        if (isClient) {
           return Paths.get(clientPhotosPath + "\\" + fileName);
        } else {
            return Paths.get(sellerPhotosPath + "\\" + fileName);
        }
    }

    public String fileName(final boolean isClient, int nextId) {
        int index = 0;
        double quantityOfFiles = getQuantityOfFiles(isClient) + 1;

        index = (int) (quantityOfFiles % 25);
        if(index == 0) {
            index = 25;
        }
        if(quantityOfFiles < 225) {
            return ("person.0" + nextId + "." + index + ".jpg");
        } else {
            return ("person." + ((int)(Math.ceil(quantityOfFiles / 25))) + "." + index + ".jpg");
        }
    }

    public byte[] encodeImage(final boolean isClient, final String fileName) throws IOException {
        byte[] fileContent = null;
        if(isClient) {
           fileContent = FileUtils.readFileToByteArray(new File(clientPhotosPath + "\\" + fileName));
        } else {
           fileContent = FileUtils.readFileToByteArray(new File(sellerPhotosPath + "\\" + fileName));
        }
        return (fileContent);
    }

    public String decodeImage(byte[] inputEncodedImage) throws IOException {
        String imagePath = "src\\main\\resources\\photos\\profileImage\\imageLast.jpg";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputEncodedImage);
        BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        System.out.println("Image Created!");
        String data = DatatypeConverter.printBase64Binary(byteArrayOutputStream.toByteArray());
        return "data:image/jpeg;base64," + data;
    }
}
