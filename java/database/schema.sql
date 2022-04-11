BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS comic;
DROP TABLE IF EXISTS collection;
DROP TABLE IF EXISTS comic_collection;
DROP TABLE IF EXISTS hero;
DROP TABLE IF EXISTS comic_hero;
DROP TABLE IF EXISTS publisher;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS series;

DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_comic_id;
DROP SEQUENCE IF EXISTS seq_collection_id;
DROP SEQUENCE IF EXISTS seq_comic_collection_id;
DROP SEQUENCE IF EXISTS seq_hero_id;
DROP SEQUENCE IF EXISTS seq_comic_hero_id;
DROP SEQUENCE IF EXISTS seq_publisher_id;
DROP SEQUENCE IF EXISTS seq_account_id;
DROP SEQUENCE IF EXISTS seq_series_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_comic_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_collection_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_comic_collection_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_hero_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_comic_hero_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_publisher_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_series_id
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

CREATE TABLE collection (
	collection_id int DEFAULT nextval('seq_collection_id'::regclass) NOT NULL,
	collection_name varchar(50) NOT NULL,
	-- check to make sure doesnt start with true etc.
	private boolean NOT NULL,
	CONSTRAINT PK_collection PRIMARY KEY (collection_id)
);

CREATE TABLE hero (
	hero_id int DEFAULT nextval('seq_hero_id'::regclass) NOT NULL,
	hero_name varchar(75) NOT NULL,
	CONSTRAINT PK_hero PRIMARY KEY (hero_id)
);

CREATE TABLE publisher (
	publisher_id int DEFAULT nextval('seq_publisher_id'::regclass) NOT NULL,
	publisher_name varchar(75) NOT NULL,
	CONSTRAINT PK_publisher PRIMARY KEY (publisher_id)
);

CREATE TABLE series (
	series_id int DEFAULT nextval('seq_series_id'::regclass) NOT NULL,
	series_name varchar(100) NOT NULL,
	CONSTRAINT PK_series PRIMARY KEY (series_id)
);

CREATE TABLE comic (
	comic_id int DEFAULT nextval('seq_comic_id'::regclass) NOT NULL,
	publisher_id int REFERENCES publisher (publisher_id),
	series_id int REFERENCES series (series_id),
	author varchar(100) NOT NULL,
	image varchar(300) NOT NULL,
	title varchar(50) NOT NULL,
--	release_date date DEFAULT current_date,
	CONSTRAINT PK_comic PRIMARY KEY (comic_id)
	
);

CREATE TABLE account (
	account_id int DEFAULT nextval('seq_account_id'::regclass) NOT NULL,
	collection_id int REFERENCES collection (collection_id),
--	transfer_id int REFERENCES transfer (transfer_id),
	username varchar(50) REFERENCES users (username),
	CONSTRAINT PK_account PRIMARY KEY (account_id)
);

CREATE TABLE comic_collection (
	comic_collection_id int DEFAULT nextval('seq_comic_collection_id'::regclass) NOT NULL,
	comic_id int REFERENCES comic (comic_id),
	collection_id int REFERENCES collection (collection_id),
	CONSTRAINT PK_comic_collection PRIMARY KEY (comic_collection_id)
);



CREATE TABLE comic_hero (
	comic_hero_id int DEFAULT nextval('seq_comic_hero_id'::regclass) NOT NULL,
	hero_id int REFERENCES hero (hero_id),
	comic_id int REFERENCES comic (comic_id),
	CONSTRAINT PK_comic_hero PRIMARY KEY (comic_hero_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;
