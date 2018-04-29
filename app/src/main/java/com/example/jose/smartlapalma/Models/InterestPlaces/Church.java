package com.example.jose.smartlapalma.Models.InterestPlaces;

public class Church {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "OBJECTID";
    public static final String mNameKey = "NOMBRE";
    public static final String mDirectionKey = "DIRECCICN";
    public static final String mUtmXKey = "UTM_X";
    public static final String mUtmYKey = "UTM_Y";

    private int mId;
    private double mLat;
    private double mLng;
    private String mName;
    private String mDirection;

    public Church(int id, double lat, double lng, String name, String direction){

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
