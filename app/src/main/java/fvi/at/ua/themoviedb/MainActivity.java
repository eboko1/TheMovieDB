package fvi.at.ua.themoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import fvi.at.ua.themoviedb.adapter.MovieAdapter;
import fvi.at.ua.themoviedb.api.MovieApiService;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static final String API_KEY = "";

    private static Retrofit retrofit = null;
    private static RecyclerView recyclerView = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        getApiData();


    }

    private void getApiData() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        Call<Movie> result = movieApiService.getTopRatedMovies(API_KEY);
        result.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                List<Result> results = response.body().getResults();

                MovieAdapter movieAdapter = new MovieAdapter(results,R.layout.item_movie, getApplicationContext());
                recyclerView.setAdapter(movieAdapter);
                Log.d(TAG, "Number of movies received: " + results.size());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
     }
}
