package com.user.jose.smartlapalma.Models.InterestPlaces;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

public class ArcheologicalSite {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "OBJECTID";
    public static final String mNameKey = "Nombre";
    public static final String mDirectionKey = "Dirección";
    public static final String mUtmXKey = "UTM_X";
    public static final String mUtmYKey = "UTM_Y";

    private int mId;
    private double mLat;
    private double mLng;
    private String mName;
    private String mDirection;

    public ArcheologicalSite(int id, double lat, double lng, String name, String direction){

        mId = id;
        mLat = lat;
        mLng = lng;
        mName = name;
        mDirection = direction;
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

    public String getmDirection() {
        return mDirection;
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

    public void setmDirection(String mDirection) {
        this.mDirection = mDirection;
    }
}
