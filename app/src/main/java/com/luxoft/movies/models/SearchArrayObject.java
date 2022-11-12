package com.luxoft.movies.models;

public class SearchArrayObject {
    String Title = "";
    String Poster = "";
    String imdbID = "";

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getImage() {
        return Poster;
    }

    public void setImage(String image) {
        this.Poster = image;
    }

    public String getId() {
        return imdbID;
    }

    public void setId(String id) {
        this.imdbID = id;
    }
}