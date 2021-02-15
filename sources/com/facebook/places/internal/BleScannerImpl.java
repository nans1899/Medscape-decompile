package com.facebook.places.internal;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import com.facebook.places.internal.ScannerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BleScannerImpl implements BleScanner {
    private static final byte[] EDDYSTONE_PREFIX = fromHexString("16aafe");
    private static final byte[] GRAVITY_PREFIX = fromHexString("17ffab01");
    private static final byte[] IBEACON_PREFIX = fromHexString("ff4c000215");
    private static final String TAG = "BleScannerImpl";
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private Context context;
    /* access modifiers changed from: private */
    public int errorCode;
    private boolean isScanInProgress;
    private LocationPackageRequestParams params;
    private ScanCallBackImpl scanCallBack;
    /* access modifiers changed from: private */
    public final List<BluetoothScanResult> scanResults = new ArrayList();

    BleScannerImpl(Context context2, LocationPackageRequestParams locationPackageRequestParams) {
        this.context = context2;
        this.params = locationPackageRequestParams;
    }

    public synchronized void initAndCheckEligibility() throws ScannerException {
        if (Build.VERSION.SDK_INT < 21) {
            throw new ScannerException(ScannerException.Type.NOT_SUPPORTED);
        } else if (!Validate.hasBluetoothPermission(this.context)) {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        } else if (Validate.hasLocationPermission(this.context)) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            this.bluetoothAdapter = defaultAdapter;
            if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
                throw new ScannerException(ScannerException.Type.DISABLED);
            }
            BluetoothLeScanner bluetoothLeScanner2 = this.bluetoothAdapter.getBluetoothLeScanner();
            this.bluetoothLeScanner = bluetoothLeScanner2;
            if (bluetoothLeScanner2 == null) {
                throw new ScannerException(ScannerException.Type.UNKNOWN_ERROR);
            }
        } else {
            throw new ScannerException(ScannerException.Type.PERMISSION_DENIED);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|18) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        throw new com.facebook.places.internal.ScannerException(com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startScanning() throws com.facebook.places.internal.ScannerException {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isScanInProgress     // Catch:{ all -> 0x0058 }
            if (r0 != 0) goto L_0x0050
            com.facebook.places.internal.BleScannerImpl$ScanCallBackImpl r0 = new com.facebook.places.internal.BleScannerImpl$ScanCallBackImpl     // Catch:{ all -> 0x0058 }
            r1 = 0
            r0.<init>()     // Catch:{ all -> 0x0058 }
            r5.scanCallBack = r0     // Catch:{ all -> 0x0058 }
            r0 = 1
            r5.isScanInProgress = r0     // Catch:{ all -> 0x0058 }
            r2 = 0
            r5.errorCode = r2     // Catch:{ all -> 0x0058 }
            java.util.List<com.facebook.places.internal.BluetoothScanResult> r2 = r5.scanResults     // Catch:{ all -> 0x0058 }
            monitor-enter(r2)     // Catch:{ all -> 0x0058 }
            java.util.List<com.facebook.places.internal.BluetoothScanResult> r3 = r5.scanResults     // Catch:{ all -> 0x004d }
            r3.clear()     // Catch:{ all -> 0x004d }
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            android.bluetooth.le.BluetoothLeScanner r2 = r5.bluetoothLeScanner     // Catch:{ all -> 0x0058 }
            if (r2 == 0) goto L_0x0045
            android.bluetooth.le.ScanSettings$Builder r2 = new android.bluetooth.le.ScanSettings$Builder     // Catch:{ Exception -> 0x003d }
            r2.<init>()     // Catch:{ Exception -> 0x003d }
            r3 = 2
            r2.setScanMode(r3)     // Catch:{ Exception -> 0x003d }
            r3 = 0
            r2.setReportDelay(r3)     // Catch:{ Exception -> 0x003d }
            android.bluetooth.le.BluetoothLeScanner r3 = r5.bluetoothLeScanner     // Catch:{ Exception -> 0x003d }
            android.bluetooth.le.ScanSettings r2 = r2.build()     // Catch:{ Exception -> 0x003d }
            com.facebook.places.internal.BleScannerImpl$ScanCallBackImpl r4 = r5.scanCallBack     // Catch:{ Exception -> 0x003d }
            r3.startScan(r1, r2, r4)     // Catch:{ Exception -> 0x003d }
            r5.isScanInProgress = r0     // Catch:{ Exception -> 0x003d }
            monitor-exit(r5)
            return
        L_0x003d:
            com.facebook.places.internal.ScannerException r0 = new com.facebook.places.internal.ScannerException     // Catch:{ all -> 0x0058 }
            com.facebook.places.internal.ScannerException$Type r1 = com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR     // Catch:{ all -> 0x0058 }
            r0.<init>(r1)     // Catch:{ all -> 0x0058 }
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0045:
            com.facebook.places.internal.ScannerException r0 = new com.facebook.places.internal.ScannerException     // Catch:{ all -> 0x0058 }
            com.facebook.places.internal.ScannerException$Type r1 = com.facebook.places.internal.ScannerException.Type.UNKNOWN_ERROR     // Catch:{ all -> 0x0058 }
            r0.<init>(r1)     // Catch:{ all -> 0x0058 }
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x004d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004d }
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0050:
            com.facebook.places.internal.ScannerException r0 = new com.facebook.places.internal.ScannerException     // Catch:{ all -> 0x0058 }
            com.facebook.places.internal.ScannerException$Type r1 = com.facebook.places.internal.ScannerException.Type.SCAN_ALREADY_IN_PROGRESS     // Catch:{ all -> 0x0058 }
            r0.<init>(r1)     // Catch:{ all -> 0x0058 }
            throw r0     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.places.internal.BleScannerImpl.startScanning():void");
    }

    public synchronized void stopScanning() {
        this.bluetoothLeScanner.flushPendingScanResults(this.scanCallBack);
        this.bluetoothLeScanner.stopScan(this.scanCallBack);
        waitForMainLooper(this.params.getBluetoothFlushResultsTimeoutMs());
        this.isScanInProgress = false;
    }

    private void waitForMainLooper(long j) {
        try {
            final Object obj = new Object();
            synchronized (obj) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        try {
                            synchronized (obj) {
                                obj.notify();
                            }
                        } catch (Exception e) {
                            BleScannerImpl.logException("Exception waiting for main looper", e);
                        }
                    }
                });
                obj.wait(j);
            }
        } catch (Exception e) {
            logException("Exception waiting for main looper", e);
        }
    }

    public synchronized int getErrorCode() {
        return this.errorCode;
    }

    public synchronized List<BluetoothScanResult> getScanResults() {
        ArrayList arrayList;
        synchronized (this.scanResults) {
            int bluetoothMaxScanResults = this.params.getBluetoothMaxScanResults();
            if (this.scanResults.size() > bluetoothMaxScanResults) {
                arrayList = new ArrayList(bluetoothMaxScanResults);
                Collections.sort(this.scanResults, new Comparator<BluetoothScanResult>() {
                    public int compare(BluetoothScanResult bluetoothScanResult, BluetoothScanResult bluetoothScanResult2) {
                        return bluetoothScanResult2.rssi - bluetoothScanResult.rssi;
                    }
                });
                arrayList.addAll(this.scanResults.subList(0, bluetoothMaxScanResults));
            } else {
                arrayList = new ArrayList(this.scanResults.size());
                arrayList.addAll(this.scanResults);
            }
        }
        return arrayList;
    }

    private class ScanCallBackImpl extends ScanCallback {
        private ScanCallBackImpl() {
        }

        public void onScanFailed(int i) {
            super.onScanFailed(i);
            int unused = BleScannerImpl.this.errorCode = i;
        }

        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    for (ScanResult access$400 : list) {
                        BluetoothScanResult access$4002 = BleScannerImpl.newBluetoothScanResult(access$400);
                        if (access$4002 != null) {
                            BleScannerImpl.this.scanResults.add(access$4002);
                        }
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }

        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            try {
                synchronized (BleScannerImpl.this.scanResults) {
                    BluetoothScanResult access$400 = BleScannerImpl.newBluetoothScanResult(scanResult);
                    if (access$400 != null) {
                        BleScannerImpl.this.scanResults.add(access$400);
                    }
                }
            } catch (Exception e) {
                BleScannerImpl.logException("Exception in ble scan callback", e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static BluetoothScanResult newBluetoothScanResult(ScanResult scanResult) {
        ScanRecord scanRecord = scanResult.getScanRecord();
        if (isBeacon(scanRecord.getBytes())) {
            return new BluetoothScanResult(formatPayload(scanRecord.getBytes()), scanResult.getRssi(), scanResult.getTimestampNanos());
        }
        return null;
    }

    private static String formatPayload(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return toHexString(bArr, getPayloadLength(bArr));
    }

    private static int getPayloadLength(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == 0) {
                return i;
            }
            if (b < 0) {
                return bArr.length;
            }
            i += b + 1;
        }
        return bArr.length;
    }

    private static String toHexString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i < 0 || i > bArr.length) {
            i = bArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(String.format("%02x", new Object[]{Byte.valueOf(bArr[i2])}));
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: private */
    public static void logException(String str, Exception exc) {
        if (FacebookSdk.isDebugEnabled()) {
            Log.e(TAG, str, exc);
        }
    }

    private static boolean isBeacon(byte[] bArr) {
        int i;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        int i2 = 0;
        while (i2 < length) {
            byte b = bArr[i2];
            if (b <= 0 || (i = b + 1 + i2) > length) {
                return false;
            }
            if (isAdvPacketBeacon(bArr, i2)) {
                return true;
            }
            i2 = i;
        }
        return false;
    }

    private static boolean isAdvPacketBeacon(byte[] bArr, int i) {
        int i2 = i + 1;
        if (!isArrayContained(bArr, i2, IBEACON_PREFIX) && !isArrayContained(bArr, i2, EDDYSTONE_PREFIX) && !isArrayContained(bArr, i, GRAVITY_PREFIX) && !isAltBeacon(bArr, i)) {
            return false;
        }
        return true;
    }

    private static boolean isAltBeacon(byte[] bArr, int i) {
        int i2 = i + 5;
        if (i2 >= bArr.length) {
            return false;
        }
        byte b = bArr[i];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 4];
        byte b4 = bArr[i2];
        if (b == 27 && b2 == -1 && b3 == -66 && b4 == -84) {
            return true;
        }
        return false;
    }

    private static boolean isArrayContained(byte[] bArr, int i, byte[] bArr2) {
        int length = bArr2.length;
        if (i + length > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (bArr[i + i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private static byte[] fromHexString(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
