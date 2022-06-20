package com.example.springrestcontroller.dto;

import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.model.Trainer;

public class SelectFilterHorseRespon {
    private Horse horse_id;
    private Trainer trainer_id;

    public Horse getHorse_id() {
        return horse_id;
    }

    public void setHorse_id(Horse horse_id) {
        this.horse_id = horse_id;
    }

    public Trainer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Trainer trainer_id) {
        this.trainer_id = trainer_id;
    }
}
