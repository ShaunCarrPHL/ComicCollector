package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.Comic;

import java.util.List;

public interface CollectionDao {
List<Collection> listAllCollections();
List<Collection> listAllPublicCollections();
List<Collection> getCollectionsByUserId(int userId);
public void updateCollection(Collection collection);
Collection getCollectionById(int collectionId);
int createCollection(String collectionName, int userId, boolean isPrivate);
boolean addComicToCollection(int marvelId, String comicTitle, String imageUrl, String description, int collectionId);
}
