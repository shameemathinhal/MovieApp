package com.shameem.test.data.api;



import com.shameem.test.ui.model.responseobj.MovieResponse;



import io.reactivex.Single;
import retrofit2.http.GET;


public interface ApiInterface {
    @GET("films/")
    Single<MovieResponse> getMovieList();




}
