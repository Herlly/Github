package com.example.github.domain;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetItem {
    private int total_count;
    private List<ItemsBean> items;
    public static GetItem objectFromData(String str) {
        return new Gson().fromJson(str, GetItem.class);
    }
    public static GetItem objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GetItem.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GetItem> arrayGetItemFromData(String str) {

        Type listType = new TypeToken<ArrayList<GetItem>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<GetItem> arrayGetItemFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GetItem>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
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
         * score : 1.0
         */

        private String login;
        private String avatar_url;
        private String html_url;

        public static ItemsBean objectFromData(String str) {

            return new Gson().fromJson(str, ItemsBean.class);
        }

        public static ItemsBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ItemsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<ItemsBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<ItemsBean> arrayItemsBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<ItemsBean>>() {
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
    }
}
