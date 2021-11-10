package com.gorkem.soccercase.repository;

import com.gorkem.soccercase.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballerRepository extends JpaRepository<Footballer, String> {
}
