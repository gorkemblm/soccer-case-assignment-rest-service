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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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

        if (footballerRetrieveDtos.contains(null) || footballerRetrieveDtos.contains("")) {
            return ResponseEntity.noContent().build();
        }

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "nationality", "position", "teamName");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilter);

        MappingJacksonValue mapper = new MappingJacksonValue(footballerRetrieveDtos);
        mapper.setFilters(filterProvider);

        return ResponseEntity.ok(mapper);
    }

    @PostMapping
    public ResponseEntity<Object> createFootballer(@Valid @RequestBody FootballerCreateDto footballerCreateDto) {
        FootballerRetrieveDto savedFootballer = this.footballerService.createFootballer(footballerCreateDto);

        if (savedFootballer == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFootballer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFootballer(@NotBlank(message = "Footballer id must not be empty or null.") @PathVariable(name = "id") String id,
                                                   @Valid @RequestBody FootballerUpdateDto footballerUpdateDto) {

        FootballerRetrieveDto updatedFootballer = this.footballerService.updateFootballer(id, footballerUpdateDto);

        if (updatedFootballer == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(updatedFootballer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@NotBlank(message = "Footballer id must not be empty or null.") @PathVariable(name = "id") String id) {
        var isDeletedFootballer = this.footballerService.deleteFootballer(id);

        if (isDeletedFootballer) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> retrieveFootballer(@NotBlank(message = "Footballer id must not be empty or null.") @PathVariable(name = "id") String id) {
        FootballerRetrieveDto footballerRetrieveDto = this.footballerService.retrieveFootballer(id);

        if (footballerRetrieveDto == null) {
            return ResponseEntity.noContent().build();
        }

        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("firstName", "lastName", "age", "nationality", "position", "teamName");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("RetrieveFootballerFilter", propertyFilter);

        MappingJacksonValue mapper = new MappingJacksonValue(footballerRetrieveDto);
        mapper.setFilters(filterProvider);

        return ResponseEntity.ok(mapper);
    }
}
