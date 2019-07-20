package com.example.desafiowebmotors.car_listing;

import android.arch.paging.PagedListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicles;



public class RepoListAdapter extends PagedListAdapter<Vehicles, RepoViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;


    protected RepoListAdapter() {
        super(DIFF_CALLBACK);
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_viewholder, parent, false), recyclerViewClickListener);
    }


    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Vehicles aRepoW = getItem(position);
        ((RepoViewHolder) holder).bind(aRepoW);
    }

    private static final DiffUtil.ItemCallback<Vehicles> DIFF_CALLBACK = new DiffUtil.ItemCallback<Vehicles>() {
        @Override
        public boolean areItemsTheSame(Vehicles oldItem, Vehicles newItem) {

            // a simple comparision suffices here
            return oldItem.id == newItem.id && oldItem.make.equals(newItem.make);
        }

        @Override
        public boolean areContentsTheSame(Vehicles oldItem, Vehicles newItem) {
            return oldItem == newItem;
        }
    };

}

