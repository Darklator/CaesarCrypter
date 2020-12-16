package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" "abcdefghijklmnopqrstuvwxyz"
    private char[][] alphabet = new char[2][];


    public CaesarCrypter() {
        alphabet[0] = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabet[1] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    public String translate(boolean decrypting, String letterShifts, String text) {
        // Splitting string letterShifts (user's key) by space and turning gotten array's elements
        // into integers.
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
            int indexInAnAlphabet = -1; // -1 means that the letter is not found in an alphabet.
            int alphabetLanguageIndex = 0; // Index of language alphabet.
            int shiftedIndexInAnAlphabet = 0; // Shifted version letter's index
            char letterToChange = text.charAt(i); // We take the letter from user's text
            boolean isUpperCase = Character.isUpperCase(letterToChange); // We remember if the
                                                                        // letter is uppercase.
            letterToChange = Character.toLowerCase(letterToChange); // We change the letter to
            // lowercase, so that it is can be compared with lowercase letters in alphabets.
            everyLetterOfEveryAlphabet: // We tag outerloop to use break completely
            for (int j = 0; j < alphabet.length; j++) { // Go through every alphabet
                for (int k = 0; k < alphabet[j].length; k++) { // Go through every letter in the
                                                                // current alphabet.
                     if (letterToChange == alphabet[j][k]) { // If the letter was found
                         alphabetLanguageIndex = j; // Remember the alphabet index
                         indexInAnAlphabet = k; // Remember the letter index in the alphabet
                         shiftedIndexInAnAlphabet = (indexInAnAlphabet +
                                 integerLetterShifts[letterShiftIndex]) % alphabet[j].length;

                         if (shiftedIndexInAnAlphabet < 0)
                             shiftedIndexInAnAlphabet = alphabet[j].length -
                                     Math.abs(shiftedIndexInAnAlphabet);

                         letterShiftIndex++;
                         if (letterShiftIndex > (integerLetterShifts.length - 1)) {
                             letterShiftIndex = 0; // We start going through every key again
                         }
                         break everyLetterOfEveryAlphabet;
                     }
                 }
            }

            if (indexInAnAlphabet != -1) // If the letter has been found in an alphabet then we use
                                        // its shifted version, else we do nothing.
                letterToChange = alphabet[alphabetLanguageIndex][shiftedIndexInAnAlphabet];

            if (isUpperCase) // We return upper case to the letter if it had it before
                letterToChange = Character.toUpperCase(letterToChange);

            newString.append(letterToChange); // We build a new string using every shifted letter
        }

        return newString.toString();
    }
}
