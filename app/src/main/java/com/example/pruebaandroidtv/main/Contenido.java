package com.example.pruebaandroidtv.main;

public class Contenido {

    private String id;
    private String title;
    private String cover;
    private String duration;
    private String url;
    private String section;

    public Contenido() {

    }

    public Contenido(String id, String title, String cover, String duration, String url, String section) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.duration = duration;
        this.url = url;
        this.section = section;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
