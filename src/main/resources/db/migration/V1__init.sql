CREATE TABLE person (
	id BIGINT primary key auto_increment,
	first_name varchar(255) not null,
	lastt_name varchar(255) not null
);

insert into person (first_name, lastt_name) values ('Dave', 'Syer');