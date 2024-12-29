package com.sushavi.stats.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDto {
    private String playerName;
    private String playerRole;
    private Long idCountry;
}
