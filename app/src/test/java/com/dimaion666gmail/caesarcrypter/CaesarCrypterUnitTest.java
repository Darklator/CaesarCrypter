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
        assertEquals("BCD", caesarCrypter.translate(false,"1", "ABC"));
        assertEquals("A", caesarCrypter.translate(false,"1", "Z"));
        assertEquals("Z", caesarCrypter.translate(true,"1", "A"));
        // TODO: I should make it possible to input negative key memebers
    }
}
