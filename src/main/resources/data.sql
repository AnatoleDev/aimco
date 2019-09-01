DROP TABLE IF EXISTS calculator;

CREATE TABLE calculator
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    date       date         not null,
    result     float        NOT NULL,
    expression VARCHAR(250) NOT NULL
);