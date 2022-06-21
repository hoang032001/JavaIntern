package com.example.springrestcontroller.repository;

import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HorseRepository extends JpaRepository<Horse, Integer> {
//    @Query("select h from Horse h inner join HorseAccount ha on ha.horse = h " +
//            "inner join Trainer t on t.account = ha.account " +
//            "where (case when ?1 > 0 then YEAR(h.foaled)=?1 and t.id=?2 else t.id=?2 end)")
//    List<Horse> findHorseYearAndTrainerId(int year, int id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Horse set name= :name where id = :id")
    Integer updateHorse(@Param(value = "id") int id, @Param(value = "name") String name);

    @Modifying
    @Transactional
    @Query("delete from Horse  h where h.id = ?1")
    Integer deleteHorseWithId(int id);

    @Modifying
    @Transactional
    @Query("delete from HorseAccount ha where ha.horse.id = ?1")
    Integer deleteHorseAccountWithId(int id);
}
