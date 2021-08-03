DROP TABLE IF EXISTS User;
CREATE TABLE User(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     user_name VARCHAR(255) NOT NULL,
                     password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Task;
CREATE TABLE Task(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     user_id BIGINT NOT NULL REFERENCES User(id)
);

DROP TABLE IF EXISTS Card;
CREATE TABLE Card(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     done BOOLEAN NOT NULL,
                     task_id BIGINT NOT NULL REFERENCES Task(id)
);

DROP TABLE IF EXISTS Sub_task;
CREATE TABLE Sub_task(
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255) NOT NULL,
                     done BOOLEAN NOT NULL,
                     card_id BIGINT NOT NULL REFERENCES Card(id)
);