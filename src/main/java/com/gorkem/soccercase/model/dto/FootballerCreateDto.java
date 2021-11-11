package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.constants.Message;
import com.gorkem.soccercase.model.Nationality;
import com.gorkem.soccercase.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FootballerCreateDto {
    @NotBlank(message = Message.FOOTBALLER_FIRST_NAME_NOT_BLANK)
    @Size(min = 3, message = Message.FOOTBALLER_FIRST_NAME_MIN_SIZE_3)
    private String firstName;

    @NotBlank(message = Message.FOOTBALLER_LAST_NAME_NOT_BLANK)
    @Size(min = 3, message = Message.FOOTBALLER_LAST_NAME_MIN_SIZE)
    private String lastName;

    @NotNull(message = Message.FOOTBALLER_AGE_NOT_EMPTY)
    @Positive(message = Message.FOOTBALLER_AGE_POSITIVE)
    private int age;

    @NotNull(message = Message.FOOTBALLER_NATIONALITY_NOT_EMPTY)
    private Nationality nationality;

    @NotNull(message = Message.FOOTBALLER_POSITION_NOT_EMPTY)
    private Position position;

    @NotBlank(message = Message.FOOTBALLER_TEAM_ID_NOT_BLANK)
    private String teamId;
}
