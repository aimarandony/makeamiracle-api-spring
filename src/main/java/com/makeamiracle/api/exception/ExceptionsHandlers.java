package com.makeamiracle.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandlers {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFound.class})
    @ResponseBody
    public ErrorModel notFoundRequest(HttpServletRequest request, Exception exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer status = HttpStatus.NOT_FOUND.value();
        String error = HttpStatus.NOT_FOUND.getReasonPhrase();
        String message = exception.getMessage();
        String path = request.getRequestURI();

        return new ErrorModel(timestamp, status, error, message, path);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequest.class})
    @ResponseBody
    public ErrorModel badRequest(HttpServletRequest request, Exception exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer status = HttpStatus.BAD_REQUEST.value();
        String error = HttpStatus.BAD_REQUEST.getReasonPhrase();
        String message = exception.getMessage();
        String path = request.getRequestURI();

        return new ErrorModel(timestamp, status, error, message, path);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({InternalServerError.class})
    @ResponseBody
    public ErrorModel internalServerError(HttpServletRequest request, Exception exception) {
        LocalDateTime timestamp = LocalDateTime.now();
        Integer status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String message = exception.getMessage();
        String path = request.getRequestURI();

        return new ErrorModel(timestamp, status, error, message, path);
    }
}
