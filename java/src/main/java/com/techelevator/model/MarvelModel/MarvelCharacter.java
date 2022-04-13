package com.techelevator.model.MarvelModel;

public class MarvelCharacter {
    private int characterId;
    private String characterName;
    private String thumbnail;
    private String description;

    public MarvelCharacter() {
    }

    public MarvelCharacter(int characterId, String characterName, String thumbnail, String description) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.thumbnail = thumbnail;
        this.description = description;
    }
    //getters

    public String getDescription() {
        return description;
    }

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


    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String toString() {
        return "Character{" + "characterId=" + characterId +
                ", characterName" + characterName + '\'' +
                "thumbnail" + thumbnail + '\'' +
                "description" + description + '\'' +
                '}';
    }
}
