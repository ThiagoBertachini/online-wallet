package com.tbemerencio.online_wallet.configs;

import com.tbemerencio.online_wallet.entities.WalletType;
import com.tbemerencio.online_wallet.repositories.WalletTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final WalletTypeRepository walletTypeRepository;

    public DataLoader(WalletTypeRepository walletTypeRepository) {
        this.walletTypeRepository = walletTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(WalletType.WalletTypeEnum.values()).forEach(
                walletTypeEnum -> walletTypeRepository.save(walletTypeEnum.getWalletType())
        );
    }
}
