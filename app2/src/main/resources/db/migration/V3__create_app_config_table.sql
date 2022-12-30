create table app_config
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR NOT NULL,
    value       VARCHAR NOT NULL,
    description VARCHAR NOT NULL
);

create unique index app_config_name_idx on app_config (name);

insert into app_config (name, value, description)
values ('signature', 'Warmest regards.', 'Signature to hello message');