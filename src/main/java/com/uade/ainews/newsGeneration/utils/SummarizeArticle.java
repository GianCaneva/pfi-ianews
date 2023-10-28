package com.uade.ainews.newsGeneration.utils;

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

    // Call Python external service to summarize an article
    public static String sumUp(String message, Integer textExtension) {
        String summary = "";
        try {
            String restUrl = "http://localhost:8081/api/summarize/article";
            String response = sendTextViaRest(message, textExtension, restUrl);
            summary = decodeUnicode(response);

        } catch (Exception e) {
            System.err.println("Error al enviar el texto: " + e.getMessage());
        }
        return summary;
    }


    public static String sendTextViaRest(String text, Integer textExtension, String restUrl) throws IOException {
        // Build URL with textExtension parameter
        String urlWithParams = restUrl + "?textExtension=" + textExtension;
        URL url = new URL(urlWithParams);

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
            throw new RuntimeException("Server error: " + responseCode);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            return response.toString().substring(1, response.length() - 1);
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

