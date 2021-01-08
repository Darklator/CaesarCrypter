package com.dimaion666gmail.vigenerecipher;

/**
 * This class is custom exception.
 *
 * @version 1.0 08 Jan 2021
 * @author Dmitry Ionov
 */
public class InvalidKeyException extends Exception {
    public InvalidKeyException() {
        super("This key contains invalid characters!");
    }
}