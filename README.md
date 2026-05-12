# BetFut API

Projeto acadêmico desenvolvido para a disciplina de arquitetura de microservices.

A aplicação simula uma plataforma fictícia de apostas em futebol, utilizando uma arquitetura distribuída baseada em Spring Boot e Spring Cloud.

---

# Objetivo do Projeto

O sistema tem como objetivo permitir:

- cadastro de usuários;
- gerenciamento de partidas;
- realização de apostas;
- gerenciamento de carteira/saldo;
- comunicação entre microservices;
- centralização de acesso via API Gateway;
- tolerância a falhas entre serviços.

A arquitetura foi construída utilizando o padrão de microservices, permitindo que cada serviço possua sua própria responsabilidade e seu próprio banco de dados.

---

# Arquitetura

O projeto utiliza:

- Eureka Server (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- Microservices independentes
- PostgreSQL
- MongoDB
- OpenFeign
- Resilience4j
- Comunicação distribuída

---

# Serviços

| Serviço | Porta | Responsabilidade | Banco |
|---|---|---|---|
| eureka-server | 8761 | Registro e descoberta de serviços | - |
| api-gateway | 8080 | Entrada única da aplicação | - |
| user-service | 8081 | Cadastro e gerenciamento de usuários | PostgreSQL |
| match-service | 8082 | Cadastro e gerenciamento de partidas | PostgreSQL |
| wallet-service | 8083 | Gerenciamento de saldo/carteira | PostgreSQL |
| betting-service | 8084 | Gerenciamento de apostas | MongoDB |

---

# Discovery Server

O Eureka Server é responsável por registrar os microservices da arquitetura.

URL:

```text
http://localhost:8761
```

Serviços registrados atualmente:

- API-GATEWAY
- USER-SERVICE
- MATCH-SERVICE
- WALLET-SERVICE
- BETTING-SERVICE

---

# API Gateway

O API Gateway centraliza todas as requisições externas da aplicação.

Atualmente, as seguintes rotas estão configuradas:

| Rota externa | Serviço destino |
|---|---|
| `/api/users/**` | `user-service` |
| `/api/matches/**` | `match-service` |
| `/api/wallets/**` | `wallet-service` |
| `/api/bets/**` | `betting-service` |