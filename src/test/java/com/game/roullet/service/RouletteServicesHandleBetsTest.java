package com.game.roullet.service;

import com.game.roullet.entity.Bet;
import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.*;
import com.game.roullet.request.BetRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RouletteServicesHandleBetsTest {
    private static final byte SPIN_NUMBER = 25;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    PlayerService playerService;


    @InjectMocks
    RouletteService rouletteService;



    List<Player> playerList = new ArrayList<>();
    @Before
    public void init() {
    }


    @Test
    public void testHandleBets(){

        Player player1 = new Player();
        Player player2 = new Player();
        player1.setId(1);
        player2.setId(2);

        player1.setBet(Arrays.asList(new Bet(1,"row",20,1,null)));
        player2.setBet(Arrays.asList(new Bet(2,"red",20,-1,null)));

        playerList.add(player1);
        playerList.add(player2);
        rouletteService.handleBets(playerList , SPIN_NUMBER);
        verify(playerService).receiveWiningAmount(player1.getId(), (20 * 3));
        verify(playerService).receiveWiningAmount(player2.getId(),(20 * 2));

    }

}
