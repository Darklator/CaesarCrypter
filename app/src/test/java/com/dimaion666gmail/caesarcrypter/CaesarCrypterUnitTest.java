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
    public void translateTest() {
        assertEquals("B", caesarCrypter.translate(false,"B", "A"));
        assertEquals("A", caesarCrypter.translate(false,"B", "Z"));
        assertEquals("Z", caesarCrypter.translate(true,"B", "A"));

        assertEquals("Б", caesarCrypter.translate(false,"Б", "А"));
        assertEquals("А", caesarCrypter.translate(false,"Б", "Я"));
        assertEquals("Я", caesarCrypter.translate(true,"Б", "А"));
        // TODO: I should make it possible to input negative key memebers
    }
}
