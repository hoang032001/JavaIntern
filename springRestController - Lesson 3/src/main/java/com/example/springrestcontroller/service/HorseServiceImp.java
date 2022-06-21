package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.SelectFilterHorseRespon;
import com.example.springrestcontroller.dto.UpdateHorseRequest;
import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.model.Trainer;
import com.example.springrestcontroller.repository.HorseRepository;
import com.example.springrestcontroller.repository.HorseRepositoryEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorseServiceImp implements IHorseService{
    @Autowired
    HorseRepository horseRepository;
    @Autowired
    HorseRepositoryEntityManager horseRepositoryEntityManager;

    //find all horse and return a list
    @Override
    public ResponseEntity findAllHorse(){
        try{
            List<Horse> list = horseRepository.findAll();
            if(!list.isEmpty()){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No List");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //find horse while filter trainer id and year
    @Override
    public ResponseEntity filterListHorse(HttpServletRequest request){
//        try {
            //get request/parameter
            int id = Integer.parseInt(request.getParameter("trainer_id"));
            int year = Integer.parseInt(request.getParameter("year"));
            //check > 0
            if (id > 0) {
                //call query
                List<Horse> list = horseRepositoryEntityManager.findFilter(year, id);
                if (list != null && !list.isEmpty()) {
                    return new ResponseEntity<>(list, HttpStatus.OK);
                }
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No Horse!");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No trainer id or year!");
//        }catch(Exception e) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
//        }
    }

    //create horse by ?name
    @Override
    public ResponseEntity createHorse(HttpServletRequest request){
        try {
            //get request
            String name = request.getParameter("name");
            //check null
            if (name != null) {
                //create local time for horse.foaled
                SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss.");
                Horse horse = new Horse();
                horse.setName(name);
                //set time by new Date() of the local
                horse.setFoaled(new Timestamp(DATE_TIME_FORMAT.parse(DATE_TIME_FORMAT.format(new Date())).getTime()));
                horseRepository.save(horse);
                return new ResponseEntity<>("Horse has added!", HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Name is null");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //update horse name by id
    @Override
    public ResponseEntity updateHorse(UpdateHorseRequest request){
        try {
            //check null
            if(request.getId() > 0 && request.getNewName() != null && !request.getNewName().isEmpty()) {
                //update horse, update success will return 1
                if(horseRepository.updateHorse(request.getId(), request.getNewName()) == 1) {
                    return new ResponseEntity<>("Success Updated", HttpStatus.OK);
                }
                return new ResponseEntity<>("Query Error: Return 0", HttpStatus.OK);
            }
            return new ResponseEntity<>("No ID or Horse Name", HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
    //delete horse by id
    @Override
    public ResponseEntity deleteHorse(int id){
        try {
            if(id > 0) {
                //delete the foreign key first (horse_account)
                if(horseRepository.deleteHorseAccountWithId(id) == 1) {
                    //delete horse
                    if(horseRepository.deleteHorseWithId(id) == 1){
                        return new ResponseEntity<>("Delete Successful", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Can't Delete Horse after deleted Horse_Account", HttpStatus.OK);
                }
                return new ResponseEntity<>("Can't delete horse_account", HttpStatus.OK);
            }
            return new ResponseEntity<>("Wrong ID", HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
 }
