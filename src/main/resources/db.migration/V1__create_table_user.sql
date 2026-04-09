CREATE TABLE usuario(
    id serial PRIMARY KEY,
    username varchar(244),
    email varchar(244) UNIQUE NOT NULL,
    senha varchar(244)
)