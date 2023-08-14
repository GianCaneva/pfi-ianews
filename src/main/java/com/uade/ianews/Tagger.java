package com.uade.ianews;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

public class Tagger {

    static public void main(String[] args) {
        //String text = "Posteriormente, el desarrollo urbanístico estuvo marcado por el aumento de la población debido a la inmigración desde otras partes de España, lo que conllevó diversos proyectos urbanísticos como el Plan Comarcal de 1953 o el Plan General Metropolitano de 1976. Igualmente, la adecuación del espacio urbano de la ciudad se ha visto favorecida entre los siglos XIX y XXI por diversos eventos celebrados en la ciudad, como la Exposición Universal de 1888, la Internacional de 1929, el XXXV Congreso Eucarístico Internacional de 1952, los Juegos Olímpicos de 1992 y el Fórum Universal de las Culturas de 2004.";
        String text = "Subsequently, urban development was marked by the increase in population due to immigration from other parts of Spain, which led to various urban projects such as the Regional Plan of 1953 or the General Metropolitan Plan of 1976. Likewise, the adaptation of the urban space of the city has been favored between the 19th and 21st centuries by various events held in the city, such as the Universal Exposition of 1888, the International Exposition of 1929, the XXXV International Eucharistic Congress of 1952, the Olympic Games of 1992 and the Universal Forum of Cultures of 2004.";
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos");
        //props.setProperty("pos.model", "spanish-distsim.tagger");
        props.setProperty("pos.maxlen", "1000");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                System.out.println(word + " " + token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
            }
        }
    }

}