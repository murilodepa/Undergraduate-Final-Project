package com.tcc.recognition;

import com.tcc.utils.ControlThreads;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter.ToMat;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class FaceRecognitionLBPH {

    private opencv_face.FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

    public opencv_face.FaceRecognizer getFaceRecognizer() {
        return this.faceRecognizer;
    }

    public void setFaceRecognizer(opencv_face.FaceRecognizer faceRecognizer) {
        this.faceRecognizer = faceRecognizer;
    }

    public void faceDetection() {
        try {
            ToMat converterMat = new ToMat();
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
            ControlThreads controlThreads = new ControlThreads();

            String[] people = {"", "Murilo", "Solange"};
            camera.start();

            opencv_objdetect.CascadeClassifier cascadeClassifier = new opencv_objdetect.CascadeClassifier("src\\main\\resources\\classifiers\\haarcascade_frontalface_alt.xml");
            //opencv_face.FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
            faceRecognizer.load("src\\main\\resources\\classifiers\\LBPHClassifier.yml");

            CanvasFrame canvasFrame = new CanvasFrame("Recognition", CanvasFrame.getDefaultGamma() / camera.getGamma());
            Frame capturedFrame = null;
            opencv_core.Mat colorImage = new opencv_core.Mat();

            while ((capturedFrame = camera.grab()) != null) {
                colorImage = converterMat.convert(capturedFrame);
                opencv_core.Mat grayscaleImage = new opencv_core.Mat();
                cvtColor(colorImage, grayscaleImage, COLOR_BGRA2GRAY);
                opencv_core.RectVector detectedFaces = new opencv_core.RectVector();
                cascadeClassifier.detectMultiScale(grayscaleImage, detectedFaces, 1.1, 1, 0, new opencv_core.Size(150, 150), new opencv_core.Size(500, 500));

                for(int i = 0; i < detectedFaces.size(); i++) {
                    opencv_core.Rect faceData = detectedFaces.get(i);
                    rectangle(colorImage, faceData, new opencv_core.Scalar(0, 0, 255, 0));
                    opencv_core.Mat capturedFace = new opencv_core.Mat(grayscaleImage, faceData);
                    resize(capturedFace, capturedFace, new opencv_core.Size(160, 160));

                    IntPointer label = new IntPointer(1);
                    DoublePointer trust = new DoublePointer(1);
                    getFaceRecognizer().predict(capturedFace, label, trust);
                    int predict = label.get(0);

                    String name;
                    if(predict == -1) {
                        name = "Unknown";
                    } else {
                        name = people[predict] + " - " + trust.get(0);
                    }

                    int x = Math.max(faceData.tl().x() - 10, 0);
                    int y = Math.max(faceData.tl().y() - 10, 0);
                    putText(colorImage, name, new opencv_core.Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new opencv_core.Scalar(0, 255, 0, 0));
                }

                if (canvasFrame.isVisible()) {
                    canvasFrame.showImage(capturedFrame);
                } else {
                    break;
                }
            }
            canvasFrame.dispose();
            camera.stop();
        } catch (Exception e) {
            System.out.println("threadFaceRecognition - Error" + e);
        }
    }
}



