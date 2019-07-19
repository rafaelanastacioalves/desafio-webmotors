package com.example.desafiowebmotors.model;

import com.google.gson.annotations.SerializedName;

public class Vehicles {

    @SerializedName("ID")
    public int id;
    @SerializedName("Make")
    public String make;
    @SerializedName("Version")
    public String version;
    @SerializedName("Image")
    public String image;
    @SerializedName("KM")
    public String km;
    @SerializedName("Price")
    public String price;
    @SerializedName("YearModel")
    public int yearModel;
    @SerializedName("YearFab")
    public int yearFab;
    @SerializedName("Color")
    public String color;

    
}
