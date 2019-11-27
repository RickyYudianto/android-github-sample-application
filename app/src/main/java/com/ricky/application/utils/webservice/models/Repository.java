package com.ricky.application.utils.webservice.models;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("watchers_count")
    private int watchers;

    @SerializedName("stargazers_count")
    private int stars;

    @SerializedName("created_at")
    private String createdDate;

    public Repository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", watchers=" + watchers +
                ", stars=" + stars +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
