package com.example.acer.themoviedbnano.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.acer.themoviedbnano.DetailActivity;
import com.example.acer.themoviedbnano.POJOs.ReviewModel;
import com.example.acer.themoviedbnano.R;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewModel> reviewModelList;

    public ReviewAdapter(FragmentActivity activity, List<ReviewModel> reviewModelList) {
        this.context=activity;
        this.reviewModelList=reviewModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       View view=LayoutInflater.from(context).inflate(R.layout.reviewdesignadapter,viewGroup,false);

       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.content.setText(reviewModelList.get(i).getContent());
        viewHolder.author.append(reviewModelList.get(i).getAuthor());

    }

    @Override
    public int getItemCount() {
        return reviewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content,author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.reviewtext);
            author=itemView.findViewById(R.id.authortext);
        }
    }
}
