package com.yanzord.cloud.tema10.totalizerservice.exception;

public class TwitterAccessException extends RuntimeException {
    public TwitterAccessException() {
        super("A problem occured while trying to connect to the twitter service.");
    }
}
