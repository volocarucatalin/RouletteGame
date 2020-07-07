package com.game.roullet.service;

import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
import com.game.roullet.request.BetRequest;
import com.game.roullet.response.BetResponse;
import com.game.roullet.response.JoinResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isEmpty()) {
            throw new RuntimeException("This player with id : " + playerId + " does not exist!");

        }
        Player player = playerOptional.get();


        if (registrationRepository.findByPlayerId(playerId).isPresent()) {
            int roomId = player.getRegistration().getRoom().getId();
            throw new RuntimeException("Player is already present in room with id : " + roomId);
        }


        Room room = new Room();


        Registration registration = new Registration();
        registration.setPlayer(player);
        registration.setRole("Admin");
        registration.setRoom(room);

        room.setRegistrations(List.of(registration));
        roomRepository.save(room);


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

    @Transactional
    public void leaveRoom(int playerId, int roomId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player dose not exist");
        }
        Player player = playerOptional.get();

        if (player.getRegistration() == null) {
            throw new RuntimeException("Player is not register in any place!");
        }

        if (player.getRegistration().getRoom().getId() != roomId) {
            throw new RuntimeException("Player is registered already to another room");
        }

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room dose not exist ");
        }

        Room room = roomOptional.get();

        Optional<Registration> registrationOptional = registrationRepository.findByPlayerIdAndRoomId(playerId, roomId);

        if (registrationOptional.isEmpty()) {
            throw new RuntimeException("Player is not registered to this room");
        }

        if (registrationOptional.get().getRole().equals("Admin")) {
            room.getRegistrations().clear();
            registrationRepository.deleteAllByRoom(room);
        }

        if (registrationOptional.get().getRole().equals("User")) {
            room.getRegistrations().remove(registrationOptional.get());
            registrationRepository.deleteById(registrationOptional.get().getId());
        }

    }

    public BetResponse makeBet(int roomId, BetRequest betRequest) {
        Optional<Registration> registrationOptional;
        return null;
    }
}
