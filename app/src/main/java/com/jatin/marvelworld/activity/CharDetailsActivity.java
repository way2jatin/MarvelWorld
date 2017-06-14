package com.jatin.marvelworld.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jatin.marvelworld.R;
import com.jatin.marvelworld.util.AppController;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 13/6/17.
 */

public class CharDetailsActivity extends AppCompatActivity {

    @BindView(R.id.char_cover)
    NetworkImageView charPhoto;

    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_description)
    AppCompatTextView txtDescription;
    @BindView(R.id.txt_featured_comics)
    AppCompatTextView txtFeaturedComics;
    @BindView(R.id.txt_featured_series)
    AppCompatTextView txtFeaturedSeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chars_details);
        ButterKnife.bind(this);

        String characterName = getIntent().getStringExtra("name");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String description = getIntent().getStringExtra("description");
        int comicNo = getIntent().getIntExtra("comics",0);
        int seriesNo = getIntent().getIntExtra("series",0);


        charPhoto.setDefaultImageResId(R.drawable.marvellogo);
        charPhoto.setImageUrl(imageUrl + "/portrait_uncanny.jpg", AppController.getInstance().getImageLoader());

        txt_name.setText(characterName);

        if (description == null || description.isEmpty()){
            txtDescription.setText("No Description Found");
        }
        else {
            txtDescription.setText(description);
        }

        if (comicNo > 0){
            txtFeaturedComics.setText("Featured in " + comicNo + " Marvel Comics");
        }
        else {
            txtFeaturedComics.setText("Not featured in any Marvel Comics");
        }

        if (seriesNo > 0){
            txtFeaturedSeries.setText("Featured in " + seriesNo + " Marvel Series");
        }
        else {
            txtFeaturedSeries.setText("Not featured in any Marvel Series");
        }
    }
}
