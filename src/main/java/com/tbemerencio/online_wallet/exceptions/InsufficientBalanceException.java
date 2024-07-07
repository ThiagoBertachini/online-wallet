package com.tbemerencio.online_wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends WalletException {

    private BigDecimal value;

    public InsufficientBalanceException(BigDecimal value){
        this.value = value;
    }
    @Override
    public ProblemDetail toProblemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Not enough balance. " + value);
        return pb;
    }
}
