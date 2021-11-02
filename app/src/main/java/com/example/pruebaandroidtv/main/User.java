package com.example.pruebaandroidtv.main;

import java.util.ArrayList;

public class User {

    private String name;
    private String avatar;
    private String[] favs;
    private String[] lastShowed;

    public User(){

    }

    public User(String name, String avatar, String[] favs, String[] lastShowed) {
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

    public String[] getFavs() {
        return favs;
    }

    public void setFavs(String[] favs) {
        this.favs = favs;
    }

    public String[] getLastShowed() {
        return lastShowed;
    }

    public void setLastShowed(String[] lastShowed) {
        this.lastShowed = lastShowed;
    }
}
