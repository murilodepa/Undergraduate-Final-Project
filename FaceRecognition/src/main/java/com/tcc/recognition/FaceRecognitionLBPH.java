package com.tcc.recognition;

import com.tcc.consumeApi.BackendAPI;
import com.tcc.utils.ControlThreads;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*;

import org.bytedeco.opencv.opencv_face.FaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;

import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.FONT_HERSHEY_PLAIN;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.putText;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murilo de Paula Araujo
 */
public class FaceRecognitionLBPH {
    private static final int MINIMUM_TRUST = 95;
    private static final int MINIMUM_CLIENTS_TO_RECOGNIZE_AGAIN = 10;

    public static FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();
    //public static FaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
    //public static FaceRecognizer faceRecognizer = FisherFaceRecognizer.create();

    private List<Integer> recognitionClients = new ArrayList<>();

    public void faceDetection() {
        try {

            OpenCVFrameConverter.ToMat converterMat = new OpenCVFrameConverter.ToMat();
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
            camera.start();

            BackendAPI backendAPI = BackendAPI.getInstance();
            String info = null;
            String[] names = {"", "Tom Cruise", "Murilo Araujo", ""};

            CascadeClassifier cascadeClassifier = new CascadeClassifier("src\\main\\resources\\classifiers\\haarcascade_frontalface_alt.xml");
            faceRecognizer.read("src\\main\\resources\\classifiers\\LBPHClassifier.yml");
            faceRecognizer.setThreshold(MINIMUM_TRUST);

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

              //  if (quantityOfDetectedFaces != detectedFaces.size()) {
                    for (i = 0; i < detectedFaces.size(); i++) {
                        Rect faceData = detectedFaces.get(i);
                        rectangle(colorImage, faceData, new Scalar(0, 255, 0, 0));
                        Mat capturedFace = new Mat(grayscaleImage, faceData);

                        IntPointer label = new IntPointer(1);
                        DoublePointer trust = new DoublePointer(1);

                        if((capturedFace.size(0) == 160) || (capturedFace.size(1) == 160)) {
                            continue;
                        }
                        resize(capturedFace, capturedFace, new Size(160, 160));

                        //System.out.println("THREAD " + Thread.currentThread() + " - I'm waiting for the classifier file update!");
                        ControlThreads.SEMAPHORE.acquire();
                        //System.out.println("THREAD " + Thread.currentThread() + "I get the new classifier file!");
                        faceRecognizer.predict(capturedFace, label, trust);
                        ControlThreads.SEMAPHORE.release();

                        int predict = label.get(0);
                        int trustInt = (int) trust.get(0);

                        if(predict == -1 || trustInt >= MINIMUM_TRUST) {
                            info = "Unknown";
                            //Mat faceDetected = new Mat(colorImage, faceData);
                            //resize(faceDetected, faceDetected, new Size(160, 160));
                            //imwrite("src\\main\\resources\\Unknown_Client\\UnknownClient_" + index + ".jpg", faceDetected);
                            //index++;
                            //System.out.println("Photo - " + index + "- Captured\n");
                        } else {
                            System.out.println("Recognized user with an acceptable confidence value!");
                            //stream().filter(clientId -> userId == clientId.getId()).findAny().orElse(null);
                            info = names[predict];
                         /*   if (recognitionClients != null && !recognitionClients.contains(predict)) {
                                if (recognitionClients.size() < MINIMUM_CLIENTS_TO_RECOGNIZE_AGAIN) {
                                    recognitionClients.add(predict);
                                } else {
                                    recognitionClients.remove(0);
                                    recognitionClients.add(predict);
                                }
                                System.out.println("Sending to backend the recognized user with id: " + predict);
                                backendAPI.recognizedClient(predict);
                            }*/
                        }

                        int x = Math.max(faceData.tl().x() - 10, 0);
                        int y = Math.max(faceData.tl().y() - 10, 0);
                        putText(colorImage, info, new Point(x, y), FONT_HERSHEY_PLAIN, 1.6, new Scalar(0, 255, 0, 0));
                    }
                    quantityOfDetectedFaces = i;
              //  }

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

