package com.techelevator.model.MarvelModel;

import java.util.Date;

public class MarvelComic {

    private int comicId;
    private int marvelId;
    private String comicTitle;
    private String author;
    private String imageURL;
    private Date releaseDate;
    private int creatorId;
    private int seriesId;
    private String description;
    public MarvelComic (){}

    public MarvelComic(int comicId, String comicTitle, String author, String imageURL, Date releaseDate, int creatorId, int seriesId, String description) {
        this.comicId = comicId;
        this.marvelId = marvelId;
        this.comicTitle = comicTitle;
        this.author = author;
        this.imageURL = imageURL;
        this.releaseDate = releaseDate;
        this.creatorId = creatorId;
        this.seriesId = seriesId;
        this.description = description;
    }

    //getters


    public int getComicId() {
        return comicId;
    }

    public int getMarvelId() {return marvelId;}

    public String getComicTitle() {
        return comicTitle;
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

    public String getDescription() {
        return description;
    }

    //setters
    public void setComicId(int comicId) {
        this.comicId = comicId;
    }

    public void setMarvelId(int marvelId) {this.marvelId = marvelId;}

    public void setComicTitle(String comicTitle) {
        this.comicTitle = comicTitle;
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

    public void setDescription(String description) {this.description = description;}

    public String toString() {
        return "Comic{" + comicId +
                "comicId=" + comicId +
                "marvelId=" + marvelId +
                ",comicTitle" + comicTitle +
                ",author" + author +
                ",imageURL" + imageURL +
                ",releaseDate" + releaseDate +
                ",creatorId" + creatorId +
                ",seriesId" + seriesId +
                ",description" + description +
                '}';

    }
}
