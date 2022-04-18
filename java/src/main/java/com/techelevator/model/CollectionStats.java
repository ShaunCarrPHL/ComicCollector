package com.techelevator.model;

public class CollectionStats {

    private int count;
    private String username;

    public CollectionStats(){}

    public CollectionStats(int count, String username) {
        this.count = count;
        this.username = username;
    }

    public int getCount() {return count;}

    public String getUsername() {return username;}

    public void setCount(int count) {this.count = count;}

    public void setUsername(String username) {this.username = username;}
}
