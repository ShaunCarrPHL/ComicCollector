package com.techelevator.controller;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

import com.techelevator.dao.*;
import com.techelevator.model.Comic;
import com.techelevator.services.MarvelComicService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComicController {

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
    String timestamp = String.valueOf(Math.random());
    String privateKey = "26f65896d570b55683c0ae7e408acddd730e64bd";
    String publicKey = "f574334c33d7534733b1fb8eedd88f7e";

    MarvelComicService marvelComicService = new MarvelComicService(Api_Base_URL, timestamp, publicKey, privateKey);

    private ComicDao comicDao;
    private CollectionDao collectionDao;
    private SeriesDao seriesDao;
    private CharacterDao characterDao;
    private UserDao userDao;

    public ComicController(ComicDao comicDao, CollectionDao collectionDao, SeriesDao seriesDao, CharacterDao characterDao, UserDao userDao) {
        this.comicDao = comicDao;
        this.collectionDao = collectionDao;
        this.seriesDao = seriesDao;
        this.characterDao = characterDao;
        this.userDao = userDao;
    }


   @RequestMapping(path = "/comics/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {
        return comicDao.getComicById(comicId);
    }

    @RequestMapping(path = "/comics/{title}", method = RequestMethod.GET)
    public Comic getComicByTitle(@PathVariable String title) {
        return comicDao.getComicByTitle(title);
    }










}
