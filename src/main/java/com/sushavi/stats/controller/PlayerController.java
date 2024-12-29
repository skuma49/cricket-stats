package com.sushavi.stats.controller;

import com.sushavi.stats.cc.LogExecutionTime;
import com.sushavi.stats.dto.PlayerDto;
import com.sushavi.stats.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;
    @GetMapping("/fetch-all-players")
    public List<PlayerDto> fetchAllPlayers(){
        log.info("API Call to fetch all the players");
        return playerService.getPlayers();
    }

    @LogExecutionTime
    @PostMapping("/save-player")
    public ResponseEntity<String> savePlayer(@RequestBody PlayerDto playerDto){
        log.info("API Call to save the player into the Database {}", playerDto);
        try{
            playerService.savePlayer(playerDto);
            return ResponseEntity.ok("Player saved successfully");
        } catch (Exception exception){
            log.error("Error found in saving the record", exception);
            return ResponseEntity.badRequest()
                    .body(exception.getMessage());
        }
    }

    @LogExecutionTime
    @GetMapping("/fetch-single-players")
    public PlayerDto fetchSinglePlayer(@RequestParam String playerName){
        log.info("API Call to fetch Player Data for {}", playerName);
        return playerService.getSinglePlayer(playerName);
    }


    @LogExecutionTime
    @GetMapping("/fetch-all-players-by-country")
    public List<PlayerDto> fetchAllPlayersByCountry(@RequestParam Long idCountry){
        log.info("API Call to fetch all players for Country : {}", idCountry);
        return playerService.getPlayersByCountry(idCountry);
    }
}
