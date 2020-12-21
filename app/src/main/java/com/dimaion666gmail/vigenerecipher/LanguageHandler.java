package com.dimaion666gmail.vigenerecipher;

public abstract class LanguageHandler {
    public abstract boolean doesTheLetterExistHere(char letter);
    public abstract int getOrderInAlphabet(char letter);
    public abstract char shiftLetter(int shiftStep, char letter);
}
