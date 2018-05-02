package com.example.jose.smartlapalma.Models.Meteorology;

public class SkyState {

    public static final String skyStateKey = "estadoCielo";

    public static final String valueKey = "value";
    public static final String descriptionKey = "descripcion";

    private String mValue;
    private String mDescription;

    public String getmValue() {
        return mValue;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
