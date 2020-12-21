package com.dimaion666gmail.vigenerecipher;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        super("Your key contains invalid characters!");
    }
}
