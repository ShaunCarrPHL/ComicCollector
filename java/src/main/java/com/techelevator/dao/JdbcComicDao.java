package com.techelevator.dao;

import com.techelevator.model.Character;
import com.techelevator.model.Comic;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcComicDao implements ComicDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcComicDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getMarvelComicId(int comicId) {return 0;}

    @Override
    public List<Comic> listAllComics() {

        List<Comic> comics = new ArrayList<>();
        String sql ="SELECT * FROM comics;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Comic comic = mapRowToComic(results);
            comics.add(comic);
        }
        return comics;
    }

    @Override
    public List<Comic> listComicsBySeriesId(int seriesId) {return null;}

    @Override
    public List<Comic> listComicsByAuthor(String author) {return null;}

    @Override
    public List<Comic> listComicsByCollection(int collectionId) {

        List<Comic> comics = new ArrayList<>();

        String sql = "SELECT * FROM comic " +
                     "JOIN comic_collection ON comic.comic_id = comic_collection.comic_id " +
                     "WHERE collection_id = ? ";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while (results.next()) {
            Comic comic = mapRowToComic(results);
            comics.add(comic);
        }
        return comics;
    }

    @Override
    public Comic getComicById(int comicId) {
        Comic comic = null;
        String sql = "SELECT * FROM comic WHERE comic_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, comicId);

        if(results.next()){comic = mapRowToComic(results);}

        return comic;
    }

    @Override
    public Comic getComicByTitle(String comicTitle) {
        return null;
    }




    private Comic mapRowToComic(SqlRowSet rs) {
        Comic comic = new Comic();
        comic.setComicId(rs.getInt("comic_id"));
        comic.setMarvelId(rs.getInt("marvel_Id"));
        comic.setComicTitle(rs.getString("title"));
        comic.setImage(rs.getString("image"));
        comic.setDescription(rs.getString("description"));
        return comic;
    }

}
