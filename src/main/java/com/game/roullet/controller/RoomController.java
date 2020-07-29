package com.game.roullet.controller;

import com.game.roullet.request.BetRequest;
import com.game.roullet.request.RoomRequest;
import com.game.roullet.response.RoomResponse;
import com.game.roullet.service.RoomService;
import com.game.roullet.service.WheelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoomController {

    final private RoomService roomService;
    final private WheelService wheelService;

    @Autowired
    public RoomController(RoomService roomService, WheelService wheelService) {
        this.roomService = roomService;
        this.wheelService = wheelService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest roomRequest) {

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(roomService.createRoom(roomRequest.getPlayerId()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomResponse);

    }

    @PostMapping("/players/{playerId}/rooms/{roomId}/registrations")
    public ResponseEntity<?> joinRoom(@PathVariable(value = "playerId") int playerId, @PathVariable(value = "roomId") int roomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.joinRoom(playerId, roomId));

    }

    @DeleteMapping("/players/{playerId}/rooms/{roomId}")
    public void leaveRoom(@PathVariable(value = "playerId") int playerId, @PathVariable(value = "roomId") int roomId) {
        roomService.leaveRoom(playerId, roomId);
    }

    @PostMapping("/players/{playerId}/rooms/{roomId}/bets")
    public void makeBet(@RequestBody BetRequest betRequest, @PathVariable(value = "playerId") int playerId, @PathVariable(value = "roomId") int roomId) {
        roomService.makeBet(playerId, roomId, betRequest);
    }

    @PostMapping("/players/{playerId}/rooms/{roomId}/spins")
    public ResponseEntity<?> spinWheel(@PathVariable(value = "playerId") int playerId, @PathVariable(value = "roomId") int roomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(wheelService.spinWheel(playerId, roomId));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getRoom(@PathVariable(value = "roomId") int roomId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomService.getRoom(roomId));

    }
}
