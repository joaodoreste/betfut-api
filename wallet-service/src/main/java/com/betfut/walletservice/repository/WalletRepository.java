package com.betfut.walletservice.repository;

import com.betfut.walletservice.entity.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

// Repository responsável pelas operações da coleção wallets no MongoDB
public interface WalletRepository extends MongoRepository<Wallet, String> {

    // Busca a carteira pelo ID do usuário
    Optional<Wallet> findByUserId(Long userId);
}