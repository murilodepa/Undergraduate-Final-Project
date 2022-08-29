package com.tcc.utils;

import com.tcc.recognition.FaceRecognitionLBPH;
import org.bytedeco.javacpp.opencv_face;

import java.io.*;
import java.util.concurrent.Semaphore;

import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;

public class ControlThreads {

    public static final Semaphore SEMAPHORE = new Semaphore(1);
    private static final opencv_face.FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
    private final FaceRecognitionLBPH faceRecognitionLBPH = new FaceRecognitionLBPH();

    public ControlThreads() throws InterruptedException {
        System.out.println("ENTROU NO CONSTRUTOR");
        lockResourceOpenSave();
    }

    public void lockResourceOpenSave() throws InterruptedException {
        System.out.println("Lock current thread to update file!");
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
            faceRecognizer.load(classifierPath);
            FaceRecognitionLBPH.faceRecognizer = faceRecognizer;
            System.out.println("THREAD " + Thread.currentThread() + " - Update successfully the classifier file!");
        }

        SEMAPHORE.release();
    }
}