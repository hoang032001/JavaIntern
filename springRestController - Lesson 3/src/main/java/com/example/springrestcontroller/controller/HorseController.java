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
        return iHorseService.findAllHorse();
    }

    //find horse have trainer_id and year
    //Post Man: GET -> http://localhost:8080/horses/filter?trainer_id=?&year=?
    @GetMapping("/filter")
    public ResponseEntity filterHorse(HttpServletRequest request){
        return iHorseService.filterListHorse(request);
    }

    //create a horse with ?name
    //Post Man: POST -> http://localhost:8080/horses/save?name=?
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity createHorse(HttpServletRequest request){
        return iHorseService.createHorse(request);
    }

    //update horse name by id
    //Post Man: PUT -> http://localhost:8080/horses/update/
    //JSON: { "id":? , "newName":"?" }
    @PutMapping("/update")
    public ResponseEntity updateHorse(@RequestBody UpdateHorseRequest request){
        return iHorseService.updateHorse(request);
    }

    //delete horse by id
    //Post Man: DELETE -> http://localhost:8080/horses/delete/?
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHorse(@PathVariable("id") Integer id){
        return iHorseService.deleteHorse(id);
    }
}
