package com.example.jose.smartlapalma.Models;

public class TaxiStop {

    public static final String mFeaturesKey = "features";

    public static final String mAttributesKey = "attributes";

    public static final String mIdKey = "Id";
    public static final String mNombreKey = "Nombre";
    public static final String mMunicipioKey = "Municipio";
    public static final String mTelefonoKey = "Telefono";
    public static final String mDireccionKey = "Direccion";

    private int mId;
    private String mName;
    private String mDirection;
    private String mPhone;

    public TaxiStop(int id, String name, String direction, String phone){

        mId = id;
        mName = name;
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
}
