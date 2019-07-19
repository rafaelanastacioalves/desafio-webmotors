package com.example.desafiowebmotors.repository;

import android.arch.lifecycle.LiveData;

import com.example.desafiowebmotors.api.APIClient;
import com.example.desafiowebmotors.api.NetworkBoundSource;
import com.example.desafiowebmotors.api.ServiceGenerator;
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.domain.entities.Resource;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class AppRepository {

    public LiveData<Resource<List<MainEntity>>> getMainEntityList() {
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundSource<List<MainEntity>, List<MainEntity>>() {
            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected Call<List<MainEntity>> createCall() {
                return apiClient.getTripPackageList();
            }
        }.asLiveData();
    }

    public LiveData<Resource<EntityDetails>> getEntityDetails(String id) {
        APIClient apiClient = ServiceGenerator.createService(APIClient.class);
        return new NetworkBoundSource<EntityDetails, EntityDetails>() {
            @Override
            protected void onFetchFailed() {

            }

            @Override
            protected Call<EntityDetails> createCall() {
                return apiClient.getTripPackageDetails(id);
            }
        }.asLiveData();
    }
}



