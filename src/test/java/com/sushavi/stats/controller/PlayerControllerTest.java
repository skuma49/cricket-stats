package com.sushavi.stats.controller;

import com.sushavi.stats.TestDataCreator;
import com.sushavi.stats.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @InjectMocks
    PlayerController playerController;

    @Mock
    PlayerService playerService;

    @Test
    void getPlayers() {
        when(playerService.getPlayers()).thenReturn(TestDataCreator.getInstance().getMockedPlayer());
        var players = playerController.fetchAllPlayers();

        assertNotNull(players);
        assertEquals(3, players.size());
    }

    @Test
    void savePlayer() {
        playerController.savePlayer(TestDataCreator.getInstance().getMockedPlayer().get(0));
        Mockito.verify(playerService, Mockito.times(1)).savePlayer(any());
    }
}