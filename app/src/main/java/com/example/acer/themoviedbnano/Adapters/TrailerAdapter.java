package com.example.acer.themoviedbnano.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.themoviedbnano.DetailActivity;
import com.example.acer.themoviedbnano.POJOs.TrailerModel;
import com.example.acer.themoviedbnano.R;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    Context context;
    ArrayList<TrailerModel> trailerModelList;


    public TrailerAdapter(Context context, ArrayList<TrailerModel> trailerModelList) {
        this.context=context;
        this.trailerModelList=trailerModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.trailerdesignadapter,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.trailername.setText(trailerModelList.get(i).getName());
        final String key=trailerModelList.get(i).getKey();
        viewHolder.trailervideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+key)));

            }
        });

    }

    @Override
    public int getItemCount() {
        return trailerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView trailervideo;
        TextView trailername;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trailervideo=itemView.findViewById(R.id.trailervideologo);
            trailername=itemView.findViewById(R.id.trailertextview);

        }
    }
}
