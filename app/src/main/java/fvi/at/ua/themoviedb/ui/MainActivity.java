package fvi.at.ua.themoviedb.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.adapter.MovieAdapter;
import fvi.at.ua.themoviedb.controller.Controller;
import fvi.at.ua.themoviedb.model.MovieResult;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.InternetConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "your api_key";
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

        if(InternetConnection.isInternetConnection(getApplicationContext()) == true) {
            Log.i(TAG, "is inet connect = " + InternetConnection.isInternetConnection(getApplicationContext()));
            Toast.makeText(this,"inet successful ",Toast.LENGTH_LONG).show();getApiData();
        } else {
            Toast.makeText(this,"Please connect internet ",Toast.LENGTH_LONG).show();
        }

    }

    private void getApiData() {

        Call<MovieResult> result = Controller.getMovieApiServise().getTopRatedMovies(API_KEY);
        result.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "isSuccessful = " + response.isSuccessful());

                    List<Movie> results = response.body().getResults();

                    MovieAdapter movieAdapter = new MovieAdapter(results, R.layout.item_movie, getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                    Log.d(TAG, "results size " + results.size());

                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e(TAG,"onFailure = " + t.getMessage());
            }
        });
     }
}
