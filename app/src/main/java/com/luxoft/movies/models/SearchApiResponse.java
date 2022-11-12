package com.luxoft.movies.models;

import java.util.List;

public class SearchApiResponse {
    List<SearchArrayObject> titles = null;
    List<SearchArrayObject> ids = null;

    public List<SearchArrayObject> getTitles() {
        return titles;
    }

    public void setTitles(List<SearchArrayObject> titles) {
        this.titles = titles;
    }


    public List<SearchArrayObject> getIds() {
        return ids;
    }

    public void setIds(List<SearchArrayObject> ids) {
        this.ids = ids;
    }
}