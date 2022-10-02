/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.tcc.utils;

import com.tcc.recognition.FaceRecognitionLBPH;
import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;

import java.io.*;
import java.util.concurrent.Semaphore;

/**
 * @author Murilo de Paula Araujo
 */
public class ControlThreads {

    public static final Semaphore SEMAPHORE = new Semaphore(1);
    private static FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();

    public ControlThreads() throws InterruptedException {
        lockResourceOpenSave();
    }

    public void lockResourceOpenSave() throws InterruptedException {
        System.out.println("Acquire or Lock current thread to update file!");
        SEMAPHORE.acquire();

        //System.out.println("THREAD " + Thread.currentThread() + " - will update the classifier file!");

        //Thread.sleep(5000);
        String classifierPath = "src\\main\\resources\\classifiers\\LBPHClassifier.yml";
        String newClassifierPath = "src\\main\\resources\\classifiers\\LBPHClassifier2.yml";
        File deleteClassifier = new File(classifierPath);

        File newClassifier = new File(newClassifierPath);
        if (newClassifier.exists()) {
            if (deleteClassifier.exists()) {
                deleteClassifier.delete();
            }
            newClassifier.renameTo(new File(classifierPath));
            faceRecognizer.read(classifierPath);
            FaceRecognitionLBPH.faceRecognizer = faceRecognizer;
            System.out.println("THREAD " + Thread.currentThread() + " - Update successfully the classifier file!");
        }

        SEMAPHORE.release();
        System.out.println("Release or UnLock current thread to update file!");
    }
}