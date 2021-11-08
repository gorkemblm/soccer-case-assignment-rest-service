package com.gorkem.soccercase.controller;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.model.dto.FootballerUpdateDto;
import com.gorkem.soccercase.service.FootballerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping
public class FootballersController {

    private final FootballerService footballerService;

    public FootballersController(FootballerService footballerService) {
        this.footballerService = footballerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/footballers")
    public ResponseEntity<Object> retrieveFootballers() {
        return ResponseEntity.ok(this.footballerService.retrieveFootballers());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create-footballer")
    public ResponseEntity<Object> createFootballer(@RequestBody FootballerCreateDto footballerCreateDto) {
        FootballerRetrieveDto savedFootballer = this.footballerService.createFootballer(footballerCreateDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedFootballer.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update-footballer/{id}")
    public ResponseEntity<Object> updateFootballer(@PathVariable(name = "id") String id, @RequestBody FootballerUpdateDto footballerUpdateDto) {
        return ResponseEntity.ok(this.footballerService.updateFootballer(id, footballerUpdateDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete-footballer/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") String id) {
        var isDeletedFootballer = this.footballerService.deleteFootballer(id);

        if (!isDeletedFootballer) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/retrieve-footballer/{id}")
    public ResponseEntity<Object> retrieveFootballer(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(this.footballerService.retrieveFootballer(id));
    }
}
