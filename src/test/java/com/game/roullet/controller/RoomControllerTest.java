package com.game.roullet.controller;

import com.game.roullet.request.RoomRequest;
import com.game.roullet.response.PlayerResponse;
import com.game.roullet.response.RoomResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testCreateRoom() {
        createPlayer();
        RoomResponse expected = new RoomResponse(1);
        HttpEntity<RoomRequest> request = new HttpEntity<>(new RoomRequest(1), new HttpHeaders());

        ResponseEntity<RoomResponse> response = restTemplate.exchange(
                createURLWithPort("/rooms"), HttpMethod.POST, request, RoomResponse.class);

        RoomResponse actual = response.getBody();
        assertEquals(actual, expected);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private void createPlayer() {
        restTemplate.exchange(createURLWithPort("/players"), HttpMethod.POST, null, PlayerResponse.class);
    }
}
