package com.example.jose.smartlapalma.Models.Meteorology;

public class DayWeather {

    public static final String dateKey = "fecha";

    private Precipitation mPrecipitation;
    private SkyState mSkyState;

    public Precipitation getmPrecipitation() {
        return mPrecipitation;
    }

    public void setmPrecipitation(Precipitation mPrecipitation) {
        this.mPrecipitation = mPrecipitation;
    }

    public SkyState getmSkyState() {
        return mSkyState;
    }

    public void setmSkyState(SkyState mSkyState) {
        this.mSkyState = mSkyState;
    }
}
