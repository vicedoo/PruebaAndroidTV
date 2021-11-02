package com.example.pruebaandroidtv.main;

public class ResponseGetView {

    private User user;
    private Contenido[] contenidos;

    public ResponseGetView(){}

    public ResponseGetView(User user, Contenido[] contenidos) {
        this.user = user;
        this.contenidos = contenidos;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contenido[] getContenidos() {
        return contenidos;
    }

    public void setContenidos(Contenido[] contenidos) {
        this.contenidos = contenidos;
    }
}
