package com.example.desafiowebmotors.car_listing;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.car_detailing.CarDetailingActivity;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicle;

import timber.log.Timber;

import static com.example.desafiowebmotors.car_detailing.CarDetailingActivity.CAR_DETAIL_EXTRA;

public class VehicleListingActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private final RecyclerViewClickListener mClickListener = this;
    private VehicleListAdapter vehicleListAdapter;
    private RecyclerView mRecyclerView;
    private LiveDataVehicleListViewModel mLiveDataVehicleListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setupRecyclerView();
        subscribe();
    }


    private void subscribe() {
        mLiveDataVehicleListViewModel = ViewModelProviders.of(this).get(LiveDataVehicleListViewModel.class);

        mLiveDataVehicleListViewModel.getCarList().observe(this, mainEntities -> {
            Timber.d("On Changed");
            populateRecyclerView(mainEntities);
        });

    }



    private void setupViews() {
        setContentView(R.layout.activity_main);
        Timber.tag("LifeCycles");
        Timber.i("onCreate Activity");
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.vehicle_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        if (vehicleListAdapter == null) {
            vehicleListAdapter = new VehicleListAdapter();
        }
        vehicleListAdapter.setRecyclerViewClickListener(mClickListener);
        mRecyclerView.setAdapter(vehicleListAdapter);
    }


    private void populateRecyclerView(PagedList<Vehicle> data) {
        vehicleListAdapter.submitList(data);

    }


    @Override
    public void onClick(View view, int position) {
        AppCompatImageView transitionImageView = view.findViewById(R.id.vehicle_photo);

        Vehicle vehicle = vehicleListAdapter.getCurrentList().get(position);
        Intent i = new Intent(this, CarDetailingActivity.class);
        i.putExtra(CAR_DETAIL_EXTRA, vehicle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = null;
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                    transitionImageView, transitionImageView.getTransitionName()).toBundle();
            startActivity(i, bundle);

        } else {
            startActivity(i);
        }

    }

    public VehicleListAdapter getAdapter() {
        return vehicleListAdapter;
    }


}
