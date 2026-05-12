package com.betfut.matchservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "football_matches")
@Data
public class FootballMatch {

    // Chave primária da partida
    @Id

    // ID gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do time mandante
    private String homeTeam;

    // Nome do time visitante
    private String awayTeam;

    // Data e hora da partida
    private LocalDateTime matchDate;

    // Status da partida: SCHEDULED, FINISHED, CANCELLED
    private String status;

    // Placar do time mandante
    private Integer homeScore;

    // Placar do time visitante
    private Integer awayScore;

    // Odd para vitória do time mandante
    private BigDecimal homeOdd;

    // Odd para empate
    private BigDecimal drawOdd;

    // Odd para vitória do time visitante
    private BigDecimal awayOdd;
}