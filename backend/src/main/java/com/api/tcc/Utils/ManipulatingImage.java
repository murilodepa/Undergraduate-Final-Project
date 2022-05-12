package com.api.tcc.Utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public Path fileNameAndPath(final boolean isClient) {
        if (isClient) {
           return Paths.get((clientPhotosPath + "\\" + fileName(isClient)));
        } else {
            return Paths.get((sellerPhotosPath + "\\" + fileName(isClient)));
        }
    }

    public String fileName(final boolean isClient) {
        int index = 0;
        double quantityOfFiles = getQuantityOfFiles(isClient) + 1;

        index = (int) (quantityOfFiles % 25);
        if(index == 0) {
            index = 25;
        }
        if(quantityOfFiles < 225) {
            return ("person.0" + ((int)(Math.ceil(quantityOfFiles / 25))) + "." + index + ".jpg");
        } else {
            return ("person." + ((int)(Math.ceil(quantityOfFiles / 25))) + "." + index + ".jpg");
        }
    }


    public String encodeImage(final boolean isClient) throws IOException {
        // Encode da Image
        byte[] fileContent = null;
        if(isClient) {
           fileContent = FileUtils.readFileToByteArray(new File(clientPhotosPath + "\\" + fileName(isClient)));
        } else {
           fileContent = FileUtils.readFileToByteArray(new File(sellerPhotosPath + "\\" + fileName(isClient)));
        }
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return (Base64.getEncoder().encodeToString(fileContent));
    }

    public void decodeImage(String encodedString, boolean isClient) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
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
