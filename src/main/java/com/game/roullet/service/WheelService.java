package com.game.roullet.service;

import com.game.roullet.entity.Bet;
import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Spin;
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
        //TODO: 1. Verify if its admin
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

        //TODO: 2. Verify if the room its open
        if (roomId != registration.getRoom().getId()) {
            throw new RuntimeException("Player is registered in another room");
        }

        String roomStatus = registration.getRoom().getStatus();
        if (roomStatus.equals("close")) {
            throw new RuntimeException("Room is close");
        }

        //TODO: 3. Generate number
        int spinNumber = rouletteService.revealNumber();

        //TODO: 4. Get all open bets from the room
        rouletteService.handleBets(player, spinNumber);

        //TODO: 5. Iterate all best and close them
        List<Bet> betList = player.getBet();
        for (Bet bet : betList) {
            bet.setStatus("close");
            betRepository.save(bet);
        }




    }
}
