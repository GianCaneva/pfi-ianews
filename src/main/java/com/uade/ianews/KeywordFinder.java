package com.uade.ianews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class KeywordFinder {

    public static List<String>  getKeyWords2(String message) {
        String apiKey = "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";

        try {
            URL url = new URL("https://api.openai.com/v1/engines/text-davinci-003/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            String messageWithoutQuotes = message.replace("\"", "");
            String prompt = "Devolver sin numerar separado por comas las 5 palabras claves mas importantes de esta noticia: " + messageWithoutQuotes ;
            String data = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 100}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response to extract the generated text
                String jsonResponse = response.toString();
                String generatedText = extractGeneratedTextFromJson(jsonResponse);
                System.out.println("Generated Text: " + standardizeResponse(generatedText));
                connection.disconnect();
                List<String> strings = standardizeResponse(generatedText);
                return strings;

            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    private static String extractGeneratedTextFromJson(String jsonResponse) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonResponse).getAsJsonObject();

        JsonArray choices = jsonObject.getAsJsonArray("choices");
        if (choices != null && choices.size() > 0) {
            JsonObject choice = choices.get(0).getAsJsonObject();
            JsonElement textElement = choice.get("text");
            if (textElement != null) {
                return textElement.getAsString();
            }
        }

        return "No generated text found";
    }


    public static List<String> standardizeResponse(String responseRaw) {
        String responseRawWithoutDots = responseRaw.replace(".", "");
        String[] elements = responseRawWithoutDots.split(",");
        List<String> result = new ArrayList<>();

        for (String element : elements) {
            String upperCaseElement = element.toUpperCase();

            String replaceLetterA = upperCaseElement.replace("Á", "A");
            String replaceLetterE = replaceLetterA.replace("É", "E");
            String replaceLetterI = replaceLetterE.replace("Í", "I");
            String replaceLetterO = replaceLetterI.replace("Ó", "O");
            String replaceLetterU = replaceLetterO.replace("Ú", "U");

            String trimmedElement = replaceLetterU.trim();
            result.add(trimmedElement);
        }
        return result;

    }



}