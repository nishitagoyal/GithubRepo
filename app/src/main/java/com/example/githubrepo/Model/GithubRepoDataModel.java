package com.example.githubrepo.Model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GithubRepoDataModel implements Serializable {
    private String author;
    private String name;
    private String avatar;
    private String url;
    private String description;
    private int stars;
    private int forks;
    private int currentPeriodStars;
    @SerializedName("builtBy")
    List<DeveloperDetail>DeveloperDetailList;

    public List<DeveloperDetail> getDeveloperDetailList() {
        return DeveloperDetailList;
    }

    public void setDeveloperDetailList(List<DeveloperDetail> developerDetailList) {
        DeveloperDetailList = developerDetailList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getCurrentPeriodStars() {
        return currentPeriodStars;
    }

    public void setCurrentPeriodStars(int currentPeriodStars) {
        this.currentPeriodStars = currentPeriodStars;
    }

        public static class DeveloperDetail implements Serializable
        {
            String username;
            String href;
            String avatar;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
}

