package com.example.pruebaandroidtv.main;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetView {

    private User user;
    private ArrayList<Contenido> contents = new ArrayList<>();

    public ResponseGetView(){}

    public ResponseGetView(User user, ArrayList<Contenido> contents) {
        this.user = user;
        this.contents = contents;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Contenido> getContenidos() {
        return contents;
    }

    public void setContenidos(ArrayList<Contenido> contents) {
        this.contents = contents;
    }
}
