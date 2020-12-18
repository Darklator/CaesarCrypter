package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {

    private LanguageHandler[] languageHandlers = new LanguageHandler[2];

    public CaesarCrypter() {
        languageHandlers[0] = new StandartLanguageHandler(97, 122);
        languageHandlers[1] = new RussianLanguageHandler();
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
            char letterToBeChanged = text.charAt(i);

            for (int j = 0; j < languageHandlers.length; j++) {
                if (languageHandlers[j].doesTheLetterExistHere(letterToBeChanged)) {
                    letterToBeChanged = languageHandlers[j].shiftLetter(integerLetterShifts[letterShiftIndex], letterToBeChanged);

                    letterShiftIndex++;
                    if (letterShiftIndex >= integerLetterShifts.length) letterShiftIndex = 0;

                    break;
                }
            }
            newString.append(letterToBeChanged);
        }
        return newString.toString();
    }
}
