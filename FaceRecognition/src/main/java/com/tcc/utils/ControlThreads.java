package com.tcc.utils;

import com.tcc.recognition.FaceRecognitionLBPH;
import org.bytedeco.javacpp.opencv_face;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;

public class ControlThreads {

    private Lock lock = new ReentrantLock();
    private FaceRecognitionLBPH faceRecognitionLBPH = new FaceRecognitionLBPH();
    private opencv_face.FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public ControlThreads() {
    }

    public void lockResourceOpenSave() {
        String classifierPath = "src\\main\\resources\\classifiers\\LBPHClassifier.yml";
        String newClassifierPath = "src\\main\\resources\\classifiers\\LBPHClassifier2.yml";
        File deleteClassifier = new File(classifierPath);
        if (deleteClassifier.exists()) {
            deleteClassifier.delete();
        }

        File newClassifier = new File(newClassifierPath);
        if (newClassifier.exists()) {
            newClassifier.renameTo(new File(classifierPath));
        }
        faceRecognizer.load(classifierPath);
        faceRecognitionLBPH.setFaceRecognizer(faceRecognizer);
    }
}
