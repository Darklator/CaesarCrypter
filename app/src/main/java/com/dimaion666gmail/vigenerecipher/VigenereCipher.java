/*
 * Dmitry Ionov
 *
 * Copyright notice
 */

package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for translating texts due vigenere cipher algorithm
 * NOTE: The first letter means 1
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public class VigenereCipher {

    /**
     * This variable contains language alphabets handlers:
     * English, Russian
     */
    private static final LanguageHandler[] languageHandlers = new LanguageHandler[] {
            // New wished language alphabets handlers should be added here
            new StandardLanguageHandler(97, 122),
            new RussianLanguageHandler()
    };

    /**
     * VigenereCipher doesn't need instances, so its constructor is private
     */
    private VigenereCipher() {}

    /**
     * This method translates input text and returns translated text
     *
     * @param isDecrypting the boolean that defines decrypting key mode if it is true
     * @param key the String that contains letters to be used as shift steps
     * @param text the String that wanted to be translated
     * @return TODO: Description must be written
     * @throws InvalidKeyException the exception to be thrown if key contains characters not from
     *                             any allowed alphabet
     */
    public static String translate(boolean isDecrypting, String key, String text) throws InvalidKeyException {
        if (key.isEmpty()) throw new InvalidKeyException();

        // Получение длин сдвигов в соответствии с порядковым номером каждой буквы
        int[] letterShifts = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            char letterKey = key.charAt(i);
            boolean letterExistsInAlphabets = false;

            for (LanguageHandler languageHandler : languageHandlers) {
                if (languageHandler.doesTheLetterExistHere(letterKey)) {
                    letterShifts[i] = languageHandler.getOrderInAlphabet(letterKey);
                    letterExistsInAlphabets = true;
                    break;
                }
            }

            if (!letterExistsInAlphabets)
                throw new InvalidKeyException();
        }

        // Дешифровка или шифровка?
        if (isDecrypting)
            for (int i = 0; i < letterShifts.length; i++)
                letterShifts[i] = letterShifts[i] * (-1);

        // Перевод пользовательского текста
        StringBuilder newString = new StringBuilder();
        int letterShiftIndex = 0; // Какую длину шага сдвига мы используем из letterShifts.

        for (int i = 0; i < text.length(); i++) { // Прохождение по каждой букве.
            char letterToBeChanged = text.charAt(i);

            for (LanguageHandler languageHandler : languageHandlers) { // Поиск нужного обработчика алфавита.
                if (languageHandler.doesTheLetterExistHere(letterToBeChanged)) {
                    // Если нужный обработчик найден, то мы меняем букву в соответствии с его указаниями.
                    letterToBeChanged = languageHandler.shiftLetter(letterShifts[letterShiftIndex], letterToBeChanged);

                    letterShiftIndex++; // Используем следующую длину шага сдвига
                    // Если была использована последняя длина, то начинаем с первой.
                    if (letterShiftIndex >= letterShifts.length) letterShiftIndex = 0;

                    break;
                }
            }

            // Включение полученного символа в новую строку
            newString.append(letterToBeChanged);
        }
        return newString.toString();
    }
}
