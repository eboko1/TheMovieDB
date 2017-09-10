package fvi.at.ua.themoviedb.utils;

/**
 * Created by Vika on 03.09.2017.
 */

public class Constants {

    public static final class HTTP {
        //for api service in MainActivity
        public static final String API_KEY = "your api_key";
        // for MovieAdapter.class, helps picasso image url
        public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w300/";
        // for Controller.class
        public static final String BASE_URL = "https://api.themoviedb.org/";

    }

    public static final class DATABASE {

        public static final String DB_NAME = "Movie.db";
        public static final int DB_VERSION = 1;
        public static final String TABLE_NAME = "MOVIE";

        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_MOVIE_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String PRODUCT_ID = "productId";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String POPULARITY = "popularity";
        public static final String PHOTO_URL = "photo_url";
        public static final String PHOTO = "photo";

        public static final String TABLE_CREATE_MOVIE = "CREATE TABLE "+ TABLE_NAME + " " +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE +" TEXT not null, "
                + OVERVIEW + " TEXT not null, "
                + POPULARITY + " TEXT not null, "
                + PHOTO_URL + " TEXT not null)";
    }
}
