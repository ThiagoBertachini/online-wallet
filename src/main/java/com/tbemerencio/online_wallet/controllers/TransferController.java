package com.tbemerencio.online_wallet.controllers;

import com.tbemerencio.online_wallet.controllers.dto.TransferDTO;
import com.tbemerencio.online_wallet.entities.Transfer;
import com.tbemerencio.online_wallet.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO transferDTO){
        var response = transferService.transfer(transferDTO);
        return ResponseEntity.ok(response);
    }
}
