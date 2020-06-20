package com.game.roullet.service;

import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
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


}
