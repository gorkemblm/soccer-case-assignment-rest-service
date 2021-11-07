package com.gorkem.soccercase.model.dto.converters;

import com.gorkem.soccercase.model.Footballer;
import com.gorkem.soccercase.model.dto.FootballerCreateDto;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import org.springframework.stereotype.Component;

@Component
public class FootballerDtoConverter {

    public FootballerRetrieveDto convertForRetrieve(Footballer footballer) {
        FootballerRetrieveDto dto = new FootballerRetrieveDto();
        dto.setAge(footballer.getAge());
        dto.setFirstName(footballer.getFirstName());
        dto.setLastName(footballer.getLastName());
        dto.setNationality(footballer.getNationality());
        dto.setTeam(footballer.getTeam());
        dto.setPosition(footballer.getPosition());

        return dto;
    }

    public Footballer convertForCreate(FootballerCreateDto footballerCreateDto) {
        return Footballer.builder()
                .age(footballerCreateDto.getAge())
                .nationality(footballerCreateDto.getNationality())
                .position(footballerCreateDto.getPosition())
                .team(footballerCreateDto.getTeam())
                .firstName(footballerCreateDto.getFirstName())
                .lastName(footballerCreateDto.getLastName())
                .build();
    }
}
