package com.uade.ianews.utils;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SummarizeArticle1 {

    public static void sumUp(String message) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:50000/sum");

        // Crear una cadena de parámetros codificada
          String parametros = null;
        try {
            parametros = "text=" + URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringEntity params = new StringEntity(parametros, ContentType.parse("UTF-8"));
        httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
        httpPost.setEntity(params);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response);
            //String responseBody = EntityUtils.toString(response.getEntity());
            //System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
