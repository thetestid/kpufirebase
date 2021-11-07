package com.webnatues.kpufirebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


@IgnoreExtraProperties
public class Post {

    public Object profile;
    public String id;
    public int W;
    public String sWitch;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();


    public Post(){}

    public Post(Object profile,String id, int W,String sWitch){
        this.id =id;
        this.profile= profile;
        this.sWitch = sWitch;
        this.W = W;
    }

    @Exclude
    public Map<String,Object>toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("profile", profile);
        result.put("id", id);
        result.put("W", W);
        result.put("sWitch", sWitch);


        return result;
    }
}
