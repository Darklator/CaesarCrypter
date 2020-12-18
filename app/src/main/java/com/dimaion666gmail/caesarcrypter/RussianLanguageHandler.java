package com.dimaion666gmail.caesarcrypter;

import java.util.HashMap;

public class RussianLanguageHandler extends LanguageHandler {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1105]
    char[] alphabet;

    public RussianLanguageHandler() {
        alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
    }

    @Override
    public boolean doesTheLetterExistHere(char theLetterWeSeacrh) {
        theLetterWeSeacrh = Character.toLowerCase(theLetterWeSeacrh);

        if(1072 <= (int)theLetterWeSeacrh && (int)theLetterWeSeacrh <= 1103 || (int)theLetterWeSeacrh == 1105)
            return true;
        else
            return false;
    }

    @Override
    public char shiftLetter(int shiftStep, char letterToBeShifted) {
        shiftStep = shiftStep % 33;

        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = -1;

        for (int i = 0; i < 33; i++) {
            if (alphabet[i] == letterToBeShifted) {
                letterIndex = i;
            }
        }

        letterIndex = (letterIndex + shiftStep) % 33;

        if (letterIndex < 0) {
            letterIndex = 33 - Math.abs(letterIndex);
        }

        char shiftedLetter = alphabet[letterIndex];

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
