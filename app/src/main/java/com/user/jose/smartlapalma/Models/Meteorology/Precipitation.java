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

public class Precipitation {

    public static final String probPrecipitationKey = "probPrecipitacion";

    public static final String probPrecipitationValueKey = "value";

    private String mPrecipitationValue;

    public String getmPrecipitationValue() {
        return mPrecipitationValue;
    }

    public void setmPrecipitationValue(String mPrecipitationValue) {
        this.mPrecipitationValue = mPrecipitationValue;
    }
}
