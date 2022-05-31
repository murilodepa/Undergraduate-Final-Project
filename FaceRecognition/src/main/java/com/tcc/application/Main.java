package com.tcc.application;

import com.tcc.recognition.FaceRecognitionLBPH;
import com.tcc.utils.ControlThreads;
import com.tcc.websocket.Server;

import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) {
	// write your code here
  /*      System.out.println("aaaaaaaa");

        BackendAPI backendAPI = BackendAPI.getInstance();
   //     backendAPI.recognizedClient("Murilo de Paula Araujo");
        String[] idAndNames = backendAPI.requestNamesAndIndex();
        System.out.println(Arrays.toString(idAndNames));*/

       // new Thread(serverThread).start();
       // new ControlThreads().lockResourceOpenSave(false);
        new Thread(faceRecognitionThread).start();

        //new Server();

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
