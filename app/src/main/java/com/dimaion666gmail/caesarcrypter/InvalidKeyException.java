package com.dimaion666gmail.caesarcrypter;

public class InvalidKeyException extends Exception {
    public InvalidKeyException(String key) {
        super("Your key contains invalid characters!");
    }
}
