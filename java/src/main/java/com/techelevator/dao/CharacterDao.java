package com.techelevator.dao;

import com.techelevator.model.Character;

import java.util.List;

public interface CharacterDao {
    List<Character> ListAllCharacters();
    List<Character> ListAllCharactersInComic(int comicId);
    Character getComicByCharacterName(String name);
    Character getCharacterById(int characterId);
}
