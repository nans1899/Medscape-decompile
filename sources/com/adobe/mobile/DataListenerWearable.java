package com.adobe.mobile;

import android.net.Uri;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import java.util.Iterator;

public final class DataListenerWearable {
    public static void onDataChanged(DataEventBuffer dataEventBuffer) {
        DataItem dataItem;
        Uri uri;
        if (dataEventBuffer != null) {
            Iterator it = dataEventBuffer.iterator();
            while (it.hasNext()) {
                DataEvent dataEvent = (DataEvent) it.next();
                if (!(dataEvent.getType() != 1 || (dataItem = dataEvent.getDataItem()) == null || (uri = dataItem.getUri()) == null || uri.getPath() == null || !uri.getPath().startsWith("/abdmobile/data/config/"))) {
                    ConfigSynchronizer.restoreConfig(dataItem);
                }
            }
        }
    }
}
