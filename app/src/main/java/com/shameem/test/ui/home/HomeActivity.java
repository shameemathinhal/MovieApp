package com.shameem.test.ui.home;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shameem.test.R;
import com.shameem.test.base.BaseActivity;
import com.shameem.test.ui.home.adapter.MovieAdapter;
import com.shameem.test.ui.model.responseobj.MovieResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;

    private MovieAdapter movieAdapter;
    private Observer<MovieResponse> movieResponseObserver;
    private HomeViewModel homeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
        rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter(this, movieResponse.getResults());
        rvMovie.setAdapter(movieAdapter);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
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
