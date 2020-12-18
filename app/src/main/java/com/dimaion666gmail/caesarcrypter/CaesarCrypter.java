package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {

    private LanguageHandler[] languageHandlers = new LanguageHandler[2];

    public CaesarCrypter() {
        // Инициализируем обработчика каждому алфавиту
        languageHandlers[0] = new StandartLanguageHandler(97, 122);  // abcdefghijklmnopqrstuvwxyz - [97; 122]
        languageHandlers[1] = new RussianLanguageHandler();
    }

    public String translate(boolean isDecrypting, String letterShifts, String text) throws InvalidKeyException{
        // Получение длин сдвигов в соответствии с порядковым номером каждой буквы в каждом алфавите
        if (letterShifts.isEmpty()) return text;

        int[] integerLetterShifts = new int[letterShifts.length()];

        for (int i = 0; i < letterShifts.length(); i++) {
            char letterKey = letterShifts.charAt(i);
            for (int j = 0; j < languageHandlers.length; j++) {
                if (languageHandlers[j].doesTheLetterExistHere(letterKey)) {
                    integerLetterShifts[i] = languageHandlers[j].getOrderInAlphabet(letterKey);
                    break;
                } else {
                    // Выбрасываем исключение
                    // TODO: тут что-то не так
                    throw new InvalidKeyException(letterShifts);
                }
            }
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
