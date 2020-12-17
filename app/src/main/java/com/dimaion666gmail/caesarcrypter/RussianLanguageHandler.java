package com.dimaion666gmail.caesarcrypter;

public class RussianLanguageHandler extends LanguageHandler {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1105]

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
        shiftStep = shiftStep % 32;

        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = (int)letterToBeShifted - 1072;

        letterIndex = (letterIndex + shiftStep) % 32;

        if (letterIndex < 0) {
            letterIndex = 32 - Math.abs(letterIndex);
        }

        letterIndex += 1072;

        char shiftedLetter = (char)letterIndex;

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
