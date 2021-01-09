package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for handling language alphabets that letters are placed in unicode as consistently
 * as in these alphabets. NOTE: an alphabet must be lowercase;
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public class StandardLanguageHandler extends LanguageHandler {
    private final int startInUnicode;
    private final int endInUnicode;
    private final int alphabetLength;

    // The variable that provides conversion between simplified alphabet codes and unicode
    private final int conversion;

    /**
     * This constructor defines language alphabet parameters.
     *
     * @param startInUnicode this int defines the alphabet start in unicode.
     * @param endInUnicode this int defines the alphabet end in unicode.
     */
    public StandardLanguageHandler(int startInUnicode, int endInUnicode) {
        this.startInUnicode = startInUnicode;
        this.endInUnicode = endInUnicode;
        this.alphabetLength = endInUnicode - startInUnicode + 1;
        this.conversion = startInUnicode;
    }

    @Override
    public boolean doesTheLetterExistHere(char letter) {
        letter = Character.toLowerCase(letter);
        return (((int) letter >= startInUnicode) && ((int) letter <= endInUnicode));
    }

    @Override
    public int getTheOrderInTheAlphabet(char letter) {
        letter = Character.toLowerCase(letter);
        return ((int)letter - conversion + 1);
    }

    @Override
    public char shiftTheLetter(int shiftStep, char letter) {
        int letterIndex;
        char shiftedLetter;

        // We remember if the letter is uppercase.
        boolean isUpperCase = Character.isUpperCase(letter);

        letter = Character.toLowerCase(letter); // We work with lowercase letters in the alphabet.

        // We get the letter order in the alphabet.
        letterIndex = getTheOrderInTheAlphabet(letter) - 1;
        shiftStep = shiftStep % alphabetLength; // We drop the useless shiftStep length.

        // We move the order. If the order moves abroad in the end, it returns in the start anyway.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // If the order moves abroad in the start, it returns in the end anyway.
        if (letterIndex < 0) {
            letterIndex = alphabetLength - Math.abs(letterIndex);
        }

        letterIndex += conversion; // We get the unicode of the simplified code.
        shiftedLetter = (char)letterIndex;

        if (isUpperCase) {
            shiftedLetter = Character.toUpperCase(shiftedLetter);
        }

        return shiftedLetter;
    }
}
