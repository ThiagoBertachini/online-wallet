package com.tbemerencio.online_wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataAlreadyExistsException extends WalletException{

    private String detail;

    public WalletDataAlreadyExistsException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Wallet already exists");
        pd.setDetail(detail);
        return pd;
    }
}
