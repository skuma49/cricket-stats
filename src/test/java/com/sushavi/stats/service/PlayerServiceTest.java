package com.sushavi.stats.service;

import com.sushavi.stats.TestDataCreator;
import com.sushavi.stats.repository.PlayerRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    PlayerService playerService;

    @Mock
    PlayerRepository playerRepository;

    @Test
    void getPlayers() {
        ReflectionTestUtils.setField(playerService, "clubFetchEnabledFlag", "on");
        when(playerRepository.findAll()).thenReturn(TestDataCreator.getInstance().getMockedPlayerDB());
        var players = playerService.getPlayers();

        assertNotNull(players);
        assertEquals(3, players.size());

    }

    @Test
    void savePlayer() {
        when(playerRepository.save(any())).thenReturn(TestDataCreator.getInstance().getMockedPlayerDB().get(0));
        playerService.savePlayer(TestDataCreator.getInstance().getMockedPlayer().get(0));

        Mockito.verify(playerRepository, Mockito.times(1)).save(any());
    }
}