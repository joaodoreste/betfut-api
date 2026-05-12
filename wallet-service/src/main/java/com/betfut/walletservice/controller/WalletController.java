package com.betfut.walletservice.controller;

import com.betfut.walletservice.entity.Wallet;
import com.betfut.walletservice.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    // Repository usado para acessar o MongoDB
    private final WalletRepository repository;

    // Cria uma carteira para um usuário
    @PostMapping
    public Wallet create(@RequestBody Wallet wallet) {

        // Se o saldo vier vazio, começa com zero
        if (wallet.getBalance() == null) {
            wallet.setBalance(BigDecimal.ZERO);
        }

        // Salva a carteira no MongoDB
        return repository.save(wallet);
    }

    // Busca a carteira pelo ID do usuário
    @GetMapping("/user/{userId}")
    public Wallet findByUserId(@PathVariable Long userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada para o usuário: " + userId));
    }

    // Faz um depósito fictício na carteira
    @PostMapping("/user/{userId}/deposit")
    public Wallet deposit(@PathVariable Long userId, @RequestParam BigDecimal amount) {
        Wallet wallet = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada para o usuário: " + userId));

        wallet.setBalance(wallet.getBalance().add(amount));

        return repository.save(wallet);
    }

    // Debita saldo da carteira
    @PostMapping("/user/{userId}/debit")
    public Wallet debit(@PathVariable Long userId, @RequestParam BigDecimal amount) {
        Wallet wallet = repository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carteira não encontrada para o usuário: " + userId));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        return repository.save(wallet);
    }
}