package com.game.roullet.service;

import com.game.roullet.entity.Player;
import com.game.roullet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public int createPlayer() {
        Player player = new Player();


        return playerRepository.save(player).getPlayerId();
    }
}
