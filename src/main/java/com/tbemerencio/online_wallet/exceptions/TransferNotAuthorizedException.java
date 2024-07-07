package com.tbemerencio.online_wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends WalletException {

    @Override
    public ProblemDetail toProblemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Transfer not authorized.");
        return pb;
    }
}
