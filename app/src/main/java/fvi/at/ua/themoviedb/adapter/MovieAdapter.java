package fvi.at.ua.themoviedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.PriorityQueue;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.model.Result;

/**
 * Created by Vika on 03.09.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Result> results;
    private int rowLayout;
    private Context context;

    public MovieAdapter(List<Result> results, int rowLayout, Context context) {
        this.results = results;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        //Picasso.with(context)
          //      .load()

        holder.title.setText(results.get(position).getTitle());
        holder.release_data.setText(results.get(position).getReleaseDate());
        holder.overview_desc.setText(results.get(position).getOverview());
        holder.popularity.setText(results.get(position).getPopularity().toString());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView title, release_data, popularity, overview_desc;
        ImageView  movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView)itemView.findViewById(R.id.movie_image);
            title = (TextView)itemView.findViewById(R.id.title);
            release_data = (TextView)itemView.findViewById(R.id.release_data);
            popularity = (TextView)itemView.findViewById(R.id.popularity);
            overview_desc = (TextView)itemView.findViewById(R.id.overview_desc);
        }

    }
}