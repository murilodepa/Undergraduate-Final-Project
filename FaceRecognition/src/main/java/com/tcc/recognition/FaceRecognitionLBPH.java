package com.tcc.recognition;

import com.tcc.consumeApi.BackendAPI;
import com.tcc.utils.ControlThreads;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Point;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;
import org.bytedeco.javacv.OpenCVFrameGrabber;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.String.valueOf;
import static javax.swing.text.StyleConstants.Size;
import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;

import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class FaceRecognitionLBPH {

    private static final int RECOGNITION_TRUST = 800;
    private static final int MINIMUM_TRUST = 1000;
    private static final int MINIMUM_CLIENTS_TO_RECOGNIZE_AGAIN = 10;
//    public static FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
    //public static FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
    private List<Integer> recognitionClients = new ArrayList<>();

    public void faceDetection() {
        try {
            OpenCVFrameConverter.ToMat converterMat = new OpenCVFrameConverter.ToMat();
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
            camera.start();

            BackendAPI backendAPI = BackendAPI.getInstance();
            String info = null;
            String[] names = {"Murilo", "Maria da Silva", "Marcos Paqueta"};


            opencv_objdetect.CascadeClassifier cascadeClassifier = new opencv_objdetect.CascadeClassifier("src\\main\\resources\\classifiers\\haarcascade_frontalface_alt.xml");
            FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
            faceRecognizer.load("src\\main\\resources\\classifiers\\LBPHClassifier.yml");
            faceRecognizer.setThreshold(800);

            CanvasFrame canvasFrame = new CanvasFrame("Recognition", CanvasFrame.getDefaultGamma() / camera.getGamma());
            Frame capturedFrame = null;
            Mat colorImage = new Mat();
            int index = 0, i = 0;
            long quantityOfDetectedFaces = 0;

            while ((capturedFrame = camera.grab()) != null) {
                colorImage = converterMat.convert(capturedFrame);
                Mat grayscaleImage = new Mat();
                cvtColor(colorImage, grayscaleImage, COLOR_BGRA2GRAY);
                RectVector detectedFaces = new RectVector();

                cascadeClassifier.detectMultiScale(grayscaleImage, detectedFaces, 1.1, 2, 0, new Size(100, 100), new Size(500, 500));

                //System.out.println("detectedFaces.size() - " + detectedFaces.size());

                //if (quantityOfDetectedFaces != detectedFaces.size()) {
                    for (i = 0; i < detectedFaces.size(); i++) {
                        Rect faceData = detectedFaces.get(i);
                        rectangle(colorImage, faceData, new Scalar(0, 255, 0, 0));
                        Mat capturedFace = new Mat(grayscaleImage, faceData);
                        resize(capturedFace, capturedFace, new Size(160, 160));

                        IntPointer label = new IntPointer(1);
                        DoublePointer trust = new DoublePointer(1);

                        //System.out.println("THREAD " + Thread.currentThread() + " - I'm waiting for the classifier file update!");
                        ControlThreads.SEMAPHORE.acquire();
                        //System.out.println("THREAD " + Thread.currentThread() + "I get the new classifier file!");
                        faceRecognizer.predict(capturedFace, label, trust);
                        ControlThreads.SEMAPHORE.release();

                        int predict = label.get(0);
                        int trustInt = (int) trust.get(0);
                        System.out.println(" Trust: " + trustInt);

                        if(predict == -1 || trustInt >= MINIMUM_TRUST) {
                            System.out.println("Face detected, however the user is unknown to the system!");
                            info = "Unknown - " + trustInt;
                            Mat faceDetected = new Mat(colorImage, faceData);
                            resize(faceDetected, faceDetected, new Size(160, 160));
                            //imwrite("src\\main\\resources\\Unknown_Client\\UnknownClient_" + index + ".jpg", faceDetected);
                            index++;
                            System.out.println("Photo - " + index + "- Captured\n");
                        } else {
                            System.out.println("Recognized user with an acceptable confidence value!");
                            //stream().filter(clientId -> userId == clientId.getId()).findAny().orElse(null);
                            info = predict + " - " + trustInt;
                            if (recognitionClients != null && !recognitionClients.contains(predict)) {
                                if (recognitionClients.size() < MINIMUM_CLIENTS_TO_RECOGNIZE_AGAIN) {
                                    recognitionClients.add(predict);
                                } else {
                                    recognitionClients.remove(0);
                                    recognitionClients.add(predict);
                                }
                                System.out.println("Sending to backend the recognized user with id: " + predict);
                                //backend.recognizedClient(predict);
                            }
                        }

                        int x = max(faceData.tl().x() - 10, 0);
                        int y = max(faceData.tl().y() - 10, 0);
                        putText(colorImage, info, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                    }
                    quantityOfDetectedFaces = i;
               // } /* else {
                  //  Thread.sleep(2000);
                //}*/


                if (canvasFrame.isVisible()) {
                    canvasFrame.showImage(capturedFrame);
                } else {
                    break;
                }
            }
            canvasFrame.dispose();
            camera.stop();

            System.exit(0);
        } catch (Exception e) {
            System.out.println("ThreadFaceRecognition - Error" + e);
        }
    }
}

