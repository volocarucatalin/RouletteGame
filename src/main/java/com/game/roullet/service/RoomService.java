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

import java.util.Optional;


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

        registration.setPlayer(player);
        registration.setRoom(room);
        registration.setRole("Admin");

        registrationRepository.save(registration);


        return room.getId();
    }

    public JoinResponse joinRoom(int playerId, int roomId) {

        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player dose not exist");
        }
        Player player = playerOptional.get();

        if (player.getRegistration() != null) {
            throw new RuntimeException("Player is registered already to another room");
        }


        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room dose not exist ");
        }


        Room room = roomOptional.get();

        if (room.getRegistrations() == null || room.getRegistrations().size() == 0) {
            throw new RuntimeException("Room was closed");
        }
        if (room.getRegistrations().size() > 3) {
            throw new RuntimeException("Room is full");
        }

        Registration registration = new Registration();
        registration.setPlayer(player);
        registration.setRoom(room);
        registration.setRole("User");

        registrationRepository.save(registration);

        JoinResponse joinResponse = new JoinResponse();
        joinResponse.setRoomStatus(true);

        return joinResponse;

    }

}
