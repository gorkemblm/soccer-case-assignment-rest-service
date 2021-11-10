package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.constants.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamUpdateDto {
    @NotBlank(message = Message.TEAM_NAME_NOT_BLANK)
    @Min(value = 3, message = Message.TEAM_NAME_MIN_SIZE_3)
    @Max(value = 128, message = Message.TEAM_NAME_MAX_SIZE_128)
    private String name;
}
