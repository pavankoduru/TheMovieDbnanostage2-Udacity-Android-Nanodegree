package com.example.acer.themoviedbnano;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.themoviedbnano.Adapters.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>
{
    RecyclerView rv;
    String popularurl="https://api.themoviedb.org/3/movie/popular?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String ratedurl="https://api.themoviedb.org/3/movie/top_rated?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String urlmain;
    ProgressBar pbar;
    public static List<MovieModel> modelclass;
    TextView textView;


    static  MovieViewModel movieViewModel;
    public static final int Loader_id1=1;
    public static  final int Loader_id2=2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rcycle);
        pbar=findViewById(R.id.pbar);
        modelclass=new ArrayList<>();
        movieViewModel=ViewModelProviders.of(this).get(MovieViewModel.class);
        textView=findViewById(R.id.textview);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(new MyAdapter(this, (ArrayList<MovieModel>) modelclass));

        textView.setText("Popular movies");
        urlmain=popularurl;
        if(checkInternetConnection())
            getSupportLoaderManager().initLoader(Loader_id1,null,this);
        else Toast.makeText(this, "Kindly check your Internet Connection", Toast.LENGTH_SHORT).show();



    }
    public  static MovieViewModel getMovieModel()
    {
        return movieViewModel;
    }
    public static  List<MovieModel> getModelList() {
        return modelclass;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.moviemenu,menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.popular:
                urlmain=popularurl;

                textView.setText(item.getTitle());
                urlmain=popularurl;
                if(checkInternetConnection())
                    getSupportLoaderManager().restartLoader(Loader_id1,null,this);
                else Toast.makeText(this, "Kindly check your Internet Connection", Toast.LENGTH_SHORT).show();


                break;




            case R.id.rated:
                urlmain=ratedurl;
                textView.setText(item.getTitle());


                if(checkInternetConnection())
                    getSupportLoaderManager().initLoader(Loader_id2,null,this);
                else Toast.makeText(this, "Kindly check your Internet Connection", Toast.LENGTH_SHORT).show();



                break;
            case R.id.fav:

                textView.setText(item.getTitle());

                movieViewModel.getAllmovie().observe(this, new Observer<List<MovieModel>>() {
                    @Override
                    public void onChanged(@Nullable List<MovieModel> myEntities) {
                        rv.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                        rv.setAdapter(new MyAdapter(MainActivity.this, myEntities));
                    }
                });
                break;


        }

        return super.onOptionsItemSelected(item);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new AsyncTaskLoader<String>(this) {
            @Nullable
            @Override
            public String loadInBackground() {
                try {
                    URL urlink=new URL(urlmain);

                    HttpURLConnection httpURLConnection= (HttpURLConnection) urlink.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream=httpURLConnection.getInputStream();
                    Scanner s= new Scanner(inputStream);
                    s.useDelimiter("\\A");
                    if(s.hasNext())
                    {
                        Log.i("datareturn","datareturning");
                        return s.next();

                    }
                    else return null;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
                modelclass.clear();
                rv.setAdapter(new MyAdapter(MainActivity.this, (ArrayList<MovieModel>) modelclass));
                pbar.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try {
            if(s!=null) {
                modelclass.clear();
                JSONObject obj = new JSONObject(s);
                JSONArray dataArray = obj.optJSONArray("results");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject obj1 = dataArray.optJSONObject(i);
                    String title = obj1.optString("original_title");
                    String overview = obj1.optString("overview");
                    String imgurl = obj1.optString("poster_path");
                    String oimgurl = obj1.optString("backdrop_path");
                    String otitle = obj1.optString("original_title");
                    String mid=obj1.optString("id");
                    String rating=obj1.optString("vote_average");
                    String dateofrelease=obj1.optString("release_date");
                    modelclass.add(new MovieModel(mid,imgurl,title,oimgurl,rating,overview,otitle,dateofrelease));

                }
            } else{
                Toast.makeText(MainActivity.this, " Sorry!..check your  Internet Connection", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        pbar.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    public boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return (connectivityManager.getActiveNetworkInfo()!=null)&&(connectivityManager.getActiveNetworkInfo().isAvailable())&&(connectivityManager.getActiveNetworkInfo().isConnected());
    }
}
