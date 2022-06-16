package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.HorseAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorseAccountRepository extends JpaRepository<HorseAccount, Integer> {
}
