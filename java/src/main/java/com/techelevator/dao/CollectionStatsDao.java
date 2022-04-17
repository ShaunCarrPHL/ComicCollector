package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.User;

import java.util.List;

public interface CollectionStatsDao {

    int amountOfComicsInASingleCollection(int collectionId);
    int comicAmountInAllCollections(int userId);
    List<User> comicCollectionLeaderboard();
}
