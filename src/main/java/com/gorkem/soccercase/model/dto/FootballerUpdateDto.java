package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerUpdateDto {
    @NotEmpty(message = Message.FOOTBALLER_AGE_NOT_EMPTY)
    @Positive(message = Message.FOOTBALLER_AGE_POSITIVE)
    private int age;

    @NotEmpty(message = Message.FOOTBALLER_POSITION_NOT_EMPTY)
    private Position position;

    @NotBlank(message = Message.FOOTBALLER_TEAM_ID_NOT_BLANK)
    private String teamId;
}
