package com.example.springrestcontroller.controller;

import com.example.springrestcontroller.dto.UpdateHorseRequest;
import com.example.springrestcontroller.model.Horse;
import com.example.springrestcontroller.service.IHorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/horses")
public class HorseController {
    @Autowired
    IHorseService iHorseService;

    //Post Man: GET -> http://localhost:8080/horses/all
    @GetMapping("/all")
    public ResponseEntity listHorse(){
        try{
            List<Horse> list = iHorseService.findAllHorse();
            if(list!=null){
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No List");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //find horse have trainer_id and year
    //Post Man: GET -> http://localhost:8080/horses/filter?trainer_id=?&year=?
    @GetMapping("/filter")
    public ResponseEntity filterHorse(HttpServletRequest request){
        try {
            //get data from service
            List<Horse> list = iHorseService.filterListHorse(request);
            //check null
            if (list != null) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No Horse");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //create a horse with ?name
    //Post Man: POST -> http://localhost:8080/horses/save?name=?
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity createHorse(HttpServletRequest request){
        try {
            //check boolean with some conditions in service
            if(iHorseService.createHorse(request)) {
                return new ResponseEntity<>("Horse has added!", HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Name is null");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //update horse name by id
    //Post Man: PUT -> http://localhost:8080/horses/update/
    //JSON: { "id":? , "newName":"?" }
    @PutMapping("/update")
    public ResponseEntity updateHorse(@RequestBody UpdateHorseRequest request){
        try{
            //update horse
            return new ResponseEntity<>(iHorseService.updateHorse(request), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }

    //delete horse by id
    //Post Man: DELETE -> http://localhost:8080/horses/delete/?
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHorse(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(iHorseService.deleteHorse(id), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error Exception");
        }
    }
}
