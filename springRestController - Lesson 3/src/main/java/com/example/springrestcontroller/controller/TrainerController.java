package com.example.springrestcontroller.controller;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;
import com.example.springrestcontroller.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;


@RestController
@RequestMapping("/trainers")
@Validated
public class TrainerController {
    @Autowired
    private ITrainerService iTrainerService;

    //Post Man: PUT -> http://localhost:8080/trainers/update
    //JSON: { "id":? , "newName":"?new_horse_name?" }
    @PutMapping("/update")
    public ResponseEntity updateTrainer(@RequestBody UpdateTrainerRequest request){
        return iTrainerService.updateTrainer(request);
    }

    //Post Man: DELETE -> http://localhost:8080/trainers/delete/?
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainer(@NotEmpty @PathVariable("id") Integer id){
        return iTrainerService.deleteTrainer(id);
    }


}
