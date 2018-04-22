package com.example.jose.smartlapalma.Models;

public class BusStop {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "ID";
    public static final String mLatKey = "LATITUD";
    public static final String mLngKey = "LONGITUD";
    public static final String mNameKey = "PARADA";
    public static final String mLineKey = "LINEAS";

    private int mId;
    private String mLat;
    private String mLng;
    private String mName;
    private String mLine;

    // Constructor
    public BusStop(int id, String lat, String lng, String name, String line){

        mId = id;
        mLat = lat;
        mLng = lng;
        mName = name;
        mLine = line;
    }

    public int getmId() {
        return mId;
    }

    public String getmLat() {
        return mLat;
    }

    public String getmLng() {
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

    public void setmLat(String mLat) {
        this.mLat = mLat;
    }

    public void setmLng(String mLng) {
        this.mLng = mLng;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmLine(String mLine) {
        this.mLine = mLine;
    }
}
