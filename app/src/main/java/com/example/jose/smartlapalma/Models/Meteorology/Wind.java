package com.example.jose.smartlapalma.Models.Meteorology;

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
