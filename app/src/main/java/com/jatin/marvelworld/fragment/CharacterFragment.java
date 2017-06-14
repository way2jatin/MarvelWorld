package com.jatin.marvelworld.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatin.marvelworld.BuildConfig;
import com.jatin.marvelworld.R;
import com.jatin.marvelworld.activity.SearchActivity;
import com.jatin.marvelworld.adapter.CharacterAdapter;
import com.jatin.marvelworld.db.MarvelDb;
import com.jatin.marvelworld.listener.APIResponseListener;
import com.jatin.marvelworld.model.chars.CharacterResponse;
import com.jatin.marvelworld.model.chars.Result;
import com.jatin.marvelworld.util.APIHelper;
import com.jatin.marvelworld.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.favorite)
    FloatingActionButton favorite;

    @BindView(R.id.empty_result)
    TextView emptyText;

    private static String PRIVATE_API_KEY = BuildConfig.PRIVATE_API_KEY;
    private static String PUBLIC_API_KEY = BuildConfig.PUBLIC_API_KEY;
    String search;
    ObjectMapper mapper;

    CharacterAdapter adapter;
    List<Result> characterList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            search = bundle.getString("Search");
            getActivity().setTitle("Search->Characters->" + "\"" + search + "\"");
        }

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        mapper = new ObjectMapper();

        adapter = new CharacterAdapter(characterList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        if (search == null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSearchDialog();
                }
            });
        } else {
            fab.setVisibility(View.INVISIBLE);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFavouriteHeroDialog();
            }
        });

        if (MarvelDb.getInstance(getActivity().getApplicationContext()).hasCharacters() && !hasData(search)){
            try {
                characterList.addAll(MarvelDb.getInstance(getActivity().getApplicationContext()).retrieveCharacters().getData().getResults());
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            } catch (IOException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        } else {
            getAllCharacters();
        }

        return view;
    }


    private void getAllCharacters() {
        APIHelper apiHelper = new APIHelper(getActivity().getApplicationContext());

        long timeStamp = System.currentTimeMillis();
        String timeStampString = Long.toString(timeStamp);
        String md5ApiKey = MD5(timeStamp + PRIVATE_API_KEY + PUBLIC_API_KEY);

        if (hasData(search)){
            apiHelper.callJsonWsGet(getResources().getString(R.string.base_url) + "characters?nameStartsWith="+search+"&orderBy=name&limit=20&ts="+timeStampString+"&apikey="+PUBLIC_API_KEY+"&hash="+md5ApiKey,null,characterListener,false);
        }
        else {
            apiHelper.callJsonWsGet(getResources().getString(R.string.base_url) + "characters?orderBy=name&limit=20&ts="+timeStampString+"&apikey="+PUBLIC_API_KEY+"&hash="+md5ApiKey,null,characterListener,false);
        }
    }

    private APIResponseListener characterListener = new APIResponseListener() {
        @Override
        public void handleResponse(String response) {
            try{
                characterList.clear();
                CharacterResponse characterResponse = mapper.readValue(response,CharacterResponse.class);
                if (characterResponse.getData().getResults().size() ==0){
                    emptyText.setVisibility(View.VISIBLE);
                }
                if (!hasData(search)){
                    MarvelDb.getInstance(getActivity().getApplicationContext()).insertJSONStore("characters",response);
                }
                characterList.addAll(characterResponse.getData().getResults());
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }catch (Exception e){
                Log.e("Exception" ,e.getMessage(),e);
            }
        }

        @Override
        public void handleError(String response) {
            Log.d("ErrorResponse",response);
        }
    };

    void showSearchDialog() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("SEARCH CHARACTERS");
        alertDialog.setMessage("Enter \"JUST few Starting\" letters of Character(Ex. Spi for Spider-Man. DON'T enter full name to get best search results");
        alertDialog.setIcon(R.mipmap.ic_launcher);

        final EditText input = new EditText(getActivity());
        input.setHint("Enter text here then click SEARCH");

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String search = input.getText().toString().trim();
                if (search.length() <= 0)
                    dialog.cancel();
                else {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("Search", search);
                    intent.putExtra("Fragment", "Chars");
                    startActivity(intent);
                }
            }
        });

        AlertDialog dialog = alertDialog.create();

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        wmlp.y = 200;

        dialog.show();
    }


}
