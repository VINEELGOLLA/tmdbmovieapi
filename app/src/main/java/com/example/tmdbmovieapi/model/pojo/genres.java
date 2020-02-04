package com.example.tmdbmovieapi.model.pojo;

import java.io.Serializable;
import java.util.List;

public class genres implements Serializable {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "genres{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public genres(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
