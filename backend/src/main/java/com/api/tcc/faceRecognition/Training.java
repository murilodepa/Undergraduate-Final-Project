package com.api.tcc.faceRecognition;

import com.api.tcc.websocket.SocketClient;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.springframework.scheduling.annotation.Async;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_face.createEigenFaceRecognizer;
import static org.bytedeco.javacpp.opencv_face.createLBPHFaceRecognizer;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

public class Training {

    public Training() throws IOException {
        System.out.println("Async task executed by thread: " + Thread.currentThread().getName());
        trainingImages();
      //  new SocketClient();
    }

    @Async("threadTrainingAndSendClient")
    public void trainingImages() {
        File clientDirectory = new File("src\\main\\resources\\photos\\clients");
        File sellerDirectory = new File("src\\main\\resources\\photos\\sellers");

        FilenameFilter filenameFilter = (dir, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith("jpeg");
        File[] clientFiles = clientDirectory.listFiles(filenameFilter);
        File[] sellerFiles = sellerDirectory.listFiles(filenameFilter);
        assert clientFiles != null;
        assert sellerFiles != null;
        int quantityOfFiles = (clientFiles.length + sellerFiles.length);

        if (quantityOfFiles > 0) {
            MatVector photos = new MatVector(quantityOfFiles);
            Mat labels = new Mat(quantityOfFiles, 1, CV_32SC1);

            IntBuffer labelsBuffer = labels.createBuffer();

            int count, clientFilesLength = clientFiles.length;
            for (count = 0; count < clientFilesLength; count++) {
                Mat photo = imread(clientFiles[count].getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
                int labelClass = Integer.parseInt(clientFiles[count].getName().split("\\.")[1]);
                System.out.println(labelClass);
                resize(photo, photo, new Size(160, 160));
                photos.put(count, photo);
                labelsBuffer.put(count, labelClass);
            }

            for (File file: sellerFiles) {
                Mat photo = imread(file.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
                int labelClass = Integer.parseInt(file.getName().split("\\.")[1]);
                System.out.println(labelClass);
                resize(photo, photo, new Size(160, 160));
                photos.put(count, photo);
                labelsBuffer.put(count, labelClass);
                count++;
            }

            FaceRecognizer LBPH = createLBPHFaceRecognizer(12, 10, 15, 15,0);
            LBPH.train(photos, labels);
            LBPH.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");

      /*      FaceRecognizer eigenfaces = createEigenFaceRecognizer(50, 0);
           eigenfaces.train(photos, labels);
            eigenfaces.save("src\\main\\resources\\classifiers\\LBPHClassifier.yml");*/
        }
    }
}
