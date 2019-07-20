package com.example.desafiowebmotors.car_listing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.icu.util.VersionInfo;
import android.support.annotation.NonNull;

import com.example.desafiowebmotors.model.Vehicles;
import com.example.desafiowebmotors.repository.DataSourceFactory;


import java.util.concurrent.Executors;

import static android.arch.lifecycle.Transformations.switchMap;

public class LiveDataRepoListViewModel extends ViewModel {

    private LiveData<PagedList<Vehicles>> mMainEntityPagedList = setupPagedList();
    private LiveData<Boolean> mIsLoading;


    @NonNull
    private LiveData setupPagedList() {

        DataSourceFactory dataSourceFactory = new DataSourceFactory("Java", "start");
        mIsLoading = switchMap(dataSourceFactory.getDataSource(), input -> input.getLiveLoadStatus());

        return new LivePagedListBuilder(dataSourceFactory,10)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build();
    }

    public LiveData<PagedList<Vehicles>> getMainEntityList() {
        return mMainEntityPagedList;
    }


    public LiveData<Boolean> getLoadStatus() {
        return mIsLoading;
    }
}