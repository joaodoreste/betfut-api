package com.betfut.bettingservice.repository;

import com.betfut.bettingservice.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository responsável pelas operações da tabela bets
public interface BetRepository extends JpaRepository<Bet, Long> {

    // Busca apostas pelo ID do usuário
    List<Bet> findByUserId(Long userId);
}