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

public class Humidity {

    public static final String humidityKey = "humedadRelativa";

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
