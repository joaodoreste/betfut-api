package com.betfut.bettingservice.controller;

import com.betfut.bettingservice.client.WalletClient;
import com.betfut.bettingservice.entity.Bet;
import com.betfut.bettingservice.repository.BetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bets")
@RequiredArgsConstructor
public class BetController {

    private final BetRepository betRepository;
    private final WalletClient walletClient;

    // Cria uma aposta
    @PostMapping
    public Bet create(@RequestBody Bet bet) {

        // Debita o valor apostado da carteira do usuário
        walletClient.debit(bet.getUserId(), bet.getAmount());

        // Calcula o possível retorno da aposta
        bet.setPossibleReturn(bet.getAmount().multiply(bet.getOdd()));

        // Define status inicial da aposta
        bet.setStatus("CREATED");

        // Define data de criação
        bet.setCreatedAt(LocalDateTime.now());

        // Salva a aposta no banco
        return betRepository.save(bet);
    }

    // Lista todas as apostas
    @GetMapping
    public List<Bet> findAll() {
        return betRepository.findAll();
    }

    // Lista apostas de um usuário
    @GetMapping("/user/{userId}")
    public List<Bet> findByUserId(@PathVariable Long userId) {
        return betRepository.findByUserId(userId);
    }
}