package com.ricky.application.utils.webservice.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;
//    private String url;
//    private String followersUrl;
//    private String followingUrl;
//    private String gistsUrl;
//    private String starredUrl;
//    private String subscriptionsUrl;
//    private String organizationsUrl;
//    private String reposUrl;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getFollowersUrl() {
//        return followersUrl;
//    }
//
//    public void setFollowersUrl(String followersUrl) {
//        this.followersUrl = followersUrl;
//    }
//
//    public String getFollowingUrl() {
//        return followingUrl;
//    }
//
//    public void setFollowingUrl(String followingUrl) {
//        this.followingUrl = followingUrl;
//    }
//
//    public String getGistsUrl() {
//        return gistsUrl;
//    }
//
//    public void setGistsUrl(String gistsUrl) {
//        this.gistsUrl = gistsUrl;
//    }
//
//    public String getStarredUrl() {
//        return starredUrl;
//    }
//
//    public void setStarredUrl(String starredUrl) {
//        this.starredUrl = starredUrl;
//    }
//
//    public String getSubscriptionsUrl() {
//        return subscriptionsUrl;
//    }
//
//    public void setSubscriptionsUrl(String subscriptionsUrl) {
//        this.subscriptionsUrl = subscriptionsUrl;
//    }
//
//    public String getOrganizationsUrl() {
//        return organizationsUrl;
//    }
//
//    public void setOrganizationsUrl(String organizationsUrl) {
//        this.organizationsUrl = organizationsUrl;
//    }
//
//    public String getReposUrl() {
//        return reposUrl;
//    }
//
//    public void setReposUrl(String reposUrl) {
//        this.reposUrl = reposUrl;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
