package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import com.techelevator.model.Collection;
import com.techelevator.model.MarvelModel.MarvelComic;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class CollectionController {

    private CollectionDao collectionDao;
    private ComicDao comicDao;

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
 // create collection, add comic to collection, update collection ie rename collection

    //should this be a boolean?
    @ResponseStatus
    @RequestMapping ( path = "/collection", method = RequestMethod.POST)
    public int createCollection(@RequestParam String collectionName, int userId) throws Exception {
        return collectionDao.createCollection(collectionName, userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/collection/comic/{collectionId}", method = RequestMethod.POST)
    public boolean addComicToCollection(@RequestParam int marvelId, String comicTitle, String imageUrl, String description, int collectionId) throws Exception {
        return collectionDao.addComicToCollection(marvelId, comicTitle, imageUrl, description, collectionId);
    }


    @RequestMapping(path ="/collection/list", method = RequestMethod.GET)
    public List<Collection> listAllCollections() {
        return collectionDao.listAllCollections();
    }
    @RequestMapping(path ="/collection/{userId}", method = RequestMethod.GET)
    public List<Collection> getAllCollectionsByUserId(@PathVariable int userId) {
        return collectionDao.getCollectionsByUserId(userId);

    }

    @RequestMapping(path ="/collection/{collectionId}", method = RequestMethod.GET)
    public Collection getCollectionById(@PathVariable int collectionId) {
        return collectionDao.getCollectionById(collectionId);

    }
}
