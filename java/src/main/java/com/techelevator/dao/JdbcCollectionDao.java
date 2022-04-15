package com.techelevator.dao;

import com.techelevator.model.Collection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

      /*  String sql ="INSERT INTO collection(collection_name,";

        int collection_id=-1;

        try{
            collection_id = jdbcTemplate.queryForObject()
        }*/


        return null;
    }
}
