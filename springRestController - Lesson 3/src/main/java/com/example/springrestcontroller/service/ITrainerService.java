package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;
import org.springframework.http.ResponseEntity;

public interface ITrainerService {
    ResponseEntity updateTrainer(UpdateTrainerRequest request);

    ResponseEntity deleteTrainer(Integer id);
}
