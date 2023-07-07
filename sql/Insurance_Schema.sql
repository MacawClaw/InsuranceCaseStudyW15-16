drop database if exists insurance;
create database insurance;
use insurance;

create table roles(
	role_id int primary key auto_increment,
    role_name varchar(100) not null
);

create table users(
	username varchar(512) primary key,
    user_password varchar(512) not null,
    enabled tinyint not null,
    user_fname varchar(512) not null,
    user_lname varchar(512) not null,
    user_email varchar(512) not null,
    user_phone varchar(512) not null,
    user_address varchar(512) not null
);

create table users_roles(
	role_id int not null,
    username varchar(512) not null,
    constraint fk_users_roles_role_id
    foreign key (role_id)
    references roles(role_id),
    constraint fk_users_roles_username
    foreign key (username)
    references users(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

create table vehicles(
	vehicle_vin varchar(17) primary key,
    vehicle_year int not null,
    vehicle_make varchar(512) not null,
    vehicle_model varchar(512) not null,
    username varchar(512) not null,
    constraint fk_vehicles_username
    foreign key (username)
    references users(username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);