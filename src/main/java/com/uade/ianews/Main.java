package com.uade.ianews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        System.out.println(chatGPT("hola como andas?"));
        // Prints out a response to the question.
    }

    public static String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");


            String question = "Devolver sin numerar separado por comas las 5 palabras claves mas importantes de esta noticia?: ";
            String noticia = "Sergio Massa se reunió con el gabinete Económico: aseguran que seguirá siendo ministro después las PASO.Esta tarde los funcionarios elevarán las medidas que se planifican para después de las elecciones. Massa junto al equipo de Economía en la última reunión antes de las PASO. co en el Palacio de Hacienda. En el encuentro planificaron las actividades de gestión con vistas a las próximas semanas. A las 17:30 todas las áreas del Ministerio le elevarán a Massa las medidas para la semana entrante. Según pudo saber Infobae de fuentes oficiales, el ministro seguiría en el cargo después de las elecciones primarias abiertas simultáneas y obligatorias (PASO), más allá de los resultados. Así, él mismo estaría a cargo de los nuevos anuncios. “Vamos ya a trabajar en las medidas de la semana que viene para que la gente viva con certidumbre, más allá del proceso electoral. Una elección no es más que eso: una elección. En todo caso, lo que se discute en la elección son otras cosas”, dijo Massa hoy en una entrevista con Infobae. “Cuando se mira en perspectiva los próximos meses de la Argentina, tenés 7.500 millones de dólares de ingreso por el acuerdo con el Fondo, otros 2.750 millones de dólares de ingreso del acuerdo con el Fondo para noviembre, tenés la recuperación de parte de los yuanes que se usaron para pagarle al Fondo que vuelven a las reservas del Banco Central. Está, además, la activación del segundo tramo del swap con China. Recordémosle a la gente que el desembolso que hace el Banco Central chino directamente al Fondo es la apertura del segundo tramo del swap, que pusiéramos en marcha cuando estuvimos de visita en China. Eso te da más libertad y más capacidad de intervención”, agregó.";

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + question+noticia + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content") + 11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}