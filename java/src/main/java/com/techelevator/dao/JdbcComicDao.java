package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcComicDao implements ComicDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcComicDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comic> listAllComics() {
        return null;
    }

    @Override
    public List<Comic> listComicsByPublisherId(int publisherId) {
        return null;
    }

    @Override
    public List<Comic> listComicsBySeriesId(int seriesId) {
        return null;
    }

    @Override
    public List<Comic> listComicsByAuthor(String author) {
        return null;
    }

    @Override
    public List<Comic> listComicsInCollection(int collectionId) {
        return null;
    }

    @Override
    public Comic getComicById(int comicId) {
        Comic comic = null;
        String sql = "SELECT * FROM comic WHERE comic_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);

        if(results.next()){
            comic = mapRowToComic(results);
        }

        return comic;
    }

    @Override
    public Comic getComicByTitle(String comicTitle) {
        return null;
    }

    @Override
    public Comic addComic(Comic comic, int collectionId) {
        return null;
    }

    private Comic mapRowToComic(SqlRowSet rs) {
        Comic comic = new Comic();
        comic.setComicId(rs.getInt("comic_id"));
        comic.setComicTitle(rs.getString("comic_name"));
        comic.setImage(rs.getString("imageURL"));
        comic.setAuthor(rs.getString("author"));
        comic.setReleaseDate(rs.getDate("release_date"));
        return comic;
    }

}
