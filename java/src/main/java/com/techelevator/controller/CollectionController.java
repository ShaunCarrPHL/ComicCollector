package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CollectionController {

    private CollectionDao collectionDao;
    private ComicDao comicDao;

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
 // create collection, add comic to collection, update collection ie rename collection

    //should this be a boolean?
    @ResponseStatus
    @RequestMapping ( path = "/collection/", method = RequestMethod.POST)
    public int createCollection(@RequestParam String collectionName, int userId) throws Exception {
        return collectionDao.createCollection(collectionName, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/collection/{collectionId}", method = RequestMethod.POST)
    public boolean addComicToCollection(@RequestParam int marvelId, String comicTitle, String imageUrl, String description, int collectionId) throws Exception {
        return collectionDao.addComicToCollection(marvelId, comicTitle, imageUrl, description, collectionId);
    }

}
