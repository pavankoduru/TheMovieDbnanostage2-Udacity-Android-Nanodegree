package com.example.acer.themoviedbnano;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    public MovieRepository movieRepository;
    public LiveData<List<MovieModel>> getMovieData;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        getMovieData = movieRepository.getallMovies();
    }


    public void insertdata(MovieModel movieModel) {
        movieRepository.insert(movieModel);
    }

    public void deletedata(MovieModel movieModel) {
        movieRepository.delete(movieModel);
    }

    public void updatedata(MovieModel movieModel) {
        movieRepository.update(movieModel);
    }

    public String read(String m) {
        return movieRepository.isFav(m);
    }

    public LiveData<List<MovieModel>> getAllmovie() {
        return getMovieData;
    }
}
