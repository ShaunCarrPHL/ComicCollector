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

/*
    private int comicId;
    private int marvelId;
    private String comicTitle;
    private String author;
    private String imageURL;
    private Date releaseDate;
    private int creatorId;
    private int seriesId;
    private String description;
    public Comic (){}*/
