package com.tbemerencio.online_wallet.controllers.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDTO(
        @DecimalMin("0.01") @NotNull BigDecimal value,
        @NotNull long payer,
        @NotNull long payee) {
}
