package com.example.tmdbmovieapi.model.pojo;

import java.io.Serializable;
import java.util.List;

public class similarmovielistmain implements Serializable {
    private String page;
    private List <similarmovielist> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List <similarmovielist> getResults() {
        return results;
    }

    public void setResults(List <similarmovielist> results) {
        this.results = results;
    }

    public similarmovielistmain(String page, List <similarmovielist> results) {
        this.page = page;
        this.results = results;
    }
}
