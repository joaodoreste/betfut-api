package com.betfut.matchservice.repository;

import com.betfut.matchservice.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository responsável pelas operações da tabela football_matches
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
}