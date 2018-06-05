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

public class TaxiStop {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "Id";
    public static final String mNombreKey = "Nombre";
    public static final String mMunicipioKey = "Municipio";
    public static final String mTelefonoKey = "Telefono";
    public static final String mDireccionKey = "Direccion";

    public static final double DEFAULT_COORDINATES_VALUE = -1;

    private int mId;
    private String mName;
    private double mLat;
    private double mLng;
    private String mDirection;
    private String mPhone;

    public TaxiStop(int id, String name, String direction, String phone){

        mId = id;
        mName = name;
        mLat = DEFAULT_COORDINATES_VALUE;
        mLng = DEFAULT_COORDINATES_VALUE;
        mDirection = direction;
        mPhone = phone;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmDirection(String mDirection) {
        this.mDirection = mDirection;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmName(String mNombre) {
        this.mName = mNombre;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public void setmLng(double mLng) {
        this.mLng = mLng;
    }

    public int getmId() {
        return mId;
    }

    public String getmDirection() {
        return mDirection;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmName() {
        return mName;
    }

    public double getmLat() {
        return mLat;
    }

    public double getmLng() {
        return mLng;
    }
}
