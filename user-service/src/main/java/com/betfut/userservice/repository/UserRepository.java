package com.betfut.userservice.repository;

import com.betfut.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository responsável pelas operações no banco
public interface UserRepository extends JpaRepository<User, Long> {

}