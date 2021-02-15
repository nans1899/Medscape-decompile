package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectivityManager {
    private static InternetConnectivityManager ourInstance;
    private Context context;

    public enum ConnectionType {
        NONE,
        WIFI,
        CELLULAR,
        UNKNOWN
    }

    public static InternetConnectivityManager getInstance() {
        return ourInstance;
    }

    private InternetConnectivityManager(Context context2) {
        this.context = context2;
    }

    public static synchronized void initializeInstance(Context context2) {
        synchronized (InternetConnectivityManager.class) {
            if (ourInstance == null) {
                ourInstance = new InternetConnectivityManager(context2);
            }
        }
    }

    public boolean isConnectedToInternet() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public ConnectionType getConnectionType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 1) {
                return ConnectionType.WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                return ConnectionType.CELLULAR;
            }
            if (activeNetworkInfo.isConnectedOrConnecting()) {
                return ConnectionType.UNKNOWN;
            }
        }
        return ConnectionType.NONE;
    }
}
