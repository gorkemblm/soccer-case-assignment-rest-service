package com.gorkem.soccercase.repository;

import com.gorkem.soccercase.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
