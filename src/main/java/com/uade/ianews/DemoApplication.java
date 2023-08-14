package com.uade.ianews;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        //Lee todas las secciones de un SSR
//		System.out.println(ssrReader("https://www.clarin.com/rss/policiales/"));
        //Lee una pagina en particular
        System.out.println(singlePageReader("https://www.telam.com.ar/notas/202307/633615-bullrich-critica-cifra-desaparecidos.html"));
    }


    public static String singlePageReader(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                //PARA LEER TITULOS
                if (line.contains("<div class=\"paragraph\">")) {
                    int firstPos = line.indexOf("<br>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<br", "");
                    temp = temp.replaceFirst("</div>", "");
                    int lastPos = temp.indexOf("</div>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";
                }
            }
            in.close();

            //Remover contenido basura del html leido de la pagina
            sourceCode = sourceCode.replace("<li>", "");
            sourceCode = sourceCode.replace("</li>", "");
            sourceCode = sourceCode.replace("<div>", "");
            sourceCode = sourceCode.replace("</div>", "");
            sourceCode = sourceCode.replace("<strong>", "");
            sourceCode = sourceCode.replace("</strong>", "");
            sourceCode = sourceCode.replace("<br>", "");
            sourceCode = sourceCode.replace("<span", "");
            sourceCode = sourceCode.replace("</span", "");
            sourceCode = sourceCode.replace("<", "");
            sourceCode = sourceCode.replace("class=", "");
            sourceCode = sourceCode.replace("h2", "");
            sourceCode = sourceCode.replace(">", "");
            sourceCode = sourceCode.replace("<i", "");
            sourceCode = sourceCode.replace("</", "");
            sourceCode = sourceCode.replace("<a", "");


            sourceCode = sourceCode.replace("href=>", "");


            return sourceCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ssrReader(String urlAddress) {
        try {
            URL rssUrl = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            while ((line = in.readLine()) != null) {
                //PARA LEER TITULOS
                if (line.contains("<title>")) {
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";
                }
                //PARA LEER DESCRIPCIONES
                if (line.contains("<description>")) {
                    int firstPos = line.indexOf("<description>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<description>", "");
                    int lastPos = temp.indexOf("</description>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";
                }
                //PARA LEER LINKS
                if (line.contains("<guid")) {
                    int firstPos = line.indexOf("<guid");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<guid", "");
                    int lastPos = temp.indexOf("</guid>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";
                }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException exception) {
            System.out.println("Malformed URL");
        } catch (IOException exception) {
            System.out.println("Something went wrong reading the content");
        }
        return null;
    }

//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
}