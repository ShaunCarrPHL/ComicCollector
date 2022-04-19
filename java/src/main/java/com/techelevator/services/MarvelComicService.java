package com.techelevator.services;

import com.sun.jdi.IntegerValue;
import com.techelevator.model.MarvelModel.MarvelComic;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.lang.String;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Component
public class MarvelComicService {

    private RestTemplate restTemplate = new RestTemplate();

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
    String timestamp = String.valueOf(Math.floor(Math.random()));
    String privateKey = "26f65896d570b55683c0ae7e408acddd730e64bd";
    String publicKey = "f574334c33d7534733b1fb8eedd88f7e";

    public MarvelComicService() {
    }

    public MarvelComicService(String Api_Base_URL, String timestamp, String privateKey, String publicKey) {
        this.Api_Base_URL = Api_Base_URL;
        this.timestamp = timestamp;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    private HttpEntity<Void> makeHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(headers);
    }

    //creates the values for the end path that is always needed to access the Marvel Api
    private List<String> generateAuthInfo() {

        List<String> listOfAuthInfo = new ArrayList<>();

        listOfAuthInfo.add(timestamp);
        listOfAuthInfo.add(publicKey);
        listOfAuthInfo.add(privateKey);

        try {
            String hashString = timestamp + privateKey + publicKey;

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(hashString.getBytes());

            BigInteger bigInt = new BigInteger(1, messageDigest);

            String hashText = bigInt.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            listOfAuthInfo.add(hashText);

        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return listOfAuthInfo;
    }

    //Arranges the values from generateAuthInfo() into the string that always ends every call to Marvel API
    private String authInfoToString() {

        String auth = "ts=" + generateAuthInfo().get(0) + "&apikey=" + generateAuthInfo().get(1) + "&hash=" + generateAuthInfo().get(3);
        return auth;
    }

    //Lists comics the characterName appears in
    public List<MarvelComic> getComicListByCharacterName(String characterName) {

        List<MarvelComic> listComics = new ArrayList<>();
        int characterId = getCharacterIdByName(characterName);
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/characters/" + characterId + "/comics" + "?" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics by the creatorName provided
    public List<MarvelComic> getComicByCreatorName(String creatorName) {

        List<MarvelComic> listComics = new ArrayList<>();
        int creatorId = getCreatorIdByName(creatorName);
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/creators/" + creatorId + "/comics" + "?" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics most relevant to the title searched
    public List<MarvelComic> getComicByTitle(String title) {

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/comics?titleStartsWith=" + title + "&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics by most relevant seriesName used
    public List<MarvelComic> getComicBySeriesName(String seriesName) {
        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/series?titleStartsWith=" + seriesName + "&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //retrieves the id the API associates the given characterName with
    public int getCharacterIdByName(String characterName) {
        String characterJsonString = null;
        int characterId = 0;

        try {

            String exchangePath = Api_Base_URL + "/characters?nameStartsWith=" + characterName + "&" + authInfoToString();
            ResponseEntity<String> response =
                    restTemplate.exchange(exchangePath, HttpMethod.GET, makeHeaders(), String.class);
            characterJsonString = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        characterId = Integer.valueOf(findCharacterOrCreatorId("\"id\"", characterJsonString, 1));

        return characterId;
    }

    //retrieves the id the API associates the given creatorName with
    public int getCreatorIdByName(String creatorName) {
        String creatorJsonString = null;
        int creatorId = 0;

        try {

            String exchangePath = Api_Base_URL + "/creators?nameStartsWith=" + creatorName + "&" + authInfoToString();
            ResponseEntity<String> response =
                    restTemplate.exchange(exchangePath, HttpMethod.GET, makeHeaders(), String.class);
            creatorJsonString = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }
        creatorId = Integer.valueOf(findCharacterOrCreatorId("\"id\"", creatorJsonString, 1));

        return creatorId;
    }

    //Find an id of a Character or Creator (also works with series)
    public String findCharacterOrCreatorId(String id, String jsonString, Integer offset) {
        int indexOfId = jsonString.indexOf(id);
        int beginIndexOfValue = indexOfId + id.length() + offset;
        String[] split = jsonString.substring(beginIndexOfValue).split(",");
        String isolatedId = split[0];
        isolatedId = Integer.valueOf(isolatedId) > 0 ? isolatedId : "0";
        return isolatedId;
    }

    //List comics released this month
    public List<MarvelComic> getThisMonthsComics() {

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/comics?dateDescriptor=thisMonth&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics released last week
    public List<MarvelComic> getLastWeeksComics() {

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/comics?dateDescriptor=lastWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics released this week
    public List<MarvelComic> getThisWeeksComics() {

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/comics?dateDescriptor=thisWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //List comics releasing next week
    public List<MarvelComic> getNextWeeksComics() {

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try {
            String path = Api_Base_URL + "/comics?dateDescriptor=nextWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    //Lists ALL comics the API has...20 per page..honestly no use for this probably should just delete
    public List<MarvelComic> getAllComics() {

        List<MarvelComic> listComics = new ArrayList<>();
        String allComics = null;

        try {

            String path = Api_Base_URL + "/comics?" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            allComics = response.getBody();

        } catch (RestClientResponseException | ResourceAccessException e) {
            System.out.println(e.getMessage());
        }

        return comicsJsonString(allComics, listComics);
    }

    // set the spot in the JSON string we want to extract
    public String pathFinder(String pathKey, String comicJsonString, Integer offset) {

        int indexOfPathKey = comicJsonString.indexOf(pathKey);
        int indexStart = indexOfPathKey + pathKey.length() + offset;

        String[] split = comicJsonString.substring(indexStart).split("\"");
        String isolatedPath = split[0];
        return isolatedPath;
    }

    // reads and extracts from the JSON String to get basic Comic data
    public List<String> extractComicInfo(String comicJsonString) {

        List<String> listOfComicInfo = new ArrayList<>();

        String id = findCharacterOrCreatorId("\"id\"", comicJsonString, 1);
        listOfComicInfo.add(id);

        String title = pathFinder("\"title\"", comicJsonString, 2);
        listOfComicInfo.add(title);

        String description = pathFinder("\"description\"", comicJsonString, 1);
        listOfComicInfo.add(description);

        String path = pathFinder("\"path\"", comicJsonString, 2);
        listOfComicInfo.add(path);

        String extension = pathFinder("\"extension\"", comicJsonString, 2);
        listOfComicInfo.add(extension);

        return listOfComicInfo;
    }
    //Make a comic JSON string object
    public List<MarvelComic> comicsJsonString(String listComicsJsonString, List<MarvelComic> listComics) {
        while (listComicsJsonString.contains("\"events\"")) {
            MarvelComic marvelComic = new MarvelComic();
            List<String> listOfComicInfo = new ArrayList<>();

            listOfComicInfo = extractComicInfo(listComicsJsonString);

            marvelComic.setMarvelId(Integer.valueOf(listOfComicInfo.get(0)));
            marvelComic.setComicTitle(listOfComicInfo.get(1));
            marvelComic.setDescription(listOfComicInfo.get(2));
            marvelComic.setImage(listOfComicInfo.get(3));
            marvelComic.setExtension(listOfComicInfo.get(4));

            String ImgUrl = marvelComic.getImageURL() + "/portrait_uncanny." + marvelComic.getExtension();
            marvelComic.setImage(ImgUrl);

            int comicEnd = listComicsJsonString.indexOf("\"events\"") + 3;

            listComicsJsonString = listComicsJsonString.substring(comicEnd);

            listComics.add(marvelComic);
        }
        return listComics;
    }
}
