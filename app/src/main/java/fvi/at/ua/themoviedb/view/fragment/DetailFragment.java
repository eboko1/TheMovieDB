package fvi.at.ua.themoviedb.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fvi.at.ua.themoviedb.R;
import fvi.at.ua.themoviedb.utils.Constants;

/**
 * Created by Vika on 18.09.2017.
 */

public class DetailFragment extends DialogFragment {
    TextView detail_title, detail_overview;
    ImageView detail_image;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, null);

      //  detail_title = (TextView)v.findViewById(R.id.detail_title);
        detail_overview = (TextView)v.findViewById(R.id.detail_overview);
        detail_image = (ImageView)v.findViewById(R.id.detail_image);


        String title = getArguments().getString(Constants.BUNDLE.TITLE_KEY);
        String overview = getArguments().getString(Constants.BUNDLE.OVERVIEW_KEY);
        String imageUrl = getArguments().getString(Constants.BUNDLE.IMAGE_PATH_KEY);

        getDialog().setTitle(title);
      //  detail_title.setText(title);
        detail_overview.setText(overview);
        Picasso.with(DetailFragment.this.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(detail_image);
        return v;
    }

}
