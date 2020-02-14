package com.yanzord.cloud.tema10.totalizerservice.controller;

import com.yanzord.cloud.tema10.totalizerservice.exception.GithubAccessException;
import com.yanzord.cloud.tema10.totalizerservice.exception.GithubInvalidUserException;
import com.yanzord.cloud.tema10.totalizerservice.exception.TwitterAccessException;
import com.yanzord.cloud.tema10.totalizerservice.exception.TwitterInvalidUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { GithubInvalidUserException.class, TwitterInvalidUserException.class })
    public void handleNotFoundException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(value = { GithubAccessException.class, TwitterAccessException.class })
    public void handleBadRequestException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
