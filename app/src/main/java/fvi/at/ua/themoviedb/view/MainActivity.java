package fvi.at.ua.themoviedb.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.adapter.MovieAdapter;
import fvi.at.ua.themoviedb.controller.Rest;
import fvi.at.ua.themoviedb.model.MovieResult;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.Constants;
import fvi.at.ua.themoviedb.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static RecyclerView recyclerView = null;
    MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(InternetConnection.isInternetConnection(getApplicationContext()) == true) {
            Log.i(TAG, "connection inet " + InternetConnection.isInternetConnection(getApplicationContext()));
            Toast.makeText(this,"inet successful ",Toast.LENGTH_LONG).show();
            getApiData();
        } else {
            Toast.makeText(this,"Please connection internet ",Toast.LENGTH_LONG).show();
            getMovieFromDatabase();
        }

    }

    private void getMovieFromDatabase() {


    }

    private void getApiData() {

        Call<MovieResult> result = Rest.getMovieApiClient().getTopRatedMovies(Constants.HTTP.API_KEY);
        result.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "isSuccessful = " + response.isSuccessful());

                    List<Movie> movieList = response.body().getResults();

                    movieAdapter = new MovieAdapter(movieList, R.layout.item_movie, getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                    Log.d(TAG, "results size " + movieList.size());
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e(TAG,"onFailure = " + t.getMessage());
            }
        });
     }

}
