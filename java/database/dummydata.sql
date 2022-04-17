INSERT INTO comic(marvel_id, image, title, description)
	VALUES (123, 'imageWOWSOCOOL', 'MR. WOW SO COOL', 'WOW MR WOW SO COOL IS SO COOL WOW')
	RETURNING comic_id;
	
	
	INSERT INTO collection(collection_id, collection_name, user_id, private)
	VALUES (999,'cool collection',3,FALSE);
	
    SELECT * FROM comic WHERE comic_id = 2;
	
	SELECT * FROM comic_collection;
	SELECT * FROM users;
	
	SELECT * FROM collection WHERE  = 3;
	SELECT * FROM collection WHERE user_id = 3;
	SELECT * FROM collection;
	
	INSERT INTO collection(collection_name,user_id)
                VALUES('calm collection',3) RETURNING collection_id;
				
				SELECT * FROM comic JOIN comic_collection 
				ON comic.comic_id = comic_collection.comic_id 
				WHERE collection_id = 2;
				
		UPDATE collection SET collection_name = 'sleep collection', user_id = 3, private = true
                WHERE collection_id = 7;
	