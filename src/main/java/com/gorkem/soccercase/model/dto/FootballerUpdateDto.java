package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerUpdateDto {
    private int age;
    private Position position;
    private String teamId;
}
