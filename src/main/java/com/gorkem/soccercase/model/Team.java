package com.gorkem.soccercase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "created_at")
    private final String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"teams", "hibernateLazyInitializer", "handler"})
    private List<Footballer> footballers;
}
