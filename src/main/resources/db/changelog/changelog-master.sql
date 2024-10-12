CREATE TABLE documents
(
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    type          VARCHAR(50)  NOT NULL,
    body          TEXT,
    creation_date DATE         NOT NULL,
    signed_date   DATE,
    user_login    VARCHAR(100) NOT NULL
);
