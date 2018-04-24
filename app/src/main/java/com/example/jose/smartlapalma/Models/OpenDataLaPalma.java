package com.example.jose.smartlapalma.Models;

import java.util.ArrayList;
import java.util.List;

public class OpenDataLaPalma {

    private static OpenDataLaPalma mOpenDataLaPalma;

    private List<BusStop> mBusStopList;
    private List<TaxiStop> mTaxiStopList;

    // Private constructor
    private OpenDataLaPalma(){

        mBusStopList = new ArrayList<>();
        mTaxiStopList = new ArrayList<>();
    }

    // Instance
    public static OpenDataLaPalma getInstance(){

        if (mOpenDataLaPalma == null){
            mOpenDataLaPalma = new OpenDataLaPalma();
        }

        return mOpenDataLaPalma;
    }

    public List<BusStop> getmBusStopList() {
        return mBusStopList;
    }

    public void setmBusStopList(List<BusStop> mBusStopList) {
        this.mBusStopList = mBusStopList;
    }

    public List<TaxiStop> getmTaxiStopList() {
        return mTaxiStopList;
    }

    public void setmTaxiStopList(List<TaxiStop> mTaxiStopList) {
        this.mTaxiStopList = mTaxiStopList;
    }
}
