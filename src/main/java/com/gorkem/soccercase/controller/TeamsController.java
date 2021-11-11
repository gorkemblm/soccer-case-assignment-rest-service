package com.gorkem.soccercase.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import com.gorkem.soccercase.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamsController {

    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(headers = "API-VERSION=1")
    public ResponseEntity<Object> retrieveTeams() {
        List<TeamRetrieveDto> teamRetrieveDtos = this.teamService.retrieveTeams();

        if (teamRetrieveDtos.contains(null) || teamRetrieveDtos.contains("")) {
            return ResponseEntity.noContent().build();
        }

        SimpleBeanPropertyFilter propertyFilterForTeams = SimpleBeanPropertyFilter
                .filterOutAllExcept("name");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveTeamFilter", propertyFilterForTeams);

        MappingJacksonValue mapper = new MappingJacksonValue(teamRetrieveDtos);
        mapper.setFilters(filterProvider);

        return ResponseEntity.ok(mapper);
    }

    @PostMapping(headers = "API-VERSION=1")
    public ResponseEntity<Object> createTeam(@Valid @RequestBody TeamCreateDto teamCreateDto) {
        TeamRetrieveDto savedTeam = this.teamService.createTeam(teamCreateDto);

        if (savedTeam == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", headers = "API-VERSION=1")
    public ResponseEntity<Object> update(@NotBlank(message = Message.TEAM_ID_NOT_BLANK) @PathVariable(name = "id") String id,
                                         @Valid @RequestBody TeamUpdateDto teamUpdateDto) {

        TeamRetrieveDto updatedTeam = this.teamService.updateTeam(id, teamUpdateDto);

        if (updatedTeam == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping(value = "/{id}", headers = "API-VERSION=1")
    public ResponseEntity<Object> deleteTeam(@NotBlank(message = Message.TEAM_ID_NOT_BLANK) @PathVariable(name = "id") String id) {
        var isDeletedTeam = this.teamService.deleteTeam(id);

        if (isDeletedTeam) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/{id}", headers = "API-VERSION=1")
    public ResponseEntity<Object> retrieveTeam(@NotBlank(message = Message.TEAM_ID_NOT_BLANK) @PathVariable(name = "id") String id) {
        TeamRetrieveDto teamRetrieveDto = this.teamService.retrieveTeam(id);

        if (teamRetrieveDto == null) {
            return ResponseEntity.noContent().build();
        }

        SimpleBeanPropertyFilter propertyFilterForTeams = SimpleBeanPropertyFilter
                .filterOutAllExcept("name", "footballers");

        SimpleBeanPropertyFilter propertyFilterForFootballers = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "position");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilterForFootballers)
                .addFilter("RetrieveTeamFilter", propertyFilterForTeams);

        MappingJacksonValue mapper = new MappingJacksonValue(teamRetrieveDto);
        mapper.setFilters(filterProvider);

        return ResponseEntity.ok(mapper);
    }
}
