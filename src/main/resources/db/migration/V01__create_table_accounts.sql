create table accounts (
    id uuid NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    nickname varchar(255),
    birthday date NOT NULL,
    email varchar(255) NOT NULL,
    telephone varchar(255) NOT NULL,
    balance decimal(12, 2)
);

insert into accounts(id, first_name, last_name, nickname, birthday, email, telephone, balance) VALUES
('16a0aec3-4e35-403f-886d-ab4e83da40cb', 'Helena', 'Polônio Santos', 'Lena', '2000-02-29', 'lena.sa@gmail.com', '085987860522', 1000000.00);