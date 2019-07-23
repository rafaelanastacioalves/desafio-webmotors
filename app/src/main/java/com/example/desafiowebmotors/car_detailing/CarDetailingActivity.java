package com.example.desafiowebmotors.car_detailing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desafiowebmotors.R;
import com.example.desafiowebmotors.model.Vehicle;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarDetailingActivity extends AppCompatActivity {

    public static final String CAR_DETAIL_EXTRA = "car_detail_extra";
    private Vehicle vehicle;

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.vehicle_detail_km)
    TextView vehicleDetailKm;

    @BindView(R.id.vehicle_detail_make)
    TextView vehicleDetailMake;

    @BindView(R.id.vehicle_detail_model)
    TextView vehicleDetailModel;

    @BindView(R.id.vehicle_detail_version)
    TextView vehicleDetailVersion;

    @BindView(R.id.vehicle_detail_price)
    TextView vehicleDetailPrice;

    @BindView(R.id.vehicle_detail_year_color)
    TextView vehicleDetailColor;

    @BindView(R.id.vehicle_detail_year_fab)
    TextView vehicleDetailFab;

    @BindView(R.id.vehicle_detail_year_model)
    TextView vehicleDetailYearModel;

    @BindView(R.id.vehicle_detail_imageview)
    ImageView vehicleDetailImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_car_detailing);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recoverVariables();
        bindView();
    }


    private void recoverVariables() {
        vehicle = (Vehicle) getIntent().getSerializableExtra(CAR_DETAIL_EXTRA);
    }

    private void bindView() {


        vehicleDetailKm.setText(getString(R.string.refactor_me_Sasda,String.valueOf(vehicle.km)));
        vehicleDetailMake.setText(vehicle.make);
        vehicleDetailModel.setText(vehicle.model);
        vehicleDetailYearModel.setText(getString(R.string.car_detail_year_model, String.valueOf(vehicle.yearModel)));
        vehicleDetailVersion.setText(vehicle.version);
        vehicleDetailPrice.setText(Html.fromHtml(getString(R.string.car_detail_price, vehicle.price)));
        vehicleDetailColor.setText(vehicle.color);
        vehicleDetailFab.setText(getString(R.string.car_detail_year_fab, String.valueOf(vehicle.yearFab)));

        Picasso.get()
                .load(vehicle.image)
                .into(vehicleDetailImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }


}
