package com.gorkem.soccercase.service;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.dto.converters.FootballerDtoConverter;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.repository.FootballerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballerService {

    private final FootballerRepository footballerRepository;
    private final FootballerDtoConverter footballerDtoConverter;

    public FootballerService(FootballerRepository footballerRepository, FootballerDtoConverter footballerDtoConverter) {
        this.footballerRepository = footballerRepository;
        this.footballerDtoConverter = footballerDtoConverter;
    }

    public List<FootballerRetrieveDto> retrieveFootballers() {
        List<Footballer> footballers = this.footballerRepository.findAll();

        return footballers.stream()
                .map(footballerDtoConverter :: convertForRetrieve)
                .collect(Collectors.toList());
    }
}
