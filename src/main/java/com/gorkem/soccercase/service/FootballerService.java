package com.gorkem.soccercase.service;

import com.gorkem.soccercase.exception.ResourceNotFoundException;
import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerUpdateDto;
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
                .map(footballerDtoConverter::convertForRetrieve)
                .collect(Collectors.toList());
    }

    public FootballerRetrieveDto createFootballer(FootballerCreateDto footballerCreateDto) {
        Footballer footballer = this.footballerDtoConverter.convertForCreate(footballerCreateDto);

        return this.footballerDtoConverter.convertForRetrieve(this.footballerRepository.save(footballer));
    }

    public FootballerRetrieveDto updateFootballer(String id, FootballerUpdateDto footballerUpdateDto) {
        Footballer footballer = this.footballerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Footballer not found for this id : %s", id)));

        Footballer updatedFootballer = this.footballerDtoConverter.convertForUpdate(footballerUpdateDto, footballer);

        return this.footballerDtoConverter.convertForRetrieve(this.footballerRepository.save(updatedFootballer));
    }

    public boolean deleteFootballer(String id) {
        Footballer footballer = this.footballerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Footballer not found for this id : %s", id)));

        try {
            this.footballerRepository.delete(footballer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public FootballerRetrieveDto retrieveFootballer(String id) {
        Footballer footballer = this.footballerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Footballer not found for this id : %s", id)));

        return this.footballerDtoConverter.convertForRetrieve(footballer);
    }
}
