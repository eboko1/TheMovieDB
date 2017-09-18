package fvi.at.ua.themoviedb.api;

import fvi.at.ua.themoviedb.model.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Vika on 03.09.2017.
 */

public interface MovieApiClient {
    @GET("3/movie/popular")
    Call<MovieResult> getTopRatedMovies(@Query("api_key") String api_key);
}
