package com.yanzord.cloud.tema10.totalizerservice.exception;

public class GithubInvalidUserException extends RuntimeException {
    public GithubInvalidUserException() {
        super("Invalid github user.");
    }
}
