package com.uade.ainews.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BiasRemover {

    public static String remove(String text){

        Set<String> spanishAdjectives = getAllAdjetivesInSpanish();

        String[] palabras = text.split("\\s+");
        StringBuilder processedText = new StringBuilder();

        for (String palabra : palabras) {
            if (!spanishAdjectives.contains(palabra.toLowerCase())) {
                processedText.append(palabra).append(" ");
            }
        }
        return String.valueOf(processedText).trim();
    }

    private static Set<String> getAllAdjetivesInSpanish() {
        Set<String> spanishAdjectives = new HashSet<>(Arrays.asList(
                "bueno", "buena", "malo", "mala", "interesante", "divertido", "feliz", "triste",
                "grande", "pequeño", "inteligente", "hermoso", "feo", "rápido", "lento",
                "rico", "pobre", "fuerte", "débil", "joven", "viejo", "amable", "cruel",
                "alegre", "serio", "caliente", "frío", "alto", "bajo", "limpio", "sucio",
                "sabroso", "insípido", "salado", "dulce", "agrio", "amargo", "agradable",
                "desagradable", "doloroso", "cómodo", "incómodo", "valiente", "miedoso",
                "sincero", "falso", "honesto", "desonesto", "puntual", "impuntual",
                "trabajador", "vago", "alegre", "aburrido", "abierto", "cerrado",
                "ligero", "pesado", "bonito", "feo", "luminoso", "oscuro",
                "delicioso", "desagradable", "intenso", "suave", "ruidoso", "silencioso",
                "brillante", "opaco", "fino", "grueso", "frágil", "resistente",
                "triste", "alegre", "corto", "largo", "estrecho", "ancho",
                "cálido", "frío", "húmedo", "seco", "duro", "blando",
                "áspero", "suave", "agudo", "grave", "amargo", "dulce",
                "inteligente", "tonto", "valiente", "cobarde", "bueno", "malo",
                "nuevo", "viejo", "rico", "pobre", "feliz", "triste",
                "sano", "enfermo", "limpio", "sucio", "rápido", "lento",
                "ligero", "pesado", "claro", "oscuro", "agradable", "desagradable",
                "duro", "blando", "fácil", "difícil", "amable", "cruel",
                "simpático", "antipático", "abierto", "cerrado", "alto", "bajo",
                "joven", "viejo", "largo", "corto", "gordo", "delgado",
                "fuerte", "débil", "rico", "pobre", "cálido", "frío", "rapidamente","gran", "exitoso", "exitosa"
        ));
        return spanishAdjectives;
    }

}
