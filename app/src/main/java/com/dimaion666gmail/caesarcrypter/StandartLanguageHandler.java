package com.dimaion666gmail.caesarcrypter;

public class StandartLanguageHandler extends {
    // abcdefghijklmnopqrstuvwxyz - [97; 122]
    private int theStartInUnicode;
    private int theEndInUnicode;
    private int conversion;

    public StandartLanguageHandler(int theStartInUnicode, int theEndInUnicode) {
        this.theStartInUnicode = theStartInUnicode;
        this.theEndInUnicode = theEndInUnicode;
        this.conversion = theStartInUnicode;
    }

    @Override
    public boolean doesTheLetterExistHere(char theLetterWeSeacrh) {
        theLetterWeSeacrh = Character.toLowerCase(theLetterWeSeacrh);

        if(theStartInUnicode <= (int)theLetterWeSeacrh && (int)theLetterWeSeacrh <= theEndInUnicode)
            return true;
        else
            return false;
    }

    @Override
    public char shiftLetter(int shiftStep, char letterToBeShifted) {
        shiftStep = shiftStep % (theEndInUnicode - theStartInUnicode);

        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = (int)letterToBeShifted - conversion;

        letterIndex = (letterIndex + shiftStep) % (theEndInUnicode - theStartInUnicode);

        if (letterIndex < 0) {
            letterIndex = (theEndInUnicode - theStartInUnicode) - Math.abs(letterIndex);
        }

        letterIndex += conversion;

        char shiftedLetter = (char)letterIndex;

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
