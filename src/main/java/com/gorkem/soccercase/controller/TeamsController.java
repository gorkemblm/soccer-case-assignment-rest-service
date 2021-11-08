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
@RequestMapping(name = "/teams")
public class TeamsController {

    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> retrieveTeams() {
        return ResponseEntity.ok(this.teamService.retrieveTeams());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody TeamCreateDto teamCreateDto) {
        TeamRetrieveDto savedTeam = this.teamService.createTeam(teamCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

        @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestParam(name = "id") String id, @RequestBody TeamUpdateDto teamUpdateDto) {
        return ResponseEntity.ok(this.teamService.updateTeam(id, teamUpdateDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") String id) {
        var isDeletedTeam = this.teamService.deleteTeam(id);

        if (!isDeletedTeam) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> retrieveTeam(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.teamService.retrieveTeam(id));
    }
}
