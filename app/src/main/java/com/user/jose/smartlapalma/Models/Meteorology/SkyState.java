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

public class SkyState {

    public static final String skyStateKey = "estadoCielo";

    public static final String valueKey = "value";
    public static final String descriptionKey = "descripcion";

    private String mValue;
    private String mDescription;

    public String getmValue() {
        return mValue;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
