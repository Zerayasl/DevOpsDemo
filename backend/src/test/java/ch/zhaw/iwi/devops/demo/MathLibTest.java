package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MathLibTest {

    @Test
    void testIsEven() {
        boolean result = MathLib.isEven(30);
        assertTrue(result);
    }

    @Test
    void testIsNotEven() {
        boolean result = MathLib.isEven(3);
        assertFalse(result);
    }
}