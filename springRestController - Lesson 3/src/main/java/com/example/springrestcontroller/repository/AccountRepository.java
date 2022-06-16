package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("select a from Account a where a.username=?1")
    Account findAccountByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Account a set a.password=?1 where a.id=?2")
    void updatePasswordById(String password, Integer id);
}
