package com.techelevator.model;

import java.util.Date;

public class Comic {
    private int comicId;
    private String comicName;
    private String author;
    private String image;
    private Date releaseDate;
    private int publisherId;
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

    public String getImage() {
        return image;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public int getPublisherId() {
        return publisherId;
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

    public void setImage(String image) {
        this.image = image;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }
}


