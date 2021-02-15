package com.tapstream.sdk;

import android.util.Log;
import com.tapstream.sdk.Logging;

public class AndroidLogger implements Logging.Logger {
    private static final String TAG = "Tapstream";

    public void log(int i, String str) {
        int i2 = 5;
        if (i == 4) {
            i2 = 4;
        } else if (i != 5) {
            i2 = 6;
        }
        Log.println(i2, TAG, str);
    }
}
