package com.techelevator.dao;

import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {
List<Collection> listAllCollections();
List<Collection> listAllPublicCollections();
List<Collection> getCollectionsByUserId(int userId);
Collection getCollectionById(int collectionId);
Collection createCollection(Collection collection);

}
