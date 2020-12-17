package com.dimaion666gmail.caesarcrypter;

import android.annotation.SuppressLint;

public class CaesarCrypter {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" [1072; 1103] U [1077]
    // английский алфавит [97; 122]

    public CaesarCrypter() {
    }

    public String translate(boolean decrypting, String letterShifts, String text) {
        // Splitting string letterShifts (user's key) by space and turning gotten array's elements
        // into integers.
        if (letterShifts.isEmpty()) return text; // If there is no letter shifts then we just return
                                                //  the same text.

        String[] stringLetterShifts = letterShifts.split(" ");
        int[] integerLetterShifts = new int[stringLetterShifts.length];

        for (int i = 0; i < stringLetterShifts.length; i++)
            integerLetterShifts[i] = Integer.parseInt(stringLetterShifts[i]);

        // Definition of either encrypting or decrypting key.
        if (decrypting == true)
            for (int i = 0; i < integerLetterShifts.length; i++)
                integerLetterShifts[i] = integerLetterShifts[i] * (-1);

        // Translating user's text
        StringBuilder newString = new StringBuilder();
        int letterShiftIndex = 0; // Which length of shift we use from integerLetterShifts array
                                                                                        // now.

        for (int i = 0; i < text.length(); i++) { // Go through every letter.
            int alphabetLanguageIndex = 0; // Index of language alphabet.
            int indexInAnAlphabet; // -1 means that the letter is not found in an alphabet.
            int shiftedIndexInAnAlphabet = 0; // Shifted version of letter's index.
            char letterToChange = text.charAt(i); // We take the letter from user's text.
            boolean isUpperCase = Character.isUpperCase(letterToChange); // We remember if the
                                                                        // letter is uppercase.
            letterToChange = Character.toLowerCase(letterToChange); // We change the letter to
            // lowercase, so that it is can be compared with lowercase letters in alphabets.
            // We tag outerloop to use break completely.

            indexInAnAlphabet = (int)letterToChange;
            indexInAnAlphabet++;
            letterToChange = (char)indexInAnAlphabet;

            if(isUpperCase) letterToChange = Character.toUpperCase(letterToChange);

            newString.append(letterToChange); // We build a new string using every shifted letter
        }
        return newString.toString();
    }
}
