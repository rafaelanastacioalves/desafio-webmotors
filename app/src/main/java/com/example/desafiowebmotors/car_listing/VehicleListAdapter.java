package com.example.desafiowebmotors.car_listing;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicle;


public class VehicleListAdapter extends PagedListAdapter<Vehicle, CarViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;


    protected VehicleListAdapter() {
        super(DIFF_CALLBACK);
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vehicle_viewholder, parent, false), recyclerViewClickListener);
    }


    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Vehicle vehicle = getItem(position);
        ((CarViewHolder) holder).bind(vehicle);
    }

    private static final DiffUtil.ItemCallback<Vehicle> DIFF_CALLBACK = new DiffUtil.ItemCallback<Vehicle>() {
        @Override
        public boolean areItemsTheSame(Vehicle oldItem, Vehicle newItem) {

            // a simple comparision suffices here
            return oldItem.id == newItem.id && oldItem.make.equals(newItem.make);
        }

        @Override
        public boolean areContentsTheSame(Vehicle oldItem, Vehicle newItem) {
            return oldItem == newItem;
        }
    };

}

