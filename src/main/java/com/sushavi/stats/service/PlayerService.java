package com.sushavi.stats.service;

import com.sushavi.stats.cc.LogExecutionTime;
import com.sushavi.stats.dto.PlayerDto;
import com.sushavi.stats.entity.Player;
import com.sushavi.stats.exceptions.StatsException;
import com.sushavi.stats.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    @Value("${club.fetch.enabled}")
    private String clubFetchEnabledFlag;

    private final PlayerRepository playerRepository;

    @LogExecutionTime
    public List<PlayerDto> getPlayers() {

        log.info("Entering inside getPlayers  -- clubFetchEnabledFlag : [{}] ", clubFetchEnabledFlag);
        List<PlayerDto> playerDtoList = null;
        if("on".equals(clubFetchEnabledFlag)){
            var playersList = (List<Player>) playerRepository.findAll();
            playersList.stream().sorted();
            log.info("No of Players found :: {}", playersList.size());
            playerDtoList = convertPlayersToDto(playersList);
        }
        return playerDtoList;
    }

    public PlayerDto getSinglePlayer(String playerName){
        log.info("Entering inside getSinglePlayer  -- playerName : [{}] ", playerName);
        PlayerDto playerDto = null;

        AtomicReference<Player> playerAtomicReference = new AtomicReference<>();

        playerRepository.findByPlayerName(playerName).ifPresentOrElse(
                player -> {
                    log.info("Player [ {} ] already exists with ID {}", player.getPlayerName(), player.getPlayerId());
                    playerAtomicReference.set(player);
                },
                () -> {
                    log.info("Player [ {} ] does not exist in the database", playerName);
                    throw new StatsException("Player does not exist");
                });

        playerDto  = getPlayerDto(playerAtomicReference.get());

        return playerDto;
    }

    private List<PlayerDto> convertPlayersToDto(List<Player> playersList) {
        return playersList.stream().map(this::getPlayerDto).collect(Collectors.toList());
    }

    public PlayerDto savePlayer(PlayerDto playerDto) {
        var player = getPlayer(playerDto);

        playerRepository.findByPlayerNameAndPlayerRole(playerDto.getPlayerName(), playerDto.getPlayerRole()).ifPresentOrElse(
                existingPlayer -> {
                    log.info("Player [ {} - {} ] already exists with ID {}", existingPlayer.getPlayerName(), existingPlayer.getPlayerRole(), existingPlayer.getPlayerId());
                    throw new StatsException("Player already exists");
                },
                () -> {
                    log.info("Player [ {} - {} ] does not exist in the database", playerDto.getPlayerName(), playerDto.getPlayerRole());
                    var savedPlayer = playerRepository.save(player);
                    log.info("Player [ {} - {} ] saved successfully with ID {}", savedPlayer.getPlayerName(), savedPlayer.getPlayerRole(), savedPlayer.getPlayerId());
                });
        return playerDto;
    }

    private Player getPlayer(PlayerDto player) {
        return Player.builder()
                .playerName(player.getPlayerName())
                .playerRole(player.getPlayerRole())
                .idCountry(player.getIdCountry())
                .build();
    }

    private PlayerDto getPlayerDto(Player player) {
        return PlayerDto.builder()
                .playerName(player.getPlayerName())
                .playerRole(player.getPlayerRole())
                .idCountry(player.getIdCountry())
                .build();
    }

    public List<PlayerDto> getPlayersByCountry(Long idCountry) {
        log.info("Entering inside getPlayersByCountry  -- idCountry : [{}] ", idCountry);
        List<PlayerDto> playerDtoList = null;
        var playersList = playerRepository.findAllByIdCountry(idCountry);
        log.info("No of Players found :: {}", playersList.size());
        playerDtoList = convertPlayersToDto(playersList);
        return playerDtoList;
    }
}
