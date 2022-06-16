package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Trainer t where t.id=?1")
    Integer deleteTrainerByID(Integer id);

    @Modifying
    @Transactional
    @Query("update Trainer t set t.name=?1 where t.id=?2")
    Integer updateTrainerById(String name, Integer id);
}
