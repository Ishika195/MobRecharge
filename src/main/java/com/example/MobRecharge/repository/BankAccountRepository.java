package com.example.MobRecharge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MobRecharge.entity.BankAccount;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

	BankAccount findByAccountId(Integer id);

    BankAccount findByNumber(int number);
}
