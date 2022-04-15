INSERT INTO comic(marvel_id, image, title, description)
	VALUES (123, 'imageWOWSOCOOL', 'MR. WOW SO COOL', 'WOW MR WOW SO COOL IS SO COOL WOW')
	RETURNING comic_id;
	
	