package com.betfut.bettingservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
@Data
public class Bet {

    // ID da aposta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID do usuário que fez a aposta
    private Long userId;

    // ID da partida apostada
    private Long matchId;

    // Escolha da aposta: HOME, DRAW ou AWAY
    private String prediction;

    // Valor apostado
    private BigDecimal amount;

    // Odd usada na aposta
    private BigDecimal odd;

    // Possível retorno se a aposta vencer
    private BigDecimal possibleReturn;

    // Status da aposta: CREATED, WON ou LOST
    private String status;

    // Data/hora em que a aposta foi criada
    private LocalDateTime createdAt;
}