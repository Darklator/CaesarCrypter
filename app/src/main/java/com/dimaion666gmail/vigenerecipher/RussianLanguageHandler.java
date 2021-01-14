package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for handling Russian language alphabet.
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public final class RussianLanguageHandler extends LanguageHandler {

    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1105]
    private final char[] alphabet;
    private final int alphabetLength;

    /**
     * This constructor builds a Russian alphabet handler.
     */
    public RussianLanguageHandler() {
        alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabetLength = alphabet.length;
    }

    @Override
    public boolean doesTheLetterExistHere(char letter) {
        letter = Character.toLowerCase(letter);

        return (((int) letter >= 1072) && ((int) letter <= 1103) || ((int) letter == 1105));
    }

    @Override
    public int getTheOrderInTheAlphabet(char letter) {
        int order = Character.toLowerCase(letter);

        // It is written because of the letter ё.
        if ((order > 1077) && (order != 1105)) {
            order -= 1071;
        } else if (order == 1105) {
            order = 6;
        } else {
            order -= 1072;
        }

        return (order + 1);
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

        shiftedLetter = alphabet[letterIndex];

        if (isUpperCase) {
            shiftedLetter = Character.toUpperCase(shiftedLetter);
        }

        return shiftedLetter;
    }

}
