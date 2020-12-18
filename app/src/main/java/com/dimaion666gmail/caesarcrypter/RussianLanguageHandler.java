package com.dimaion666gmail.caesarcrypter;

import java.util.HashMap;

public class RussianLanguageHandler extends LanguageHandler {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1105]
    HashMap<Integer, Integer> unicodeToSimpleCode = new HashMap(); // unicode - simple code
    HashMap<Integer, Integer> simpleCodeToUnicode = new HashMap(); // simple code - unicode


    public RussianLanguageHandler() {
        for (int i = 0; i < 33; i++) {
            if (i < 6) {
                simpleCodeToUnicode.put(i, i + 1072);
                unicodeToSimpleCode.put(i + 1072, i);
            } else if (i == 6) {
                simpleCodeToUnicode.put(i, 1105);
                unicodeToSimpleCode.put(1105, i);
            } else {
                simpleCodeToUnicode.put(i, (i + 1072 - 1));
                unicodeToSimpleCode.put((i + 1072 - 1), i);
            }
        }
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

        int letterIndex = unicodeToSimpleCode.get((int)letterToBeShifted);

        letterIndex = (letterIndex + shiftStep) % 33;

        if (letterIndex < 0) {
            letterIndex = 33 - Math.abs(letterIndex);
        }

        char shiftedLetter = (char)(int)simpleCodeToUnicode.get(letterIndex);

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
