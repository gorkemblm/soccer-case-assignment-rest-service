package com.gorkem.soccercase.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.model.dto.FootballerUpdateDto;
import com.gorkem.soccercase.service.FootballerService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/footballers")
public class FootballersController {

    private final FootballerService footballerService;

    public FootballersController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @GetMapping
    public ResponseEntity<Object> retrieveFootballers() {
        List<FootballerRetrieveDto> footballerRetrieveDtos = this.footballerService.retrieveFootballers();

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "nationality", "position", "teamName");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(footballerRetrieveDtos);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }

    @PostMapping
    public ResponseEntity<Object> createFootballer(@RequestBody FootballerCreateDto footballerCreateDto) {
        FootballerRetrieveDto savedFootballer = this.footballerService.createFootballer(footballerCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/retrieve-footballer/{id}")
                .buildAndExpand(savedFootballer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFootballer(@PathVariable(name = "id") String id, @RequestBody FootballerUpdateDto footballerUpdateDto) {
        return ResponseEntity.ok(this.footballerService.updateFootballer(id, footballerUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") String id) {
        var isDeletedFootballer = this.footballerService.deleteFootballer(id);

        if (!isDeletedFootballer) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieveFootballer(@PathVariable(name = "id") String id) {
        FootballerRetrieveDto footballerRetrieveDto = this.footballerService.retrieveFootballer(id);

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "nationality", "position", "teamName");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(footballerRetrieveDto);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }
}
