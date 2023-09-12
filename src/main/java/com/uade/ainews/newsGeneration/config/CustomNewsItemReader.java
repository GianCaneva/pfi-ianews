package com.uade.ainews.newsGeneration.config;

import com.uade.ainews.utils.SsrReader;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Component
@StepScope
public class CustomNewsItemReader implements ItemReader<List<String>> {
    public static final String CLARIN_RSS = "https://www.clarin.com/rss/lo-ultimo/";
    public static final String PERFIL_RSS = "https://www.perfil.com/feed";
    private List<String> allLinks;
    private int nextLinkIndex;

    @Value("#{stepExecution}")
    private StepExecution stepExecution;

    public List<String> execute() {
        // Inicializa la lista de enlaces aquí
        allLinks = new LinkedList<>();
        allLinks.add(CLARIN_RSS);
        allLinks.add(PERFIL_RSS);

        // Obtiene todos los enlaces al inicializar
        for (int i = 0; i < allLinks.size(); i++) {
            try {
                allLinks.addAll(SsrReader.getAllLinks(allLinks.get(i)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        nextLinkIndex = 0;

        // Almacena la lista en el contexto del paso (step context)
        stepExecution.getExecutionContext().put("allLinks", allLinks);
        return allLinks;
    }

    @Override
    public List<String> read() throws Exception {
        return execute();
    }
}
