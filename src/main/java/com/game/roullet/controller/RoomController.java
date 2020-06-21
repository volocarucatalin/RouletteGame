package com.game.roullet.controller;

import com.game.roullet.request.RoomRequest;
import com.game.roullet.response.JoinResponse;
import com.game.roullet.response.RoomResponse;
import com.game.roullet.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
        JoinResponse joinResponse = new JoinResponse();

        joinResponse = roomService.joinRoom(playerId, roomId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(joinResponse);

    }
    // @PostMapping("/players/{playerId}/rooms/{roomId}/")

}
