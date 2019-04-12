CREATE DATABASE waterfallRest;

CREATE TABLE waterfall_info (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(75) NOT NULL,
  location VARCHAR(50) NOT NULL,
  coordinates VARCHAR(50) NOT NULL,
  image_file VARCHAR(250)
);

INSERT INTO waterfall_info (name, location, coordinates, image_file) VALUES ('Kalandula Falls', 'Calandula, Angola', '9&deg;4&prime;33&Prime;S 16&deg;0&prime;12&Prime;E', 'Kalandula_waterfalls_of_the_Lucala-River_in_Malange,_Angola_(2).JPG');
INSERT INTO waterfall_info (name, location, coordinates) VALUES ('Kagera waterfalls', 'Southeastern Burundi', '3.830°S 30.080°E');
INSERT INTO waterfall_info (name, location, coordinates, image_file) VALUES ('Rusumo Falls', 'Rawanda', '2_22_31_S, 30_47_33_E', 'RusumoFalls1.jpg');