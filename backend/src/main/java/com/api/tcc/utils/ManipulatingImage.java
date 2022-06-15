package com.api.tcc.utils;

import com.api.tcc.faceRecognition.Training;
import com.api.tcc.websocket.SocketClient;
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

    public static final int QUANTITY_OF_PHOTOS = 6;
    String clientPhotosPath = "src\\main\\resources\\photos\\clients";
    String sellerPhotosPath = "src\\main\\resources\\photos\\sellers";

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

    public String fileName(final boolean isClient, int userId) throws IOException {
        int index;
        String fileName = null;
        double quantityOfFiles = getQuantityOfFiles(isClient) + 1;

        index = (int) (quantityOfFiles % QUANTITY_OF_PHOTOS);
        if (index == 0) {
            index = QUANTITY_OF_PHOTOS;
        }
        if (userId < 10) {
            fileName = ("person.0" + userId + "." + index + ".jpg");
        } else {
            fileName = ("person." + userId + "." + index + ".jpg");
        }

        return fileName;
    }

    public byte[] encodeImage(final boolean isClient, final String fileName) throws IOException {
        byte[] fileContent = null;
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
}
