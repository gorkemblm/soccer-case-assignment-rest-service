package com.gorkem.soccercase.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("RetrieveTeamFilter")
public class TeamRetrieveDto {
    private String id;
    private String name;
    private List<FootballerRetrieveDto> footballers;
}
