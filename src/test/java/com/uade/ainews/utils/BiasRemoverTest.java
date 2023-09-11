package com.uade.ainews.utils;

import static org.junit.jupiter.api.Assertions.*;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class BiasRemoverTest extends TestCase {
    @Test
    public void testRemove() {
        String originalText = "La exitosa campaña finalizo rapidamente en el dia de ayer.";
        String actualResult = BiasRemover.remove(originalText);
        String expectedResult = "La campaña finalizo en el dia de ayer.";
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}