package com.yanzord.cloud.tema10.totalizerservice.exception;

public class TwitterInvalidUserException extends RuntimeException {
    public TwitterInvalidUserException() {
        super("Invalid twitter user.");
    }
}
