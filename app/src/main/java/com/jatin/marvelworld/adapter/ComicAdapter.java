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
import com.jatin.marvelworld.activity.ComicsDetailsActivity;
import com.jatin.marvelworld.model.comics.Result;
import com.jatin.marvelworld.util.AppController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 13/6/17.
 */

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    List<Result> comicList = new ArrayList<>();

    public ComicAdapter(List<Result> comicList) {
        this.comicList = comicList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(parent.getContext(), v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Result result = comicList.get(position);
        holder.name.setText(result.getTitle());
        holder.poster.setDefaultImageResId(R.drawable.marvellogo);
        holder.poster.setImageUrl(result.getThumbnail().getPath() + "/standard_fantastic.jpg", AppController.getInstance().getImageLoader());
    }

    @Override
    public int getItemCount() {
        return comicList.size();
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
        public void onClick(View v) {
            Result comicDetails = comicList.get(getAdapterPosition());
            Intent mainIntent = new Intent(context, ComicsDetailsActivity.class);
            mainIntent.putExtra("imageUrl",comicDetails.getThumbnail().getPath());
            mainIntent.putExtra("title",comicDetails.getTitle());
            mainIntent.putExtra("pageCount",comicDetails.getPageCount());
            mainIntent.putExtra("date",comicDetails.getDates().get(0).getDate());
            mainIntent.putExtra("printPrice",comicDetails.getPrices().get(0).getPrice());
            mainIntent.putExtra("eBookPrice",comicDetails.getPrices().get(1).getPrice());
            mainIntent.putParcelableArrayListExtra("itemsItem",comicDetails.getCreators().getItems());
            context.startActivity(mainIntent);
        }
    }
}
