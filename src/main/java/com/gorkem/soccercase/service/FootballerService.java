package com.gorkem.soccercase.service;

import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.exception.ServiceLayerException;
import com.gorkem.soccercase.exception.ResourceNotFoundException;
import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.Team;
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
    private final TeamService teamService;

    public FootballerService(FootballerRepository footballerRepository, FootballerDtoConverter footballerDtoConverter, TeamService teamService) {
        this.footballerRepository = footballerRepository;
        this.footballerDtoConverter = footballerDtoConverter;
        this.teamService = teamService;
    }

    public List<FootballerRetrieveDto> retrieveFootballers() {
        List<Footballer> footballers;
        try {
            footballers = this.footballerRepository.findAll();

        } catch (Exception e) {
            throw new ServiceLayerException(Message.FOOTBALLER_FAIL_RETRIEVE_RECORDS);
        }
        return footballers.stream()
                .map(footballerDtoConverter::convertForRetrieve)
                .collect(Collectors.toList());
    }

    public FootballerRetrieveDto createFootballer(FootballerCreateDto footballerCreateDto) {
        Team team = this.teamService.findTeamById(footballerCreateDto.getTeamId());

        Footballer footballer = this.footballerDtoConverter.convertForCreate(footballerCreateDto, team);

        Footballer savedFootballer;
        try {
            savedFootballer = this.footballerRepository.save(footballer);
        } catch (Exception e) {
            throw new ServiceLayerException(Message.FOOTBALLER_FAIL_SAVING_RECORD);
        }
        return this.footballerDtoConverter.convertForRetrieve(savedFootballer);
    }

    public FootballerRetrieveDto updateFootballer(String id, FootballerUpdateDto footballerUpdateDto) {
        Team team = this.teamService.findTeamById(footballerUpdateDto.getTeamId());

        Footballer footballer = findFootballerById(id);

        Footballer updatedFootballer = this.footballerDtoConverter.convertForUpdate(footballerUpdateDto, footballer, team);

        Footballer savedFootballer;
        try {
            savedFootballer = this.footballerRepository.save(updatedFootballer);

        } catch (Exception e) {
            throw new ServiceLayerException(Message.FOOTBALLER_FAIL_UPDATING_RECORD);
        }
        return this.footballerDtoConverter.convertForRetrieve(savedFootballer);
    }

    public boolean deleteFootballer(String id) {
        Footballer footballer = findFootballerById(id);
        try {
            this.footballerRepository.delete(footballer);
        } catch (Exception e) {
            throw new ServiceLayerException(Message.FOOTBALLER_FAIL_DELETING_RECORD);
        }
        return true;
    }

    public FootballerRetrieveDto retrieveFootballer(String id) {
        Footballer footballer = findFootballerById(id);

        return this.footballerDtoConverter.convertForRetrieve(footballer);
    }

    public Footballer findFootballerById(String id) {
        return this.footballerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Message.PUBLIC_FAIL_RECORD_NOT_FOUND + id));
    }
}
