package com.adobe.mobile;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public final class DataListenerHandheld {
    private static void handleRequest(DataMap dataMap, GoogleApiClient googleApiClient, Context context) {
        if (googleApiClient == null || context == null || dataMap == null) {
            StaticMethods.logDebugFormat("Wearable - GoogleApiClient or Context or DataMap is null", new Object[0]);
            return;
        }
        WearableDataRequest createRequestFromDataMap = WearableDataRequest.createRequestFromDataMap(dataMap);
        if (createRequestFromDataMap == null) {
            StaticMethods.logDebugFormat("Wearable - Invalid data request (%s)", dataMap.toString());
            return;
        }
        ConnectionResult blockingConnect = GoogleApiClientWrapper.blockingConnect(googleApiClient, 15000, TimeUnit.MILLISECONDS);
        if (blockingConnect == null || !blockingConnect.isSuccess()) {
            StaticMethods.logDebugFormat("Wearable - Failed to setup connection", new Object[0]);
            return;
        }
        DataMap handle = createRequestFromDataMap.handle(context);
        PutDataMapRequest create = PutDataMapRequest.create("/abdmobile/data/response");
        create.getDataMap().putAll(handle);
        Wearable.DataApi.putDataItem(googleApiClient, create.asPutDataRequest());
    }

    public static void onDataChanged(DataEventBuffer dataEventBuffer, GoogleApiClient googleApiClient, Context context) {
        DataItem dataItem;
        Uri uri;
        if (dataEventBuffer != null) {
            Iterator it = dataEventBuffer.iterator();
            while (it.hasNext()) {
                DataEvent dataEvent = (DataEvent) it.next();
                if (!(dataEvent.getType() != 1 || (dataItem = dataEvent.getDataItem()) == null || (uri = dataItem.getUri()) == null || uri.getPath() == null || !uri.getPath().startsWith("/abdmobile/data/request"))) {
                    handleRequest(DataMapItem.fromDataItem(dataItem).getDataMap(), googleApiClient, context);
                }
            }
        }
    }
}
