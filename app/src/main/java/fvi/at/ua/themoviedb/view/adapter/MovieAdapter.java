package fvi.at.ua.themoviedb.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.Constants;
import fvi.at.ua.themoviedb.view.fragment.DetailFragment;

/**
 * Created by Vika on 03.09.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private static final String TAG = "MainActivity";

    private Context context;
    private List<Movie> movieList;
    private int rowLayout;
    FragmentManager fm;

    public MovieAdapter( Context context, List<Movie> movieList, int rowLayout, FragmentManager fm) {
        Log.i(TAG, "MovieAdapter");
        this.movieList = movieList;
        this.rowLayout = rowLayout;
        this.context = context;
        this.fm = fm;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "MovieViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder");
        final String imageUrl = Constants.HTTP.IMAGE_URL_BASE_PATH + movieList.get(position).getBackdropPath();

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.movieImage);
        final String title = movieList.get(position).getTitle();
        final String overview = movieList.get(position).getOverview();
        holder.title.setText(title);
        holder.release_data.setText(movieList.get(position).getReleaseDate());
        holder.overview_desc.setText(overview);
        holder.popularity.setText(movieList.get(position).getPopularity().toString());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.i(TAG, "holder.setItemClickListener "+position);
                openDetailFragment(title, overview, imageUrl);
                Log.i(TAG, "openDetailFragment title/ "+title+"/overview/"+overview);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount "+movieList.size());
        return movieList.size();


    }

    private void openDetailFragment(String title, String overview , String imageUrl){
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BUNDLE.TITLE_KEY, title);
        bundle.putString(Constants.BUNDLE.OVERVIEW_KEY, overview);
        bundle.putString(Constants.BUNDLE.IMAGE_PATH_KEY, imageUrl);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        detailFragment.show(fm, Constants.TAG.FM_TAG);

    }

    public Movie getSelectedMovie(int position) {
        return movieList.get(position);
    }



    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, release_data, popularity, overview_desc;
        ImageView  movieImage;
        LinearLayout movie_layout;
        ItemClickListener itemClickListener;


        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView)itemView.findViewById(R.id.movie_image);
            title = (TextView)itemView.findViewById(R.id.title);
            release_data = (TextView)itemView.findViewById(R.id.release_data);
            popularity = (TextView)itemView.findViewById(R.id.popularity);
            overview_desc = (TextView)itemView.findViewById(R.id.overview_desc);
            movie_layout = (LinearLayout)itemView.findViewById(R.id.movies_layout);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener = ic;
        };

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }


    }
}
