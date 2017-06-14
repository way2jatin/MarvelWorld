package com.jatin.marvelworld.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.jatin.marvelworld.R;
import com.jatin.marvelworld.fragment.CharacterFragment;
import com.jatin.marvelworld.fragment.ComicFragment;

/**
 * Created by uw on 13/6/17.
 */

public class SearchActivity extends AppCompatActivity {

    CharacterFragment characterFragment;
    ComicFragment comicFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle bundle = getIntent().getExtras();
        String whichFragment = bundle.getString("Fragment");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (whichFragment.equals("Chars")) {
            //add CharsFragment
            characterFragment = new CharacterFragment();
            fragmentTransaction.add(R.id.search, characterFragment);
            characterFragment.setArguments(bundle);
        } else {
            //add ComicsFragment
            comicFragment = new ComicFragment();
            fragmentTransaction.add(R.id.search, comicFragment);
            comicFragment.setArguments(bundle);
        }

        fragmentTransaction.commit();
    }
}
