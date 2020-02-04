package com.example.tmdbmovieapi.model.pojo;

import java.io.Serializable;
import java.util.List;

public class genremain implements Serializable {
    private String release_date;
    private String runtime;
    private String tagline;
    private String status;


    private List<genres> genres;

    @Override
    public String toString() {
        return "genremain{" +
                "release_date='" + release_date + '\'' +
                ", runtime='" + runtime + '\'' +
                ", tagline='" + tagline + '\'' +
                ", status='" + status + '\'' +
                ", genres=" + genres +
                '}';
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List <com.example.tmdbmovieapi.model.pojo.genres> getGenres() {
        return genres;
    }

    public void setGenres(List <com.example.tmdbmovieapi.model.pojo.genres> genres) {
        this.genres = genres;
    }

    public genremain(String release_date, String runtime, String tagline, String status, List <com.example.tmdbmovieapi.model.pojo.genres> genres) {
        this.release_date = release_date;
        this.runtime = runtime;
        this.tagline = tagline;
        this.status = status;
        this.genres = genres;
    }
}
