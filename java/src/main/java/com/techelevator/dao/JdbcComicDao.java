package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.List;

public class JdbcComicDao implements ComicDao{



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
        return null;
    }

    @Override
    public Comic getComicByName(String comicName) {
        return null;
    }

    @Override
    public Comic addComic(Comic comic, int collectionId) {
        return null;
    }
}
