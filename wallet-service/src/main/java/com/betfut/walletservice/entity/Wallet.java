package com.betfut.walletservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

// Define que essa classe será salva como documento no MongoDB
@Document(collection = "wallets")
@Data
public class Wallet {

    // ID do documento no MongoDB
    @Id
    private String id;

    // ID do usuário dono da carteira
    private Long userId;

    // Saldo atual da carteira
    private BigDecimal balance;
}