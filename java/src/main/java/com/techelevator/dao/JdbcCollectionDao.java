package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JdbcCollectionDao implements CollectionDao{
    @Override
    public List<Collection> listAllCollections() {
        return null;
    }

    @Override
    public List<Collection> listAllPublicCollections() {
        return null;
    }

    @Override
    public List<Collection> getCollectionsByUserId(int userId) {
        return null;
    }

    @Override
    public Collection getCollectionById(int collectionId) {
        return null;
    }

    @Override
    public Collection createCollection(Collection collection) {
        return null;
    }
}
