package com.malekAubert.android.movieapp;

public class Movie {
    private String title;
    private String releaseDate;
    private String director;
    private String description;
    private String urlPoster;

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public Movie(String title, String releaseDate, String director, String description, String urlPoster) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.description = description;
        this.urlPoster = urlPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
