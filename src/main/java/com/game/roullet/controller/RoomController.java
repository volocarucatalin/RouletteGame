package com.game.roullet.controller;

import com.game.roullet.request.BetRequest;
import com.game.roullet.request.RoomRequest;
import com.game.roullet.response.BetResponse;
import com.game.roullet.response.RoomResponse;
import com.game.roullet.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
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

    @PutMapping("/rooms/{roomId}")
    public ResponseEntity<?> makeBet(@PathVariable(value = "roomId" ) int roomId, @RequestBody BetRequest betRequest){
            BetResponse success = roomService.makeBet(roomId , betRequest);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(success);
    }
}
