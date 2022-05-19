package com.api.tcc.faceRecognition;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import static org.bytedeco.javacpp.opencv_core.FONT_HERSHEY_PLAIN;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgproc.*;

public class Recognition {

    public static void faceDetection() {
        try {
            OpenCVFrameConverter.ToMat converterMat = new OpenCVFrameConverter.ToMat();
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
            String[] people = {"", "Murilo", "Solange"};
            camera.start();

            CascadeClassifier cascadeClassifier = new CascadeClassifier("src\\main\\resources\\classifiers\\haarcascade_frontalface_alt.xml");
            FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
            faceRecognizer.load("src\\main\\resources\\classifiers\\LBPHClassifier.yml");

            CanvasFrame canvasFrame = new CanvasFrame("Recognition", CanvasFrame.getDefaultGamma() / camera.getGamma());
            Frame capturedFrame = null;
            Mat colorImage = new Mat();

            while ((capturedFrame = camera.grab()) != null) {
                colorImage = converterMat.convert(capturedFrame);
                Mat grayscaleImage = new Mat();
                cvtColor(colorImage, grayscaleImage, COLOR_BGRA2GRAY);
                RectVector detectedFaces = new RectVector();
                cascadeClassifier.detectMultiScale(grayscaleImage, detectedFaces, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                for(int i = 0; i < detectedFaces.size(); i++) {
                    Rect faceData = detectedFaces.get(i);
                    rectangle(colorImage, faceData, new Scalar(0, 0, 255, 0));
                    Mat capturedFace = new Mat(grayscaleImage, faceData);
                    resize(capturedFace, capturedFace, new Size(160, 160));

                    IntPointer label = new IntPointer(1);
                    DoublePointer trust = new DoublePointer(1);
                    faceRecognizer.predict(capturedFace, label, trust);
                    int predict = label.get(0);

                    String name;
                    if(predict == -1) {
                        name = "Unknown";
                    } else {
                        name = people[predict] + " - " + trust.get(0);
                    }

                    int x = Math.max(faceData.tl().x() - 10, 0);
                    int y = Math.max(faceData.tl().y() - 10, 0);
                    putText(colorImage, name, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                }

                if (canvasFrame.isVisible()) {
                    canvasFrame.showImage(capturedFrame);
                }
            }
            canvasFrame.dispose();
            camera.stop();
        } catch (Exception e) {
            System.out.println("threadFaceRecognition - Error" + e);
        }
    }
}
