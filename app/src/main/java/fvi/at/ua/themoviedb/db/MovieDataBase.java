package fvi.at.ua.themoviedb.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.Constants;

/**
 * Created by Vika on 08.09.2017.
 */

public class MovieDataBase  extends SQLiteOpenHelper {

    private static final String TAG = "MovieDataBase";


    public MovieDataBase(Context context) {
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
        contentValues.put("TITLE", movie.getTitle());
        contentValues.put("OVERVIEW", movie.getOverview());
        contentValues.put("POPULARITY", movie.getPopularity());
        contentValues.put("PHOTO_URL", movie.getPosterPath());

        try {
            db.insert(Constants.DATABASE.TABLE_NAME, null, contentValues);
        } catch (Exception e){
            Log.i(TAG, "error insertMovie " + e.getMessage());
        }
        db.close();
    }

}
