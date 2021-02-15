package com.medscape.android.ads;

import android.content.Context;
import android.util.Log;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import java.util.HashMap;

public class GetAdContentTask {
    private static final String TAG = "GetAdContentTask";

    public static void getAdInBackground(Context context, String str, final GetAdContentListener getAdContentListener) {
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            str = "https://" + str;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Cookie", Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, ""));
        VolleyRestClient.getInstance(context).get(str, 0, hashMap, new ICallbackEvent() {
            public void onError(Object obj) {
                Log.i(GetAdContentTask.TAG, "onError: ");
            }

            public void onCompleted(Object obj) {
                Log.i(GetAdContentTask.TAG, "onCompleted: ");
                if (obj instanceof String) {
                    getAdContentListener.onContentsDownloaded((String) obj);
                }
            }
        });
    }
}
