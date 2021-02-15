package com.webmd.webmdrx.intf;

import android.location.Location;

public interface ICurrentLocationListener {
    void onLocationFailed();

    void onLocationReceived(Location location);
}
