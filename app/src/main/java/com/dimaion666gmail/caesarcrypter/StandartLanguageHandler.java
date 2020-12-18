package com.dimaion666gmail.caesarcrypter;

public class StandartLanguageHandler extends LanguageHandler {
    // abcdefghijklmnopqrstuvwxyz - [97; 122]

    @Override
    public boolean doesTheLetterExistHere(char theLetterWeSeacrh) {
        theLetterWeSeacrh = Character.toLowerCase(theLetterWeSeacrh);

        if(97 <= (int)theLetterWeSeacrh && (int)theLetterWeSeacrh <= 122)
            return true;
        else
            return false;
    }

    @Override
    public char shiftLetter(int shiftStep, char letterToBeShifted) {
        shiftStep = shiftStep % 26;

        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = (int)letterToBeShifted - 97;

        letterIndex = (letterIndex + shiftStep) % 26;

        if (letterIndex < 0) {
            letterIndex = 26 - Math.abs(letterIndex);
        }

        letterIndex += 97;

        char shiftedLetter = (char)letterIndex;

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
