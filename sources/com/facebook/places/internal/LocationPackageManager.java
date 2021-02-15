package com.facebook.places.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.places.internal.ScannerException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class LocationPackageManager {
    private static final String TAG = "LocationPackageManager";

    public interface Listener {
        void onLocationPackage(LocationPackage locationPackage);
    }

    public static void requestLocationPackage(final LocationPackageRequestParams locationPackageRequestParams, final Listener listener) {
        FacebookSdk.getExecutor().execute(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for bluetooth beacons", r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x0082, code lost:
                r2 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for wifi access points", r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:30:0x0099, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception getting location", r1);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a7, code lost:
                r1 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a8, code lost:
                com.facebook.places.internal.LocationPackageManager.access$300("Exception scanning for locations", r1);
                r0.locationError = r1.type;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7 A[ExcHandler: ScannerException (r1v1 'e' com.facebook.places.internal.ScannerException A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    com.facebook.places.internal.LocationPackage r0 = new com.facebook.places.internal.LocationPackage
                    r0.<init>()
                    com.facebook.places.internal.LocationPackageRequestParams r1 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    boolean r1 = r1.isLocationScanEnabled()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    r2 = 0
                    if (r1 == 0) goto L_0x0029
                    android.content.Context r1 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    com.facebook.places.internal.LocationScanner r1 = com.facebook.places.internal.ScannerFactory.newLocationScanner(r1, r3)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    r1.initAndCheckEligibility()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.FutureTask r1 = com.facebook.places.internal.LocationPackageManager.newLocationScanFuture(r1, r3)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.Executor r3 = com.facebook.FacebookSdk.getExecutor()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    r3.execute(r1)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    goto L_0x002a
                L_0x0029:
                    r1 = r2
                L_0x002a:
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    boolean r3 = r3.isWifiScanEnabled()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    if (r3 == 0) goto L_0x0040
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.FutureTask r3 = com.facebook.places.internal.LocationPackageManager.newWifiScanFuture(r3)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.Executor r4 = com.facebook.FacebookSdk.getExecutor()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    r4.execute(r3)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    goto L_0x0041
                L_0x0040:
                    r3 = r2
                L_0x0041:
                    com.facebook.places.internal.LocationPackageRequestParams r4 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    boolean r4 = r4.isBluetoothScanEnabled()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    if (r4 == 0) goto L_0x0056
                    com.facebook.places.internal.LocationPackageRequestParams r2 = r2     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.FutureTask r2 = com.facebook.places.internal.LocationPackageManager.newBluetoothScanFuture(r2)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    java.util.concurrent.Executor r4 = com.facebook.FacebookSdk.getExecutor()     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    r4.execute(r2)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                L_0x0056:
                    if (r2 == 0) goto L_0x006d
                    java.lang.Object r2 = r2.get()     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    com.facebook.places.internal.LocationPackage r2 = (com.facebook.places.internal.LocationPackage) r2     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    java.util.List<com.facebook.places.internal.BluetoothScanResult> r4 = r2.ambientBluetoothLe     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    r0.ambientBluetoothLe = r4     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    boolean r2 = r2.isBluetoothScanningEnabled     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    r0.isBluetoothScanningEnabled = r2     // Catch:{ Exception -> 0x0067, ScannerException -> 0x00a7 }
                    goto L_0x006d
                L_0x0067:
                    r2 = move-exception
                    java.lang.String r4 = "Exception scanning for bluetooth beacons"
                    com.facebook.places.internal.LocationPackageManager.logException(r4, r2)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                L_0x006d:
                    if (r3 == 0) goto L_0x0088
                    java.lang.Object r2 = r3.get()     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    com.facebook.places.internal.LocationPackage r2 = (com.facebook.places.internal.LocationPackage) r2     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    boolean r3 = r2.isWifiScanningEnabled     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    r0.isWifiScanningEnabled = r3     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    com.facebook.places.internal.WifiScanResult r3 = r2.connectedWifi     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    r0.connectedWifi = r3     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    java.util.List<com.facebook.places.internal.WifiScanResult> r2 = r2.ambientWifi     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    r0.ambientWifi = r2     // Catch:{ Exception -> 0x0082, ScannerException -> 0x00a7 }
                    goto L_0x0088
                L_0x0082:
                    r2 = move-exception
                    java.lang.String r3 = "Exception scanning for wifi access points"
                    com.facebook.places.internal.LocationPackageManager.logException(r3, r2)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                L_0x0088:
                    if (r1 == 0) goto L_0x00b1
                    java.lang.Object r1 = r1.get()     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    com.facebook.places.internal.LocationPackage r1 = (com.facebook.places.internal.LocationPackage) r1     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    com.facebook.places.internal.ScannerException$Type r2 = r1.locationError     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    r0.locationError = r2     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    android.location.Location r1 = r1.location     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    r0.location = r1     // Catch:{ Exception -> 0x0099, ScannerException -> 0x00a7 }
                    goto L_0x00b1
                L_0x0099:
                    r1 = move-exception
                    java.lang.String r2 = "Exception getting location"
                    com.facebook.places.internal.LocationPackageManager.logException(r2, r1)     // Catch:{ ScannerException -> 0x00a7, Exception -> 0x00a0 }
                    goto L_0x00b1
                L_0x00a0:
                    r1 = move-exception
                    java.lang.String r2 = "Exception requesting a location package"
                    com.facebook.places.internal.LocationPackageManager.logException(r2, r1)
                    goto L_0x00b1
                L_0x00a7:
                    r1 = move-exception
                    java.lang.String r2 = "Exception scanning for locations"
                    com.facebook.places.internal.LocationPackageManager.logException(r2, r1)
                    com.facebook.places.internal.ScannerException$Type r1 = r1.type
                    r0.locationError = r1
                L_0x00b1:
                    com.facebook.places.internal.LocationPackageManager$Listener r1 = r3
                    r1.onLocationPackage(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.LocationPackageManager.AnonymousClass1.run():void");
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newLocationScanFuture(final LocationScanner locationScanner, LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    locationPackage.location = locationScanner.getLocation();
                } catch (ScannerException e) {
                    locationPackage.locationError = e.type;
                    LocationPackageManager.logException("Exception while getting location", e);
                } catch (Exception unused) {
                    locationPackage.locationError = ScannerException.Type.UNKNOWN_ERROR;
                }
                return locationPackage;
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newBluetoothScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            /* JADX WARNING: Can't wrap try/catch for region: R(9:1|2|3|4|5|6|7|8|(1:10)(3:11|(1:13)|14)) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001f */
            /* JADX WARNING: Removed duplicated region for block: B:10:0x0029 A[Catch:{ all -> 0x0052, Exception -> 0x0057 }] */
            /* JADX WARNING: Removed duplicated region for block: B:11:0x0032 A[Catch:{ all -> 0x0052, Exception -> 0x0057 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.facebook.places.internal.LocationPackage call() throws java.lang.Exception {
                /*
                    r7 = this;
                    com.facebook.places.internal.LocationPackage r0 = new com.facebook.places.internal.LocationPackage
                    r0.<init>()
                    r1 = 0
                    android.content.Context r2 = com.facebook.FacebookSdk.getApplicationContext()     // Catch:{ Exception -> 0x0057 }
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ Exception -> 0x0057 }
                    com.facebook.places.internal.BleScanner r2 = com.facebook.places.internal.ScannerFactory.newBleScanner(r2, r3)     // Catch:{ Exception -> 0x0057 }
                    r2.initAndCheckEligibility()     // Catch:{ Exception -> 0x0057 }
                    r2.startScanning()     // Catch:{ all -> 0x0052 }
                    com.facebook.places.internal.LocationPackageRequestParams r3 = r2     // Catch:{ Exception -> 0x001f }
                    long r3 = r3.getBluetoothScanDurationMs()     // Catch:{ Exception -> 0x001f }
                    java.lang.Thread.sleep(r3)     // Catch:{ Exception -> 0x001f }
                L_0x001f:
                    r2.stopScanning()     // Catch:{ Exception -> 0x0057 }
                    int r3 = r2.getErrorCode()     // Catch:{ Exception -> 0x0057 }
                    r4 = 1
                    if (r3 != 0) goto L_0x0032
                    java.util.List r2 = r2.getScanResults()     // Catch:{ Exception -> 0x0057 }
                    r0.ambientBluetoothLe = r2     // Catch:{ Exception -> 0x0057 }
                    r0.isBluetoothScanningEnabled = r4     // Catch:{ Exception -> 0x0057 }
                    goto L_0x005f
                L_0x0032:
                    boolean r2 = com.facebook.FacebookSdk.isDebugEnabled()     // Catch:{ Exception -> 0x0057 }
                    if (r2 == 0) goto L_0x004f
                    java.lang.String r2 = "LocationPackageManager"
                    java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0057 }
                    java.lang.String r6 = "Bluetooth LE scan failed with error: %d"
                    java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0057 }
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0057 }
                    r4[r1] = r3     // Catch:{ Exception -> 0x0057 }
                    java.lang.String r3 = java.lang.String.format(r5, r6, r4)     // Catch:{ Exception -> 0x0057 }
                    com.facebook.internal.Utility.logd((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ Exception -> 0x0057 }
                L_0x004f:
                    r0.isBluetoothScanningEnabled = r1     // Catch:{ Exception -> 0x0057 }
                    goto L_0x005f
                L_0x0052:
                    r3 = move-exception
                    r2.stopScanning()     // Catch:{ Exception -> 0x0057 }
                    throw r3     // Catch:{ Exception -> 0x0057 }
                L_0x0057:
                    r2 = move-exception
                    java.lang.String r3 = "Exception scanning for bluetooth beacons"
                    com.facebook.places.internal.LocationPackageManager.logException(r3, r2)
                    r0.isBluetoothScanningEnabled = r1
                L_0x005f:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.LocationPackageManager.AnonymousClass3.call():com.facebook.places.internal.LocationPackage");
            }
        });
    }

    /* access modifiers changed from: private */
    public static FutureTask<LocationPackage> newWifiScanFuture(final LocationPackageRequestParams locationPackageRequestParams) {
        return new FutureTask<>(new Callable<LocationPackage>() {
            public LocationPackage call() throws Exception {
                LocationPackage locationPackage = new LocationPackage();
                try {
                    WifiScanner newWifiScanner = ScannerFactory.newWifiScanner(FacebookSdk.getApplicationContext(), locationPackageRequestParams);
                    newWifiScanner.initAndCheckEligibility();
                    locationPackage.connectedWifi = newWifiScanner.getConnectedWifi();
                    locationPackage.isWifiScanningEnabled = newWifiScanner.isWifiScanningEnabled();
                    if (locationPackage.isWifiScanningEnabled) {
                        locationPackage.ambientWifi = newWifiScanner.getWifiScans();
                    }
                } catch (Exception e) {
                    LocationPackageManager.logException("Exception scanning for wifi access points", e);
                    locationPackage.isWifiScanningEnabled = false;
                }
                return locationPackage;
            }
        });
    }

    /* access modifiers changed from: private */
    public static void logException(String str, Throwable th) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, th);
        }
    }
}
