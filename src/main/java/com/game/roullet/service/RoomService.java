package com.game.roullet.service;

import com.game.roullet.entity.*;
import com.game.roullet.repository.*;
import com.game.roullet.request.BetRequest;
import com.game.roullet.response.GetRoomResponse;
import com.game.roullet.response.JoinResponse;
import com.game.roullet.util.Role;
import com.game.roullet.util.RoomStatus;
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
    private final BetRepository betRepository;
    private final SpinRepository spinRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, PlayerRepository playerRepository, RegistrationRepository registrationRepository, BetRepository betRepository, SpinRepository spinRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
        this.registrationRepository = registrationRepository;
        this.betRepository = betRepository;
        this.spinRepository = spinRepository;
    }

    public int createRoom(int playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isEmpty()) {
            throw new RuntimeException("This player with id : " + playerId + " does not exist!");

        }
        Player player = playerOptional.get();

        if (registrationRepository.findByPlayerId(playerId) != null) {
            int roomId = player.getRegistration().getRoom().getId();
            throw new RuntimeException("Player is already present in room with id : " + roomId);
        }

        Room room = new Room();
        Registration registration = new Registration();
        registration.setPlayer(player);
        registration.setRole(Role.ADMIN);
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
        registration.setRole(Role.USER);

        registrationRepository.save(registration);

        JoinResponse joinResponse = new JoinResponse();
        joinResponse.setSuccess(true);

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

        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isEmpty()) {
            throw new RuntimeException("Room dose not exist ");
        }

        if (player.getRegistration().getRoom().getId() != roomId) {
            throw new RuntimeException("Player is registered already to another room");
        }

        Room room = roomOptional.get();

        Registration registration = registrationRepository.findByPlayerIdAndRoomId(playerId, roomId);

        if (registration == null) {
            throw new RuntimeException("Player is not registered to this room");
        }

        if (registration.getRole() == Role.ADMIN) {
            room.getRegistrations().clear();
            registrationRepository.deleteAllByRoom(room);
            room.setStatus(RoomStatus.CLOSE);
        }

        if (registration.getRole() == Role.USER) {
            room.getRegistrations().remove(registration);
            registrationRepository.deleteById(registration.getId());
        }
    }

    public void makeBet(int playerId , int roomId, BetRequest betRequest) {

        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player dose not exist");
        }
        Player player = playerOptional.get();

        if (player.getRegistration() == null) {
            throw new RuntimeException("Player is not register in any place!");
        }


        if(player.getRoom().getId() != roomId){
            throw new RuntimeException("Player is in another room");
        }

        Bet bet = new Bet();

         bet.setBetAmount(betRequest.getBetAmount());
         bet.setBetType(betRequest.getBetType());
         bet.setBetTypeValue(betRequest.getNumber());
         bet.setPlayer(player);

         if(player.getBalance() < bet.getBetAmount()){
             throw new RuntimeException("Your Balance is to low");
         }

         int newBalance = player.getBalance() - bet.getBetAmount();

         player.setBalance(newBalance);
         betRepository.save(bet);
    }

    public GetRoomResponse getRoom(int roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if(optionalRoom.isEmpty()){
            throw  new RuntimeException("Room dose not exist");
        }
        Room room = optionalRoom.get();

        GetRoomResponse getRoomResponse = new GetRoomResponse();

        getRoomResponse.setRoomStatus(room.getStatus());
        getRoomResponse.setNumberOfPlayers(room.getRegistrations().size());
        Spin lastSpin = spinRepository.findByRoomIdOrderByTime(roomId);
        getRoomResponse.setLastResult(lastSpin.getSpinNumber());
        getRoomResponse.setLastResultTime(lastSpin.getTime());

        return getRoomResponse;

    }
}
