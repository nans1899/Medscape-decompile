package com.webmd.webmdrx.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class RxLocation {
    private String cityState;
    private double latitude;
    private double longitude;
    private String state;
    private String zip;

    public RxLocation() {
        this.latitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.longitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.cityState = "";
        this.state = "";
        this.zip = "";
    }

    public RxLocation(RxLocation rxLocation) {
        this.latitude = rxLocation.latitude;
        this.longitude = rxLocation.longitude;
        this.cityState = rxLocation.cityState;
        this.state = rxLocation.state;
        this.zip = rxLocation.zip;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public String getCityState() {
        return this.cityState;
    }

    public void setCityState(String str) {
        this.cityState = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String str) {
        this.zip = str;
    }

    public boolean isLocationValid() {
        if (this.latitude != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && this.longitude != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && !this.cityState.isEmpty() && !this.zip.isEmpty()) {
            return true;
        }
        return false;
    }

    public String getOmnitureLocation() {
        return (this.cityState.toLowerCase() + "," + this.zip).replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
    }
}
