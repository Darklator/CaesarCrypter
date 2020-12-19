package com.dimaion666gmail.caesarcrypter;

import android.icu.text.SymbolTable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCrypterUnitTest {
    CaesarCrypter caesarCrypter;

    @Before
    public void setUp() throws Exception{
        caesarCrypter = new CaesarCrypter();
    }

    @Test
    public void translateTest() throws InvalidKeyException {
        assertEquals("B", caesarCrypter.translate(false,"A", "A"));
        assertEquals("A", caesarCrypter.translate(false,"A", "Z"));
        assertEquals("Z", caesarCrypter.translate(true,"A", "A"));

        assertEquals("Б", caesarCrypter.translate(false,"А", "А"));
        assertEquals("А", caesarCrypter.translate(false,"А", "Я"));
        assertEquals("Я", caesarCrypter.translate(true,"А", "А"));

        try {
            assertEquals("БГ", caesarCrypter.translate(false,"*", "АБ"));
            fail("Expected InvalidKeyException");
        } catch (InvalidKeyException ikex) {
            assertNotEquals("", ikex.getMessage());
        }
    }
}
