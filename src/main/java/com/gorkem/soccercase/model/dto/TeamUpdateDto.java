package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.constants.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeamUpdateDto {
    @NotBlank(message = Message.TEAM_NAME_NOT_BLANK)
    @Size(min = 3, message = Message.TEAM_NAME_MIN_SIZE_3)
    private String name;
}
