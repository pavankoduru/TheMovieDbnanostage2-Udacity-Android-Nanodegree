package com.example.acer.themoviedbnano.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.acer.themoviedbnano.DetailActivity;
import com.example.acer.themoviedbnano.MainActivity;
import com.example.acer.themoviedbnano.MovieModel;
import com.example.acer.themoviedbnano.MovieViewModel;
import com.example.acer.themoviedbnano.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    List<MovieModel> modelclass;







    public MyAdapter(MainActivity mainActivity, List<MovieModel> modelclass) {
        context=mainActivity;
        this.modelclass=modelclass;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v=LayoutInflater.from(context).inflate(R.layout.adapterdesign,viewGroup,false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final MovieModel model=modelclass.get(i);
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500"+model.getMovieimg()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.warning).into(myViewHolder.imageView);


        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String[] str=new String[9];
                str[0]=modelclass.get(i).getMovietitle();

                str[1]=modelclass.get(i).getMovieOverview();
                str[2]=modelclass.get(i).getMovieOriginalTitle();
                str[3]=modelclass.get(i).getMoviePosterpath();
                str[4]=modelclass.get(i).getMovieid();
                str[5]=modelclass.get(i).getMovieRating();
                str[6]=modelclass.get(i).getMovieDateofRelease();
                str[7]=String.valueOf(i);
                str[8]=modelclass.get(i).getMovieimg();

                Intent intent=new Intent(context.getApplicationContext(), DetailActivity.class);

                intent.putExtra("mydata",str);

                context.startActivity(intent);

            }


        });










    }

    @Override
    public int getItemCount() {
        return modelclass.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);


        }
    }
}
