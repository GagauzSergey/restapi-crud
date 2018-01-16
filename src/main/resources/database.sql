-- Table: Tester
CREATE TABLE tester (
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(255) NOT NULL,
  lastname  VARCHAR(255) NOT NULL,
  position  VARCHAR(255) NOT NULL,
  salary INT NOT NULL
)
  ENGINE = InnoDB;

-- Data Insert to table: user
INSERT INTO tester VALUE (1, 'Sergii', 'Gagauz', 'JUNIOR', 2000);
INSERT INTO tester VALUE (2, 'Vitalii', 'Volkov', 'TESTLEAD', 10000);



