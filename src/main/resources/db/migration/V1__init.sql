create table dictionary
(
    id int primary key,
    name varchar(64),
    code varchar(8),
    value varchar(64)
);

create table user
(
    id int primary key,
    firstName varchar(64) not null,
    lastName varchar(64) not null,
    email varchar(64) unique,
    password varchar(64) not null,
    city varchar(64),
    address varchar(64),
    zipCode varchar(64)
);