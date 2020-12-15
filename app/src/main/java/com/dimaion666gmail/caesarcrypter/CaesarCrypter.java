package com.dimaion666gmail.caesarcrypter;

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
        // Define encrypting or decrypting letter shift
        if (encryptOrDecrypt == 0) {
            
        }
        // Translate text
        return null;
    }
}
