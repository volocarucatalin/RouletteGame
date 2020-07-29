package com.game.roullet.service;

import com.game.roullet.entity.*;
import com.game.roullet.repository.*;
import com.game.roullet.response.SpinResponse;
import com.game.roullet.util.Role;
import com.game.roullet.util.RoomStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class WheelService {

    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;
    private final RegistrationRepository registrationRepository;
    private final BetRepository betRepository;
    private final RouletteService rouletteService;
    private final SpinRepository spinRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public WheelService(RoomRepository roomRepository, PlayerRepository playerRepository, RegistrationRepository registrationRepository, BetRepository betRepository, RouletteService rouletteService, SpinRepository spinRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
        this.registrationRepository = registrationRepository;
        this.betRepository = betRepository;
        this.rouletteService = rouletteService;
        this.spinRepository = spinRepository;
        this.modelMapper = modelMapper;
    }


    public SpinResponse spinWheel(int playerId, int roomId) {
        Spin spin = new Spin();
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player not exist");
        }

        Player player = playerOptional.get();
        Room room = player.getRoom();

        if (player.getRegistration().getRole() == Role.USER) {
            throw new RuntimeException("You cant spin the wheel because you are not Admin");
        }

        if (roomId != room.getId()) {
            throw new RuntimeException("Player is registered in another room");
        }

        if (room.getStatus() == RoomStatus.CLOSE) {
            throw new RuntimeException("Room is close");
        }

        byte spinNumber = rouletteService.revealNumber();
        spin.setSpinNumber(spinNumber);
        spin.setPlayerId(playerId);
        spin.setRoomId(roomId);
        spin.setTime(new Date());
        spinRepository.save(spin);

        rouletteService.handleBets(room.getPlayers(), spinNumber);
        return modelMapper.map(spin, SpinResponse.class);
    }
}
