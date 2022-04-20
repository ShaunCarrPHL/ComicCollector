package com.techelevator.model;

public class ComicDTO {
    private int marvelId;
    private  String comicTitle;
    private String imageURL;
    private String description;
    private int collectionId;

    public int getMarvelId() {
        return marvelId;
    }

    public String getComicTitle() {
        return comicTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setMarvelId(int marvelId) {
        this.marvelId = marvelId;
    }

    public void setComicTitle(String comicTitle) {
        this.comicTitle = comicTitle;
    }

    public void setImageUrl(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public String toString() {
        return "ComicDTO{" +
                "marvelId='" + marvelId + '\'' +
                ", comicTitle=" + comicTitle + '\'' +
                ", imageURL=" + imageURL + '\'' +
                ", description=" + description + '\'' +
                ", collectionId=" + collectionId + '\'' +
                '}';
    }
}
