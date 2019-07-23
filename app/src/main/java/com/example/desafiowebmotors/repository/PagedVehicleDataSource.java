package com.example.desafiowebmotors.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.desafiowebmotors.model.Vehicle;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class PagedVehicleDataSource extends PageKeyedDataSource<String, Vehicle> {


    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<Boolean>();

    public PagedVehicleDataSource() {

        loadStatus.postValue(Boolean.TRUE);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Vehicle> callback) {
        loadStatus.postValue(Boolean.TRUE);
        AppRepository repository = new AppRepository();
        Single<List<Vehicle>> observable = repository.getVehiclesList(1);
        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vehicleList -> {
                            loadStatus.setValue(Boolean.FALSE);
                            callback.onResult(
                                    vehicleList,
                                    "",
                                    String.valueOf(1 + 1)
                            );
                        },
                        error -> Timber.w("Subsctibe On Error: " + error.getMessage())
                );

    }

    public MutableLiveData<Boolean> getLiveLoadStatus() {
        return loadStatus;
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Vehicle> callback) {
        // no need to go back here
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Vehicle> callback) {
        loadStatus.postValue(Boolean.TRUE);
        AppRepository repository = new AppRepository();
        Single<List<Vehicle>> observable = repository.getVehiclesList(Integer.valueOf(params.key));
        observable.subscribeOn(Schedulers.computation())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(vehicleList -> {
                            loadStatus.postValue(Boolean.FALSE);
                            callback.onResult(
                                    vehicleList,
                                    String.valueOf(Integer.valueOf(params.key) + 1)
                            );
                        },
                        error -> Timber.w("Subsctibe On Error: " + error.getMessage())

                );
    }



}







