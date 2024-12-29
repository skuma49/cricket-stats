package com.sushavi.stats;

import com.sushavi.stats.dto.PlayerDto;
import com.sushavi.stats.entity.Player;

import java.util.List;

public class TestDataCreator {
    public static TestDataCreator getInstance(){
        return new TestDataCreator();
    }
    public List<PlayerDto> getMockedPlayer() {
        return List.of(getPlayerDto( "Sushant", "Batsman", 91L),
                getPlayerDto( "Shlok", "Bowler", 44L),
                getPlayerDto( "Madhavi", "Wicketkeeper", 91L));
    }

    public List<Player> getMockedPlayerDB() {
        return List.of(getPlayers( 1L, "Sushant", "Batsman", 91L),
                getPlayers( 2L, "Shlok", "Bowler", 44L),
                getPlayers( 3L, "Madhavi", "Wicketkeeper", 91L));
    }

    private static PlayerDto getPlayerDto( String playerName, String playerRole, long idCountry) {
        return PlayerDto.builder()
                .playerName(playerName)
                .playerRole(playerRole)
                .idCountry(idCountry)
                .build();
    }

    private static Player getPlayers(long idPlayer, String playerName, String playerRole, long idCountry) {
        return Player.builder()
                .playerId(idPlayer)
                .playerName(playerName)
                .playerRole(playerRole)
                .idCountry(idCountry)
                .build();
    }
}
