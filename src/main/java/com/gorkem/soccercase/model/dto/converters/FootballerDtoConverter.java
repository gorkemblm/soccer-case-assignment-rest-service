package com.gorkem.soccercase.model.dto.converters;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.Team;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.model.dto.FootballerUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FootballerDtoConverter {

    public FootballerRetrieveDto convertForRetrieve(Footballer footballer) {
        FootballerRetrieveDto dto = new FootballerRetrieveDto();
        dto.setId(footballer.getId());
        dto.setAge(footballer.getAge());
        dto.setFirstName(footballer.getFirstName());
        dto.setLastName(footballer.getLastName());
        dto.setNationality(footballer.getNationality());
        dto.setTeamName(footballer.getTeam().getName());
        dto.setPosition(footballer.getPosition());

        return dto;
    }

    public List<FootballerRetrieveDto> multipleConvertForRetrieve(List<Footballer> footballers) {

        return footballers.stream()
                .map(this::convertForRetrieve)
                .collect(Collectors.toList());
    }

    public Footballer convertForCreate(FootballerCreateDto footballerCreateDto, Team team) {
        return Footballer.builder()
                .age(footballerCreateDto.getAge())
                .nationality(footballerCreateDto.getNationality())
                .position(footballerCreateDto.getPosition())
                .team(team)
                .firstName(footballerCreateDto.getFirstName())
                .lastName(footballerCreateDto.getLastName())
                .build();
    }

    public Footballer convertForUpdate(FootballerUpdateDto footballerUpdateDto, Footballer footballer, Team team) {
        footballer.setAge(footballerUpdateDto.getAge());
        footballer.setPosition(footballerUpdateDto.getPosition());
        footballer.setTeam(team);
        footballer.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        return footballer;
    }
}
