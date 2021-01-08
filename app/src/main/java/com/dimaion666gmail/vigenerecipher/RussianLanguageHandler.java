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

    public RussianLanguageHandler() {
        // Порядок алфавита в unicode нарушен, а работу с массивом я считаю наиболее
        // оптимальным решением из найденных.
        alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabetLength = alphabet.length;
    }

    @Override
    public boolean doesTheLetterExistHere(char letter) {
        letter = Character.toLowerCase(letter);
        return 1072 <= (int) letter && (int) letter <= 1103 || (int) letter == 1105;
    }

    @Override
    public int getOrderInAlphabet(char letter) {
        letter = Character.toLowerCase(letter);
        int order = findLetterIndexInAlphabet(letter);
        return (order + 1);
    }

    @Override
    public char shiftLetter(int shiftStep, char letter) {
        shiftStep = shiftStep % alphabetLength; // Отбрасываем лишнюю длину сдвига.

        // Если буква в верхнем регистре, то запоминаем.
        boolean isUpperCase = Character.isUpperCase(letter);
        // В алфавите мы работаем с буквами в нижнем регистре.
        letter = Character.toLowerCase(letter);

        // Ищем порядковый номер буквы в алфавите.
        int letterIndex = findLetterIndexInAlphabet(letter);

        // Сдвигаем порядковый номер и ищем букву. Если номер уходит за границы алфавита в конце,
        // то он всё равно уходит в начало по формуле.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // Если номер уходит за границы алфавита в начале, то он всё равно уходит в конец по
        // условию.
        if (letterIndex < 0)
            letterIndex = alphabetLength - Math.abs(letterIndex);

        // Получаем смещённую букву.
        char shiftedLetter = alphabet[letterIndex];

        // Возвращаем верхний регистр, если он был.
        if (isUpperCase)
            shiftedLetter = Character.toUpperCase(shiftedLetter);

        // Вовзращаем смещённую букву.
        return shiftedLetter;
    }

    // Получение порядкового номера буквы в алфавите, начиная с 0
    private int findLetterIndexInAlphabet(char letter) {
        int letterIndex = letter;

        // Тут учитывается отличительный индекс буквы ё в кодировке.
        if (letterIndex > 1077 && letterIndex != 1105)
            letterIndex -= 1071;
        else if (letterIndex == 1105)
            letterIndex = 6;
        else
            letterIndex -= 1072;

        return letterIndex;
    }
}
