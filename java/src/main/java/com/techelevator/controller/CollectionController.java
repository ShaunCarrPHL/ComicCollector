package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import com.techelevator.model.Collection;
import com.techelevator.model.CollectionDTO;
import com.techelevator.model.ComicDTO;
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

    public CollectionController(CollectionDao collectionDao, ComicDao comicDao) {
        this.collectionDao = collectionDao;
        this.comicDao = comicDao;
    }

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
 // create collection, add comic to collection, update collection ie rename collection

    //should this be a boolean?
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping ( path = "/collection", method = RequestMethod.POST)
    public int createCollection(@RequestBody CollectionDTO newCollection) throws Exception {
        int test =collectionDao.createCollection(newCollection.getCollectionName(), newCollection.getUserId());
        return test;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/collection/comic/{collectionId}", method = RequestMethod.POST)
    public boolean addComicToCollection(@RequestBody ComicDTO newComic) throws Exception {
        return collectionDao.addComicToCollection(newComic.getMarvelId(), newComic.getComicTitle(), newComic.getImageUrl(), newComic.getDescription(), newComic.getCollectionId());
    }


    @RequestMapping(path ="/collection/list", method = RequestMethod.GET)
    public List<Collection> listAllCollections() {
        return collectionDao.listAllCollections();
    }

    @RequestMapping(path ="/collection/user/{userId}", method = RequestMethod.GET)
    public List<Collection> getAllCollectionsByUserId(@PathVariable int userId) {
        return collectionDao.getCollectionsByUserId(userId);

    }

    @RequestMapping(path ="/collection/mycollection/{collectionId}", method = RequestMethod.GET)
    public Collection getCollectionById(@PathVariable int collectionId) {
        return collectionDao.getCollectionById(collectionId);

    }
}
