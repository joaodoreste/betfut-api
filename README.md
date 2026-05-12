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
- centralização de acesso via API Gateway.

A arquitetura foi construída utilizando o padrão de microservices, permitindo que cada serviço possua sua própria responsabilidade e seu próprio banco de dados.

---

# Arquitetura

O projeto utiliza:

- Eureka Server (Service Discovery)
- Spring Cloud Gateway (API Gateway)
- Microservices independentes
- PostgreSQL
- Comunicação distribuída

---

# Serviços

| Serviço | Porta | Responsabilidade | Banco |
|---|---|---|---|
| eureka-server | 8761 | Registro e descoberta de serviços | - |
| api-gateway | 8080 | Entrada única da aplicação | - |
| user-service | 8081 | Cadastro e gerenciamento de usuários | PostgreSQL |

---

# Discovery Server

O Eureka Server é responsável por registrar os microservices da arquitetura.

URL:

http://localhost:8761

Serviços registrados atualmente:

- API-GATEWAY
- USER-SERVICE

---

# API Gateway

O API Gateway centraliza todas as requisições externas da aplicação.

Atualmente, a seguinte rota está configurada:

| Rota externa | Serviço destino |
|---|---|
| `/api/users/**` | `user-service` |

Exemplo:

POST http://localhost:8080/api/users

O Gateway encaminha automaticamente a requisição para o `user-service`.