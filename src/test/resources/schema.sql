DROP TABLE IF EXISTS Player;

CREATE TABLE Player(
  PLAYER_ID INT AUTO_INCREMENT  PRIMARY KEY,
  PLAYER_NAME VARCHAR(250) NOT NULL,
  PLAYER_ROLE VARCHAR(250) NOT NULL,
  ID_COUNTRY INT NOT NULL
);