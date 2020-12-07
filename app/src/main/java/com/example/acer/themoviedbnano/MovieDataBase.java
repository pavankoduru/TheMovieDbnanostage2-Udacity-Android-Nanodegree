package com.example.acer.themoviedbnano;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.graphics.Movie;


@Database(entities = {MovieModel.class},version = 1,exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public static MovieDataBase movieDataBase;

    public static MovieDataBase getdetails(Context context) {
        if (movieDataBase == null) {
            movieDataBase = Room.databaseBuilder(context, MovieDataBase.class,
                    "movie").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return movieDataBase;

    }




}
