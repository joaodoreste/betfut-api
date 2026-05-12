package com.betfut.bettingservice.service;

import com.betfut.bettingservice.client.WalletClient;
import com.betfut.bettingservice.entity.Bet;
import com.betfut.bettingservice.repository.BetRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BetService {

    // Repository da aposta
    private final BetRepository betRepository;

    // Cliente responsável por chamar o wallet-service
    private final WalletClient walletClient;

    // Retry:
    // tenta novamente caso a chamada falhe
    @Retry(name = "walletService")

    // Circuit Breaker:
    // evita múltiplas chamadas quando o serviço está falhando
    @CircuitBreaker(
            name = "walletService",
            fallbackMethod = "walletFallback"
    )

    // Método responsável por criar aposta
    public Bet create(Bet bet) {

        // Chama o wallet-service para debitar saldo da carteira
        walletClient.debit(
                bet.getUserId(),
                bet.getAmount()
        );

        // Calcula possível retorno da aposta
        bet.setPossibleReturn(
                bet.getAmount().multiply(bet.getOdd())
        );

        // Define status inicial
        bet.setStatus("CREATED");

        // Define data/hora da criação
        bet.setCreatedAt(LocalDateTime.now());

        // Salva a aposta no PostgreSQL
        return betRepository.save(bet);
    }

    // Método executado caso o wallet-service falhe
    public Bet walletFallback(Bet bet, Throwable exception) {
        throw new ResponseStatusException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Não foi possível criar a aposta. Wallet-service indisponível no momento."
        );
    }
}