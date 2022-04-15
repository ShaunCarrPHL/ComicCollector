package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
//@RestController
public class CollectionController {

    private CollectionDao collectionDao;
    private ComicDao comicDao;

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
 // create collection, add comic to collection, update collection ie rename collection
    //@RequestMapping(HttpStatus.CREATED)

}
