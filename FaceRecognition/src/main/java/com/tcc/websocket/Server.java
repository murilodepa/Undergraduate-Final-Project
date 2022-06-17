package com.tcc.websocket;

import com.tcc.utils.ControlThreads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Murilo de Paula Araujo
 */
public class Server {

    private Socket socket;
    private static final int LOCAL_PORT = 5555;

    public Server() {
        initializationServerSocket();
    }

    private void initializationServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(LOCAL_PORT);
            System.out.println("Server On! ");
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ListenerSocket(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ListenerSocket implements Runnable {
        public ListenerSocket(Socket socket) throws IOException {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {

            try {
                int bytesRead;
                DataInputStream clientData = new DataInputStream(
                        socket.getInputStream());

                String fileName = clientData.readUTF();
                String filePath = "src\\main\\resources\\classifiers\\LBPHClassifier2.yml";
                OutputStream outputStream = new FileOutputStream((filePath));
                long size = clientData.readLong();
                byte[] buffer = new byte[1024];
                while (size > 0
                        && (bytesRead = clientData.read(buffer, 0,
                        (int) Math.min(buffer.length, size))) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }
                outputStream.close();
                System.out.println("File " + fileName
                        + " received for client!");
                new ControlThreads();
                System.out.println("PASSOU!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}