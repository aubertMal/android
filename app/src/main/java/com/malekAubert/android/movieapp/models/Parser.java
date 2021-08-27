package com.malekAubert.android.movieapp.models;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {
    public Movie setMovieFromJSON(String movieJson) {
        Movie movie = new Movie("", "", "", "", "");
        try {
            JSONObject jsonObject = new JSONObject(movieJson);
            movie.setTitle(jsonObject.getString("Title"));
            movie.setReleaseDate(jsonObject.getString("Released"));
            movie.setDirector(jsonObject.getString("Director"));
            movie.setDescription(jsonObject.getString("Plot"));
            movie.setUrlPoster(jsonObject.getString("Poster"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public ArrayList<Movie> setMoviesFromJSON(String stringJson) {
        ArrayList<Movie> movies = new ArrayList<>();
//TODO
        return movies;
    }
}
