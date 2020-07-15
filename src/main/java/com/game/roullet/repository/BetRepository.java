package com.game.roullet.repository;

import com.game.roullet.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository  extends JpaRepository<Bet, Integer> {
}
