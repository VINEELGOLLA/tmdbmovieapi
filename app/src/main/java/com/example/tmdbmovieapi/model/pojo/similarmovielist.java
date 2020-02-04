package com.example.tmdbmovieapi.model.pojo;

public class similarmovielist {
    String poster_path;
    String original_title;
    String id;

    public String getPoster_path() {
        return poster_path;
    }

    public similarmovielist() {
    }

    @Override
    public String toString() {
        return "similarmovielist{" +
                "poster_path='" + poster_path + '\'' +
                ", original_title='" + original_title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public similarmovielist(String poster_path, String original_title, String id) {
        this.poster_path = poster_path;
        this.original_title = original_title;
        this.id = id;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
