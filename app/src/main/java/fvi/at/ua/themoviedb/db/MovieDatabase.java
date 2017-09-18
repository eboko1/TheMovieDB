package fvi.at.ua.themoviedb.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.Constants;

/**
 * Created by Vika on 08.09.2017.
 */

public class MovieDatabase extends SQLiteOpenHelper {

    private static final String TAG = "MovieDatabase";



    public MovieDatabase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constants.DATABASE.TABLE_CREATE_MOVIE);
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.DROP_QUERY);
        this.onCreate(db);
    }

    public void insertMovie(Movie movie) {
        Log.d(TAG, "Values movie: " + movie.getTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.DATABASE.TITLE, movie.getTitle());
        contentValues.put(Constants.DATABASE.OVERVIEW, movie.getOverview());
        contentValues.put(Constants.DATABASE.POPULARITY, movie.getPopularity());
        contentValues.put(Constants.DATABASE.PHOTO_PATH, movie.getPosterPath());

        try {
            db.insert(Constants.DATABASE.TABLE_NAME, null, contentValues);
        } catch (Exception e){
            Log.i(TAG, "error insertMovie " + e.getMessage());
        }
        db.close();
    }

    public void startCursor() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(Constants.DATABASE.GET_MOVIE_QUERY, null);

        final List<Movie> movieList = new ArrayList<>();

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    Movie movie = new Movie();
                    //   movie.setFromDatabase(true);
                    movie.setTitle(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.TITLE)));
                    movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.RELEASE_DATA)));
                    movie.setOverview(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.OVERVIEW)));
                    movie.setPopularity(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.POPULARITY)));
                    movieList.add(movie);
                    // publishFlower(flower);

                } while (cursor.moveToNext());
            }
        }
    }
}
