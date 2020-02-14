package com.yanzord.cloud.tema10.totalizerservice.exception;

public class GithubAccessException extends RuntimeException {
    public GithubAccessException() {
        super("A problem occured while trying to connect to the github service.");
    }
}
