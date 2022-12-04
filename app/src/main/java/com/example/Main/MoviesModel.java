package com.example.recyclerdemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
public class MoviesModel {
    ArrayList<Movie>results;
    @SerializedName("total_pages")
    String totalPages;
    public ArrayList<Movie> getMovies() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }

    public String getTotal_pages() {
        return totalPages;
    }

    public void setTotal_pages(String total_pages) {
        this.totalPages = total_pages;
    }
}
