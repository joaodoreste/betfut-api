# BetFut API

## Integrantes

- João Gabriel Raja Gabaglia Doreste
- Nome 2
- Nome 3
- Nome 4
- Nome 5

---

## Descrição do Projeto

O projeto BetFut API simula uma plataforma fictícia de apostas esportivas utilizando arquitetura de microservices.

O sistema permite:

- cadastro de usuários;
- gerenciamento de partidas;
- gerenciamento de carteira/saldo;
- realização de apostas;
- comunicação distribuída entre serviços;
- tolerância a falhas.

---

## Arquitetura

O projeto foi desenvolvido utilizando:

- Eureka Server (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- Microservices independentes
- PostgreSQL
- MongoDB
- OpenFeign
- Resilience4j

Arquitetura da aplicação:

```text
Cliente
   ↓
API Gateway
   ↓
Microservices
   ↓
PostgreSQL / MongoDB
```

---

## Microservices

| Serviço | Responsabilidade | Porta | Banco |
|---|---|---|---|
| eureka-server | Registro e descoberta de serviços | 8761 | - |
| api-gateway | Entrada única da aplicação | 8080 | - |
| user-service | Cadastro de usuários | 8081 | PostgreSQL |
| match-service | Cadastro de partidas | 8082 | PostgreSQL |
| wallet-service | Gerenciamento de carteira/saldo | 8083 | PostgreSQL |
| betting-service | Gerenciamento de apostas | 8084 | MongoDB |

---

## Como executar

Ordem recomendada:

1. eureka-server
2. api-gateway
3. user-service
4. match-service
5. wallet-service
6. betting-service

Executar cada serviço utilizando:

```bash
mvn spring-boot:run
```

---

## Discovery Server

URL:

```text
http://localhost:8761
```

Serviços registrados:

- API-GATEWAY
- USER-SERVICE
- MATCH-SERVICE
- WALLET-SERVICE
- BETTING-SERVICE

---

## API Gateway

Rotas configuradas:

| Rota | Serviço |
|---|---|
| `/api/users/**` | user-service |
| `/api/matches/**` | match-service |
| `/api/wallets/**` | wallet-service |
| `/api/bets/**` | betting-service |

---

## Comunicação entre Microservices

Fluxo principal:

```text
betting-service → wallet-service
```

Ao criar uma aposta:

1. betting-service recebe a requisição;
2. wallet-service debita saldo;
3. aposta é salva no MongoDB.

---

## Resiliência

O projeto utiliza:

- Retry
- Circuit Breaker
- Fallback

Caso o `wallet-service` fique indisponível, a API retorna:

```text
503 Service Unavailable
```

---

## Exemplos de requisições

### Criar usuário

```http
POST http://localhost:8080/api/users
```

Body:

```json
{
  "name": "João",
  "email": "joao@email.com"
}
```

---

### Criar partida

```http
POST http://localhost:8080/api/matches
```

Body:

```json
{
  "homeTeam": "Flamengo",
  "awayTeam": "Palmeiras",
  "matchDate": "2026-05-20T21:30:00",
  "status": "SCHEDULED",
  "homeOdd": 1.90,
  "drawOdd": 3.20,
  "awayOdd": 2.40
}
```

---

### Adicionar saldo

```http
POST http://localhost:8080/api/wallets/user/1/deposit?amount=100
```

---

### Criar aposta

```http
POST http://localhost:8080/api/bets
```

Body:

```json
{
  "userId": 1,
  "matchId": 1,
  "prediction": "HOME",
  "amount": 20,
  "odd": 1.90
}
```