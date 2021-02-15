package com.android.webmd.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;

public class Util {
    public static final int TIMEOUT = 60000;

    public static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getDeviceId();
    }
}
