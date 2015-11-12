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
    firstName varchar(64),
    lastName varchar(64),
    email varchar(64),
    password varchar(64),
    city varchar(64),
    address varchar(64),
    zipCode varchar(64)
);