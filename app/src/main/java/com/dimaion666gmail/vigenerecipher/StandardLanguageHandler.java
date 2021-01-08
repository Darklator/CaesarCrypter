package com.dimaion666gmail.vigenerecipher;

/**
 * This class is for handling language alphabets whose letters are placed in unicode as consistently
 * as in these alphabets.
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
     * This method gets letter's order in handler's alphabet.
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
        shiftStep = shiftStep % alphabetLength; // Отбрасываем лишнюю длину сдвига.

        // Если буква в верхнем регистре, то запоминаем.
        boolean isUpperCase = Character.isUpperCase(letter);
        // В алфавите мы работаем с буквами в нижнем регистре.
        letter = Character.toLowerCase(letter);

        // Получаем порядковый номер буквы в алфавите.
        int letterIndex = (int)letter - conversion;

        // Сдвигаем порядковый номер и ищем букву. Если номер уходит за границы алфавита в конце,
        // то он всё равно уходит в начало по формуле.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // Если номер уходит за границы алфавита в начале, то он всё равно уходит в конец по
        // условию.
        if (letterIndex < 0)
            letterIndex = alphabetLength - Math.abs(letterIndex);

        // Приводим упрощённый порядковый номер к виду unicode.
        letterIndex += conversion;

        // Получаем символ.
        char shiftedLetter = (char)letterIndex;

        // Возвращаем верхний регистр, если он был.
        if (isUpperCase)
            shiftedLetter = Character.toUpperCase(shiftedLetter);

        // Вовзращаем смещённую букву.
        return shiftedLetter;
    }
}
