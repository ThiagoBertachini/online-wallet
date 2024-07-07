package com.tbemerencio.online_wallet.controllers;

import com.tbemerencio.online_wallet.exceptions.WalletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(WalletException.class)
    public ProblemDetail handleWalletException(WalletException e){
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        List<InvalidParam> fieldErrors = e.getFieldErrors().stream().map(fieldError ->
                new InvalidParam(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Request parameters not valid.");
        pd.setProperty("invalid-params", fieldErrors);

        return pd;
    }

    private record InvalidParam(String name, String reason){}
}
