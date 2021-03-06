package com.user.jose.smartlapalma.Models.Transports;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

public class BusStop {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "ID";
    public static final String mLatKey = "DMSLat";
    public static final String mLngKey = "DMSLon";
    public static final String mNameKey = "PARADA";
    public static final String mLineKey = "LINEAS";

    private int mId;
    private double mLat;
    private double mLng;
    private String mName;
    private String mLine;

    // Constructor
    public BusStop(int id, double lat, double lng, String name, String line){

        mId = id;
        mLat = lat;
        mLng = lng;
        mName = name;
        mLine = line;
    }

    public int getmId() {
        return mId;
    }

    public double getmLat() {
        return mLat;
    }

    public double getmLng() {
        return mLng;
    }

    public String getmName() {
        return mName;
    }

    public String getmLine() {
        return mLine;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public void setmLng(double mLng) {
        this.mLng = mLng;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmLine(String mLine) {
        this.mLine = mLine;
    }
}
