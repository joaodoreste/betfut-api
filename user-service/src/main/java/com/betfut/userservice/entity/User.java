package com.betfut.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    // Define o campo como chave primária
    @Id

    // Faz o ID ser gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário
    private String name;

    // Email do usuário
    private String email;
}