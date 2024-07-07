package com.tbemerencio.online_wallet.integrations.authorization.service;

import com.tbemerencio.online_wallet.controllers.dto.TransferDTO;
import com.tbemerencio.online_wallet.exceptions.WalletException;
import com.tbemerencio.online_wallet.integrations.authorization.client.AuthorizationClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient){
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer){
        var response = authorizationClient.isAuthorized();
        if (response.getStatusCode().isError()){
            throw new WalletException();
        }
        return Objects.requireNonNull(response.getBody()).authorized();
    }
}
