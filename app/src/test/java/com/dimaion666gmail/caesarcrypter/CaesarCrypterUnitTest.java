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
        assertEquals("БВГ", caesarCrypter.translate(0,"1", "АБВ"));
        assertEquals("Бвг", caesarCrypter.translate(0,"1", "Абв"));
        assertEquals("Аяю", caesarCrypter.translate(0,"1", "Яюэ"));
        assertEquals("Изж", caesarCrypter.translate(0,"10", "Яюэ"));
        assertEquals("Ир", caesarCrypter.translate(0,"33", "Ир"));
        assertEquals("/*-+", caesarCrypter.translate(0,"2", "/*-+"));
        assertEquals("БГЕЖ", caesarCrypter.translate(0,"1 2 3 4", "АБВГ"));
        assertEquals("ЦЧШ", caesarCrypter.translate(1,"10", "АБВ"));
        assertEquals("ЯАБ", caesarCrypter.translate(1,"1", "АБВ"));
        assertEquals("BCD", caesarCrypter.translate(0,"1", "ABC"));
        assertEquals("ZAB", caesarCrypter.translate(1,"1", "ABC"));
    }
}
