package com.example.acer.themoviedbnano;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName ="MovieFav")
public class MovieModel {
    @PrimaryKey @NonNull
    String Movieid;
    @ColumnInfo
    String Movieimg;
    @ColumnInfo
    String Movietitle;
    @ColumnInfo
    String MoviePosterpath;
    @ColumnInfo
    String MovieRating;
    @ColumnInfo
    String MovieOverview;
    @ColumnInfo
    String MovieOriginalTitle;
    @ColumnInfo
    String MovieDateofRelease;


    public MovieModel() {

    }

    public MovieModel(@NonNull String movieid, String movieimg, String movietitle, String moviePosterpath, String movieRating, String movieOverview, String movieOriginalTitle, String movieDateofRelease) {
        Movieid = movieid;
        Movieimg = movieimg;
        Movietitle = movietitle;
        MoviePosterpath = moviePosterpath;
        MovieRating = movieRating;
        MovieOverview = movieOverview;
        MovieOriginalTitle = movieOriginalTitle;
        MovieDateofRelease = movieDateofRelease;
    }

    public String getMovieid() {
        return Movieid;
    }

    public void setMovieid(String movieid) {
        Movieid = movieid;
    }

    public String getMovieimg() {
        return Movieimg;
    }

    public void setMovieimg(String movieimg) {
        Movieimg = movieimg;
    }

    public String getMovietitle() {
        return Movietitle;
    }

    public void setMovietitle(String movietitle) {
        Movietitle = movietitle;
    }

    public String getMoviePosterpath() {
        return MoviePosterpath;
    }

    public void setMoviePosterpath(String moviePosterpath) {
        MoviePosterpath = moviePosterpath;
    }

    public String getMovieRating() {
        return MovieRating;
    }

    public void setMovieRating(String movieRating) {
        MovieRating = movieRating;
    }

    public String getMovieOverview() {
        return MovieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        MovieOverview = movieOverview;
    }

    public String getMovieOriginalTitle() {
        return MovieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        MovieOriginalTitle = movieOriginalTitle;
    }

    public String getMovieDateofRelease() {
        return MovieDateofRelease;
    }

    public void setMovieDateofRelease(String movieDateofRelease) {
        MovieDateofRelease = movieDateofRelease;
    }
}
