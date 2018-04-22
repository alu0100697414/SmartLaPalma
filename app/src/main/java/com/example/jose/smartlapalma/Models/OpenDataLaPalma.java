package com.example.jose.smartlapalma.Models;

import java.util.ArrayList;
import java.util.List;

public class OpenDataLaPalma {

    private static OpenDataLaPalma mOpenDataLaPalma;

    private List<BusStop> mBusStopList;

    // Private constructor
    private OpenDataLaPalma(){

        mBusStopList = new ArrayList<>();
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
}
