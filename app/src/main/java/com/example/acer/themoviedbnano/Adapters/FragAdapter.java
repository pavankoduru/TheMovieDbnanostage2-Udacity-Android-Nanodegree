package com.example.acer.themoviedbnano.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.acer.themoviedbnano.Fragments.Reviews;
import com.example.acer.themoviedbnano.Fragments.Trailers;

public class FragAdapter extends FragmentPagerAdapter {
    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch(i)
        {
            case 0:
                return new Trailers();
            case 1:
                return new Reviews();
        }


        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Trailers";
            case 1:
                return "Reviews";
        }

        return super.getPageTitle(position);
    }
}
