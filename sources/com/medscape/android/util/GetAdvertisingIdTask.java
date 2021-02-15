package com.medscape.android.util;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.medscape.android.settings.Settings;
import com.wbmd.wbmdcommons.logging.Trace;

public class GetAdvertisingIdTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = GetAdvertisingIdTask.class.getSimpleName();
    private Context mContext;

    public GetAdvertisingIdTask(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public Void doInBackground(Void... voidArr) {
        String adPreferencesData = getAdPreferencesData();
        String str = TAG;
        Trace.e(str, "ad id: " + adPreferencesData);
        Settings.singleton(this.mContext).saveSetting("advertising_identifier", adPreferencesData);
        this.mContext = null;
        return null;
    }

    private String getAdPreferencesData() {
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
            if (advertisingIdInfo == null || advertisingIdInfo.getId() == null) {
                return "00000000-0000-0000-0000-000000000000";
            }
            return advertisingIdInfo.getId();
        } catch (Exception unused) {
            return "00000000-0000-0000-0000-000000000000";
        }
    }
}
