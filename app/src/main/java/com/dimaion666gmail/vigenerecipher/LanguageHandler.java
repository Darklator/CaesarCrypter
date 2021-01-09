package com.dimaion666gmail.vigenerecipher;

/**
 * This class contains methods that must be implemented by a new language alphabet handler.
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public abstract class LanguageHandler {

    /**
     * This method determines if handler's alphabet contains the letter.
     *
     * @param letter the char we check.
     * @return returns boolean.
     */
    public abstract boolean doesTheLetterExistHere(char letter);

    /**
     * This method gets letter's order in handler's alphabet starting with 1.
     *
     * @param letter the char that order we look for.
     * @return returns the int that is the order in handler's alphabet.
     */
    public abstract int getTheOrderInTheAlphabet(char letter);

    /**
     * This method shifts a letter.
     *
     * @param shiftStep the int defines how far we must shift.
     * @param letter the char we want to shift.
     * @return returns the char that is shifted letter.
     */
    public abstract char shiftTheLetter(int shiftStep, char letter);
}