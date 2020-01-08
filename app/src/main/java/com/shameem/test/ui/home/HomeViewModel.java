package com.shameem.test.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.shameem.test.base.BaseViewModel;
import com.shameem.test.data.api.ApiClient;
import com.shameem.test.ui.model.responseobj.MovieResponse;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<MovieResponse> movieResponseLiveData = new MutableLiveData<>();


    public MutableLiveData<MovieResponse> getMovieList() {
        ApiClient.getApiInterface().getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        setIsLoading(true);
                    }

                    @Override
                    public void onSuccess(MovieResponse movieResponse) {
                        setIsLoading(false);
                        movieResponseLiveData.postValue(movieResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setIsLoading(false);
                    }
                });
        return movieResponseLiveData;
    }

}
