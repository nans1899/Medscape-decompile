package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.models.RxLocation;

public interface ILocationSelectedListener {
    void onLocationSelected(RxLocation rxLocation, int i);
}
