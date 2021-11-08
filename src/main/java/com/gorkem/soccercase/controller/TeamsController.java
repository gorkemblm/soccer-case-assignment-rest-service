package com.gorkem.soccercase.controller;

import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import com.gorkem.soccercase.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
public class TeamsController {

    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teams")
    public ResponseEntity<Object> retrieveTeams() {
        return ResponseEntity.ok(this.teamService.retrieveTeams());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create-team")
    public ResponseEntity<Object> createTeam(@RequestBody TeamCreateDto teamCreateDto) {
        TeamRetrieveDto savedTeam = this.teamService.createTeam(teamCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update-team/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") String id, @RequestBody TeamUpdateDto teamUpdateDto) {
        return ResponseEntity.ok(this.teamService.updateTeam(id, teamUpdateDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete-team/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable(name = "id") String id) {
        var isDeletedTeam = this.teamService.deleteTeam(id);

        if (!isDeletedTeam) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/retrieve-team/{id}")
    public ResponseEntity<Object> retrieveTeam(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.teamService.retrieveTeam(id));
    }
}
