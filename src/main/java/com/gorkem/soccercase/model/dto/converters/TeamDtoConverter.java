package com.gorkem.soccercase.model.dto.converters;

import com.gorkem.soccercase.model.Team;
import com.gorkem.soccercase.model.dto.FootballerRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamDtoConverter {

    private final FootballerDtoConverter footballerDtoConverter;

    public TeamDtoConverter(FootballerDtoConverter footballerDtoConverter) {
        this.footballerDtoConverter = footballerDtoConverter;
    }

    public TeamRetrieveDto convertForRetrieve(Team team) {
        List<FootballerRetrieveDto> footballerRetrieveDtos = this.footballerDtoConverter.multipleConvertForRetrieve(team.getFootballers());

        return TeamRetrieveDto.builder()
                .id(team.getId())
                .name(team.getName())
                .footballers(footballerRetrieveDtos)
                .build();
    }

    public Team convertForCreate(TeamCreateDto teamCreateDto) {
        return Team.builder()
                .name(teamCreateDto.getName())
                .build();
    }

    public Team convertForUpdate(TeamUpdateDto teamUpdateDto, Team team) {
        team.setName(teamUpdateDto.getName());
        team.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return team;
    }

    public List<TeamRetrieveDto> multipleConvertForRetrieve(List<Team> teams) {
        return teams.stream()
                .map(this::convertForRetrieve)
                .collect(Collectors.toList());
    }
}
