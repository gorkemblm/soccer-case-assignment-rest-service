package com.gorkem.soccercase.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "created_at")
    private final String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "team")
    private List<Footballer> footballers;
}
