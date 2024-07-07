package com.tbemerencio.online_wallet.repositories;

import com.tbemerencio.online_wallet.entities.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTypeRepository extends JpaRepository<WalletType, Long> {

}
