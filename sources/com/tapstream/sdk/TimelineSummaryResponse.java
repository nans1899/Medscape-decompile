package com.tapstream.sdk;

import com.tapstream.sdk.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimelineSummaryResponse implements ApiResponse {
    private final List<String> campaigns;
    private final List<String> deeplinks;
    private final Map<String, String> eventParams;
    private final Map<String, String> hitParams;
    private final HttpResponse httpResponse;
    private final String latestDeeplink;
    private final Long latestDeeplinkTimestamp;

    private static List<String> jsonArrayToStringList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
        }
        return arrayList;
    }

    private static Map<String, String> jsonObjectToStringMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap(jSONObject.length());
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
        }
        return hashMap;
    }

    static TimelineSummaryResponse createSummaryResponse(HttpResponse httpResponse2) {
        String bodyAsString = httpResponse2.getBodyAsString();
        if (bodyAsString != null) {
            try {
                JSONObject jSONObject = new JSONObject(bodyAsString);
                return new TimelineSummaryResponse(httpResponse2, jSONObject.getString("latest_deeplink"), Long.valueOf(jSONObject.getLong("latest_deeplink_timestamp")), jsonArrayToStringList(jSONObject.getJSONArray("deeplinks")), jsonArrayToStringList(jSONObject.getJSONArray("campaigns")), jsonObjectToStringMap(jSONObject.getJSONObject("hit_params")), jsonObjectToStringMap(jSONObject.getJSONObject("event_params")));
            } catch (JSONException e) {
                Logging.log(5, "JSON decode error from timeline summary: (%s)", e.getMessage());
            }
        }
        return new TimelineSummaryResponse(httpResponse2);
    }

    public TimelineSummaryResponse(HttpResponse httpResponse2) {
        this.httpResponse = httpResponse2;
        this.latestDeeplink = null;
        this.latestDeeplinkTimestamp = -1L;
        this.deeplinks = null;
        this.campaigns = null;
        this.hitParams = null;
        this.eventParams = null;
    }

    public TimelineSummaryResponse(HttpResponse httpResponse2, String str, Long l, List<String> list, List<String> list2, Map<String, String> map, Map<String, String> map2) {
        this.httpResponse = httpResponse2;
        this.latestDeeplink = str;
        this.latestDeeplinkTimestamp = l;
        this.deeplinks = list;
        this.campaigns = list2;
        this.hitParams = map;
        this.eventParams = map2;
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r0 = r1.campaigns;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEmpty() {
        /*
            r1 = this;
            java.util.Map<java.lang.String, java.lang.String> r0 = r1.eventParams
            if (r0 == 0) goto L_0x000a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0015
        L_0x000a:
            java.util.List<java.lang.String> r0 = r1.campaigns
            if (r0 == 0) goto L_0x0017
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r0 = 0
            goto L_0x0018
        L_0x0017:
            r0 = 1
        L_0x0018:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.TimelineSummaryResponse.isEmpty():boolean");
    }

    public String getLatestDeeplink() {
        return this.latestDeeplink;
    }

    public Long getLatestDeeplinkTimestamp() {
        return this.latestDeeplinkTimestamp;
    }

    public List<String> getDeeplinks() {
        return this.deeplinks;
    }

    public List<String> getCampaigns() {
        return this.campaigns;
    }

    public Map<String, String> getHitParams() {
        return this.hitParams;
    }

    public Map<String, String> getEventParams() {
        return this.eventParams;
    }
}
