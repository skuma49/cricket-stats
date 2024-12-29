package com.sushavi.stats;

import com.sushavi.stats.controller.PlayerController;
import com.sushavi.stats.service.PlayerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = CricketStatsApplication.class, properties = {"spring.profiles.active=test"})
public class ContractVerifierBaseClass {

    @Autowired
    PlayerController playerController;

    @BeforeEach
    public void setup(){
        RestAssuredMockMvc.standaloneSetup(playerController);
    }
}