CREATE TABLE Players (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(50) NOT NULL
);

CREATE TABLE Matches (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         player1_id INT,
                         player2_id INT,
                         winner_id INT,
                         FOREIGN KEY (player1_id) REFERENCES Players(id),
                         FOREIGN KEY (player2_id) REFERENCES Players(id)
);
