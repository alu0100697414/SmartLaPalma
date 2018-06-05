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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TouristAccommodation {

    public static final List<Integer> notAccommodation = Arrays.asList(171, 487);

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "ID";
    public static final String mNameKey = "NOMBRE";
    public static final String mPhoneKey = "TELEFONO";
    public static final String mUtmXKey = "UTM_X";
    public static final String mUtmYKey = "UTM_Y";

    private int mId;
    private double mLat;
    private double mLng;
    private String mName;
    private String mPhone;

    public TouristAccommodation(int id, double lat, double lng, String name, String phone){

        mId = id;
        mLat = lat;
        mLng = lng;
        mName = name;
        mPhone = phone;
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

    public String getmPhone() {
        return mPhone;
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

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
