package com.gorkem.soccercase.controller;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.service.TeamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/teams")
public class TeamsController {

    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public void retrieveTeams() {
//
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public void create() {
//
//    }
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
//    public void retrieveTeam() {
//
//    }
}
