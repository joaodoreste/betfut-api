package com.betfut.bettingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

// Cliente usado para chamar o wallet-service pelo nome registrado no Eureka
@FeignClient(name = "wallet-service")
public interface WalletClient {

    // Chama o endpoint de débito da carteira
    @PostMapping("/wallets/user/{userId}/debit")
    void debit(@PathVariable Long userId, @RequestParam BigDecimal amount);
}