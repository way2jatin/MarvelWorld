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

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_CHARACTER = 0;
    private final int VIEW_PROGRESS = 1;
    List<Result> characterList = new ArrayList<>();
    private OnLoadMoreListener onLoadMoreListener;

    public CharacterAdapter(List<Result> characterList) {
        this.characterList = characterList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_CHARACTER){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new CharacterViewHolder(parent.getContext(), v);
        }
        else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_progress, parent, false);

            return new ProgressViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterViewHolder){
            CharacterViewHolder viewHolder = (CharacterViewHolder) holder;
            final Result result = characterList.get(position);
            viewHolder.name.setText(result.getName());
            viewHolder.poster.setDefaultImageResId(R.drawable.marvellogo);
            viewHolder.poster.setImageUrl(result.getThumbnail().getPath()+"/standard_fantastic.jpg", AppController.getInstance().getImageLoader());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return characterList.get(position) != null ? VIEW_CHARACTER : VIEW_PROGRESS;
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public int getCharacterItemsCount() {
        if (isProgressViewVisible())
            return characterList.size() - 1;

        return characterList.size();
    }

    public boolean isProgressViewVisible() {
        return characterList.contains(null);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }
    public interface OnLoadMoreListener {
        void onLoadMore();
    }


    public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final Context context;
        @BindView(R.id.poster)
        NetworkImageView poster;
        @BindView(R.id.name)
        TextView name;

        public CharacterViewHolder(final Context context, View v) {
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

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(View itemView) {
            super(itemView);
        }
    }
}
