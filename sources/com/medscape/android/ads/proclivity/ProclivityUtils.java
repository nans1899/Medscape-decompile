package com.medscape.android.ads.proclivity;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.util.MedscapeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ProclivityUtils {
    public List<ProclivityDataModel> data = new ArrayList();
    boolean isProclivityComplete = false;

    public static Map<String, String> getProclivityMap(List<ProclivityDataModel> list, AdSize[] adSizeArr) {
        HashMap hashMap = new HashMap();
        if (list != null && list.size() > 0 && adSizeArr != null && adSizeArr.length > 0) {
            for (ProclivityDataModel next : list) {
                AdSize adSize = new AdSize(next.width, next.height);
                int length = adSizeArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        AdSize adSize2 = adSizeArr[i];
                        if (adSize2.getHeight() == adSize.getHeight() && adSize2.getWidth() == adSize.getWidth()) {
                            hashMap.putAll(next.getProclivityMap());
                            list.remove(next);
                            return hashMap;
                        }
                        i++;
                    }
                }
            }
        }
        return hashMap;
    }

    public static void makeProclivityRequest(Context context, Map<String, String> map, final IProclivityCompleteListener iProclivityCompleteListener) {
        if (context == null || map == null) {
            if (iProclivityCompleteListener != null) {
                iProclivityCompleteListener.onProclivityCompleted(new ArrayList());
            }
        } else if (!CapabilitiesManager.getInstance(context).isProclivityFeatureAvailable() || !map.containsKey("dt")) {
            iProclivityCompleteListener.onProclivityCompleted(new ArrayList());
        } else {
            ProclivityApiManager.fetchProclivityData(context, ProclivityConverter.mapToJsonBodyRequest(map), new IHttpRequestCompleteListener() {
                public void onHttpRequestFailed(MedscapeException medscapeException) {
                    iProclivityCompleteListener.onProclivityCompleted(new ArrayList());
                }

                public void onHttpRequestSucceeded(HttpResponseObject httpResponseObject) {
                    if (httpResponseObject != null) {
                        try {
                            if (httpResponseObject.getResponseData() != null) {
                                iProclivityCompleteListener.onProclivityCompleted(ProclivityParser.parseProclivityData(new JSONObject(httpResponseObject.getResponseData())));
                            }
                        } catch (JSONException unused) {
                            iProclivityCompleteListener.onProclivityCompleted(new ArrayList());
                        }
                    }
                }
            });
        }
    }

    public void makeProclivityCall(Context context, Map<String, String> map, IProclivityCompleteListener iProclivityCompleteListener) {
        if (!this.isProclivityComplete) {
            makeProclivityRequest(context, map, new IProclivityCompleteListener(iProclivityCompleteListener) {
                public final /* synthetic */ IProclivityCompleteListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void onProclivityCompleted(List list) {
                    ProclivityUtils.this.lambda$makeProclivityCall$0$ProclivityUtils(this.f$1, list);
                }
            });
        } else {
            iProclivityCompleteListener.onProclivityCompleted(this.data);
        }
    }

    public /* synthetic */ void lambda$makeProclivityCall$0$ProclivityUtils(IProclivityCompleteListener iProclivityCompleteListener, List list) {
        this.isProclivityComplete = true;
        this.data = list;
        iProclivityCompleteListener.onProclivityCompleted(list);
    }
}
