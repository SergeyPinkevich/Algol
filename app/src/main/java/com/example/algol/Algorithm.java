package com.example.algol;

import java.util.ArrayList;

/**
 * Created by Сергей Пинкевич on 04.12.2016.
 */

public class Algorithm {

    private int id;
    private String name;
    private String category;
    private String description;
    private String complexity;

    public Algorithm(int id, String name, String category, String description, String complexity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.complexity = complexity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }
}
