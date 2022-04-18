package com.techelevator.controller;
import com.techelevator.dao.CollectionStatsDao;
import com.techelevator.model.CollectionStats;

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
//  Returns the amount of comics in a single collection
    @RequestMapping(path = "/collection/collectionStats/myStats/{collectionId}", method = RequestMethod.GET)
    public int countComicsInACollection(@PathVariable int collectionId) {
        return collectionStatsDao.amountOfComicsInASingleCollection(collectionId);
    }
//  Returns the amount of comics in all a single user's collection(s)
    @RequestMapping(path = "/collection/collectionStats/myStats/allMyComics/{userId}", method = RequestMethod.GET)
    public int amountOfComicsInAllCollections ( @PathVariable int userId){
        return collectionStatsDao.comicAmountInAllCollections(userId);
    }
//  Returns the amount of comics all user's collections. Grouped by username. Ordered by amount (most comics first)
    @RequestMapping(path = "/collection/collectionStats/leaderboard", method = RequestMethod.GET)
    public List<CollectionStats> comicCountLeaderboard (){ return collectionStatsDao.comicCollectionLeaderboard();}

}
