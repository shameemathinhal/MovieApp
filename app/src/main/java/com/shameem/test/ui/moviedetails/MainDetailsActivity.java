package com.shameem.test.ui.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.shameem.test.R;
import com.shameem.test.base.BaseActivity;
import com.shameem.test.pref.SharedPrefs;
import com.shameem.test.ui.home.HomeViewModel;
import com.shameem.test.ui.model.Result;
import com.shameem.test.ui.model.responseobj.MovieResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainDetailsActivity extends BaseActivity {

    @BindView(R.id.tvMovieName)
    TextView tvMovieName;
    @BindView(R.id.tvEpisodeNumber)
    TextView tvEpisodeNumber;
    @BindView(R.id.tvDirector)
    TextView tvDirector;
    @BindView(R.id.tvReleaseDate)
    TextView tvReleaseDate;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.llEpisode)
    LinearLayout llEpisode;
    @BindView(R.id.llDirector)
    LinearLayout llDirector;
    @BindView(R.id.llReleaseDate)
    LinearLayout llReleaseDate;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.ivMovie)
    ImageView ivMovie;


    private Observer<MovieResponse> movieResponseObserver;
    private HomeViewModel homeViewModel;

    public static Intent start(Context context) {
        return new Intent(context, MainDetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(R.string.tiltle);
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    showProgress();
                } else {
                    dismissProgress();
                }
            }
        });
        movieResponseObserver = this::setMovieAdapter;
        homeViewModel.getMovieList()
                .observe(this, movieResponseObserver);

    }

    private void setMovieAdapter(MovieResponse movieResponse) {
        for (Result result : movieResponse.getResults()) {
            int id = SharedPrefs.getInt(SharedPrefs.Keys.EPISODE_ID, 0);
            if (id == result.getEpisodeId()) {
                tvMovieName.setVisibility(View.VISIBLE);
                llEpisode.setVisibility(View.VISIBLE);
                llDirector.setVisibility(View.VISIBLE);
                llReleaseDate.setVisibility(View.VISIBLE);
                tvDes.setVisibility(View.VISIBLE);
                tvDescription.setVisibility(View.VISIBLE);
                ivMovie.setVisibility(View.VISIBLE);
                tvMovieName.setText(result.getTitle());
                tvEpisodeNumber.setText(String.valueOf(result.getEpisodeId()));
                tvDirector.setText(result.getDirector());
                tvReleaseDate.setText(result.getReleaseDate());
                tvDescription.setText(result.getOpeningCrawl());


            }
        }
    }

    @Override
    public int setLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean setToolbar() {
        return true;
    }

    @Override
    public boolean hideStatusBar() {
        return false;
    }

    @Override
    public boolean setFullScreen() {
        return false;
    }
}
