package com.tbemerencio.online_wallet.services;

import com.tbemerencio.online_wallet.controllers.dto.TransferDTO;
import com.tbemerencio.online_wallet.entities.Transfer;
import com.tbemerencio.online_wallet.entities.Wallet;
import com.tbemerencio.online_wallet.exceptions.InsufficientBalanceException;
import com.tbemerencio.online_wallet.exceptions.TransferNotAllowedForWalletTypeException;
import com.tbemerencio.online_wallet.exceptions.TransferNotAuthorizedException;
import com.tbemerencio.online_wallet.exceptions.WalletNotFoundException;
import com.tbemerencio.online_wallet.integrations.authorization.service.AuthorizationService;
import com.tbemerencio.online_wallet.integrations.notification.service.NotificationService;
import com.tbemerencio.online_wallet.repositories.TransferRepository;
import com.tbemerencio.online_wallet.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    public TransferService(WalletRepository walletRepository, TransferRepository transferRepository, AuthorizationService authorizationService, NotificationService notificationService) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional // garante que todas execuções ocorrem em uma transação, qualquer erro desfaz todas execuções
    public Transfer transfer(TransferDTO transferDTO) {
        var sender = walletRepository.findById(transferDTO.payer()).orElseThrow(
                () -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee()).orElseThrow(
                () -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        // cria nova thread e não impacta fluxo se gerar erro
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEqualOrGreatherThan(transferDTO.value())){
            throw new InsufficientBalanceException(transferDTO.value());
        }

        if (!authorizationService.isAuthorized(transferDTO)){
            throw new TransferNotAuthorizedException();
        }
    }
}
