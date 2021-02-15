package com.medscape.android.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;

public class ConnectivityUtils {
    private static final String TAG = "ConnectivityUtils";
    private static PowerManager.WakeLock partialLock;
    private static PowerManager powerManager;
    private static WifiManager.WifiLock wifiLock;
    private static WifiManager wifiManager;
    private static WifiManager.WifiLock wifiPerfLock;

    public void createLock(Context context) {
        if (wifiManager == null) {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        }
        if (wifiLock == null) {
            wifiLock = wifiManager.createWifiLock(TAG);
        }
        if (wifiPerfLock == null) {
            try {
                if (Integer.valueOf(Build.VERSION.SDK).intValue() >= 9) {
                    wifiPerfLock = wifiManager.createWifiLock(3, TAG);
                }
            } catch (Exception unused) {
            }
        }
        if (powerManager == null) {
            powerManager = (PowerManager) context.getSystemService("power");
        }
        if (partialLock == null) {
            partialLock = powerManager.newWakeLock(1, TAG);
        }
    }

    public boolean acquireLock() {
        acquirePartialLock();
        acquireWifiLock();
        return acquireWifiPerfLock();
    }

    private boolean acquirePartialLock() {
        boolean z = true;
        try {
            if (partialLock == null || partialLock.isHeld()) {
                z = false;
            } else {
                partialLock.acquire();
            }
            return z;
        } catch (Exception e) {
            LogUtil.e(TAG, "e.getMessage() +%s", e.getMessage().toString());
            return false;
        }
    }

    private boolean acquireWifiLock() {
        try {
            if (wifiLock != null && !wifiLock.isHeld()) {
                wifiLock.acquire();
            }
        } catch (Exception unused) {
            LogUtil.d(TAG, "Failed to acquire wifi lock for this device", new Object[0]);
        }
        return false;
    }

    private boolean acquireWifiPerfLock() {
        try {
            if (wifiPerfLock != null && !wifiPerfLock.isHeld()) {
                wifiPerfLock.acquire();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed to acquire wifi perf lock for this device = %s", e.getMessage().toString());
        }
        return false;
    }

    public boolean releaseLocks() {
        try {
            if (partialLock != null && partialLock.isHeld()) {
                partialLock.release();
            }
            if (wifiLock != null && wifiLock.isHeld()) {
                wifiLock.release();
            }
            if (wifiPerfLock == null || !wifiPerfLock.isHeld()) {
                return true;
            }
            wifiPerfLock.release();
            return true;
        } catch (Exception e) {
            LogUtil.e(TAG, "Failed to acquire wifi perf lock for this device = %s", e.getMessage().toString());
            return false;
        }
    }
}
