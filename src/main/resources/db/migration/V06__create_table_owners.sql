CREATE TABLE owners (
    id uuid NOT NULL PRIMARY KEY,
    first_name varchar(40) NOT NULL,
    last_name varchar(40) NOT NULL,
    nickname varchar(40),
    birth_date date NOT NULL,
    email varchar(60) NOT NULL UNIQUE,
    telephone varchar(14) NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL
);

ALTER TABLE accounts ADD owner_id uuid UNIQUE REFERENCES owners(id);

INSERT INTO owners(id, first_name, last_name, nickname, birth_date, email, telephone, created_at, updated_at) VALUES
('16a0aec3-4e35-403f-886d-ab4e83da40cb', 'Helena', 'Polônio Santos', 'Lena', '2000-02-29', 'lena.sa@gmail.com', '85987860522', '2022-09-19 10:00:00', '2022-09-19 10:00:00');

INSERT INTO accounts (id, balance, created_at, updated_at, agency, number, owner_id) VALUES
('01ee0bcb-4dd0-441e-a762-95b24c90ae88', 10000.00, '2022-09-19 10:00:00', '2022-09-19 10:00:00', '0864', '31234123-1', '16a0aec3-4e35-403f-886d-ab4e83da40cb');