package com.example.springrestcontroller.controller;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;
import com.example.springrestcontroller.model.Trainer;
import com.example.springrestcontroller.repository.TrainerRepository;
import com.example.springrestcontroller.service.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private ITrainerService iTrainerService;

    //Post Man: PUT -> http://localhost:8080/trainers/update
    //JSON: { "id":? , "newName":"?new_horse_name?" }
    @PutMapping("/update")
    public ResponseEntity updateTrainer(@RequestBody UpdateTrainerRequest request){
        try{
            return new ResponseEntity<>(iTrainerService.updateTrainer(request), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //Post Man: DELETE -> http://localhost:8080/trainers/delete/?
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainer(@PathVariable("id") Integer id){
        try{
                return new ResponseEntity<>(iTrainerService.deleteTrainer(id), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }


}
