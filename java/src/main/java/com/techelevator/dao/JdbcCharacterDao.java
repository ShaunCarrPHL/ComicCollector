package com.techelevator.dao;

import com.techelevator.model.Character;

import java.util.List;

public class JdbcCharacterDao implements CharacterDao{
    @Override
    public List<Character> ListAllCharacters() {
        return null;
    }

    @Override
    public List<Character> ListAllCharactersInComic(int comicId) {
        return null;
    }

    @Override
    public Character getCharacterByName(String name) {
        return null;
    }

    @Override
    public Character getCharacterById(int characterId) {
        return null;
    }
}
