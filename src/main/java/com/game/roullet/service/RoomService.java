package com.game.roullet.service;

import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
import com.game.roullet.response.JoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        playerRepository.findById(playerId).get();

        Optional<Registration> current = registrationRepository.findByPlayerId(playerId);

        if (current.isPresent()) {
            throw new RuntimeException("Player is already present in room with id : " + current.get().getRoomId());
        }

        Room room = new Room();

        roomRepository.save(room);

        Registration registration = new Registration();

        registration.setPlayerId(playerId);
        registration.setRoomId(room.getRoom());
        registration.setRole("Admin");

        registrationRepository.save(registration);


        return room.getRoom();
    }


    public JoinResponse joinRoom(int playerId, int roomId) {

        JoinResponse joinResponse = new JoinResponse();
        joinResponse.setRoomStatus(false);
        List<Registration> registrationList = registrationRepository.findAllRegistrationByRoomId(roomId);

        if (playerRepository.findById(playerId).isEmpty()) {
            throw new RuntimeException("Player dose not exist");
        }
        if (roomRepository.findById(roomId).isEmpty()) {
            throw new RuntimeException("Room dose not is closed or dose not exist : " + joinResponse);
        }

        if (registrationList.size() >= 4) {
            throw new RuntimeException("Room is full");
        }
        Optional<Registration> player = registrationRepository.findByPlayerId(playerId);
        if (player.isPresent()) {
            throw new RuntimeException("Player is registered already to another room");
        }

        joinResponse.setRoomStatus(true);

        Registration registration = new Registration();
        registration.setRoomId(roomId);
        registration.setPlayerId(playerId);
        registration.setRole("User");

        registrationRepository.save(registration);

        return joinResponse;

    }
}
