package com.dimaion666gmail.caesarcrypter;

import android.util.Log;

public class CaesarCrypter {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" "abcdefghijklmnopqrstuvwxyz"
    private char[][] alphabet = new char[2][];
    private int[] letterShiftsInt;


    public CaesarCrypter() {
        alphabet[0] = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabet[1] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    public String translate(int encryptOrDecrypt, String letterShift, String text) {
        // Turn letterShift into an array of shifts
        String[] letterShiftsString = letterShift.split(" ");
        letterShiftsInt = new int[letterShiftsString.length];

        for (int i = 0; i < letterShiftsString.length; i++) {
            letterShiftsInt[i] = Integer.parseInt(letterShiftsString[i]);
        }

        // Define encrypting or decrypting letter shifts
        if (encryptOrDecrypt == 0) {
            for (int i = 0; i < letterShiftsInt.length; i++)
                letterShiftsInt[i] = Math.abs(letterShiftsInt[i]);
        } else {
            for (int i = 0; i < letterShiftsInt.length; i++)
                letterShiftsInt[i] = letterShiftsInt[i] * (-1);
        }

        // Translate text
        StringBuilder newString = new StringBuilder();
        int letterShiftsIntIndex = 0;

        for (int i = 0; i < text.length(); i++) { // Go through every letter
            int index = -1;
            char letterToChange = text.charAt(i);
            boolean isUpperCase = Character.isUpperCase(letterToChange);
            letterToChange = Character.toLowerCase(letterToChange);

            for (int j = 0; j < alphabet[0].length; j++) { // Find letter's index
                if (letterToChange == alphabet[0][j]) {
                    index = (j + letterShiftsInt[letterShiftsIntIndex]) % 33;
                    letterShiftsIntIndex++;
                    if (letterShiftsIntIndex > (letterShiftsInt.length - 1)) {
                        letterShiftsIntIndex = 0;
                    }
                }
            }

            if (index != -1) { // If the letter is found in the alphabet then we shift it, else we do nothing
                letterToChange = alphabet[0][index];
            }

            if (isUpperCase) { // We return upper case to the letter if it had it before
                letterToChange = Character.toUpperCase(letterToChange);
            }

            newString.append(letterToChange); // We build a new string
        }

        return newString.toString();
    }
}
