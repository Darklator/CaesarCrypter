package com.dimaion666gmail.caesarcrypter;

public class RussianLanguageHandler extends LanguageHandler {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1105]
    private char[] alphabet;
    private int alphabetLength;

    public RussianLanguageHandler() {
        // Порядок алфавита в unicode нарушен, а работу с массивом я считаю наиболее
        // оптимальным решением из найденных.
        alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabetLength = alphabet.length;
    }

    @Override
    public boolean doesTheLetterExistHere(char theLetterWeSeacrh) {
        theLetterWeSeacrh = Character.toLowerCase(theLetterWeSeacrh);

        if(1072 <= (int)theLetterWeSeacrh && (int)theLetterWeSeacrh <= 1103 || (int)theLetterWeSeacrh == 1105)
            return true;
        else
            return false;
    }

    @Override
    public char shiftLetter(int shiftStep, char letterToBeShifted) {
        shiftStep = shiftStep % alphabetLength; // Отбрасываем лишнюю длину сдвига.

        // Если буква в верхнем регистре, то запоминаем, потом возвращаем.
        boolean isUpperCase = Character.isUpperCase(letterToBeShifted);
        // В алфавите мы работаем с буквами в нижнем регистре.
        letterToBeShifted = Character.toLowerCase(letterToBeShifted);

        int letterIndex = 0;

        // Ищем порядковый номер буквы в массиве алфавита.
        for (int i = 0; i < alphabetLength; i++) {
            if (alphabet[i] == letterToBeShifted) {
                letterIndex = i;
            }
        }

        // Сдвигаем порядковый номер и ищем букву. Если номер уходит за границы алфавита в конце,
        // то он всё равно уходит в начало по формуле.
        letterIndex = (letterIndex + shiftStep) % alphabetLength;

        // Если номер уходит за границы алфавита в начале, то он всё равно уходит в конец по
        // условию.
        if (letterIndex < 0) {
            letterIndex = alphabetLength - Math.abs(letterIndex);
        }

        // Получаем смещённую букву.
        char shiftedLetter = alphabet[letterIndex];

        // Возвращаем верхний регистр, если он был.
        if (isUpperCase) shiftedLetter = Character.toUpperCase(shiftedLetter);

        // Вовзращаем смещённую букву.
        return shiftedLetter;
    }
}
