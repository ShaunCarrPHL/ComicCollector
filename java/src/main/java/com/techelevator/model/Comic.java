package com.techelevator.model;

import java.util.Date;

public class Comic {
    private int comicId;
    private String comicName;
    private String author;
    private String imageURL;
    private Date releaseDate;
    private int creatorId;
    private int seriesId;

//constructor?

    //getters
    public int getComicId() {
        return comicId;
    }

    public String getComicName() {
        return comicName;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public int getSeriesId() {
        return seriesId;
    }

    //setters
    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setImage(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
}


