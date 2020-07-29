package com.game.roullet.controller;

import com.game.roullet.response.PlayerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class PlayerControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testCreatePlayer() {

        PlayerResponse expected = new PlayerResponse(1);
        ResponseEntity<PlayerResponse> response = restTemplate.exchange(
                createURLWithPort("/players"), HttpMethod.POST, null, PlayerResponse.class);
        PlayerResponse actual = response.getBody();
        assertEquals(actual, expected);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
