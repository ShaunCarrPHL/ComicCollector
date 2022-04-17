package com.techelevator.controller;

import java.util.List;

import com.techelevator.dao.*;
import com.techelevator.model.Comic;
import com.techelevator.model.MarvelModel.MarvelComic;
import com.techelevator.services.MarvelComicService;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
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

    @RequestMapping(path = "/comics/mycomic/{comicId}", method = RequestMethod.GET)
    public Comic getComicById(@PathVariable int comicId) {
        return comicDao.getComicById(comicId);
    }

    @RequestMapping(path = "/comics/title/{title}", method = RequestMethod.GET)
    public Comic getComicByTitle(@PathVariable String title) {
        return comicDao.getComicByTitle(title);
    }

    @RequestMapping(path ="/comics", method = RequestMethod.GET)
    public List<MarvelComic> listAllComics() {
        return marvelComicService.getAllComics();
    }
// List comics by character name
    @RequestMapping(path = "/characters/name/{characterName}", method = RequestMethod.GET)
    public List<MarvelComic> getAllComicsByCharacterName(@PathVariable String characterName) {
        return marvelComicService.getComicListByCharacterName(characterName);}
// List comics by creator
    @RequestMapping(path = "/creators/name/{creatorName}", method = RequestMethod.GET)
    public List<MarvelComic> getComicsByCreatorName(@PathVariable String creatorName) {
        return marvelComicService.getComicByCreatorName(creatorName);}
// Search and List comics by title provided
    @RequestMapping(path = "/comic/bytitle/{title}", method = RequestMethod.GET)
    public List<MarvelComic> getComicsByTitle(@PathVariable String title) {
        return marvelComicService.getComicByTitle(title);}
// List comics that came out this month so far
    @RequestMapping(path ="/comics/date/thisMonth", method = RequestMethod.GET)
    public List<MarvelComic> listThisMonthsComics() {return marvelComicService.getThisMonthsComics();}
// List comics that came out last week
    @RequestMapping(path ="/comics/date/lastWeek", method = RequestMethod.GET)
    public List<MarvelComic> listLastWeeksComics() {return marvelComicService.getLastWeeksComics();}
// List comics that came out this week
    @RequestMapping(path ="/comics/date/thisWeek", method = RequestMethod.GET)
    public List<MarvelComic> listThisWeeksComics() {return marvelComicService.getThisWeeksComics();}
// List comics that come out next week
    @RequestMapping(path ="/comics/date/nextWeek", method = RequestMethod.GET)
    public List<MarvelComic> listNextWeeksComics() {return marvelComicService.getNextWeeksComics();}

}
