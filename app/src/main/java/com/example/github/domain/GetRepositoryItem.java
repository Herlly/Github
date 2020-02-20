package com.example.github.domain;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetRepositoryItem  {
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String language;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    private String name;

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    private String html_url;
    private String description;
    private int stargazers_count;
    private int forks_count;
}
