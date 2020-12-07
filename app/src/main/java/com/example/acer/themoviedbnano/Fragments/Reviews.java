package com.example.acer.themoviedbnano.Fragments;


import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.themoviedbnano.Adapters.ReviewAdapter;
import com.example.acer.themoviedbnano.POJOs.ReviewModel;
import com.example.acer.themoviedbnano.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Reviews extends Fragment {
    RecyclerView recyclerView;
    String Review_url1="https://api.themoviedb.org/3/movie/";
    String Review_url2="/reviews?api_key=2102fede37aef6e84e37cf5bdd0b80aa";
    String mid;
    RequestQueue requestQueue;
    List<ReviewModel> reviewModelList;




    public Reviews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_reviews, container, false);
        reviewModelList=new ArrayList<>();
        recyclerView=view.findViewById(R.id.reviewrecyclerview);

        String[] s=getActivity().getIntent().getStringArrayExtra("mydata");
        mid=s[4];



        requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, Review_url1 + mid + Review_url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null) {

                    try {
                        JSONObject RootObject = new JSONObject(response);
                        JSONArray results = RootObject.optJSONArray("results");

                        for (int index = 0; index < results.length(); index++) {
                            JSONObject object = results.optJSONObject(index);
                            String author = object.optString("author");

                            String content = object.optString("content");

                            reviewModelList.add(new ReviewModel(author, content));

                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(new ReviewAdapter(getActivity(),reviewModelList));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else Toast.makeText(getActivity(), "No Reviews Available", Toast.LENGTH_SHORT).show();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        if(checkInternetConnection())
        requestQueue.add(stringRequest);
        else Toast.makeText(getActivity(), "Kindly check your Internet Connection", Toast.LENGTH_SHORT).show();

        return view;


    }
    public boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        return (connectivityManager.getActiveNetworkInfo()!=null)&&(connectivityManager.getActiveNetworkInfo().isAvailable())&&(connectivityManager.getActiveNetworkInfo().isConnected());}




}
