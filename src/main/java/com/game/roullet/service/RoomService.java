package com.game.roullet.service;

import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
import com.game.roullet.response.JoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, PlayerRepository playerRepository, RegistrationRepository registrationRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
        this.registrationRepository = registrationRepository;
    }

    public int createRoom(int playerId) {
        Player player = new Player();

        if (playerRepository.findById(playerId).isEmpty()) {
            throw new RuntimeException("This player with id : " + playerId + " does not exist!");

        }


        if (registrationRepository.findByPlayerId(playerId).isPresent()) {
            int roomId = player.getRegistration().getRoom().getId();
            throw new RuntimeException("Player is already present in room with id : " + roomId);
        }


        Room room = new Room();
        room.setId(playerId);
        roomRepository.save(room);

        Registration registration = new Registration();

        registration.setPlayerId(playerId);
        registration.setPlayer(player);
        registration.setRoom(room);
        registration.setRole("Admin");

        registrationRepository.save(registration);


        return room.getId();
    }


    public JoinResponse joinRoom(int playerId, int roomId) {

        JoinResponse joinResponse = new JoinResponse();
        joinResponse.setRoomStatus(false);


        if (playerRepository.findById(playerId).isEmpty()) {
            throw new RuntimeException("Player dose not exist");
        }

        List<Registration> registrationList = registrationRepository.findAll();

        int numberOdPlayers = 0;
        Room room = new Room();

        for (Registration registration : registrationList) {
            if (registration.getRoom().getId().equals(roomId)) {
                throw new RuntimeException("Room dose not exist : " + joinResponse);
            }
            if (registration.getPlayer().getPlayerId() == playerId) {
                throw new RuntimeException("Player is registered already to another room");
            }
            if (registration.getRoom().getId() == roomId) {
                numberOdPlayers = numberOdPlayers + 1;

            }

        }
        if (numberOdPlayers > 3) {
            throw new RuntimeException("Room is full");
        }

        room.setId(roomId);


        joinResponse.setRoomStatus(true);

        Registration registration = new Registration();
        registration.setPlayerId(playerId);
        registration.setRoom(room);
        registration.setRole("User");

        registrationRepository.save(registration);

        return joinResponse;

    }
}
