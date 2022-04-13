package com.techelevator.controller;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.List;

import com.techelevator.dao.*;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelModel.MarvelComic;
import com.techelevator.services.MarvelComicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class ComicController {

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";



    private ComicDao comicDao;
    private CollectionDao collectionDao;
    private SeriesDao seriesDao;
    private CharacterDao characterDao;
    private UserDao userDao;
    private MarvelComicService marvelComicService;

    public ComicController(ComicDao comicDao, CollectionDao collectionDao, SeriesDao seriesDao, CharacterDao characterDao, UserDao userDao, MarvelComicService marvelComicService) {
        this.comicDao = comicDao;
        this.collectionDao = collectionDao;
        this.seriesDao = seriesDao;
        this.characterDao = characterDao;
        this.userDao = userDao;
        this.marvelComicService = marvelComicService;
    }


   @RequestMapping(path = "/comics/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {
        return comicDao.getComicById(comicId);
    }

    @RequestMapping(path = "/comics/{title}", method = RequestMethod.GET)
    public Comic getComicByTitle(@PathVariable String title) {
        return comicDao.getComicByTitle(title);
    }

    @RequestMapping(path ="comic", method = RequestMethod.GET)
    public List<Comic> listAllComics() {
        return comicDao.listAllComics();
    }

    @RequestMapping(path = "/characters/name/{characterName}", method = RequestMethod.GET)
    public List<MarvelComic> getAllComicsByCharacterName(@PathVariable String characterName) {
        return marvelComicService.getComicListByCharacterName(characterName);
    }







}
