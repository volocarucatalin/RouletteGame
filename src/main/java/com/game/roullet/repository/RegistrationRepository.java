package com.game.roullet.repository;

import com.game.roullet.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {


    Optional<Registration> findByPlayerId(Integer playerId);
}
