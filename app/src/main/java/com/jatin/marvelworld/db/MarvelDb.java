package com.jatin.marvelworld.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jatin.marvelworld.model.chars.CharacterResponse;
import com.jatin.marvelworld.model.comics.ComicResponse;

import java.io.IOException;

/**
 * Created by jatinjha on 14/06/17.
 */

public class MarvelDb {

    private final String db_name = "marvel.db";
    private final int db_version = 1;
    private Context con;
    private DatabaseHelper dbhelp;
    private SQLiteDatabase db;

    private static final String TBL_JSON_STORE = "json_store";

    String db_create = "create table "+TBL_JSON_STORE + " (type TEXT, json TEXT)";

    private MarvelDb(Context con){
        this.con = con;
        dbhelp = new DatabaseHelper(con,db_name,null,db_version);
        db = dbhelp.getWritableDatabase();
    }

    private static MarvelDb marvelDb;

    public static synchronized MarvelDb getInstance(Context con){
        if (marvelDb == null){
            marvelDb = new MarvelDb(con);
        }
        return marvelDb;
    }

    public class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            createDb(sqLiteDatabase);
        }

        private void createDb(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(db_create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            dropDb(sqLiteDatabase);
            createDb(sqLiteDatabase);
        }

        private void dropDb(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_JSON_STORE);
        }
    }

    public void insertJSONStore(String type, String json){
        ContentValues initialVal = new ContentValues();
        initialVal.put("type", type);
        initialVal.put("json", json);

        db.insert(TBL_JSON_STORE, null, initialVal);
    }

    public CharacterResponse retrieveCharacters() throws IOException{
        CharacterResponse characterResponse = null;
        Cursor cursor = db.rawQuery("Select json from " + TBL_JSON_STORE + " where type='characters'",null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (cursor.moveToFirst()){
            do {
                characterResponse = mapper.readValue(cursor.getString(0),CharacterResponse.class);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return characterResponse;
    }

    public ComicResponse retrieveComics() throws IOException{
        ComicResponse comicResponse = null;
        Cursor cursor = db.rawQuery("Select json from " + TBL_JSON_STORE + " where type='comics'",null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (cursor.moveToFirst()){
            do {
                comicResponse = mapper.readValue(cursor.getString(0),ComicResponse.class);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return comicResponse;
    }

    public boolean hasCharacters(){
        Cursor cursor = db.rawQuery("Select json from " + TBL_JSON_STORE + " where type='characters'",null);
        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

    public boolean hasComics(){
        Cursor cursor = db.rawQuery("Select json from " + TBL_JSON_STORE + " where type='comics'",null);
        if (cursor.getCount() > 0){
            cursor.close();
            return true;
        }
        else {
            cursor.close();
            return false;
        }
    }

}

