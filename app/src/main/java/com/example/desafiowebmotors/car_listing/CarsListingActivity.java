package com.example.desafiowebmotors.car_listing;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.car_detailing.CarDetailingActivity;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicle;

import timber.log.Timber;

import static com.example.desafiowebmotors.car_detailing.CarDetailingActivity.CAR_DETAIL_EXTRA;

public class CarsListingActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private final RecyclerViewClickListener mClickListener = this;
    private RepoListAdapter mRepoListAdapter;
    private RecyclerView mRecyclerView;
    private LiveDataRepoListViewModel mLiveDataRepoListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setupRecyclerView();
        subscribe();
    }


    private void subscribe() {
        mLiveDataRepoListViewModel = ViewModelProviders.of(this).get(LiveDataRepoListViewModel.class);

        mLiveDataRepoListViewModel.getCarList().observe(this, mainEntities -> {
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
        mRecyclerView = (RecyclerView) findViewById(R.id.repo_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        if (mRepoListAdapter == null) {
            mRepoListAdapter = new RepoListAdapter();
        }
        mRepoListAdapter.setRecyclerViewClickListener(mClickListener);
        mRecyclerView.setAdapter(mRepoListAdapter);
    }


    private void populateRecyclerView(PagedList<Vehicle> data) {
        mRepoListAdapter.submitList(data);

    }


    @Override
    public void onClick(View view, int position) {
        Vehicle vehicle = mRepoListAdapter.getCurrentList().get(position);
        Intent i = new Intent(this, CarDetailingActivity.class);
        i.putExtra(CAR_DETAIL_EXTRA, vehicle);
        startActivity(i);

    }

    public RepoListAdapter getAdapter() {
        return mRepoListAdapter;
    }


}
