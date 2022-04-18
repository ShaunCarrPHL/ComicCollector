package com.techelevator.dao;

import com.techelevator.model.CollectionStats;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcCollectionStatsDao implements CollectionStatsDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcComicDao jdbcComicDao;

    public JdbcCollectionStatsDao(JdbcTemplate jdbctemplate) {
        this.jdbcTemplate = jdbctemplate;
    }

    @Override
    public int amountOfComicsInASingleCollection(int collectionId) {
        int amount = 0;
        String sql = "SELECT COUNT(comic_id) AS amount FROM comic_collection WHERE collection_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        if (results.next()) {
            amount = results.getInt("amount");
        }

        return amount;
    }

    @Override
    public int comicAmountInAllCollections(int userId) {
        int amount = 0;
        String sql = "SELECT COUNT(comic_id) AS amount " +
                     "FROM collection AS c " +
                     "JOIN comic_collection AS cc ON c.collection_id = cc.collection_id " +
                     "WHERE c.user_id =?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            amount = results.getInt("amount");
        }

        return amount;
    }

    @Override
    public List<CollectionStats> comicCollectionLeaderboard() {
        List<CollectionStats> leaderboard = new ArrayList<>();
        String sql = "SELECT COUNT(comic_id) as amount, u.username\n" +
                     "FROM collection as c\n" +
                     "JOIN comic_collection AS cc ON c.collection_id = cc.collection_id\n" +
                     "JOIN users AS u ON c.user_id = u.user_id\n" +
                     "GROUP BY u.username\n" +
                     "ORDER BY amount DESC;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            CollectionStats collectionStats = mapRowToCollectionStats(results);
            leaderboard.add(collectionStats);
        }

        return leaderboard; }

    private CollectionStats mapRowToCollectionStats(SqlRowSet rs) {
        CollectionStats collectionStats = new CollectionStats();
        collectionStats.setCount(rs.getInt("amount"));
        collectionStats.setUsername(rs.getString("username"));
        return collectionStats;
    }

}
