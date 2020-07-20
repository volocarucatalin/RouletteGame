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

import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RouletteServiceLeaveRoomTest {
    public static final String PLAYER_DOSE_NOT_EXIST = "Player dose not exist";
    public static final String ROOM_DOSE_NOT_EXIST_ = "Room dose not exist ";
    public static final int INVALID_PLAYER = 2;
    public static final int VALID_PLAYER = 1;
    public static final int VALID_ROOM = 1;
    public static final int INVALID_ROOM = 3;
    public static final int VALID_SECOND_ROOM = 2;
    public static final String ADMIN = "Admin";
    public static final String USER = "User";
    public static final int PLAYER_ID = 1;
    public static final int ROOM_ID = 1;
    public static final String PLAYER_IS_REGISTERED_ALREADY_TO_ANOTHER_ROOM = "Player is registered already to another room";
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

    Optional<Registration> registrationOptional;

    Registration registration = new Registration();

    Optional<Player> optionalEmpty;

    Room room = new Room();

    Room roomSecond = new Room();

    Optional<Room> roomOptionalEmpty;

    Optional<Room> roomOptional;

    Optional<Room> roomSecondOptional;

    Player player = new Player();

    Optional<Player> playerOptional;

    @Before
    public void init() {
        registrationOptional = Optional.of(registration);
        optionalEmpty = Optional.empty();
        room.setId(VALID_ROOM);
        roomOptionalEmpty = Optional.empty();
        roomSecond.setId(VALID_SECOND_ROOM);
        roomSecondOptional = Optional.of(roomSecond);
        player.setId(VALID_PLAYER);
        playerOptional = Optional.of(player);
        roomOptional = Optional.of(room);
        when(playerRepository.findById(INVALID_PLAYER)).thenReturn(optionalEmpty);
        when(playerRepository.findById(VALID_PLAYER)).thenReturn(playerOptional);
        when(roomRepository.findById(VALID_ROOM)).thenReturn(roomOptional);
        when(roomRepository.findById(VALID_SECOND_ROOM)).thenReturn(roomSecondOptional);
        when(roomRepository.findById(INVALID_ROOM)).thenReturn(roomOptionalEmpty);
        when(registrationRepository.findByPlayerIdAndRoomId(PLAYER_ID, ROOM_ID)).thenReturn(registration);
    }

    @Test
    public void testLeaveRoomPlayerNotFound() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(PLAYER_DOSE_NOT_EXIST);
        roomService.leaveRoom(INVALID_PLAYER, VALID_ROOM);
    }

    @Test
    public void testLeaveRoomPlayerAnotherRoom() {
        registration.setPlayer(player);
        registration.setRoom(room);
        registration.setRole("Admin");
        player.setRegistration(registration);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(PLAYER_IS_REGISTERED_ALREADY_TO_ANOTHER_ROOM);
        roomService.leaveRoom(VALID_PLAYER, VALID_SECOND_ROOM);
    }

    @Test
    public void testLeaveRoomNotExist() {
        player.setRegistration(registration);
        registration.setRoom(room);
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage(ROOM_DOSE_NOT_EXIST_);
        roomService.leaveRoom(VALID_PLAYER, INVALID_ROOM);
    }

    @Test
    public void testLeaveRoomUser() {
        registration.setRoom(room);
        registration.setPlayer(player);
        registration.setRole(USER);
        player.setRegistration(registration);
        roomService.leaveRoom(VALID_PLAYER, VALID_ROOM);
    }

    @Test
    public void testLeaveRoomAdmin() {
        registration.setRoom(room);
        registration.setPlayer(player);
        registration.setRole(ADMIN);
        player.setRegistration(registration);
        roomService.leaveRoom(VALID_PLAYER, VALID_ROOM);
    }
}


