package com.example.study.trash.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.study.trash.Board.BoardFragment;
import com.example.study.trash.Map.MyLocation;

/**
 * Created by KwakSeYoun on 2015. 10. 25..
 */
public class MainAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragments = new Fragment[2];

    public MainAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new MyLocation();
        fragments[1] = new BoardFragment();
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments[position];
    }
}

