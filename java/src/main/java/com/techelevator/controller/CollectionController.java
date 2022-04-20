package com.techelevator.controller;

import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.ComicDao;
import com.techelevator.model.Collection;
import com.techelevator.model.CollectionDTO;
import com.techelevator.model.Comic;
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
// create  collection also needs to be tested with new .isPrivate functionality
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping ( path = "/collection", method = RequestMethod.POST)
    public int createCollection(@RequestBody CollectionDTO newCollection) throws Exception {
        int test =collectionDao.createCollection(newCollection.getCollectionName(), newCollection.getUserId(), newCollection.isPrivate());
        return test;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping( path = "/collection/comic/{collectionId}", method = RequestMethod.POST)
    public boolean addComicToCollection(@RequestBody ComicDTO newComic, @PathVariable int collectionId) throws Exception {
        return collectionDao.addComicToCollection(newComic.getMarvelId(), newComic.getComicTitle(), newComic.getImageURL(), newComic.getDescription(), collectionId);
    }

    @RequestMapping(path ="/collection/mycollection/{collectionId}", method = RequestMethod.PUT)
    public void updateCollectionById(@RequestBody Collection collection, @PathVariable int collectionId) {
       collectionDao.updateCollection(collection);
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
//Not sure if this should go here or in ComicController...also needs to be tested
    @RequestMapping(path = "/collection/mycollection/mycomics/{collectionId}", method = RequestMethod.GET)
    public List<Comic> listComicsInCollection(@PathVariable int collectionId) {
        return comicDao.listComicsByCollection(collectionId);
    }

}
