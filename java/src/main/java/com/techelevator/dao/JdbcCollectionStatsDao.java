package com.techelevator.dao;

import com.techelevator.model.Collection;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCollectionStatsDao implements CollectionStatsDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcComicDao jdbcComicDao;

    public JdbcCollectionStatsDao(JdbcTemplate jdbctemplate) {
        this.jdbcTemplate = jdbctemplate;
    }

    @Override
    public int amountOfComicsInASingleCollection(int collectionId) {
        int amount = 0;
        String sql = "SELECT COUNT(comic_id) AS count FROM comic_collection WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        if (results.next()) {
            amount = results.getInt("count");
        }

        return amount;
    }

    @Override
    public int comicAmountInAllCollections(int userId) {
        int amount = 0;
        String sql = "SELECT COUNT(comic_id) AS count " +
                     "FROM collection AS c " +
                     "JOIN comic_collection AS cc ON c.collection_id = cc.collection_id " +
                     "WHERE c.user_id =?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            amount = results.getInt("count");
        }

        return amount;
    }
//TODO
    @Override
    public List<User> comicCollectionLeaderboard() { return null; }
}
