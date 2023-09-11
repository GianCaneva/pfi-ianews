package com.uade.ainews.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
public class SummarizeArticle {

    public static String sumUp(String message) {
        String summary = "";
        try {
            String restUrl = "http://localhost:8080/api/receive";
            String response = sendTextViaRest(message, restUrl);
            String decodedResponse = decodeUnicode(response);
            summary = extractResponse(decodedResponse);

        } catch (Exception e) {
            System.err.println("Error al enviar el texto: " + e.getMessage());
        }
        return summary;
    }


    public static String sendTextViaRest(String text, String restUrl) throws IOException {
        URL url = new URL(restUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/plain; charset=" + StandardCharsets.UTF_8);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = text.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Error en la respuesta del servidor: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    public static String decodeUnicode(String input) {
        Properties properties = new Properties();
        properties.put("input", input);
        return properties.getProperty("input");
    }

    private static String extractResponse(String jsonResponse) {
        String summaryText = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            if (jsonNode.isArray() && jsonNode.size() > 0) {
                JsonNode summaryNode = jsonNode.get(0).get("summary_text");
                if (summaryNode != null) {
                    summaryText = summaryNode.asText();
                    System.out.println("Texto resumido: " + summaryText);
                }
            }

        } catch (Exception e) {
            System.err.println("Error al parsear JSON: " + e.getMessage());
        }
        return summaryText;
    }
}

