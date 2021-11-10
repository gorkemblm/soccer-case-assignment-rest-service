package com.gorkem.soccercase.service;

import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.exception.ResourceNotFoundException;
import com.gorkem.soccercase.exception.ServiceLayerException;
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
        List<Team> teams;
        try {
            teams = this.teamRepository.findAll();

        } catch (Exception e) {
            throw new ServiceLayerException(Message.TEAM_FAIL_RETRIEVE_RECORDS);
        }
        return teams.stream()
                .map(teamDtoConverter::convertForRetrieve)
                .collect(Collectors.toList());
    }

    public TeamRetrieveDto createTeam(TeamCreateDto teamCreateDto) {
        Team team = this.teamDtoConverter.convertForCreate(teamCreateDto);

        Team savedTeam;
        try {
            savedTeam = this.teamRepository.save(team);

        } catch (Exception e) {
            throw new ServiceLayerException(Message.TEAM_FAIL_SAVING_RECORD);
        }
        return this.teamDtoConverter.convertForRetrieve(savedTeam);
    }

    public TeamRetrieveDto updateTeam(String id, TeamUpdateDto teamUpdateDto) {
        Team team = findTeamById(id);

        Team updatedTeam = this.teamDtoConverter.convertForUpdate(teamUpdateDto, team);

        Team savedTeam;
        try {
            savedTeam = this.teamRepository.save(updatedTeam);

        } catch (Exception e) {
            throw new ServiceLayerException(Message.TEAM_FAIL_UPDATING_RECORD);
        }
        return this.teamDtoConverter.convertForRetrieve(savedTeam);
    }

    public boolean deleteTeam(String id) {
        Team team = findTeamById(id);
        try {
            this.teamRepository.delete(team);
        } catch (Exception e) {
            throw new ServiceLayerException(Message.TEAM_FAIL_DELETING_RECORD);
        }
        return true;
    }

    public TeamRetrieveDto retrieveTeam(String id) {
        Team team = findTeamById(id);

        return this.teamDtoConverter.convertForRetrieve(team);
    }

    public Team findTeamById(String id) {
        return this.teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(Message.PUBLIC_FAIL_RECORD_NOT_FOUND + id)));
    }
}
