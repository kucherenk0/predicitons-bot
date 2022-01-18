create sequence hibernate_sequence start with 1 increment by 1
create table color (id bigint not null, base_color varchar(200), deleted boolean, hex_code varchar(200), name varchar(200), primary key (id))
create table color_description (id bigint not null, base_color varchar(200), deleted boolean, text varchar(200), primary key (id))
create table prediction (id bigint not null, deleted boolean, text varchar(200), state varchar(200), primary key (id))
create table user (id bigint not null, chat_id varchar(200), deleted boolean, message_history varchar(10000), tg_id varchar(200), tg_name varchar(1000), state varchar(200), primary key (id))
