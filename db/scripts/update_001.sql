create table if not exists des (
    id serial primary key,
    name varchar(255),
    text text,
    link varchar(500),
    created timestamp
);