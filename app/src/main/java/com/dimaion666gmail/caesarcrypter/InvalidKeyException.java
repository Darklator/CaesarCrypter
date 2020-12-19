package com.dimaion666gmail.caesarcrypter;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        super("Your key contains invalid characters!");
    }
}
