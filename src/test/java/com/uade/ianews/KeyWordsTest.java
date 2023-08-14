package com.uade.ianews;


import junit.framework.TestCase;

import com.uade.ianews.dto.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
public class KeyWordsTest extends TestCase {

    @Test
    public void givenMultiplesNewsThereAreNotSimilarOnes() {
        String messageToBeCast = " PrimerElementoá , SegundoéElemento, terceríelemento, CuÁrtOEóelementú, QuintoElEmentO";
        List<String> parametrizedResponse = KeyWords.standardizeResponse(messageToBeCast);
        assertThat(parametrizedResponse.get(0)).isEqualTo("PRIMERELEMENTOA");
        assertThat(parametrizedResponse.get(1)).isEqualTo("SEGUNDOEELEMENTO");
        assertThat(parametrizedResponse.get(2)).isEqualTo("TERCERIELEMENTO");
        assertThat(parametrizedResponse.get(3)).isEqualTo("CUARTOEOELEMENTU");
        assertThat(parametrizedResponse.get(4)).isEqualTo("QUINTOELEMENTO");
    }

}