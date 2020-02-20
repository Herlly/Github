package com.example.github.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetUserItem {

    /**
     * login : torvalds
     * id : 1024025
     * node_id : MDQ6VXNlcjEwMjQwMjU=
     * avatar_url : https://avatars0.githubusercontent.com/u/1024025?v=4
     * gravatar_id :
     * url : https://api.github.com/users/torvalds
     * html_url : https://github.com/torvalds
     * followers_url : https://api.github.com/users/torvalds/followers
     * following_url : https://api.github.com/users/torvalds/following{/other_user}
     * gists_url : https://api.github.com/users/torvalds/gists{/gist_id}
     * starred_url : https://api.github.com/users/torvalds/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/torvalds/subscriptions
     * organizations_url : https://api.github.com/users/torvalds/orgs
     * repos_url : https://api.github.com/users/torvalds/repos
     * events_url : https://api.github.com/users/torvalds/events{/privacy}
     * received_events_url : https://api.github.com/users/torvalds/received_events
     * type : User
     * site_admin : false
     * name : Linus Torvalds
     * company : Linux Foundation
     * blog :
     * location : Portland, OR
     * email : null
     * hireable : null
     * bio : null
     * public_repos : 6
     * public_gists : 0
     * followers : 105231
     * following : 0
     * created_at : 2011-09-03T15:26:22Z
     * updated_at : 2019-12-28T20:45:15Z
     */

    private String login;
    private String avatar_url;
    private String html_url;
    private String name;
    private String bio;
    private int followers;
    private int following;
    private String created_at;

    public static GetUserItem objectFromData(String str) {

        return new Gson().fromJson(str, GetUserItem.class);
    }

    public static GetUserItem objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GetUserItem.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GetUserItem> arrayGetUserItemFromData(String str) {

        Type listType = new TypeToken<ArrayList<GetUserItem>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<GetUserItem> arrayGetUserItemFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GetUserItem>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
