DROP TABLE IF EXISTS CAR;

CREATE TABLE CAR (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(64) NOT NULL,
  price	DOUBLE,
  model_year INT
);