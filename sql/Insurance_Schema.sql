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

create table claims(
	claim_id int primary key auto_increment,
    status_id int not null,
    claim_description varchar(5000) not null,
    claim_cost decimal(10,2) not null,
    adjuster_notes varchar(5000) not null,
    vehicle_vin varchar(17) not null,
    username varchar(512) not null,
    constraint fk_claims_status_id
    foreign key (status_id)
    references claims_status(status_id),
    constraint fk_claims_vehicle_vin
    foreign key (vehicle_vin)
    references vehicles(vehicle_vin),
    constraint fk_claims_username
    foreign key (username)
    references users(username)
);

create table claims_status(
	status_id int primary key auto_increment,
    status_name varchar(40) not null
);

create table files(
	id varchar(500),
    `data` mediumblob,
    `name` varchar(500),
    `type` varchar(100),
    claim_id int
)
    