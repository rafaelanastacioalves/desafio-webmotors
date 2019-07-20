package com.example.desafiowebmotors.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.desafiowebmotors.model.Vehicles;

public class DataSourceFactory extends DataSource.Factory<String,Vehicles> {


    private final String gitRepoLanguage;
    private final String gitSortParam;

    private MutableLiveData<PagedRepoDataSource> sourceLiveData = new MutableLiveData<PagedRepoDataSource>();

    public DataSourceFactory(String gitRepoLanguage, String gitSortParam ){
        this.gitRepoLanguage = gitRepoLanguage;
        this.gitSortParam = gitSortParam;
    }
    @Override
    public DataSource<String, Vehicles> create() {
        PagedRepoDataSource source = new PagedRepoDataSource(gitRepoLanguage, gitSortParam);
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PagedRepoDataSource> getDataSource() {
        return sourceLiveData;
    }
}
