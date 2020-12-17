package com.dimaion666gmail.caesarcrypter;

public abstract class LanguageHandler {
    public abstract boolean doesTheLetterExistHere(char letterToBeShifted);
    public abstract char shiftLetter(int shiftStep, char letterToBeShifted);
}
