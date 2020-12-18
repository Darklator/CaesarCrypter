package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {

    private LanguageHandler[] languageHandlers = new LanguageHandler[2];

    public CaesarCrypter() {
        // Инициализируем обработчика каждому алфавиту
        languageHandlers[0] = new StandartLanguageHandler(97, 122);  // abcdefghijklmnopqrstuvwxyz - [97; 122]
        languageHandlers[1] = new RussianLanguageHandler();
    }

    public String translate(boolean isDecrypting, String letterShifts, String text) {
        // Splitting string letterShifts (user's key) by space and turning gotten array's elements
        // into integers.
        if (letterShifts.isEmpty()) return text; // If there is no letter shifts then we just return
                                                //  the same text.

        String[] stringLetterShifts = letterShifts.split(" ");
        int[] integerLetterShifts = new int[stringLetterShifts.length];

        for (int i = 0; i < stringLetterShifts.length; i++)
            integerLetterShifts[i] = Integer.parseInt(stringLetterShifts[i]);

        // Definition of either encrypting or decrypting key.
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
