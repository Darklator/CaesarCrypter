package com.dimaion666gmail.caesarcrypter;

public class RussianLanguageHandler extends LanguageHandler {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1077]

    @Override
    public boolean doesTheLetterExistHere(char letterToBeShifted) {
        return false;
    }

    @Override
    public char shiftLetter(int shiftStep, char letterToBeShifted) {
        return 0;
    }
}
