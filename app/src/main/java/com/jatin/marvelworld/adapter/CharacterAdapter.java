package com.jatin.marvelworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jatin.marvelworld.R;
import com.jatin.marvelworld.activity.CharDetailsActivity;
import com.jatin.marvelworld.model.chars.Result;
import com.jatin.marvelworld.util.AppController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 13/6/17.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    List<Result> characterList = new ArrayList<>();

    public CharacterAdapter(List<Result> characterList) {
        this.characterList = characterList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(parent.getContext(), v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result result = characterList.get(position);
        holder.name.setText(result.getName());
        holder.poster.setDefaultImageResId(R.drawable.marvellogo);
        holder.poster.setImageUrl(result.getThumbnail().getPath()+"/standard_fantastic.jpg", AppController.getInstance().getImageLoader());

    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final Context context;
        @BindView(R.id.poster)
        NetworkImageView poster;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(final Context context, View v) {
            super(v);
            ButterKnife.bind(this, v);
            this.context = context;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Result charsDetails = characterList.get(getAdapterPosition());
            Intent mainIntent = new Intent(context, CharDetailsActivity.class);
            mainIntent.putExtra("name", charsDetails.getName());
            mainIntent.putExtra("imageUrl",charsDetails.getThumbnail().getPath());
            mainIntent.putExtra("description",charsDetails.getDescription());
            mainIntent.putExtra("comics",charsDetails.getComics().getAvailable());
            mainIntent.putExtra("series",charsDetails.getSeries().getAvailable());
            context.startActivity(mainIntent);
        }
    }
}
