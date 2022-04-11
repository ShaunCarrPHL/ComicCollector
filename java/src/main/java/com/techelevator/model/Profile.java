package com.techelevator.model;

public class Profile {

    private int profileId;
    private String imgUrl;
    private String imgName;

    public int getProfileId() {
        return profileId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getImgName() {
        return imgName;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
