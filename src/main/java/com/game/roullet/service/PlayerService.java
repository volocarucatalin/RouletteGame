package com.game.roullet.service;

import com.game.roullet.entity.Player;
import com.game.roullet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public int createPlayer() {

        Player player = new Player();


        return playerRepository.save(player).getId();
    }

    public void receiveWiningAmount(int playerId, int amount) {
        Optional<Player> playerOptional =playerRepository.findById(playerId);
        if(playerOptional.isEmpty()){
            throw new RuntimeException("Player not exist");
        }
        Player player = playerOptional.get();
                player.addAmount(amount);
    }
}
