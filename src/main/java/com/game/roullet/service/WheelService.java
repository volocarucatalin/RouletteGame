package com.game.roullet.service;

import com.game.roullet.entity.*;
import com.game.roullet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WheelService {

    private final RoomRepository roomRepository;
    private final PlayerRepository playerRepository;
    private final RegistrationRepository registrationRepository;
    private final BetRepository betRepository;
    private final RouletteService rouletteService;
    private final SpinRepository spinRepository;

    @Autowired
    public WheelService(RoomRepository roomRepository, PlayerRepository playerRepository, RegistrationRepository registrationRepository, BetRepository betRepository, RouletteService rouletteService, SpinRepository spinRepository) {
        this.roomRepository = roomRepository;
        this.playerRepository = playerRepository;
        this.registrationRepository = registrationRepository;
        this.betRepository = betRepository;
        this.rouletteService = rouletteService;
        this.spinRepository = spinRepository;
    }


    public void spinWheel(int playerId, int roomId) {
        Spin spin = new Spin();
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player not exist");
        }

        Player player = playerOptional.get();
        Registration registration = player.getRegistration();
        String playerRole = registration.getRole();

        if (playerRole.equals("User")) {
            throw new RuntimeException("You cant spin the wheel because you are not Admin");
        }

        if (roomId != registration.getRoom().getId()) {
            throw new RuntimeException("Player is registered in another room");
        }

        String roomStatus = registration.getRoom().getStatus();
        if (roomStatus.equals("close")) {
            throw new RuntimeException("Room is close");
        }

        int spinNumber = rouletteService.revealNumber();

        rouletteService.handleBets(player, spinNumber);

        List<Bet> betList = player.getBet();
        for (Bet bet : betList) {
            bet.setStatus("close");
            betRepository.save(bet);
        }
        Room room = spin.getRoom();
        room.setSpin(spin);
    }
}
