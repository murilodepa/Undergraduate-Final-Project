package com.api.tcc.websocket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    private static final int LOCAL_PORT = 5555;

    public SocketClient() throws IOException {
        sendFile();
    }

    private void sendFile() throws IOException {
        Socket socket = new Socket("localhost", LOCAL_PORT);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        try {
            String filePath = "src\\main\\resources\\classifiers\\LBPHClassifier.yml";
            File classifier = new File(filePath);
            byte[] classifierByte = new byte[(int) classifier.length()];
            FileInputStream fis = new FileInputStream(classifier);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream dis = new DataInputStream(bis);

            dis.readFully(classifierByte, 0, classifierByte.length);
            OutputStream os = socket.getOutputStream();

            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(classifier.getName());
            dos.writeLong(classifierByte.length);
            dos.write(classifierByte, 0, classifierByte.length);
            dos.flush();
            System.out.println(" Sending updated LBPHClassifier classifier File to the Server!");
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist!");
        }
    }
}