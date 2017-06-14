package com.jatin.marvelworld.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jatin.marvelworld.R;
import com.jatin.marvelworld.model.comics.ItemsItem;
import com.jatin.marvelworld.util.AppController;
import com.jatin.marvelworld.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 13/6/17.
 */

public class ComicsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.comic_cover)
    NetworkImageView cover;

    @BindView(R.id.comic_title)
    TextView txt_title;

    @BindView(R.id.comic_page)
    TextView pages;

    @BindView(R.id.comic_saledate)
    TextView saleDate;

    @BindView(R.id.comic_printprice)
    TextView txt_printPrice;

    @BindView(R.id.comic_ebook_price)
    TextView txt_eBookPrice;

    @BindView(R.id.comic_creators)
    TextView Creators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_details);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("title");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        int pageCount = getIntent().getIntExtra("pageCount",0);
        String date = getIntent().getStringExtra("date");
        double printPrice = getIntent().getDoubleExtra("printPrice",0);
        double eBookPrice = getIntent().getDoubleExtra("eBookPrice",0);
        ArrayList<ItemsItem> itemsItems = getIntent().getParcelableArrayListExtra("itemsItem");

        cover.setDefaultImageResId(R.drawable.marvellogo);
        cover.setImageUrl(imageUrl + "/landscape_incredible.jpg", AppController.getInstance().getImageLoader());

        if (title != null) {
            txt_title.setText(title);
            setTitle(title);
        }

        if (pageCount > 0)
            pages.setText(String.valueOf(pageCount));

        try {
            if (date != null) {

                String fullDateString = date;
                String setDate = fullDateString.substring(0, fullDateString.indexOf('T'));
                saleDate.setText(setDate);
            }
        } catch (NullPointerException n) {
            Log.e("NullException", n.toString(),n);
        }

        try {
            if (printPrice > 0)
                txt_printPrice.setText("$" + String.valueOf(printPrice));
        } catch (NullPointerException n) {
            Log.e("NullException", n.toString(),n);
        }


        try {
            if (eBookPrice > 0)
                txt_eBookPrice.setText("$" + String.valueOf(eBookPrice));
        } catch (NullPointerException n) {
            Log.e("NullExc", n.toString(),n);
        } catch (IndexOutOfBoundsException i) {
            Log.e("IndexExc", i.toString(),i);
        }

        try {

            if (itemsItems != null) {

                String creatorsNames = "";

                for (ItemsItem curInstance : itemsItems) {
                    String name = curInstance.getName();
                    creatorsNames = creatorsNames + name + ", ";
                }

                if (!creatorsNames.equals("")) {
                    String listOfCreatorsNames = creatorsNames.substring(0, creatorsNames.length() - 2);
                    Creators.setText(listOfCreatorsNames);
                }
            }

        } catch (NullPointerException n) {
            Log.e("NullError", n.toString(),n);
        } catch (IndexOutOfBoundsException i) {
            Log.e("IndexError", i.toString(),i);
        }
    }
}
