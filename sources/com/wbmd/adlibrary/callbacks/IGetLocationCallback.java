package com.wbmd.adlibrary.callbacks;

import android.location.Location;

public interface IGetLocationCallback {
    void onGetLocation(Location location);

    void onGetLocationError(Exception exc);
}
