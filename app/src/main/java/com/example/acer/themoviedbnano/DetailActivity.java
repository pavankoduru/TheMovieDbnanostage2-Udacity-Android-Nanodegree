package com.example.acer.themoviedbnano;

import android.database.sqlite.SQLiteConstraintException;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.themoviedbnano.Adapters.FragAdapter;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView t1,t2,t3,t5;
    ImageView i1;

    String mid;
    RatingBar r;
    TabLayout tabLayout;
    ViewPager viewPager;
    MaterialFavoriteButton materialFavoriteButton;

    String string;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        t1=findViewById(R.id.detailtext1);
        t2=findViewById(R.id.detailtext2);
        t3=findViewById(R.id.detailtext3);

        t5=findViewById(R.id.dateofrelease);
        i1=findViewById(R.id.detailimg);
        r=findViewById(R.id.rid);
        materialFavoriteButton=findViewById(R.id.materialflow);
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);


        viewPager.setAdapter(new FragAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);







         final String[] s=getIntent().getStringArrayExtra("mydata");

        t1.setText(s[0]);
        Picasso.with(this).load("https://image.tmdb.org/t/p/w500"+s[3]).placeholder(R.mipmap.ic_launcher_round).error(R.drawable.warning).into(i1);
        t2.setText(s[1]);
        t3.append(s[2]);

        r.setRating(Float.parseFloat(s[5])/2);
        mid=s[4];

        t5.append(s[6]+"==<");



        string=MainActivity.movieViewModel.read(mid);
        if(string!=null)
        {
            materialFavoriteButton.setFavorite(true,true);
        }
        else {
            materialFavoriteButton.setFavorite(false,true);
        }


        materialFavoriteButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {

                MovieModel newmodel =new MovieModel();
                if (favorite) {
                    try {

                        newmodel=new MovieModel(s[4],s[8],s[0],s[3],s[5],s[1],s[2],s[6]);

                        materialFavoriteButton.setFavorite(true);
                        MainActivity.movieViewModel.insertdata(newmodel);
                        Toast.makeText(getApplicationContext(), "added to favourites", Toast.LENGTH_SHORT).show();

                    }  catch (SQLiteConstraintException e) {
                        e.printStackTrace();

                    }
                } else {
                    newmodel=new MovieModel(s[4],s[8],s[0],s[3],s[5],s[1],s[2],s[6]);

                    MainActivity.movieViewModel.deletedata(newmodel);

                    Toast.makeText(getApplicationContext(), "Removed from favourites", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

}
