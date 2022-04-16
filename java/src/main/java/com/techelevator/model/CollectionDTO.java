package com.techelevator.model;

public class CollectionDTO {

    private String collectionName;
    private int userId;

    public String getCollectionName() {
        return collectionName;
    }

    public int getUserId() {
        return userId;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    @Override
    public String toString(){
        return "CollectionDTO{" +
                "collectionName='" + collectionName+'\'' +
                ", userId=" + userId +
                '}';



    }
}
