package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.Date;
import java.util.List;

public interface ComicDao {
    int getMarvelComicId(int comicId);
    List<Comic> listAllComics();
    List<Comic> listComicsBySeriesId(int seriesId);
    List<Comic> listComicsByAuthor(String author);
    List<Comic> listComicsByCollection(int collectionId);
    Comic getComicById(int comicId);
    Comic getComicByTitle(String comicTitle);
}
