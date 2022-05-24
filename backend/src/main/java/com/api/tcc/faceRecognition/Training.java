package com.api.tcc.faceRecognition;

import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

public class Training {
    public static void trainingImages(boolean isClient) {
        File directory;

        if (isClient) {
            directory = new File("src\\main\\resources\\photos\\clients");
        } else {
            directory = new File("src\\main\\resources\\photos\\sellers");
        }

        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith("jpeg");
            }
        };
        File[] files = directory.listFiles(filenameFilter);
        assert files != null;
        MatVector photos = new MatVector(files.length);
        Mat labels = new Mat(files.length, 1, CV_32SC1);
        IntBuffer labelsBuffer = labels.createBuffer();
        int count = 0;

        for (File image: files) {
            Mat photo = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            int labelClass = Integer.parseInt(image.getName().split("\\.")[1]);
            System.out.println(labelClass);
            resize(photo, photo, new Size(160,160));
            photos.put(count, photo);
            labelsBuffer.put(count, labelClass);
            count++;
        }
        FaceRecognizer LBPH = createLBPHFaceRecognizer(12,10,15,15,0);
        LBPH.train(photos, labels);
        LBPH.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");
    }
}
