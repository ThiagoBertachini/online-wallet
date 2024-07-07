package com.tbemerencio.online_wallet.controllers.dto;

import com.tbemerencio.online_wallet.entities.Wallet;
import com.tbemerencio.online_wallet.entities.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDTO(
        @NotBlank String fullName,
        @NotBlank String cpfCnpj,
        @NotBlank String email,
        @NotBlank String password,
        @NotNull WalletType.WalletTypeEnum walletTypeEnum) {

    public Wallet toWallet(){
        return new Wallet(fullName, cpfCnpj, email, password, walletTypeEnum.getWalletType());
    }
}
