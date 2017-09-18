package fvi.at.ua.themoviedb.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.model.Movie;
import fvi.at.ua.themoviedb.utils.Constants;

/**
 * Created by Vika on 03.09.2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static final String TAG = "MainActivity";
    private List<Movie> movieList;
    private int rowLayout;
    private Context context;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;




    public MovieAdapter(List<Movie> movieList, int rowLayout, Context context) {
        this.movieList = movieList;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        String imageUrl = Constants.HTTP.IMAGE_URL_BASE_PATH + movieList.get(position).getBackdropPath();

        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.movieImage);

        holder.title.setText(movieList.get(position).getTitle());
        holder.release_data.setText(movieList.get(position).getReleaseDate());
        holder.overview_desc.setText(movieList.get(position).getOverview());
        holder.popularity.setText(movieList.get(position).getPopularity().toString());
        holder.movie_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick " + movieList.get(position).getTitle() );

            }
        });

        //Bitmap bmImage = ((BitmapDrawable)holder.movieImage.getDrawable()).getBitmap();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public Movie getSelectedMovie(int position) {
        return movieList.get(position);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, release_data, popularity, overview_desc;
        ImageView  movieImage;
        LinearLayout movie_layout;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = (ImageView)itemView.findViewById(R.id.movie_image);
            title = (TextView)itemView.findViewById(R.id.title);
            release_data = (TextView)itemView.findViewById(R.id.release_data);
            popularity = (TextView)itemView.findViewById(R.id.popularity);
            overview_desc = (TextView)itemView.findViewById(R.id.overview_desc);
            movie_layout = (LinearLayout)itemView.findViewById(R.id.movies_layout);
        }
    }


}
