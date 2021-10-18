package com.example.MobRecharge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MobRecharge.entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

     BankAccount findById(Integer id);
}
