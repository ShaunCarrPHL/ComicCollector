package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcCollectionDao(JdbcTemplate jdbctemplate){
        this.jdbcTemplate = jdbctemplate;
    }

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
    public boolean addComic(int comicId, int collectionId) {
        String sql ="INSERT INTO collection(collection_id, comic_id" +
                "SELECT ?,? "
    }

    @Override
    public Collection getCollectionById(int collectionId) {
        return null;
    }

    @Override
    public int createCollection(String collectionName, int userId) {

       String sql ="INSERT INTO collection(collection_name,user_id)" +
               "VALUES(?,?) RETURNING collection_id";

       int collectionId = -1;

        try {
            collectionId = jdbcTemplate.queryForObject(sql, Integer.class, collectionName, userId);
        }catch (DataAccessException e){
            System.out.println(e.getMessage());
        }
        if(collectionId != -1){
            return collectionId;
        }


        return collectionId;
    }
}
