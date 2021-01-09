package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for translating texts due vigenere cipher algorithm.
 * NOTE: The first letter means 1.
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public class VigenereCipher {

    /**
     * This variable contains language alphabets handlers:
     * English, Russian.
     */
    private static final LanguageHandler[] languageHandlers = new LanguageHandler[] {
            // New wished language alphabets handlers should be added here.
            new StandardLanguageHandler(97, 122),
            new RussianLanguageHandler()
    };

    /**
     * VigenereCipher doesn't need instances, so its constructor is private.
     */
    private VigenereCipher() {}

    /**
     * This method translates input text and returns translated text.
     *
     * @param isDecrypting the boolean that defines decrypting key mode if it is true.
     * @param key the String that contains letters to be used as shift steps.
     * @param text the String that wanted to be translated.
     * @return the String that has been built.
     * @throws InvalidKeyException the exception to be thrown if key contains characters not from
     *                             any allowed alphabet.
     */
    public static String translate(boolean isDecrypting, String key, String text)
            throws InvalidKeyException {
        StringBuilder newStringBuilder = new StringBuilder();
        String newString;
        int[] letterShifts = new int[key.length()];
        int letterShiftIndex = 0;

        if (key.isEmpty()) {
            throw new InvalidKeyException();
        }

        // Getting shift steps according to every letter order. Letters will be gotten from key.
        for (int i = 0; i < key.length(); i++) {
            char letterKey = key.charAt(i);
            boolean letterExistsInAlphabets = false;

            for (LanguageHandler languageHandler : languageHandlers) {
                if (languageHandler.doesTheLetterExistHere(letterKey)) {
                    letterShifts[i] = languageHandler.getTheOrderInTheAlphabet(letterKey);
                    letterExistsInAlphabets = true;
                    break;
                }
            }

            if (!letterExistsInAlphabets) {
                throw new InvalidKeyException();
            }
        }

        // Decrypting or encrypting mode.
        if (isDecrypting) {
            for (int i = 0; i < letterShifts.length; i++) {
                letterShifts[i] = letterShifts[i] * (-1);
            }
        }

        // Translating user's text.
        for (int i = 0; i < text.length(); i++) {
            char letterToBeChanged = text.charAt(i);
            char changedLetter = letterToBeChanged;

            for (LanguageHandler languageHandler : languageHandlers) {
                if (languageHandler.doesTheLetterExistHere(letterToBeChanged)) {
                    changedLetter = languageHandler.shiftTheLetter(letterShifts[letterShiftIndex],
                            letterToBeChanged);

                    letterShiftIndex++;
                    if (letterShiftIndex >= letterShifts.length) {
                        letterShiftIndex = 0;
                    }

                    break;
                }
            }

            newStringBuilder.append(changedLetter);
        }

        newString = newStringBuilder.toString();
        return newString;
    }
}
