package com.techelevator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MarvelComicService {

    private RestTemplate restTemplate = new RestTemplate();

    private String Api_Base_URL;
    private String timestamp;
    private String privateKey;
    private String publicKey;

    public MarvelComicService(String Api_Base_URL, String timestamp, String privateKey, String publicKey) {
        this.Api_Base_URL = Api_Base_URL;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    private String generateAuthInfo() {
        String AuthInfo = "";
            // Get the hash combination ready
        try {
            byte[] hashCombo = (timestamp + publicKey + privateKey).getBytes(StandardCharsets.UTF_8);
            // Use MD5 to digest hash
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5Digest = md.digest(hashCombo);
            // turn hash byte array into a string
            String hash = Arrays.toString(theMD5Digest);
            AuthInfo = "?ts=" + timestamp + "&apikey=" + publicKey + "&hash=" + hash;
        } catch(NoSuchAlgorithmException e) {
            e.getMessage();
            System.out.println("Authentication process failed. Check API keys.");
        }

   return AuthInfo;
}
}
