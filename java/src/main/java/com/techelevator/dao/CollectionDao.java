package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.util.List;

public interface CollectionDao {
List<Collection> listAllCollections();
List<Collection> listAllPublicCollections();
List<Collection> getCollectionsByUserId(int userId);
Collection getCollectionById(int collectionId);
int createCollection(String collectionName, int userId);
boolean addComicToCollection(int marvelId, String comicTitle, String imageUrl, String description, int collectionId);

}
