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

public class Wind {

    public static final String windKey = "viento";

    public static final String directionKey = "direccion";
    public static final String velocityKey = "velocidad";

    private String mDirection;
    private String mVelocity;

    public String getmDirection() {
        return mDirection;
    }

    public String getmVelocity() {
        return mVelocity;
    }

    public void setmDirection(String mDirection) {
        this.mDirection = mDirection;
    }

    public void setmVelocity(String mVelocity) {
        this.mVelocity = mVelocity;
    }
}
