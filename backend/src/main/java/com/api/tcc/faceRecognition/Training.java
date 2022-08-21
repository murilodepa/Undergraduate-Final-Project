/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.faceRecognition;

import com.api.tcc.websocket.SocketClient;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.IntBuffer;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;

/**
 * @author Murilo de Paula Araujo
 */
public class Training {

    public Training() throws IOException {
        System.out.println("Async task executed by thread: " + Thread.currentThread().getName());
        trainingImages();
        new SocketClient();
    }

    @Async("threadTrainingAndSendClient")
    public void trainingImages() {
        File clientDirectory = new File("src\\main\\resources\\photos\\clients");
        File sellerDirectory = new File("src\\main\\resources\\photos\\sellers");

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith("jpeg");
            }
        };

        File[] clientFiles = clientDirectory.listFiles(filenameFilter);
        File[] sellerFiles = sellerDirectory.listFiles(filenameFilter);
        assert clientFiles != null;
        assert sellerFiles != null;
        int quantityOfFiles = (clientFiles.length + sellerFiles.length);

        if (quantityOfFiles > 0) {
            MatVector photos = new MatVector(quantityOfFiles);
            Mat labels = new Mat(quantityOfFiles, 1, CV_32SC1);
            IntBuffer bufferLabels = labels.createBuffer();

            int count, clientFilesLength = clientFiles.length;
            for (count = 0; count < clientFilesLength; count++) {
                Mat photo = imread(clientFiles[count].getAbsolutePath(), IMREAD_GRAYSCALE);
                int labelClass = Integer.parseInt(clientFiles[count].getName().split("\\.")[1]);
                System.out.println("Training classifier with photo of a client with ID: " + labelClass);
                resize(photo, photo, new Size(160, 160));
                photos.put(count, photo);
                bufferLabels.put(count, labelClass);
            }

            for (File file: sellerFiles) {
                Mat photo = imread(file.getAbsolutePath(), IMREAD_GRAYSCALE);
                int labelClass = Integer.parseInt(file.getName().split("\\.")[1]);
                System.out.println("Training classifier with photo of seller with ID: " + labelClass);
                resize(photo, photo, new Size(160, 160));
                photos.put(count, photo);
                bufferLabels.put(count, labelClass);
                count++;
            }

            FaceRecognizer lbph = LBPHFaceRecognizer.create();
            lbph.train(photos, labels);
            lbph.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");

           /*FaceRecognizer eigenfaces = EigenFaceRecognizer.create();
            eigenfaces.train(photos, labels);
            eigenfaces.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");*/

            /*FaceRecognizer fisherFaces = FisherFaceRecognizer.create();
            fisherFaces.train(photos, labels);
            fisherFaces.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");*/
        }
    }
}
