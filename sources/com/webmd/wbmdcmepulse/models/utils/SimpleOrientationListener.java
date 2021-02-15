package com.webmd.wbmdcmepulse.models.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SimpleOrientationListener extends OrientationEventListener {
    public static final int CONFIGURATION_ORIENTATION_UNDEFINED = 0;
    private Context ctx;
    private volatile int defaultScreenOrientation = 0;
    private ReentrantLock lock = new ReentrantLock(true);
    public int prevOrientation = -1;

    public abstract void onSimpleOrientationChanged(int i);

    public SimpleOrientationListener(Context context) {
        super(context);
        this.ctx = context;
    }

    public SimpleOrientationListener(Context context, int i) {
        super(context, i);
        this.ctx = context;
    }

    public void onOrientationChanged(int i) {
        int i2 = (i >= 330 || i < 30) ? 0 : (i < 60 || i >= 120) ? (i < 150 || i >= 210) ? (i < 240 || i >= 300) ? -1 : 3 : 2 : 1;
        if (this.prevOrientation != i2 && i != -1) {
            this.prevOrientation = i2;
            if (i2 != -1) {
                reportOrientationChanged(i2);
            }
        }
    }

    private void reportOrientationChanged(int i) {
        int deviceDefaultOrientation = getDeviceDefaultOrientation();
        int i2 = deviceDefaultOrientation == 2 ? 1 : 2;
        if (!(i == 0 || i == 2)) {
            deviceDefaultOrientation = i2;
        }
        onSimpleOrientationChanged(deviceDefaultOrientation);
    }

    private int getDeviceDefaultOrientation() {
        if (this.defaultScreenOrientation == 0) {
            this.lock.lock();
            this.defaultScreenOrientation = initDeviceDefaultOrientation(this.ctx);
            this.lock.unlock();
        }
        return this.defaultScreenOrientation;
    }

    private int initDeviceDefaultOrientation(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        boolean z = false;
        boolean z2 = configuration.orientation == 2;
        if (rotation == 0 || rotation == 2) {
            z = true;
        }
        return ((!z || !z2) && (z || z2)) ? 1 : 2;
    }
}
