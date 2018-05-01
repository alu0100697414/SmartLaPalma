package com.example.jose.smartlapalma.Models.Meteorology;

public class DayWeather {

    public static final String dateKey = "fecha";

    private Precipitation mPrecipitation;

    public Precipitation getmPrecipitation() {
        return mPrecipitation;
    }

    public void setmPrecipitation(Precipitation mPrecipitation) {
        this.mPrecipitation = mPrecipitation;
    }
}
