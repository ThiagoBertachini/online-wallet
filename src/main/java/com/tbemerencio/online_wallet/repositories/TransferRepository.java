package com.tbemerencio.online_wallet.repositories;

import com.tbemerencio.online_wallet.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
}
