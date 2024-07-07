package com.tbemerencio.online_wallet.services;

import com.tbemerencio.online_wallet.controllers.dto.CreateWalletDTO;
import com.tbemerencio.online_wallet.entities.Wallet;
import com.tbemerencio.online_wallet.exceptions.WalletDataAlreadyExistsException;
import com.tbemerencio.online_wallet.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletDTO createWalletDTO){
        Optional<Wallet> walletDB = walletRepository.findByCpfCnpjOrEmail(
                createWalletDTO.cpfCnpj(), createWalletDTO.email());
        if (walletDB.isPresent()) {
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
        }
        return walletRepository.save(createWalletDTO.toWallet());
    }
}
