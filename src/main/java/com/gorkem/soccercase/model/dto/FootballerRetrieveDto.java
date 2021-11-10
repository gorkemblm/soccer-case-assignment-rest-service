package com.gorkem.soccercase.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.gorkem.soccercase.model.Nationality;
import com.gorkem.soccercase.model.Position;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("RetrieveFootballerFilter")
public class FootballerRetrieveDto {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Nationality nationality;
    private Position position;
    private String teamName;
}
