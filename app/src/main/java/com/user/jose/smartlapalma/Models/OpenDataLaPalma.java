package com.user.jose.smartlapalma.Models;

import com.user.jose.smartlapalma.Models.InterestPlaces.ArcheologicalSite;
import com.user.jose.smartlapalma.Models.InterestPlaces.Church;
import com.user.jose.smartlapalma.Models.InterestPlaces.Library;
import com.user.jose.smartlapalma.Models.InterestPlaces.Monument;
import com.user.jose.smartlapalma.Models.InterestPlaces.TouristAccommodation;
import com.user.jose.smartlapalma.Models.Meteorology.Weather;
import com.user.jose.smartlapalma.Models.Transports.BusStop;
import com.user.jose.smartlapalma.Models.Transports.TaxiStop;

import java.util.ArrayList;
import java.util.List;

public class OpenDataLaPalma {

    private static OpenDataLaPalma mOpenDataLaPalma;

    private List<BusStop> mBusStopList;
    private List<TaxiStop> mTaxiStopList;
    private List<TouristAccommodation> mTouristAccommodationList;
    private List<Church> mChurchList;
    private List<ArcheologicalSite> mArcheologicalSiteList;
    private List<Library> mLibraryList;
    private List<Monument> mMonumentList;
    private Weather mWeather;


    // Private constructor
    private OpenDataLaPalma(){

        mBusStopList = new ArrayList<>();
        mTaxiStopList = new ArrayList<>();
        mTouristAccommodationList = new ArrayList<>();
        mChurchList = new ArrayList<>();
        mArcheologicalSiteList = new ArrayList<>();
        mLibraryList = new ArrayList<>();
        mMonumentList = new ArrayList<>();
        mWeather = new Weather();
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

    public List<TouristAccommodation> getmTouristAccommodationList() {
        return mTouristAccommodationList;
    }

    public List<Church> getmChurchList() {
        return mChurchList;
    }

    public List<ArcheologicalSite> getmArcheologicalSiteList() {
        return mArcheologicalSiteList;
    }

    public List<Library> getmLibraryList() {
        return mLibraryList;
    }

    public List<Monument> getmMonumentList() {
        return mMonumentList;
    }

    public void setmTouristAccommodationList(List<TouristAccommodation> mTouristAccommodationList) {
        this.mTouristAccommodationList = mTouristAccommodationList;
    }

    public void setmChurchList(List<Church> mChurchList) {
        this.mChurchList = mChurchList;
    }

    public void setmArcheologicalSiteList(List<ArcheologicalSite> mArcheologicalSiteList) {
        this.mArcheologicalSiteList = mArcheologicalSiteList;
    }

    public void setmLibraryList(List<Library> mLibraryList) {
        this.mLibraryList = mLibraryList;
    }

    public void setmMonumentList(List<Monument> mMonumentList) {
        this.mMonumentList = mMonumentList;
    }

    public Weather getmWeather() {
        return mWeather;
    }

    public void setmWeather(Weather mWeather) {
        this.mWeather = mWeather;
    }
}
