package com.example.pruebaandroidtv.main;

import java.util.ArrayList;

public class User {

    private String name;
    private String avatar;
    private ArrayList<String> favs;
    private ArrayList<String> lastShowed;

    public User(){

    }

    public User(String name, String avatar, ArrayList<String> favs, ArrayList<String> lastShowed) {
        this.name = name;
        this.avatar = avatar;
        this.favs = favs;
        this.lastShowed = lastShowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ArrayList<String> getFavs() {
        return favs;
    }

    public void setFavs(ArrayList<String> favs) {
        this.favs = favs;
    }

    public ArrayList<String> getLastShowed() {
        return lastShowed;
    }

    public void setLastShowed(ArrayList<String> lastShowed) {
        this.lastShowed = lastShowed;
    }
}
