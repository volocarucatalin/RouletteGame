package com.game.roullet.service;

import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJoinRoom {

    public static final String PLAYER_DOSE_NOT_EXIST = "Player dose not exist";
    public static final String ROOM_DOSE_NOT_EXIST_ = "Room dose not exist ";
    public static final String PLAYER_IS_REGISTERED_ALREADY_TO_ANOTHER_ROOM = "Player is registered already to another room";
    public static final String ROOM_IS_FULL = "Room is full";
    public static final String ROOM_WAS_CLOSED = "Room was closed";
    public static final int INVALID_PLAYER = 2;
    public static final int VALID_PLAYER = 1;
    public static final int VALID_ROOM = 1;
    public static final int INVALID_ROOM = 2;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    RoomRepository roomRepository;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    RegistrationRepository registrationRepository;

    @InjectMocks
    RoomService roomService;

    Optional<Player> optionalEmpty;

    Room room;

    Optional<Room> roomOptionalEmpty;

    Optional<Room> roomOptional;

    Player player;

    Optional<Player> playerOptional;

    @Before
    public void init() {
        optionalEmpty = Optional.empty();
        room = new Room();
        room.setId(1);
        roomOptionalEmpty = Optional.empty();
        player = new Player();
        player.setId(1);
        playerOptional = Optional.of(player);
        roomOptional = Optional.of(room);
        when(playerRepository.findById(INVALID_PLAYER)).thenReturn(optionalEmpty);
        when(playerRepository.findById(VALID_PLAYER)).thenReturn(playerOptional);
        when(roomRepository.findById(VALID_ROOM)).thenReturn(roomOptional);
        when(roomRepository.findById(INVALID_ROOM)).thenReturn(roomOptionalEmpty);
    }


    @Test
    public void testJoinRoomNotFoundPlayer() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(PLAYER_DOSE_NOT_EXIST);
        roomService.joinRoom(INVALID_PLAYER, VALID_ROOM);
    }


    @Test
    public void testRoomNotExist() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(ROOM_DOSE_NOT_EXIST_);
        roomService.joinRoom(VALID_PLAYER, INVALID_ROOM);
    }

    @Test
    public void testPlayerIsPresentInOtherRoom() {
        Registration registration = new Registration();
        registration.setPlayer(player);
        registration.setRoom(room);
        player.setRegistration(registration);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(PLAYER_IS_REGISTERED_ALREADY_TO_ANOTHER_ROOM);
        roomService.joinRoom(VALID_PLAYER, VALID_ROOM);
    }

    @Test
    public void testRoomIsFull() {
        room.setRegistrations(List.of(new Registration(), new Registration(), new Registration(), new Registration()));
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(ROOM_IS_FULL);
        roomService.joinRoom(VALID_PLAYER, VALID_ROOM);
    }

    @Test
    public void testRoomIsClosed() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(ROOM_WAS_CLOSED);
        roomService.joinRoom(VALID_PLAYER, VALID_ROOM);
    }


    @Test
    public void testJoinRoom() {
        Registration registration = new Registration();
        room.setRegistrations(List.of(registration));
        roomService.joinRoom(3, VALID_ROOM);
    }

}
