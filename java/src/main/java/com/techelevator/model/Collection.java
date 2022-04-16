package com.techelevator.model;

public class Collection {

    private  int collectionId;
    private String collectionName;
    private boolean isPrivate = false;
    private int userId;

    public Collection(){}

    public Collection(int collectionId, String collectionName, int userId, boolean isPublic){
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.userId = userId;
        this.isPrivate = false;
    }


    //Setters
    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    //Getters
    public int getCollectionId() {
        return collectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public int getUserId() {
        return userId;
    }
}

