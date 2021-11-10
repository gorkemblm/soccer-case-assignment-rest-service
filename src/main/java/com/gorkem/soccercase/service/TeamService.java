package com.gorkem.soccercase.service;

import com.gorkem.soccercase.exception.ResourceNotFoundException;
import com.gorkem.soccercase.model.Team;
import com.gorkem.soccercase.model.dto.TeamCreateDto;
import com.gorkem.soccercase.model.dto.TeamRetrieveDto;
import com.gorkem.soccercase.model.dto.TeamUpdateDto;
import com.gorkem.soccercase.model.dto.converters.TeamDtoConverter;
import com.gorkem.soccercase.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamDtoConverter teamDtoConverter;

    public TeamService(TeamRepository teamRepository, TeamDtoConverter teamDtoConverter) {
        this.teamRepository = teamRepository;
        this.teamDtoConverter = teamDtoConverter;
    }

    public List<TeamRetrieveDto> retrieveTeams() {
        List<Team> teams = this.teamRepository.findAll();

        return teams.stream()
                .map(teamDtoConverter::convertForRetrieve)
                .collect(Collectors.toList());
    }

    public TeamRetrieveDto createTeam(TeamCreateDto teamCreateDto) {
        Team team = this.teamDtoConverter.convertForCreate(teamCreateDto);

        return this.teamDtoConverter.convertForRetrieve(this.teamRepository.save(team));
    }

    public TeamRetrieveDto updateTeam(String id, TeamUpdateDto teamUpdateDto) {
        Team team = this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team not found for this id : %s", id)));

        Team updatedTeam = this.teamDtoConverter.convertForUpdate(teamUpdateDto, team);

        return this.teamDtoConverter.convertForRetrieve(this.teamRepository.save(updatedTeam));
    }

    public boolean deleteTeam(String id) {
        Team team = this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team not found for this id : %s", id)));

        try {
            this.teamRepository.delete(team);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TeamRetrieveDto retrieveTeam(String id) {
        Team team = this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team not found for this id : %s", id)));

        return this.teamDtoConverter.convertForRetrieve(team);
    }

    public Team findTeamById(String id) {
        return this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Team not found for this id : %s", id)));
    }
}
