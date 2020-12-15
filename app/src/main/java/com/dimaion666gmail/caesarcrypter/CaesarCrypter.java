package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" "abcdefghijklmnopqrstuvwxyz"
    private char[][] alphabet = new char[2][];


    public CaesarCrypter() {
        alphabet[0] = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabet[1] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    public String translate(int encryptOrDecrypt, String letterShifts, String text) {
        // Splitting string letterShifts (user's key) by space and turning gotten array's elements
        // into integers.
        String[] stringLetterShifts = letterShifts.split(" ");
        int[] integerLetterShifts = new int[stringLetterShifts.length];

        for (int i = 0; i < stringLetterShifts.length; i++)
            integerLetterShifts[i] = Integer.parseInt(stringLetterShifts[i]);

        // Definition of either encrypting or decrypting
        if (encryptOrDecrypt == 0) {
            for (int i = 0; i < integerLetterShifts.length; i++)
                integerLetterShifts[i] = Math.abs(integerLetterShifts[i]);
        } else {
            for (int i = 0; i < integerLetterShifts.length; i++)
                integerLetterShifts[i] = integerLetterShifts[i] * (-1);
        }

        // Translating user's text
        StringBuilder newString = new StringBuilder();
        int letterShiftIndex = 0; // Which length of shift we use now from integerLetterShifts

        for (int i = 0; i < text.length(); i++) { // Go through every letter
            int indexInAnAlphabet = -1; // -1 means that the letter is not found
            char letterToChange = text.charAt(i); // We take the letter from user's text
            boolean isUpperCase = Character.isUpperCase(letterToChange);
            letterToChange = Character.toLowerCase(letterToChange);

            for (int j = 0; j < alphabet[0].length; j++) { // Find letter's index in an alphabet
                if (letterToChange == alphabet[0][j]) { // If the letter has been found then we get
                                                       // its shifted index.
                    // FIXME
                    indexInAnAlphabet = (j + integerLetterShifts[letterShiftIndex]) % 33; // Mistake

                    letterShiftIndex++;
                    if (letterShiftIndex > (integerLetterShifts.length - 1)) {
                        letterShiftIndex = 0; // We start going through every key again
                    }
                    break;
                }
            }

            if (indexInAnAlphabet != -1) // If the letter has been found in an alphabet then we use
                                        // its shifted version, else we do nothing.
                letterToChange = alphabet[0][indexInAnAlphabet];

            if (isUpperCase) // We return upper case to the letter if it had it before
                letterToChange = Character.toUpperCase(letterToChange);

            newString.append(letterToChange); // We build a new string using every shifted letter
        }

        return newString.toString();
    }
}
