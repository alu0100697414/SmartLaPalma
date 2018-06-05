package com.user.jose.smartlapalma.Models.Meteorology;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

public class DayWeather {

    public static final String dateKey = "fecha";

    private Precipitation mPrecipitation;
    private SkyState mSkyState;
    private Wind mWind;
    private Temperature mTemperature;
    private ThermalSensation mThermalSensation;
    private Humidity mHumidity;
    private UV mUV;

    private String mDate;

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

    public ThermalSensation getmThermalSensation() {
        return mThermalSensation;
    }

    public void setmThermalSensation(ThermalSensation mThermalSensation) {
        this.mThermalSensation = mThermalSensation;
    }

    public Humidity getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(Humidity mHumidity) {
        this.mHumidity = mHumidity;
    }

    public UV getmUV() {
        return mUV;
    }

    public void setmUV(UV mUV) {
        this.mUV = mUV;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
