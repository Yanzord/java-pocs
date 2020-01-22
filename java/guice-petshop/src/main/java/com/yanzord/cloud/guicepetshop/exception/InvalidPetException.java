package com.yanzord.cloud.guicepetshop.exception;

public class InvalidPetException extends Exception {
    public InvalidPetException() {
        super("Pet inserted doesn't exist.");
    }
}
