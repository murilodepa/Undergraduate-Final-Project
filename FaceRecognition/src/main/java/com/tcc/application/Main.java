package com.tcc.application;

import com.tcc.consumeApi.BackendAPI;
import com.tcc.recognition.FaceRecognitionLBPH;
import com.tcc.websocket.Server;

/**
 * @author Murilo de Paula Araujo
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*BackendAPI backendAPI = BackendAPI.getInstance();
        backendAPI.recognizedClient(40);*/

      //  new ControlThreads().lockResourceOpenSave();
        //new Thread(serverThread).start();
        System.out.println("The system is trying to capture an image from a camera...");
        new Thread(faceRecognitionThread).start();
    }

    private static final Runnable faceRecognitionThread = () -> {
        try {
            new FaceRecognitionLBPH().faceDetection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    private static final Runnable serverThread = () -> {
        try {
            new Server();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}