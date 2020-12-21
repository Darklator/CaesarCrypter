package com.dimaion666gmail.vigenerecipher;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VigenereCipherUnitTest {
    VigenereCipher vigenereCipher;

    @Before
    public void setUp() throws Exception{
        vigenereCipher = new VigenereCipher();
    }

    @Test
    public void translateTest() throws InvalidKeyException {
        assertEquals("B", vigenereCipher.translate(false,"A", "A"));
        assertEquals("A", vigenereCipher.translate(false,"A", "Z"));
        assertEquals("Z", vigenereCipher.translate(true,"A", "A"));

        assertEquals("Б", vigenereCipher.translate(false,"А", "А"));
        assertEquals("А", vigenereCipher.translate(false,"А", "Я"));
        assertEquals("Я", vigenereCipher.translate(true,"А", "А"));

        try {
            assertEquals("БГ", vigenereCipher.translate(false,"*", "АБ"));
            fail("Expected InvalidKeyException");
        } catch (InvalidKeyException ikex) {
            assertNotEquals("", ikex.getMessage());
        }
    }
}
