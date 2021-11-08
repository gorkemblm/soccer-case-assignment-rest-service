package com.gorkem.soccercase.model.dto;

import com.gorkem.soccercase.model.Footballer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamCreateDto {
    private String name;
    private List<Footballer> footballers;
}
