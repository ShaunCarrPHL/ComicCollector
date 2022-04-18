BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS comic;
DROP TABLE IF EXISTS collection;
DROP TABLE IF EXISTS comic_collection;
DROP TABLE IF EXISTS hero;
DROP TABLE IF EXISTS comic_hero;
DROP TABLE IF EXISTS creator;
DROP TABLE IF EXISTS creator_comic;
DROP TABLE IF EXISTS series;
DROP TABLE IF EXISTS series_comic;

DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE comic (
	comic_id serial,
	marvel_id int NOT NULL,
	image varchar(300),
	title varchar(200) NOT NULL,
	description varchar(2000),
	CONSTRAINT PK_comic PRIMARY KEY (comic_id)
);

CREATE TABLE collection (
	collection_id serial,
	collection_name varchar(50) NOT NULL,
	user_id int NOT NULL,
	private boolean DEFAULT true,
	CONSTRAINT PK_collection PRIMARY KEY (collection_id),
	CONSTRAINT fk_collection_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comic_collection (
	comic_id int NOT NULL,
	collection_id int NOT NULL,
	CONSTRAINT FK_comic_collection_comic FOREIGN KEY (comic_id) REFERENCES comic(comic_id),
	CONSTRAINT FK_comic_collection_collection FOREIGN KEY (collection_id) REFERENCES collection(collection_id)
);

CREATE TABLE hero (
	hero_id serial,
	hero_name varchar(75) NOT NULL,
	CONSTRAINT PK_hero PRIMARY KEY (hero_id)
);

CREATE TABLE comic_hero (
	hero_id int NOT NULL,
	comic_id int NOT NULL,
	CONSTRAINT FK_comic_hero_hero FOREIGN KEY (hero_id) REFERENCES hero(hero_id),
	CONSTRAINT FK_comic_hero_comic FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);

CREATE TABLE creator (
	creator_id serial,
	creator_name varchar(100) NOT NULL,
	CONSTRAINT PK_creator PRIMARY KEY (creator_id)
);

CREATE TABLE creator_comic (
	creator_id int NOT NULL,
	comic_id int NOT NULL,
	CONSTRAINT FK_creator_comic_creator FOREIGN KEY (creator_id) REFERENCES creator(creator_id),
	CONSTRAINT FK_creator_comic_comic FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);

CREATE TABLE series (
	series_id serial,
	series_name varchar(100) NOT NULL,
	CONSTRAINT PK_series PRIMARY KEY (series_id)
);	

CREATE TABLE series_comic (
	series_id int NOT NULL,
	comic_id int NOT NULL,
	CONSTRAINT FK_series_comic_series FOREIGN KEY (series_id) REFERENCES series(series_id),
	CONSTRAINT FK_seriesr_comic_comic FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);











INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
