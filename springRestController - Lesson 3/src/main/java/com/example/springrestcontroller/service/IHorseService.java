package com.example.springrestcontroller.service;

import com.example.springrestcontroller.dto.UpdateHorseRequest;
import com.example.springrestcontroller.model.Horse;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

public interface IHorseService {
    List<Horse> findAllHorse();

    List<Horse> filterListHorse(HttpServletRequest request);

    boolean createHorse(HttpServletRequest request);

    String updateHorse(UpdateHorseRequest request);

    String deleteHorse(int id);
}
