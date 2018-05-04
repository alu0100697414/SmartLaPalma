package com.example.jose.smartlapalma.Models.Meteorology;

public class Temperature {

    public static final String temperatureKey = "temperatura";

    public static final String maxKey = "maxima";
    public static final String minKey = "minima";

    private String mMax;
    private String mMin;

    public String getmMax() {
        return mMax;
    }

    public String getmMin() {
        return mMin;
    }

    public void setmMax(String mMax) {
        this.mMax = mMax;
    }

    public void setmMin(String mMin) {
        this.mMin = mMin;
    }
}
