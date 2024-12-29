package com.sushavi.stats.repository;

import com.sushavi.stats.TestDataCreator;
import com.sushavi.stats.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class PlayerRepositoryTest {

        @Autowired
        PlayerRepository playerRepository;

        @Test
        void save() {
            playerRepository.save(TestDataCreator.getInstance().getMockedPlayerDB().get(0));

            var players = (List<Player>)playerRepository.findAllById(List.of(1L));
            assertNotNull(players);
            assertEquals(1, players.size());
        }

        @Test
        void findAll() {
            var players = (List<Player>)playerRepository.findAll();
            assertNotNull(players);
            assertEquals(4, players.size());
        }


}