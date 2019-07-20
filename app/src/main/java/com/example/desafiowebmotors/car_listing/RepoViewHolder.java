package com.example.desafiowebmotors.car_listing;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicles;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.repo_linear_layout_container)
    LinearLayout containerLinearLayout;
    private RecyclerViewClickListener aRecyclerViewListener;
    @BindView(R.id.repo_owner_photo)
    ImageView reopOwnerImageView;
    @BindView(R.id.repo_text_view_title)
    TextView titleImageView;
    @BindView(R.id.repo_text_view_description)
    TextView descriptionTextView;
    @BindView(R.id.repo_textview_number_forks)
    TextView numberForksTextView;
    @BindView(R.id.repo_textview_number_stars)
    TextView numberStarsTextView;
    @BindView(R.id.repo_textview_owner_name)
    TextView ownerNameTextView;


    public RepoViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.aRecyclerViewListener = clickListener;
        containerLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    public void bind(Vehicles aRepo) {

        titleImageView.setText(aRepo.km);
        titleImageView.setTag(aRepo);

    }
}
