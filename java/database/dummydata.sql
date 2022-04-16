INSERT INTO comic(marvel_id, image, title, description)
	VALUES (123, 'imageWOWSOCOOL', 'MR. WOW SO COOL', 'WOW MR WOW SO COOL IS SO COOL WOW')
	RETURNING comic_id;
	
	
	INSERT INTO collection(collection_id, collection_name, user_id, private)
	VALUES (999,'cool collection',3,FALSE);

	
	SELECT * FROM collection;
	
	SELECT * FROM collection WHERE  = 999;
	SELECT * FROM collection WHERE user_id = 3;
	SELECT * FROM collection WHERE collection_id = 999;
	
	INSERT INTO collection(collection_name,user_id)
                VALUES('calm collection',3) RETURNING collection_id;
	