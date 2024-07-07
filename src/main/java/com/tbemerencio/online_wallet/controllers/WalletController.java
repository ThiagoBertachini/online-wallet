package com.tbemerencio.online_wallet.controllers;

import com.tbemerencio.online_wallet.controllers.dto.CreateWalletDTO;
import com.tbemerencio.online_wallet.entities.Wallet;
import com.tbemerencio.online_wallet.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDTO createWalletDTO){
        Wallet walletCreated = walletService.createWallet(createWalletDTO);
        return ResponseEntity.ok(walletCreated);
    }
}
