package com.sushavi.stats.it;

import com.sushavi.stats.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql({ "/schema.sql", "/data.sql" })
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.profiles.active=test"})
class PlayerControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    void testGetPlayers() throws Exception {
        var values = this.restTemplate
                .getForObject("http://localhost:" + port + "/api/fetch-all-players", List.class);
        assertNotNull(values);
        assertEquals(4, values.size());
    }

    @Test
    void testSavePlayer() throws Exception {
        PlayerDto playerDto = createPlayer("Test Player", "Test Role", 1L);
        var responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save-player", playerDto, String.class);
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals("Player saved successfully", responseEntity.getBody());
    }

    @Test
    void testSavePlayerNegative() throws Exception {
        PlayerDto playerDto = createPlayer("TSachin", "TBAT", 245L);
        var responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/api/save-player", playerDto, String.class);
        assertEquals(400, responseEntity.getStatusCode().value());
        assertEquals("Player already exists", responseEntity.getBody());
    }

    @Test
    void testGetPlayersByCountry() throws Exception {
        var values = this.restTemplate
                .getForObject("http://localhost:" + port + "/api/fetch-all-players-by-country?idCountry=91", List.class);
        assertNotNull(values);
        assertEquals(3, values.size());
    }
    private static PlayerDto createPlayer(String playerName, String playerRole, long idCountry) {
        return PlayerDto.builder()
                .playerName(playerName)
                .playerRole(playerRole)
                .idCountry(idCountry)
                .build();
    }
}