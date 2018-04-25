package com.example.jose.smartlapalma.Models;

public class TouristAccommodation {

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
