package com.dimaion666gmail.caesarcrypter;

public class StandartLanguageHandler extends LanguageHandler {
    // abcdefghijklmnopqrstuvwxyz - [97; 122]
    private int theStartInUnicode;
    private int theEndInUnicode;
    private int conversion;
    private int alphabetLength;

    public StandartLanguageHandler(int theStartInUnicode, int theEndInUnicode) {
        this.theStartInUnicode = theStartInUnicode;
        this.theEndInUnicode = theEndInUnicode;
        this.conversion = theStartInUnicode;
        this.alphabetLength = theEndInUnicode - theStartInUnicode + 1;
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
        shiftStep = shiftStep % alphabetLength;

        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = (int)letterToBeShifted - conversion;

        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        if (letterIndex < 0) {
            letterIndex = alphabetLength - Math.abs(letterIndex);
        }

        letterIndex += conversion;

        char shiftedLetter = (char)letterIndex;

        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        return shiftedLetter;
    }
}
