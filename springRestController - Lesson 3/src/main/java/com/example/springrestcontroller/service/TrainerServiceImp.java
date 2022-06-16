package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateTrainerRequest;
import com.example.springrestcontroller.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImp implements ITrainerService{
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public String updateTrainer(UpdateTrainerRequest request){
        try {
            if (request.getId() > 0 && request.getNewName() != null && !request.getNewName().isEmpty()){
                if (trainerRepository.updateTrainerById(request.getNewName(), request.getId()) == 1) {
                    return "Update Successful!";
                }
                return "Can't Update: Query return 0";
            }
            return "Wrong ID or Trainer Name";
        }catch (Exception e){
            return "Error Exception";
        }
    }

    @Override
    public String deleteTrainer(Integer id){
        try {
            if(id != null && id > 0) {
                if(trainerRepository.deleteTrainerByID(id) == 1){
                    return "Delete Success!";
                }
                return "Can't Delete: Query return 0";
            }
            return "Wrong ID";
        }catch (Exception e){
            return "Error Exception";
        }
    }
}
