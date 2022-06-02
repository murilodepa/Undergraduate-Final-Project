package com.api.tcc.websocket;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    private Socket socket;
    private ObjectOutputStream outputStream;

    public SocketClient() throws IOException {
        this.socket = new Socket("localhost", 5555);
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());

        try {
            String filePath = "src\\main\\resources\\classifiers\\LBPHClassifier.yml";
            File LBPHClassifier = new File(filePath);
            byte[] LBPHClassifierByte = new byte[(int) LBPHClassifier.length()];

            FileInputStream inputStream = new FileInputStream(LBPHClassifier);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

            dataInputStream.readFully(LBPHClassifierByte,0, LBPHClassifierByte.length);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(LBPHClassifier.getName());
            dataOutputStream.writeLong(LBPHClassifierByte.length);
            dataOutputStream.write(LBPHClassifierByte, 0, LBPHClassifierByte.length);
            dataOutputStream.flush();
            System.out.println("LBPHClassifier file has been sent to server!");
        } catch (FileNotFoundException e) {
            System.err.println("This file not exist!");
        }
    }
}
