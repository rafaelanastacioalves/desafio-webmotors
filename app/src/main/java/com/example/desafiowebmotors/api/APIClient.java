package com.example.desafiowebmotors.api;

import com.example.desafiowebmotors.model.Make;
import com.example.desafiowebmotors.model.Model;
import com.example.desafiowebmotors.model.Vehicle;
import com.example.desafiowebmotors.model.Version;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    @GET("/api/OnlineChallenge/Make")
    Call<List<Make>> getMakeList();


    @GET("/api/OnlineChallenge/Model")
    Call<List<Model>> getModelList(@Path("MakeID") int id);

    @GET("/api/OnlineChallenge/Version")
    Call<List<Version>> getVersionList(@Path("ModelID") int id);

    @GET("/api/OnlineChallenge/Vehicles")
    Call<List<Vehicle>> getVehiclesList(@Query("Page") int id);

}
