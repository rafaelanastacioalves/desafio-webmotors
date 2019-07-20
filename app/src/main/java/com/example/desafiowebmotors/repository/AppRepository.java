package com.example.desafiowebmotors.repository;

import com.example.desafiowebmotors.api.APIClient;
import com.example.desafiowebmotors.api.ServiceGenerator;
import com.example.desafiowebmotors.model.Vehicle;


import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppRepository {

    public Single<List<Vehicle>> getVehiclesList(int page) {
        return Single.create(emitter -> {
            //synchronous logic in a thread apart


            // cache


            // not cached...
            APIClient apiClient = ServiceGenerator.createService(APIClient.class);
            Call<List<Vehicle>> retrofitCall = apiClient.getVehiclesList(page);

            retrofitCall.enqueue(new Callback<List<Vehicle>>() {
                @Override
                public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {
                    if (response.isSuccessful()) {
                        List<Vehicle> vehicleList = response.body();

                        // here we get again from DB as we obey the "single source of truth" approach
                        emitter.onSuccess(vehicleList);

                    } else {

                        //we emmit 4xx and 5xx error messages
                        try {
                            String errorString = response.errorBody().string();
                            emitter.onError(new Exception(errorString));
                        } catch (IOException e) {
                            emitter.onError(e.getCause());
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Vehicle>> call, Throwable t) {

                    // IO error cases
                    emitter.onError(t);
                }
            });


        });
    }


}



