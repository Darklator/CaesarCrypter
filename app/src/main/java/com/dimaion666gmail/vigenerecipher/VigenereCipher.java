package com.dimaion666gmail.vigenerecipher;

public class VigenereCipher {

    // Инициализируем обработчика каждому алфавиту
    private static final LanguageHandler[] languageHandlers = new LanguageHandler[] {
           new StandardLanguageHandler(97, 122), // abcdefghijklmnopqrstuvwxyz - [97; 122]
           new RussianLanguageHandler()};

    private VigenereCipher() {}

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
