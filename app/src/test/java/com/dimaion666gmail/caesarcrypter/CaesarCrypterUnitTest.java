package com.dimaion666gmail.caesarcrypter;

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
        assertEquals("БВГ", caesarCrypter.translate(false,"1", "АБВ"));
        assertEquals("Бвг", caesarCrypter.translate(false,"1", "Абв"));
        assertEquals("Аяю", caesarCrypter.translate(false,"1", "Яюэ"));
        assertEquals("Изж", caesarCrypter.translate(false,"10", "Яюэ"));
        assertEquals("Ир", caesarCrypter.translate(false,"33", "Ир"));
        assertEquals("/*-+", caesarCrypter.translate(false,"2", "/*-+"));
        assertEquals("БГЕЖ", caesarCrypter.translate(false,"1 2 3 4", "АБВГ"));
        assertEquals("ЦЧШ", caesarCrypter.translate(true,"10", "АБВ"));
        assertEquals("ЯАБ", caesarCrypter.translate(true,"1", "АБВ"));
        assertEquals("BCD", caesarCrypter.translate(false,"1", "ABC"));
        assertEquals("ZAB", caesarCrypter.translate(true,"1", "ABC"));
        assertEquals("ABC", caesarCrypter.translate(true,"", "ABC"));
        // TODO: I should make it possible to input negative key memebers
    }
}
