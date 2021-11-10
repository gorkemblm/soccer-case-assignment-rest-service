package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.model.Nationality;
import com.gorkem.soccercase.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerCreateDto {
    private String firstName;
    private String lastName;
    private int age;
    private Nationality nationality;
    private Position position;
    private String teamId;
}
