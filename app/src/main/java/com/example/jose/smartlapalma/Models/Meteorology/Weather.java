package com.example.jose.smartlapalma.Models.Meteorology;

import java.util.ArrayList;
import java.util.List;

public class Weather {

    public static final String dataKey = "datos";

    public static final String predictionKey = "prediccion";
    public static final String dayKey = "dia";

    private List<DayWeather> mDayWeatherList;

    public Weather(){

        mDayWeatherList = new ArrayList<>();
    }

    public List<DayWeather> getmDayWeatherList() {
        return mDayWeatherList;
    }

    public void setmDayWeatherList(List<DayWeather> mDayWeatherList) {
        this.mDayWeatherList = mDayWeatherList;
    }
}
