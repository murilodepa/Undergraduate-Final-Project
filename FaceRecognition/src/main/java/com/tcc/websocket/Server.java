package com.tcc.websocket;


import com.tcc.utils.ControlThreads;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private Socket socket;

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
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
                String caminhoCompleto = "src\\main\\resources\\classifiers\\LBPHClassifier2.yml";
                OutputStream output = new FileOutputStream((caminhoCompleto));
                long size = clientData.readLong();
                byte[] buffer = new byte[1024];
                while (size > 0
                        && (bytesRead = clientData.read(buffer, 0,
                        (int) Math.min(buffer.length, size))) != -1) {
                    output.write(buffer, 0, bytesRead);
                    size -= bytesRead;
                }
                output.close();
                System.out.println("Arquivo " + fileName
                        + " recebido pelo cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}