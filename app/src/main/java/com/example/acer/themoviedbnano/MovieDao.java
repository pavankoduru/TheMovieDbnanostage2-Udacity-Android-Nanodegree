package com.example.acer.themoviedbnano;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface MovieDao {

    @Insert
    public void mtitle(MovieModel myEntity);
    @Update
    public void getMovie(MovieModel myEntity);
    @Query("select * from  moviefav")
    public LiveData<List<MovieModel>> getAllMovie();
    @Delete
    public void movieDelete(MovieModel myEntity);

    @Query("select Movieid from MovieFav where Movieid=:movie")
    public String readID(String movie);

}
