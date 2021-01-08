package com.dimaion666gmail.vigenerecipher;

/**
 * This class contains methods that must be implemented by new language alphabet handler
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public abstract class LanguageHandler {
    public abstract boolean doesTheLetterExistHere(char letter);

    public abstract int getOrderInAlphabet(char letter);

    public abstract char shiftLetter(int shiftStep, char letter);
}