package com.techelevator.model;

public class Character {
    private int characterId;
    private String characterName;
    private String thumbnail;

    //getters

    public String getThumbnail() {
        return thumbnail;
    }

    public int getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    //setters


    public void setThumbnail(String thumbnail) {this.thumbnail = thumbnail;}

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
