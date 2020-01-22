package com.yanzord.cloud.guicepetshop.exception;

public class InvalidJobException extends Exception {
    public InvalidJobException() {
        super("Job inserted doesn't exist.");
    }
}
