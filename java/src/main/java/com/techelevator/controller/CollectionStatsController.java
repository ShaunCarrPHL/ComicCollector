package com.techelevator.controller;

import com.techelevator.dao.CollectionStatsDao;
import com.techelevator.model.Collection;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class CollectionStatsController {

    private CollectionStatsDao collectionStatsDao;

    public CollectionStatsController(CollectionStatsDao collectionStatsDao) {
        this.collectionStatsDao = collectionStatsDao;
    }

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";

    @RequestMapping(path = "/collection/collectionStats/myStats/{collection_id}", method = RequestMethod.GET)
    public int countComicsInACollection(@PathVariable int collectionId) {
        return collectionStatsDao.amountOfComicsInASingleCollection(collectionId);
    }

    @RequestMapping(path = "/collection/collectionStats/myStats/allMyComics", method = RequestMethod.GET)
    public int amountOfComicsInAllCollections ( @PathVariable int userId){
        return collectionStatsDao.comicAmountInAllCollections(userId);
    }
    //TODO Leaderboard is not yet functional
    @RequestMapping(path = "/collection/collectionStats/leaderboard", method = RequestMethod.GET)
    public List<User> comicCountLeaderboard (){ return collectionStatsDao.comicCollectionLeaderboard();}
}
