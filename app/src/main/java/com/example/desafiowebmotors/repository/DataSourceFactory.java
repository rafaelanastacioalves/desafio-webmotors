package com.example.desafiowebmotors.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.desafiowebmotors.model.Vehicle;

public class DataSourceFactory extends DataSource.Factory<String, Vehicle> {


    private MutableLiveData<PagedVehicleDataSource> sourceLiveData = new MutableLiveData<PagedVehicleDataSource>();

    public DataSourceFactory(){

    }
    @Override
    public DataSource<String, Vehicle> create() {
        PagedVehicleDataSource source = new PagedVehicleDataSource();
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PagedVehicleDataSource> getDataSource() {
        return sourceLiveData;
    }
}
