package com.tcc.application;

import com.tcc.consumeApi.BackendAPI;
import com.tcc.recognition.FaceRecognitionLBPH;
import com.tcc.utils.ControlThreads;
import com.tcc.websocket.Server;

import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) throws InterruptedException {

       // BackendAPI backendAPI = BackendAPI.getInstance();
      //  backendAPI.recognizedClient(50);
     /*   String[] idAndNames = backendAPI.requestNamesAndIndex();
        System.out.println(Arrays.toString(idAndNames));*/

        //new Thread(serverThread).start();
        new Thread(faceRecognitionThread).start();
       // new ControlThreads().lockResourceOpenSave();

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