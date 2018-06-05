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
