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
        assertEquals("Изж", caesarCrypter.translate(0,"10", "Яюэ"));
        assertEquals("Ир", caesarCrypter.translate(0,"33", "Ир"));
        assertEquals("/*-+", caesarCrypter.translate(0,"2", "/*-+"));
        assertEquals("БГЕЖ", caesarCrypter.translate(0,"1 2 3 4", "АБВГ"));
    }
}
