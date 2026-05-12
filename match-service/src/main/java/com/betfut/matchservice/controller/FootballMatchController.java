package com.betfut.matchservice.controller;

import com.betfut.matchservice.entity.FootballMatch;
import com.betfut.matchservice.repository.FootballMatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
public class FootballMatchController {

    // Injeta o repository
    private final FootballMatchRepository repository;

    // Endpoint para cadastrar partida
    @PostMapping
    public FootballMatch create(@RequestBody FootballMatch match) {

        // Salva no banco
        return repository.save(match);
    }

    // Endpoint para listar todas as partidas
    @GetMapping
    public List<FootballMatch> findAll() {

        // Busca todas as partidas
        return repository.findAll();
    }
}