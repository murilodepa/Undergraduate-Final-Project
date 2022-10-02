/*
 * Copyright (c) 2022 created by student Murilo de Paula Araujo from the Computer Engineering
 * course at Pontifical Catholic University of Campinas (PUC-Campinas).
 * All rights reserved.
 */
package com.api.tcc.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * @author Murilo de Paula Araujo
 */

public class PostRequestApiPython {

    public PostRequestApiPython() {
    }

    public static String requestApiPython(String request, String URL) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            JSONObject json = new JSONObject();
            json.put("content", request);
            StringEntity params = new StringEntity(json.toString());
            HttpPost httpPost = new HttpPost(URL);
            httpPost.addHeader("content-type", "application/json");
            httpPost.setEntity(params);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                System.out.println(body);
                return body;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
