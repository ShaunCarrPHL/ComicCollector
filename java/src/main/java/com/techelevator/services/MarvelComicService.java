package com.techelevator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MarvelComicService {

    private RestTemplate restTemplate = new RestTemplate();

    private String Api_Base_URL;
    private String privateKey;
    private String publicKey;

    public MarvelComicService(String Api_Base_URL, String privateKey, String publicKey){
        this.Api_Base_URL = Api_Base_URL;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

}
