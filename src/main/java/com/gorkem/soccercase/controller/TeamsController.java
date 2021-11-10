package com.gorkem.soccercase.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import com.gorkem.soccercase.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    private final TeamService teamService;

    public TeamsController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<Object> retrieveTeams() {
        List<TeamRetrieveDto> teamRetrieveDtos = this.teamService.retrieveTeams();

        SimpleBeanPropertyFilter propertyFilterForTeams = SimpleBeanPropertyFilter
                .filterOutAllExcept("name", "footballers");

        SimpleBeanPropertyFilter propertyFilterForFootballers = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "position");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilterForFootballers)
                .addFilter("RetrieveTeamFilter", propertyFilterForTeams);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(teamRetrieveDtos);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }

    @PostMapping
    public ResponseEntity<Object> createTeam(@RequestBody TeamCreateDto teamCreateDto) {
        TeamRetrieveDto savedTeam = this.teamService.createTeam(teamCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/retrieve-team/{id}")
                .buildAndExpand(savedTeam.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") String id, @RequestBody TeamUpdateDto teamUpdateDto) {
        return ResponseEntity.ok(this.teamService.updateTeam(id, teamUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTeam(@PathVariable(name = "id") String id) {
        var isDeletedTeam = this.teamService.deleteTeam(id);

        if (!isDeletedTeam) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    //Duplicate CODE -> move property filters to global scope
    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieveTeam(@PathVariable(name = "id") String id) {
        TeamRetrieveDto teamRetrieveDto = this.teamService.retrieveTeam(id);

        SimpleBeanPropertyFilter propertyFilterForTeams = SimpleBeanPropertyFilter
                .filterOutAllExcept("name", "footballers");

        SimpleBeanPropertyFilter propertyFilterForFootballers = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "position");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilterForFootballers)
                .addFilter("RetrieveTeamFilter", propertyFilterForTeams);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(teamRetrieveDto);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }
}
