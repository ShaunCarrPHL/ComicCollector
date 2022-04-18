package com.techelevator.services;

import com.techelevator.model.MarvelModel.MarvelComic;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import java.lang.String;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins="*")
@Component
public class MarvelComicService {

    private RestTemplate restTemplate = new RestTemplate();

    String Api_Base_URL = "http://gateway.marvel.com/v1/public";
    String timestamp = String.valueOf(Math.floor(Math.random()));
    String privateKey = "26f65896d570b55683c0ae7e408acddd730e64bd";
    String publicKey = "f574334c33d7534733b1fb8eedd88f7e";

    public MarvelComicService(){}

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

    private String authInfoToString() {

        String auth = "ts=" + generateAuthInfo().get(0) + "&apikey=" + generateAuthInfo().get(1) + "&hash=" + generateAuthInfo().get(3);
        return auth;
    }


    public String findCharacterOrCreatorId(String id, String jsonString, Integer offset){
        int indexOfId = jsonString.indexOf(id);
        int beginIndexOfValue = indexOfId + id.length() + offset;
        String[] split = jsonString.substring(beginIndexOfValue).split(",");
        String isolatedId = split[0];
        return isolatedId;
    }

    public String pathFinder(String pathKey, String comicJsonString, Integer offset){

        int indexOfPathKey = comicJsonString.indexOf(pathKey);
        int indexStart = indexOfPathKey + pathKey.length() + offset;

        String[] split = comicJsonString.substring(indexStart).split("\"");
        String isolatedPath = split[0];
        return isolatedPath;
    }

    public int getCharacterIdByName(String characterName){
        String characterJsonString = null;
        int characterId = 0;

        try{

            String exchangePath = Api_Base_URL + "characters?nameStartsWith=" + characterName + "&" + authInfoToString();
            ResponseEntity<String> response =
                    restTemplate.exchange(exchangePath, HttpMethod.GET, makeHeaders(), String.class);
            characterJsonString = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        characterId = Integer.valueOf(findCharacterOrCreatorId("\"id\"", characterJsonString,1));

        return characterId;
    }

    public int getCreatorIdByName(String creatorName){
        String creatorJsonString = null;
        int creatorId = 0;

        try{

            String exchangePath = Api_Base_URL + "creators?nameStartsWith=" + creatorName + "&" + authInfoToString();
            ResponseEntity<String> response =
                    restTemplate.exchange(exchangePath, HttpMethod.GET, makeHeaders(), String.class);
            creatorJsonString = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }
        creatorId = Integer.valueOf(findCharacterOrCreatorId("\"id\"", creatorJsonString, 1));
        return creatorId;
    }


    public List<String> extractComicInfo(String comicJsonString){

        List<String> listOfComicInfo = new ArrayList<>();

        String id = findCharacterOrCreatorId("\"id\"", comicJsonString, 1);
        listOfComicInfo.add(id);

        String title = pathFinder("\"title\"", comicJsonString,2);
        listOfComicInfo.add(title);

        String description = pathFinder("\"description\"", comicJsonString,1);
        listOfComicInfo.add(description);

        String path = pathFinder("\"path\"", comicJsonString,2);
        listOfComicInfo.add(path);

        String extension = pathFinder("\"extension\"", comicJsonString,2);
        listOfComicInfo.add(extension);

        return listOfComicInfo;
    }

    public List<MarvelComic> getComicListByCharacterName(String characterName){

        List<MarvelComic> listComics = new ArrayList<>();
        int characterId = getCharacterIdByName(characterName);
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/characters/" + characterId + "/comics" + "?" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getAllComics(){

        List<MarvelComic> listComics = new ArrayList<>();
        String allComics = null;

        try{

            String path = Api_Base_URL + "/comics?" + authInfoToString();

            ResponseEntity<String> response =
            restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            allComics = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(allComics, listComics);
    }

    public List<MarvelComic> getComicByCreatorName(String creatorName){

        List<MarvelComic> listComics = new ArrayList<>();
        int creatorId = getCreatorIdByName(creatorName);
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/creators/" + creatorId + "/comics" + "?" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getComicByTitle(String title){

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/comics?titleStartsWith=" + title + "&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getThisMonthsComics(){

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/comics?dateDescriptor=thisMonth&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getLastWeeksComics(){

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/comics?dateDescriptor=lastWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getThisWeeksComics(){

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/comics?dateDescriptor=thisWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }

    public List<MarvelComic> getNextWeeksComics(){

        List<MarvelComic> listComics = new ArrayList<>();
        String listComicsJsonString = null;

        try{
            String path = Api_Base_URL + "/comics?dateDescriptor=nextWeek&" + authInfoToString();

            ResponseEntity<String> response =
                    restTemplate.exchange(path, HttpMethod.GET, makeHeaders(), String.class);
            listComicsJsonString = response.getBody();

        } catch(RestClientResponseException | ResourceAccessException e){
            System.out.println(e.getMessage());
        }

        return comicsJsonString(listComicsJsonString, listComics);
    }


    public List<MarvelComic> comicsJsonString(String listComicsJsonString, List<MarvelComic> listComics){
        while(listComicsJsonString.contains("\"events\"")) {
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
