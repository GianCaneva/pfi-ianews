package com.uade.ainews.newsGeneration.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder extends BCryptPasswordEncoder {
    // Declaración de la instancia única como volatile para garantizar su inicialización segura en entornos multihilo.
    private static volatile Encoder instance;

    // Constructor privado para evitar la creación de instancias directamente.
    private Encoder() {
    }

    // Método estático para obtener la instancia única.
    public static Encoder getInstance() {
        if (instance == null) {
            synchronized (Encoder.class) {
                if (instance == null) {
                    instance = new Encoder();
                }
            }
        }
        return instance;
    }
}
