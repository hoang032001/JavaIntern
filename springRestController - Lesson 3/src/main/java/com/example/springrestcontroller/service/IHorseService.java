package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateHorseRequest;
import com.example.springrestcontroller.model.Horse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public interface IHorseService {
    ResponseEntity findAllHorse();

    ResponseEntity filterListHorse(HttpServletRequest request);

    ResponseEntity createHorse(HttpServletRequest request);

    ResponseEntity updateHorse(UpdateHorseRequest request);

    ResponseEntity deleteHorse(int id);
}
