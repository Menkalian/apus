CREATE DATABASE IF NOT EXISTS apus;
USE apus;
CREATE TABLE predefined
(
    id      INT NOT NULL AUTO_INCREMENT,
    name_de VARCHAR(255),
    name_en VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE action_type
(
    id    INT NOT NULL AUTO_INCREMENT,
    value VARCHAR(32),

    PRIMARY KEY (id)
);

INSERT INTO action_type(value)
VALUES
    ('CREATED'),
    ('DELETED');

CREATE TABLE `change`
(
    id          INT NOT NULL AUTO_INCREMENT,
    timestamp   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type        INT,
    affected_id INT,

    PRIMARY KEY (id),
    CONSTRAINT fk_change_type
        FOREIGN KEY (type) REFERENCES action_type (id),
    CONSTRAINT fk_change_affected
        FOREIGN KEY (affected_id) REFERENCES predefined (id)
);

CREATE TABLE list
(
    id     INT NOT NULL AUTO_INCREMENT,
    handle VARCHAR(36),
    name   VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE item
(
    id      INT NOT NULL AUTO_INCREMENT,
    list_id INT,
    custom  TINYINT,
    value   VARCHAR(255),
    amount  INT,
    comment TEXT,

    PRIMARY KEY (id),
    CONSTRAINT fk_item_list
        FOREIGN KEY (list_id) REFERENCES list (id)
);