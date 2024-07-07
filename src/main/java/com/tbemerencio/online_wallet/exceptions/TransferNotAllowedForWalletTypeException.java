package com.tbemerencio.online_wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletTypeException extends WalletException {

    @Override
    public ProblemDetail toProblemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Transfer not allowed for this wallet type");
        return pb;
    }
}
