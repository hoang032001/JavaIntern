package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;
import com.example.springrestcontroller.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImp implements ITrainerService{
    @Autowired
    private TrainerRepository trainerRepository;

    //update trainer name by id
    @Override
    public ResponseEntity updateTrainer(UpdateTrainerRequest request){
        try {
            //check null
            if (request.getId() > 0 && request.getNewName() != null && !request.getNewName().isEmpty()){
                //update success will return 1
                if (trainerRepository.updateTrainerById(request.getNewName(), request.getId()) == 1) {
                    return new ResponseEntity<>("Update Successful!", HttpStatus.OK);
                }
                return new ResponseEntity<>("Can't update: Query return 0", HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong ID or Trainer name", HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //delete trainer by id
    @Override
    public ResponseEntity deleteTrainer(Integer id){
        try {
            if(id != null && id > 0) {
                //delete success will return 1
                if(trainerRepository.deleteTrainerByID(id) == 1){
                    return new ResponseEntity<>("Delete Success!", HttpStatus.OK);
                }
                return new ResponseEntity<>("Can't Delete: Query return 0", HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong ID", HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
}
