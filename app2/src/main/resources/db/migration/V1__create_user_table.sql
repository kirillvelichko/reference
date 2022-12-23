create table "user"
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR NOT NULL,
    last_name  VARCHAR NOT NULL
);

insert into "user" (first_name, last_name)
values ('Ivan', 'Ivanov');