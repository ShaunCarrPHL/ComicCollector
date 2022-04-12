package com.techelevator.dao;

import com.techelevator.model.Comic;

import java.util.List;

public interface ComicDao {

    List<Comic> listAllComics();
    List<Comic> listComicsByPublisherId(int publisherId);
    List<Comic> listComicsBySeriesId(int seriesId);
    List<Comic> listComicsByAuthor(String author);
    List<Comic> listComicsInCollection(int collectionId);
    Comic getComicById(int comicId);
    Comic getComicByTitle(String comicTitle);
    Comic addComic(Comic comic,int collectionId);

}
