package com.example.acer.themoviedbnano.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.themoviedbnano.Adapters.TrailerAdapter;
import com.example.acer.themoviedbnano.DetailActivity;
import com.example.acer.themoviedbnano.POJOs.TrailerModel;
import com.example.acer.themoviedbnano.R;

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

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Trailers extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TrailerModel> trailerModelList;
    String trailer_url1="http://api.themoviedb.org/3/movie/" ;
    String getTrailer_url12="/videos?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String mid;


    public Trailers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        String[] s=getActivity().getIntent().getStringArrayExtra("mydata");
        mid=s[4];
        recyclerView = view.findViewById(R.id.trailerrecyclerview);

        trailerModelList = new ArrayList<>();


        if(checkInternetConnection())
        new TrailerAsync().execute();
        else Toast.makeText(getActivity(), "Kindly check your Internet Connection", Toast.LENGTH_SHORT).show();



        return view;
    }

    public class TrailerAsync extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            try {

                URL urlstring = new URL(trailer_url1 + mid + getTrailer_url12);
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlstring.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                Scanner s = new Scanner(inputStream);
                s.useDelimiter("\\A");
                if (s.hasNext()) {
                    return s.next();
                } else return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s!=null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray results = jsonObject.optJSONArray("results");
                    for (int index = 0; index < results.length(); index++) {
                        JSONObject firstObject = results.optJSONObject(index);
                        final String MovieId = firstObject.optString("key");

                        final String MovieName = firstObject.optString("name");

                        trailerModelList.add(new TrailerModel(MovieId, MovieName));
                    }


                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    recyclerView.setAdapter(new TrailerAdapter(getContext(), trailerModelList));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else Toast.makeText(getActivity(), "No trailers Available", Toast.LENGTH_SHORT).show();

        }


    }
    public boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        return (connectivityManager.getActiveNetworkInfo()!=null)&&(connectivityManager.getActiveNetworkInfo().isAvailable())&&(connectivityManager.getActiveNetworkInfo().isConnected());}

}
