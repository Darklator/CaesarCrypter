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
        assertEquals("BCD", caesarCrypter.translate(false,"1", "ABC"));
        assertEquals("A", caesarCrypter.translate(false,"1", "Z"));
        assertEquals("Z", caesarCrypter.translate(true,"1", "A"));
        assertEquals("Y", caesarCrypter.translate(false,"25", "Z"));

        assertEquals("БВГ", caesarCrypter.translate(false,"1", "АБВ"));
        assertEquals("А", caesarCrypter.translate(false,"1", "Я"));
        assertEquals("Я", caesarCrypter.translate(true,"1", "А"));
        // TODO: I should make it possible to input negative key memebers
    }
}
