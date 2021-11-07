package com.gorkem.soccercase.repository;

import com.gorkem.soccercase.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FootballerRepository extends JpaRepository<Footballer, String> {
}
