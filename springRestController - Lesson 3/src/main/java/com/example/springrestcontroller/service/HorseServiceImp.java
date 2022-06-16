package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateHorseRequest;
import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HorseServiceImp implements IHorseService{
    @Autowired
    HorseRepository horseRepository;

    //find all horse and return a list
    @Override
    public List<Horse> findAllHorse(){
        return horseRepository.findAll();
    }

    //find horse while filter trainer id and year
    @Override
    public List<Horse> filterListHorse(HttpServletRequest request){
        try {
            //get request/parameter
            int id = Integer.parseInt(request.getParameter("trainer_id"));
            int year = Integer.parseInt(request.getParameter("year"));
            //check > 0
            if (id > 0 && year > 0) {
                //call query
                List<Horse> list = horseRepository.findHorseYearAndTrainerId(year, id);
                if (list != null) {
                    return list;
                }
            }
            return null;
        }catch(Exception e) {
            return null;
        }
    }

    //create horse by ?name
    @Override
    public boolean createHorse(HttpServletRequest request){
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
                return true;
            }
            return false;
        }catch (Exception e) {
            return false;
        }
    }

    //update horse name by id
    @Override
    public String updateHorse(UpdateHorseRequest request){
        try {
            if(request.getId() > 0 && request.getNewName() != null && !request.getNewName().isEmpty()) {
                if(horseRepository.updateHorse(request.getId(), request.getNewName()) == 1) {
                    return "Success!";
                }
                return "Error Querying: Return 0";
            }
            return "No ID or Horse Name";
        }catch (Exception e){
            return "Error Exception";
        }
    }
    //delete horse by id
    @Override
    public String deleteHorse(int id){
        try {
            if(id > 0) {
                if(horseRepository.deleteHorseAccountWithId(id) == 1) {
                    if(horseRepository.deleteHorseWithId(id) == 1){
                        return "Delete Success!";
                    }
                    return "Can't Delete Horse after deleted Horse_Account";
                }
                return "Error Delete: Return 0";
            }
            return "Wrong ID";
        }catch (Exception e){
            return "Error Exception";
        }
    }
 }
