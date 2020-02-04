package com.example.tmdbmovieapi.model.pojo;

import java.io.Serializable;
import java.util.List;

public class searchnamemain implements Serializable {
    private String page;
    private List<searchname> results;

    public searchnamemain(String page, List <searchname> results) {
        this.page = page;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List <searchname> getResults() {
        return results;
    }

    public void setResults(List <searchname> results) {
        this.results = results;
    }
}
