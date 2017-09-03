package fvi.at.ua.themoviedb.controller;

import fvi.at.ua.themoviedb.api.MovieApiService;
import fvi.at.ua.themoviedb.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vika on 03.09.2017.
 */

public class Controller {

    private static Retrofit getRetrofitInstance(){
          Retrofit retrofit = null;
              if(retrofit == null) {
                 retrofit = new Retrofit.Builder()
                          .baseUrl(Constants.HTTP.BASE_URL)
                          .addConverterFactory(GsonConverterFactory.create())
                          .build();
              }
     return retrofit;
    }

    public static  MovieApiService getMovieApiServise(){
        MovieApiService movieApiService = getRetrofitInstance().create(MovieApiService.class);
        return movieApiService;
    }

}
