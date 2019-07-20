package com.example.desafiowebmotors.car_listing;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.common.CircleImageTransformation;
import com.example.desafiowebmotors.listeners.RecyclerViewClickListener;
import com.example.desafiowebmotors.model.Vehicle;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    private RecyclerViewClickListener aRecyclerViewListener;

    @BindView(R.id.vehicle_linear_layout_container)
    ConstraintLayout containerLinearLayout;
    @BindView(R.id.vehicle_photo)
    ImageView reopOwnerImageView;
    @BindView(R.id.vehicle_text_view_title)
    TextView titleImageView;
    @BindView(R.id.vehicle_text_view_description)
    TextView descriptionTextView;

    public CarViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.aRecyclerViewListener = clickListener;
        containerLinearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    public void bind(Vehicle vehicle) {


        Picasso.get()
                .load(vehicle.image)
                .resize(150, 150)
                .centerInside()
                .transform(new CircleImageTransformation())
                .placeholder(R.drawable.placeholder_user)
                .into(reopOwnerImageView);

        titleImageView.setText(vehicle.make);
        descriptionTextView.setText(vehicle.version);


    }
}
