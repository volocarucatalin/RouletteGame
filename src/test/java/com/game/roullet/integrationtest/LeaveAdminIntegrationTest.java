package com.game.roullet.integrationtest;

import com.game.roullet.entity.Player;
import com.game.roullet.entity.Registration;
import com.game.roullet.entity.Room;
import com.game.roullet.repository.PlayerRepository;
import com.game.roullet.repository.RegistrationRepository;
import com.game.roullet.repository.RoomRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeaveAdminIntegrationTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    TestEntityManager entityManager;

    Player player = new Player();
    Player player2 = new Player();
    Player player3 = new Player();
    Player player4 = new Player();
    Room room = new Room();


    @Test
    public void testLeaveAdminRoom() {
        playerRepository.save(player);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        //playerRepository.flush();

        Assert.assertEquals(4, playerRepository.count());
        Assert.assertEquals(0, registrationRepository.count());
        Assert.assertEquals(0, roomRepository.count());

        room = new Room();
        room.setRegistrations(new ArrayList<>());
        room = roomRepository.save(room);
        //Create a room
        Registration create1 = new Registration();
        create1.setRoom(room);
        create1.setPlayer(player);
        create1.setRole("Admin");
        registrationRepository.save(create1);
        entityManager.flush();


        //Create second  room
        room = new Room();
        Registration create2 = new Registration();
        room.getRegistrations().add(create2);
        create2.setRoom(room);
        create2.setPlayer(player2);
        create2.setRole("Admin");
        ;
        registrationRepository.saveAndFlush(create2);


        //Create join a room
        room = roomRepository.findById(2).get();
        Registration join2 = new Registration();
        room.getRegistrations().add(join2);
        join2.setRoom(room);
        join2.setPlayer(player3);
        join2.setRole("User");
        roomRepository.save(room);

        //Create join a room
        room = roomRepository.findById(2).get();
        Registration join3 = new Registration();
        room.getRegistrations().add(join3);
        join3.setRoom(room);
        join3.setPlayer(player4);
        join3.setRole("User");
        roomRepository.save(room);


        Assert.assertEquals(4, playerRepository.count());
        Assert.assertEquals(4, registrationRepository.count());
        Assert.assertEquals(2, roomRepository.count());

        room = roomRepository.findById(2).get();
        room.getRegistrations().clear();
        registrationRepository.deleteAllByRoom(room);

        Assert.assertEquals(2, roomRepository.count());
        Assert.assertEquals(4, playerRepository.count());
        Assert.assertEquals(1, registrationRepository.count());


    }
}
