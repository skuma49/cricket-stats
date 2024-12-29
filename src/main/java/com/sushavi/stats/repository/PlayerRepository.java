package com.sushavi.stats.repository;

import com.sushavi.stats.entity.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Optional<Player> findByPlayerNameAndPlayerRole(String playerName, String playerRole);

    Optional<Player> findByPlayerName(String playerName);

    List<Player> findAllByIdCountry(Long idCountry);
}
