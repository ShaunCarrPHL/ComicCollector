package com.techelevator.model;

public class CollectionDTO {

    private String collectionName;
    private int userId;
    private boolean isPrivate;

    public String getCollectionName() {
        return collectionName;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPrivate(){return isPrivate;}

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPrivate(boolean aPrivate) {isPrivate = aPrivate;
    }

    @Override
    public String toString(){
        return "CollectionDTO{" +
                "collectionName='" + collectionName+'\'' +
                ", userId=" + userId + '\'' +
                ", private=" + isPrivate +
                '}';



    }
}
