package com.sushavi.stats;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CricketStatsApplicationTests {

	@Test
	void contextLoads() {
		String application = "cricket-stats";
		assertEquals("cricket-stats", application);
	}

}
