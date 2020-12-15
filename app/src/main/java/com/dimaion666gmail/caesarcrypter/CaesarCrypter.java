package com.dimaion666gmail.caesarcrypter;

public class CaesarCrypter {
    // "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" "abcdefghijklmnopqrstuvwxyz"
    private char[][] alphabet = new char[2][];


    public CaesarCrypter() {
        alphabet[0] = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();
        alphabet[1] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    public String translate(int encryptOrDecrypt, String letterShift, String text) {
        return null;
    }
}
