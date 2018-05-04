package com.example.jose.smartlapalma.Models.Meteorology;

public class DayWeather {

    public static final String dateKey = "fecha";

    private Precipitation mPrecipitation;
    private SkyState mSkyState;
    private Wind mWind;
    private Temperature mTemperature;

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

    public Wind getmWind() {
        return mWind;
    }

    public void setmWind(Wind mWind) {
        this.mWind = mWind;
    }

    public Temperature getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(Temperature mTemperature) {
        this.mTemperature = mTemperature;
    }
}
