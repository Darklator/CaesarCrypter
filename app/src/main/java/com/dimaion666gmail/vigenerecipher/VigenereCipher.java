package com.dimaion666gmail.vigenerecipher;

public class VigenereCipher {

    // Инициализируем обработчика каждому алфавиту
    private static LanguageHandler[] languageHandlers = new LanguageHandler[] {
           new StandartLanguageHandler(97, 122), // abcdefghijklmnopqrstuvwxyz - [97; 122]
           new RussianLanguageHandler()};

    private VigenereCipher() {}

    public static String translate(boolean isDecrypting, String key, String text) throws InvalidKeyException {
        // Получение длин сдвигов в соответствии с порядковым номером каждой буквы
        if (key.isEmpty()) throw new InvalidKeyException();

        int[] integerLetterShifts = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            char letterKey = key.charAt(i);
            boolean letterExistsInAlphabets = false;
            for (int j = 0; j < languageHandlers.length; j++) {
                if (languageHandlers[j].doesTheLetterExistHere(letterKey)) {
                    integerLetterShifts[i] = languageHandlers[j].getOrderInAlphabet(letterKey);
                    letterExistsInAlphabets = true;
                    break;
                }
            }
            if (letterExistsInAlphabets == false) throw new InvalidKeyException();
        }

        // Дешифровка или шифровка?
        if (isDecrypting == true)
            for (int i = 0; i < integerLetterShifts.length; i++)
                integerLetterShifts[i] = integerLetterShifts[i] * (-1);

        // Перевод пользовательского текста
        StringBuilder newString = new StringBuilder();
        int letterShiftIndex = 0; // Какую длину шага сдвига мы используем из integerLetterShifts.

        for (int i = 0; i < text.length(); i++) { // Прохождение по каждой букве.
            char letterToBeChanged = text.charAt(i);

            for (int j = 0; j < languageHandlers.length; j++) { // Поиск нужного обработчика алфавита.
                if (languageHandlers[j].doesTheLetterExistHere(letterToBeChanged)) {
                    // Если нужный обработчик найден, то мы меняем букву в соответствии с его указаниями.
                    letterToBeChanged = languageHandlers[j].shiftLetter(integerLetterShifts[letterShiftIndex], letterToBeChanged);

                    letterShiftIndex++; // Используем следующую длину шага сдвига
                    // Если была использована последняя длина, то начинаем с первой.
                    if (letterShiftIndex >= integerLetterShifts.length) letterShiftIndex = 0;

                    break;
                }
            }
            // Включение полученного символа в новую строку
            newString.append(letterToBeChanged);
        }
        return newString.toString();
    }
}
