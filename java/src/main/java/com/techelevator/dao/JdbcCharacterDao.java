package com.techelevator.dao;

import com.techelevator.model.Character;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public class JdbcCharacterDao implements CharacterDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Character> ListAllCharacters() {
        return null;
    }

    @Override
    public List<Character> ListAllCharactersInComic(int comicId) {
        return null;
    }

    @Override
    public Character getComicByCharacterName(String name) {
        Character character = null;
        String sql = "SELECT * FROM hero WHERE hero_name Like =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

       if (results.next()) {
           // character = mapRowToCharacter(results);
        }

        return character;
    }

    @Override
    public Character getCharacterById(int characterId) {
        return null;
    }

    //private Character mapRowToCharacter(SqlRowSet rs) {
    //Character character = new Character();
    //return character;}

}
