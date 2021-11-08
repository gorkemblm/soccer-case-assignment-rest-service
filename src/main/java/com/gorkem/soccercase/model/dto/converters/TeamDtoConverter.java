package com.gorkem.soccercase.model.dto.converters;

import com.gorkem.soccercase.model.Team;
import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class TeamDtoConverter {

    public TeamRetrieveDto convertForRetrieve(Team team) {
        TeamRetrieveDto dto = new TeamRetrieveDto();
        dto.setName(team.getName());
        dto.setFootballers(team.getFootballers());

        return dto;
    }

    public Team convertForCreate(TeamCreateDto teamCreateDto) {
        return Team.builder()
                .name(teamCreateDto.getName())
                .footballers(teamCreateDto.getFootballers())
                .build();
    }

    public Team convertForUpdate(TeamUpdateDto teamUpdateDto, Team team) {
        team.setName(teamUpdateDto.getName());
        team.setFootballers(teamUpdateDto.getFootballers());

        return team;
    }
}
