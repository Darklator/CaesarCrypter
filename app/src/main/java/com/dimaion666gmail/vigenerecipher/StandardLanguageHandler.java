package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for handling language alphabets whose letters are placed in unicode as consistently
 * as in these alphabets. NOTE: alphabet must be lowercase;
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public class StandardLanguageHandler extends LanguageHandler {
    private final int startInUnicode;
    private final int endInUnicode;
    private final int alphabetLength;

    // Variable that provides conversion between simplified alphabet codes and unicode
    private final int conversion;

    /**
     * This constructor defines language alphabet parameters.
     *
     * @param startInUnicode this int defines alphabet start in unicode.
     * @param endInUnicode this int defines alphabet end in unicode.
     */
    public StandardLanguageHandler(int startInUnicode, int endInUnicode) {
        this.startInUnicode = startInUnicode;
        this.endInUnicode = endInUnicode;
        this.alphabetLength = endInUnicode - startInUnicode + 1;
        this.conversion = startInUnicode;
    }

    /**
     * This method determines is handler's alphabet contains the letter.
     *
     * @param letter the char we check.
     * @return returns boolean.
     */
    @Override
    public boolean doesTheLetterExistHere(char letter) {
        letter = Character.toLowerCase(letter);
        return (((int) letter >= startInUnicode) && ((int) letter <= endInUnicode));
    }

    /**
     * This method gets letter's order in handler's alphabet starting with 1.
     *
     * @param letter the char that order we look for.
     * @return returns the int that is order in handler's alphabet.
     */
    @Override
    public int getOrderInAlphabet(char letter) {
        letter = Character.toLowerCase(letter);
        return ((int)letter - conversion + 1);
    }

    /**
     * This method shifts letter.
     *
     * @param shiftStep the int defines how far we must shift.
     * @param letter the char we want to shift.
     * @return returns the char that is shifted letter.
     */
    @Override
    public char shiftLetter(int shiftStep, char letter) {
        int letterIndex;
        char shiftedLetter;
        boolean isUpperCase = Character.isUpperCase(letter); // We remember if letter is uppercase.

        letter = Character.toLowerCase(letter); // In alphabet we work with lowercase letters.
        letterIndex = getOrderInAlphabet(letter) - 1; // We get letter order in alphabet.
        shiftStep = shiftStep % alphabetLength; // We drop useless shiftStep length.

        // We move order. If order moves abroad in end, it returns in start anyway.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // If order moves abroad in start, it returns in end anyway.
        if (letterIndex < 0) {
            letterIndex = alphabetLength - Math.abs(letterIndex);
        }

        letterIndex += conversion; // We get unicode of simplified code.
        shiftedLetter = (char)letterIndex;

        if (isUpperCase) {
            shiftedLetter = Character.toUpperCase(shiftedLetter);
        }
        
        return shiftedLetter;
    }
}
