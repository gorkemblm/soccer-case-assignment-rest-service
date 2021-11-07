package com.gorkem.soccercase.controller;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.service.FootballerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(name = "/footballers")
public class FootballersController {

    private final FootballerService footballerService;

    public FootballersController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> retrieveFootballers() {
        return ResponseEntity.ok(this.footballerService.retrieveFootballers());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(FootballerCreateDto footballerCreateDto) {

    }
//
//    @RequestMapping(method = RequestMethod.PUT)
//    public void update() {
//
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void delete() {
//
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public void retrieveFootballer() {
//
//    }
}
