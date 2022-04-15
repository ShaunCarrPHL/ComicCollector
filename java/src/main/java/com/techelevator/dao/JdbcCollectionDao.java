package com.techelevator.dao;

import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import com.techelevator.model.Collection;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcCollectionDao implements CollectionDao{
    private JdbcTemplate jdbcTemplate;
    private JdbcComicDao jdbcComicDao;
    public JdbcCollectionDao(JdbcTemplate jdbctemplate){
        this.jdbcTemplate = jdbctemplate;
    }

    @Override
    public int createCollection(String collectionName, int userId) {

        String sql = "INSERT INTO collection(collection_name,user_id)" +
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
    @Override
    public List<Collection> listAllCollections() {
        List<Collection> collections = new ArrayList<>();

        String sql = "SELECT * FROM collections WHERE private = false;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()){
            Collection collection = mapRowToCollections(results);
            collections.add(collection);

        }
        return collections;
    }

    @Override
    public List<Collection> listAllPublicCollections() {
        return null;
    }

    @Override
    public List<Collection> getCollectionsByUserId(int userId) {

        List<Collection> collections = new ArrayList<>();

        String sql = "SELECT * FROM collections WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()){
            Collection collection = mapRowToCollections(results);
            collections.add(collection);}
        return collections;
    }

    //TODO check to see if different users can add the same comics to their collection
    @Override
    public boolean addComicToCollection(int marvelId, String comicTitle, String imageUrl, String description, int collectionId) {

        String sql ="INSERT INTO comic(marvel_id, title, image, description)" +
                    "SELECT ?,?,?,?" +
                    "WHERE NOT EXISTS" +
                    "(SELECT marvel_id FROM comic WHERE marvel_id = ?)" +
                    "RETURNING comic_id;";

        int comicId = -1;

        try {

            comicId = jdbcTemplate.queryForObject(sql, Integer.class, marvelId, comicTitle, imageUrl, description, marvelId);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }

        if(comicId != -1){
            System.out.println("Adding comic to comic table failed.");
        }

        sql =   "INSERT INTO comic_collection(collection_id, comic_id)" +
                "SELECT ?,?" +
                "WHERE NOT EXISTS" +
                "(SELECT comic_id FROM comic_collection WHERE comic_id = ?)" +
                "RETURNING collection_id;";

        int collectionIdCheck = -1;

        try{
             collectionIdCheck = jdbcTemplate.queryForObject(sql, Integer.class, collectionId, comicId, comicId);
        } catch(DataAccessException e){
            System.out.println(e.getMessage() + " Failed to add comic to collection");
        }
        if(collectionIdCheck != -1){
            return true;
        }

        return false;
    }

    @Override
    public Collection getCollectionById(int collectionId) {
        Collection collection = null;

        String sql = "SELECT * FROM collections WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        if (results.next()) {
            collection = mapRowToCollections(results);
        }
        return collection;
    }

private Collection mapRowToCollections(SqlRowSet rs) {
        Collection collection = new Collection();

        collection.setCollectionId(rs.getInt("collection_id"));
        collection.setCollectionName(rs.getString("collection_name"));
        collection.setUserId(rs.getInt("user_id"));
        return collection;
}

}
