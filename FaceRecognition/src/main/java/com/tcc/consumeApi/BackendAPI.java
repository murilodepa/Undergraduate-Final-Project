package com.tcc.consumeApi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackendAPI {
    private static final String URLBase = "http://localhost:8080";

    private static BackendAPI instance;

    private CloseableHttpClient clientHTTP;

    private BackendAPI() {
        this.clientHTTP = HttpClients.createDefault();
    }

    public static BackendAPI getInstance() {
        if (instance == null) {
            instance = new BackendAPI();
        }
        return instance;
    }

    public void recognizedClient(long userId) {
        try {
            HttpPost httpPost = new HttpPost(BackendAPI.URLBase + "/recognizedUser?userId=" + userId);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    int status = httpResponse.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = httpResponse.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = this.clientHTTP.execute(httpPost, responseHandler);
            System.out.println("-----------------------------------------------------");
            System.out.println(responseBody);
        } catch (IOException e) {
            Logger.getLogger(BackendAPI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void postUnknownUserPhoto(int index) {
        try {
            HttpPost httpPost = new HttpPost(BackendAPI.URLBase + "/recognizedUser?unknownUserPhoto=" + decodeImage(index));
            ResponseHandler<String> responseHandler = httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = httpResponse.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = this.clientHTTP.execute(httpPost, responseHandler);
            System.out.println("-----------------------------------------------------");
            System.out.println(responseBody);
        } catch (IOException e) {
            Logger.getLogger(BackendAPI.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ByteArrayOutputStream decodeImage(int index) throws IOException {
        String imagePath = "src\\main\\resources\\Unknown_Client\\UnknownClient_" + index + ".jpg";
        File fileImage = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(fileImage);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        System.out.println("Image created");
        return byteArrayOutputStream;
    }
}
