package com.api.tcc.Utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

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

        for (File file : list) {
            if (file.isFile()) {
                count++;
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
        // Encode da Image
        byte[] fileContent = null;
        if(isClient) {
           fileContent = FileUtils.readFileToByteArray(new File(clientPhotosPath + "\\" + fileName));
        } else {
           fileContent = FileUtils.readFileToByteArray(new File(sellerPhotosPath + "\\" + fileName));
        }
        String encodedImage = Base64.getEncoder().encodeToString(fileContent);

        byte[] outputEncodedImage = encodedImage.getBytes(StandardCharsets.UTF_8);

        return (outputEncodedImage);
    }

    public void decodeImage(byte[] inputEncodedImage, boolean isClient) throws IOException {

        String encodedImage = new String(inputEncodedImage, StandardCharsets.UTF_8);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
        InputStream is = new ByteArrayInputStream(decodedBytes);
        BufferedImage newBi = ImageIO.read(is);
        Path destination;

        if(isClient) {
            destination = Paths.get(clientPhotosPath + "\\" + "murilo.jpg");
        } else {
            destination = Paths.get(sellerPhotosPath + "\\" + "murilo.jpg");
        }
        ImageIO.write(newBi, "png", destination.toFile());
    }
}
