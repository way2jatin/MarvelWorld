package com.jatin.marvelworld.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jatin.marvelworld.R;
import com.jatin.marvelworld.fragment.CharacterFragment;
import com.jatin.marvelworld.fragment.ComicFragment;

/**
 * Created by uw on 13/6/17.
 */

public class CustomAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public CustomAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new CharacterFragment();
        else
            return new ComicFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return mContext.getString(R.string.category_characters);
        else
            return mContext.getString(R.string.category_comics);
    }
}
