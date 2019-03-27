create table all_users
(
	id serial
		constraint all_users_pk
			primary key,
	name varchar(30) not null,
	password varchar(60) not null,
	email varchar(30)
);

create unique index all_users_name_uindex
	on all_users (name);

