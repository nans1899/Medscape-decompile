package com.adobe.mobile;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class WearableDataConnection implements GoogleApiClient.OnConnectionFailedListener, DataApi.DataListener {
    private static final int LATCH_TIMEOUT_MSEC = 60000;
    protected DataMap mDataMap;
    private final GoogleApiClient mGoogleApiClient;
    private CountDownLatch mInTimeCountDownLatch;
    protected String requestID;

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    protected WearableDataConnection(Context context) {
        this.mGoogleApiClient = new GoogleApiClient.Builder(context).addOnConnectionFailedListener(this).addApi(Wearable.API).build();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        com.adobe.mobile.StaticMethods.logWarningFormat("Wearable - Failed to get data from handheld app", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007e, code lost:
        com.google.android.gms.wearable.Wearable.DataApi.removeListener(r6.mGoogleApiClient, r6);
        com.adobe.mobile.GoogleApiClientWrapper.disconnect(r6.mGoogleApiClient);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x008a, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x006c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adobe.mobile.WearableDataResponse send(com.adobe.mobile.WearableDataRequest r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Wearable - Failed to get data from handheld app"
            int r1 = r7.getTimeOut()
            boolean r1 = r6.connect(r1)
            r2 = 0
            if (r1 != 0) goto L_0x000e
            return r2
        L_0x000e:
            com.google.android.gms.wearable.DataApi r1 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.GoogleApiClient r3 = r6.mGoogleApiClient
            r1.addListener(r3, r6)
            java.lang.String r1 = r7.getUUID()
            r6.requestID = r1
            java.lang.String r1 = "/abdmobile/data/request"
            com.google.android.gms.wearable.PutDataMapRequest r1 = com.google.android.gms.wearable.PutDataMapRequest.create(r1)
            com.google.android.gms.wearable.DataMap r3 = r1.getDataMap()
            com.google.android.gms.wearable.DataMap r4 = r7.getDataMap()
            r3.putAll(r4)
            com.google.android.gms.wearable.PutDataRequest r1 = r1.asPutDataRequest()
            java.util.concurrent.CountDownLatch r3 = new java.util.concurrent.CountDownLatch
            r4 = 1
            r3.<init>(r4)
            r6.mInTimeCountDownLatch = r3
            com.google.android.gms.wearable.DataApi r3 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.GoogleApiClient r4 = r6.mGoogleApiClient
            r3.putDataItem(r4, r1)
            r1 = 0
            java.util.concurrent.CountDownLatch r3 = r6.mInTimeCountDownLatch     // Catch:{ InterruptedException -> 0x006c }
            r4 = 60000(0xea60, float:8.4078E-41)
            int r7 = r7.getTimeOut()     // Catch:{ InterruptedException -> 0x006c }
            int r7 = r7 + r4
            long r4 = (long) r7     // Catch:{ InterruptedException -> 0x006c }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException -> 0x006c }
            boolean r7 = r3.await(r4, r7)     // Catch:{ InterruptedException -> 0x006c }
            if (r7 != 0) goto L_0x0065
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ InterruptedException -> 0x006c }
            com.adobe.mobile.StaticMethods.logWarningFormat(r0, r7)     // Catch:{ InterruptedException -> 0x006c }
            com.google.android.gms.wearable.DataApi r7 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.GoogleApiClient r0 = r6.mGoogleApiClient
            r7.removeListener(r0, r6)
            com.google.android.gms.common.api.GoogleApiClient r7 = r6.mGoogleApiClient
            com.adobe.mobile.GoogleApiClientWrapper.disconnect(r7)
            return r2
        L_0x0065:
            com.adobe.mobile.WearableDataResponse r2 = r6.getResponse()     // Catch:{ InterruptedException -> 0x006c }
            goto L_0x0071
        L_0x006a:
            r7 = move-exception
            goto L_0x007e
        L_0x006c:
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x006a }
            com.adobe.mobile.StaticMethods.logWarningFormat(r0, r7)     // Catch:{ all -> 0x006a }
        L_0x0071:
            com.google.android.gms.wearable.DataApi r7 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.GoogleApiClient r0 = r6.mGoogleApiClient
            r7.removeListener(r0, r6)
            com.google.android.gms.common.api.GoogleApiClient r7 = r6.mGoogleApiClient
            com.adobe.mobile.GoogleApiClientWrapper.disconnect(r7)
            return r2
        L_0x007e:
            com.google.android.gms.wearable.DataApi r0 = com.google.android.gms.wearable.Wearable.DataApi
            com.google.android.gms.common.api.GoogleApiClient r1 = r6.mGoogleApiClient
            r0.removeListener(r1, r6)
            com.google.android.gms.common.api.GoogleApiClient r0 = r6.mGoogleApiClient
            com.adobe.mobile.GoogleApiClientWrapper.disconnect(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.WearableDataConnection.send(com.adobe.mobile.WearableDataRequest):com.adobe.mobile.WearableDataResponse");
    }

    private boolean connect(int i) {
        GoogleApiClientWrapper.connect(this.mGoogleApiClient);
        if (!waitForConnect(i)) {
            StaticMethods.logWarningFormat("Wearable - Timeout setup connection", new Object[0]);
            return false;
        } else if (hasNodes()) {
            return true;
        } else {
            StaticMethods.logWarningFormat("Wearable - No connected Node found", new Object[0]);
            return false;
        }
    }

    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Uri uri;
        DataMap dataMap;
        Iterator it = dataEventBuffer.iterator();
        while (it.hasNext()) {
            DataEvent dataEvent = (DataEvent) it.next();
            if (dataEvent.getType() == 1) {
                DataItem dataItem = dataEvent.getDataItem();
                if (dataItem != null && (uri = dataItem.getUri()) != null && uri.getPath() != null && uri.getPath().compareTo("/abdmobile/data/response") == 0 && (dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap()) != null && dataMap.containsKey(Constants.WBMDTugStringID) && dataMap.getString(Constants.WBMDTugStringID).equals(this.requestID)) {
                    this.mDataMap = dataMap;
                    this.mInTimeCountDownLatch.countDown();
                    return;
                }
            } else {
                return;
            }
        }
    }

    private boolean waitForConnect(int i) {
        if (GoogleApiClientWrapper.isConnected(this.mGoogleApiClient).booleanValue()) {
            return true;
        }
        ConnectionResult blockingConnect = GoogleApiClientWrapper.blockingConnect(this.mGoogleApiClient, (long) i, TimeUnit.MILLISECONDS);
        if (blockingConnect == null || !blockingConnect.isSuccess()) {
            return false;
        }
        return true;
    }

    private boolean hasNodes() {
        NodeApi.GetConnectedNodesResult await = GoogleApiClientWrapper.await(Wearable.NodeApi.getConnectedNodes(this.mGoogleApiClient));
        NodeApi.GetConnectedNodesResult getConnectedNodesResult = await instanceof NodeApi.GetConnectedNodesResult ? await : null;
        return (getConnectedNodesResult == null || getConnectedNodesResult.getNodes() == null || getConnectedNodesResult.getNodes().size() <= 0) ? false : true;
    }

    /* access modifiers changed from: protected */
    public WearableDataResponse getResponse() {
        DataMap dataMap = this.mDataMap;
        if (dataMap == null) {
            return null;
        }
        return WearableDataResponse.createResponseFromDataMap(dataMap, this.mGoogleApiClient);
    }
}
