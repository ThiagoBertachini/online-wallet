package com.tbemerencio.online_wallet.integrations.authorization.client;

import com.tbemerencio.online_wallet.integrations.authorization.response.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorizationClient", url = "${client.authorization-service.url}")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDTO> isAuthorized();
}
