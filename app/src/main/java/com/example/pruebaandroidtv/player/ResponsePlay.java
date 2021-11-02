package com.example.pruebaandroidtv.player;

public class ResponsePlay {

    private String id;
    private String title;
    private String cover;
    private String url;
    private int duration;
    private String rating;
    private int votes;
    private int totalVotes;

    public ResponsePlay(){

    }

    public ResponsePlay(String id, String title, String cover, String url, int duration, String rating, int votes, int totalVotes) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.url = url;
        this.duration = duration;
        this.rating = rating;
        this.votes = votes;
        this.totalVotes = totalVotes;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
