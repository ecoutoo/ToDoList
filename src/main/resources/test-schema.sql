DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS todolist CASCADE;

create table item (
	item_id integer generated by default as identity,
	text_body varchar(255) not null,
	task_done boolean not null,
	fk_list_id integer,
	primary key (item_id)
);
 
create table todolist (
	list_id integer generated by default as identity,
	title varchar(255),
    completed boolean,
	primary key (list_id)
);

alter table item add constraint FKdtss7ab4sx4oagfqyacono43a foreign key (fk_list_id) references todolist on delete cascade;