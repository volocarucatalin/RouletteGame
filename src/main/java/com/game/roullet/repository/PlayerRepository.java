package com.game.roullet.repository;

import com.game.roullet.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<Player, Integer> {

}
