package com.example.acer.themoviedbnano;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {
    public static MovieDao myDao;
    LiveData<List<MovieModel>> getalldata;

    public MovieRepository(Context context) {
        MovieDataBase myDataBase = MovieDataBase.getdetails(context);
        myDao = myDataBase.movieDao();
        getalldata = myDao.getAllMovie();


    }

    LiveData<List<MovieModel>> getallMovies() {
        return getalldata;
    }


    public void insert(MovieModel myEntity) {
        new InsertTask().execute(myEntity);
    }

    public void update(MovieModel myEntity) {
        new updateTask(myDao).execute(myEntity);
    }

    public void delete(MovieModel myEntity) {
        new deleteTask().execute(myEntity);
    }

    public String isFav(String sid) {
        return myDao.readID(sid);

    }

    public class InsertTask extends AsyncTask<MovieModel, Void, Void> {

        @Override
        protected Void doInBackground(MovieModel... myEntities) {
            myDao.mtitle(myEntities[0]);
            return null;
        }
    }

    public class updateTask extends AsyncTask<MovieModel, Void, Void> {
        private MovieDao dao;

        public updateTask(MovieDao mydao) {
            dao = mydao;
        }

        @Override
        protected Void doInBackground(MovieModel... myEntities) {
            myDao.getMovie(myEntities[0]);
            return null;
        }
    }

    public class deleteTask extends AsyncTask<MovieModel, Void, Void> {

        @Override
        protected Void doInBackground(MovieModel... myEntities) {
            myDao.movieDelete(myEntities[0]);
            return null;
        }
    }

}
