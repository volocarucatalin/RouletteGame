package com.game.roullet.controller;

import com.game.roullet.response.PlayerResponse;
import com.game.roullet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<?> createPlayer() {
        PlayerResponse responsePlayer = new PlayerResponse(playerService.createPlayer());
        return ResponseEntity.status(HttpStatus.OK)
                .body(responsePlayer);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(playerService.getAllPlayers());
    }
}
