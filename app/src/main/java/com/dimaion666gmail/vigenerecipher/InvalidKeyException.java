package com.dimaion666gmail.vigenerecipher;

public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        super("This key contains invalid characters!");
    }
}