package com.example.desafiowebmotors.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.desafiowebmotors.model.Vehicle;

public class DataSourceFactory extends DataSource.Factory<String, Vehicle> {


    private MutableLiveData<PagedRepoDataSource> sourceLiveData = new MutableLiveData<PagedRepoDataSource>();

    public DataSourceFactory(){

    }
    @Override
    public DataSource<String, Vehicle> create() {
        PagedRepoDataSource source = new PagedRepoDataSource();
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PagedRepoDataSource> getDataSource() {
        return sourceLiveData;
    }
}
