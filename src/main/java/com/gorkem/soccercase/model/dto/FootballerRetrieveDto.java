package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.model.Nationality;
import com.gorkem.soccercase.model.Position;
import com.gorkem.soccercase.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerRetrieveDto {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Nationality nationality;
    private Position position;
    private Team team;
}
