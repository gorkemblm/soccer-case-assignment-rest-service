package com.gorkem.soccercase.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("RetrieveTeamFilter")
public class TeamRetrieveDto {
    private String id;
    private String name;
    private List<FootballerRetrieveDto> footballers;
}
