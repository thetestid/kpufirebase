package com.webnatues.kpufirebase;

public class PI {
    private String profile; //이미지
    private String id; //라즈베리파이 id
    private  int W; //전력

    public PI(){}

    public String getProfile() {
        return profile;
    }

    public String getId() {
        return id;
    }

    public int getW() {
        return W;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setW(int w) {
        W = w;
    }
}
