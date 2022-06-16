package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;

public interface ITrainerService {
    String updateTrainer(UpdateTrainerRequest request);

    String deleteTrainer(Integer id);
}
