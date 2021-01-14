package com.dimaion666gmail.vigenerecipher;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VigenereCipherUnitTest {

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void translateTest() throws InvalidKeyException {
        assertEquals("B", VigenereCipher.translate(false,"A", "A"));
        assertEquals("A", VigenereCipher.translate(false,"A", "Z"));
        assertEquals("Z", VigenereCipher.translate(true,"A", "A"));

        assertEquals("Б", VigenereCipher.translate(false,"А", "А"));
        assertEquals("А", VigenereCipher.translate(false,"А", "Я"));
        assertEquals("Я", VigenereCipher.translate(true,"А", "А"));

        try {
            assertEquals("БГ", VigenereCipher.translate(false,"*", "АБ"));
            fail("Expected InvalidKeyException");
        } catch (InvalidKeyException ikex) {
            assertNotEquals("", ikex.getMessage());
        }
    }
}
