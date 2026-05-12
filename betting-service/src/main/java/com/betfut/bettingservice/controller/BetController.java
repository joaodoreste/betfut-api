package com.betfut.bettingservice.controller;

import com.betfut.bettingservice.entity.Bet;
import com.betfut.bettingservice.repository.BetRepository;
import com.betfut.bettingservice.service.BetService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bets")
@RequiredArgsConstructor
public class BetController {

    // Repository para consultas simples
    private final BetRepository betRepository;

    // Service responsável pela regra de negócio
    private final BetService betService;

    // Endpoint para criar aposta
    @PostMapping
    public Bet create(@RequestBody Bet bet) {

        // Chama o service que possui:
        // comunicação entre microservices
        // retry
        // circuit breaker
        return betService.create(bet);
    }

    // Endpoint para listar todas as apostas
    @GetMapping
    public List<Bet> findAll() {

        // Busca todas as apostas
        return betRepository.findAll();
    }

    // Endpoint para listar apostas de um usuário
    @GetMapping("/user/{userId}")
    public List<Bet> findByUserId(
            @PathVariable Long userId
    ) {

        // Busca apostas pelo ID do usuário
        return betRepository.findByUserId(userId);
    }
}