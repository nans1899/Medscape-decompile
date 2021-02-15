package com.ib.clickstream;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.GsonBuilder;
import com.ib.clickstream.ClickstreamConstants;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J5\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u00122\u0006\u0010\u0013\u001a\u00020\u00112\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u000eH\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J[\u0010\u001c\u001a\u00020\u00112\b\u0010\u001d\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015j\u0002`\u001f2\u001a\u0010 \u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010!j\u0002`\"2\b\u0010#\u001a\u0004\u0018\u00010\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010%J8\u0010&\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010!j\u0002`\"2\u001a\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010!j\u0002`\"H\u0007J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0001\u0010,\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010-\u001a\u00020.2\u000e\b\u0002\u0010\u001d\u001a\b\u0018\u00010\u000ej\u0002`/2\u001c\b\u0002\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010!j\u0002`\"2\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0002`02\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015j\u0002`\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u00101J\u0001\u0010,\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0002`02\u000e\b\u0002\u0010\u001d\u001a\b\u0018\u00010\u000ej\u0002`/2\u001c\b\u0002\u0010'\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010!j\u0002`\"2\u0014\b\u0002\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0015j\u0002`\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u00102R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/ib/clickstream/Clickstream;", "Lcom/ib/clickstream/ClickstreamBase;", "()V", "client", "Lcom/ib/clickstream/ClickstreamRetrofitClient;", "getClient$clickstream_release", "()Lcom/ib/clickstream/ClickstreamRetrofitClient;", "dataGenerator", "Lcom/ib/clickstream/ClickstreamGenerator;", "getDataGenerator$clickstream_release", "()Lcom/ib/clickstream/ClickstreamGenerator;", "isNewPartyGenerated", "", "party_id", "", "generateEvents", "Ljava/util/ArrayList;", "Lorg/json/JSONObject;", "Lkotlin/collections/ArrayList;", "eventJson", "impressions", "", "Lcom/ib/clickstream/Impression;", "(Lorg/json/JSONObject;[Lcom/ib/clickstream/Impression;)Ljava/util/ArrayList;", "generatePartyId", "getBytesSize", "", "payLoad", "getParametersJson", "eventText", "interests", "Lcom/ib/clickstream/Interests;", "siteSpecificParameters", "Ljava/util/HashMap;", "Lcom/ib/clickstream/SiteSpecificParameters;", "pvid", "chronicleID", "(Ljava/lang/String;[Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/String;)Lorg/json/JSONObject;", "getSiteSpecificParams", "additionalSiteParams", "initialize", "", "context", "Landroid/content/Context;", "sendEvent", "eventType", "Lcom/ib/clickstream/ClickstreamConstants$EventType;", "Lcom/ib/clickstream/EventText;", "Lcom/ib/clickstream/Impressions;", "(Landroid/content/Context;Lcom/ib/clickstream/ClickstreamConstants$EventType;Ljava/lang/String;Ljava/util/HashMap;[Lcom/ib/clickstream/Impression;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "(Landroid/content/Context;[Lcom/ib/clickstream/Impression;Ljava/lang/String;Ljava/util/HashMap;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Clickstream.kt */
public abstract class Clickstream extends ClickstreamBase {
    private final ClickstreamRetrofitClient client = new ClickstreamRetrofitClient();
    private final ClickstreamGenerator dataGenerator = new ClickstreamGenerator();
    /* access modifiers changed from: private */
    public boolean isNewPartyGenerated;
    /* access modifiers changed from: private */
    public String party_id = "";

    public final ClickstreamGenerator getDataGenerator$clickstream_release() {
        return this.dataGenerator;
    }

    public final ClickstreamRetrofitClient getClient$clickstream_release() {
        return this.client;
    }

    public static /* synthetic */ void sendEvent$default(Clickstream clickstream, Context context, ClickstreamConstants.EventType eventType, String str, HashMap hashMap, Impression[] impressionArr, String[] strArr, String str2, String str3, int i, Object obj) {
        int i2 = i;
        if (obj == null) {
            clickstream.sendEvent(context, eventType, (i2 & 4) != 0 ? null : str, (i2 & 8) != 0 ? null : hashMap, (i2 & 16) != 0 ? null : impressionArr, (i2 & 32) != 0 ? null : strArr, (i2 & 64) != 0 ? null : str2, (i2 & 128) != 0 ? null : str3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEvent");
    }

    public final void sendEvent(Context context, ClickstreamConstants.EventType eventType, String str, HashMap<String, String> hashMap, Impression[] impressionArr, String[] strArr, String str2, String str3) {
        ClickstreamConstants.EventType eventType2 = eventType;
        Context context2 = context;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        initialize(context);
        HashMap<String, String> hashMap2 = hashMap;
        JSONObject parametersJson = getParametersJson(str, strArr, getSiteSpecificParams(hashMap), str2, str3);
        Impression[] impressionArr2 = impressionArr;
        ThreadsKt.thread$default(true, false, (ClassLoader) null, (String) null, 0, new Clickstream$sendEvent$1(this, parametersJson, eventType, impressionArr), 30, (Object) null);
    }

    public static /* synthetic */ void sendEvent$default(Clickstream clickstream, Context context, Impression[] impressionArr, String str, HashMap hashMap, String[] strArr, String str2, String str3, int i, Object obj) {
        if (obj == null) {
            clickstream.sendEvent(context, impressionArr, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : hashMap, (i & 16) != 0 ? null : strArr, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : str3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEvent");
    }

    public final void sendEvent(Context context, Impression[] impressionArr, String str, HashMap<String, String> hashMap, String[] strArr, String str2, String str3) {
        Intrinsics.checkNotNullParameter(context, "context");
        sendEvent(context, ClickstreamConstants.EventType.impressionBatch, str, hashMap, impressionArr, strArr, str2, str3);
    }

    public final HashMap<String, String> getSiteSpecificParams(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        HashMap<String, String> siteSpecificData = getSiteSpecificData();
        if (siteSpecificData != null) {
            Map map = siteSpecificData;
            if (!map.isEmpty()) {
                hashMap2.putAll(map);
            }
        }
        if (hashMap != null) {
            hashMap2.putAll(hashMap);
        }
        List<String> siteParamsFilterList = getSiteParamsFilterList();
        if (siteParamsFilterList != null && (!siteParamsFilterList.isEmpty())) {
            Map map2 = hashMap2;
            if (!map2.isEmpty()) {
                Iterator it = map2.entrySet().iterator();
                while (it.hasNext()) {
                    if (!siteParamsFilterList.contains(((Map.Entry) it.next()).getKey())) {
                        it.remove();
                    }
                }
            }
        }
        return hashMap2;
    }

    public final JSONObject getParametersJson(String str, String[] strArr, HashMap<String, String> hashMap, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        CharSequence charSequence = str;
        boolean z = false;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            jSONObject.put("eventText", str);
        }
        if (strArr != null) {
            if (!(strArr.length == 0)) {
                jSONObject.put("interests", new JSONArray((ArrayList) ArraysKt.toCollection((T[]) strArr, new ArrayList())));
            }
        }
        if (hashMap != null) {
            Map map = hashMap;
            if (!map.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry entry : map.entrySet()) {
                    String str4 = (String) entry.getKey();
                    String str5 = (String) entry.getValue();
                    if (str5 != null) {
                        jSONObject2.put(str4, str5);
                    }
                }
                jSONObject.put("siteSpecific", jSONObject2);
            }
        }
        CharSequence charSequence2 = str2;
        if (!(charSequence2 == null || charSequence2.length() == 0)) {
            jSONObject.put("pvid", str2);
        }
        CharSequence charSequence3 = str3;
        if (charSequence3 == null || charSequence3.length() == 0) {
            z = true;
        }
        if (!z) {
            jSONObject.put("asset", str3);
        }
        jSONObject.put("site", getSiteName());
        jSONObject.put("vertical", getVertical());
        jSONObject.put("source", "mobile");
        return jSONObject;
    }

    public final String generatePartyId() {
        this.isNewPartyGenerated = true;
        String generateUniqueId = this.dataGenerator.generateUniqueId();
        this.party_id = generateUniqueId;
        return generateUniqueId;
    }

    private final void initialize(Context context) {
        if (StringsKt.isBlank(this.party_id)) {
            boolean z = false;
            SharedPreferences sharedPreferences = context.getSharedPreferences(ClickstreamBase.SHARED_PREF, 0);
            String string = sharedPreferences.getString(ClickstreamBase.SHARED_PREF_PARTY_KEY, "");
            CharSequence charSequence = string;
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                z = true;
            }
            if (z) {
                this.party_id = generatePartyId();
                this.isNewPartyGenerated = true;
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(ClickstreamBase.SHARED_PREF_PARTY_KEY, this.party_id);
                edit.apply();
                return;
            }
            this.party_id = string;
        }
    }

    public final synchronized ArrayList<JSONObject> generateEvents(JSONObject jSONObject, Impression[] impressionArr) {
        ArrayList<JSONObject> arrayList;
        Intrinsics.checkNotNullParameter(jSONObject, "eventJson");
        arrayList = new ArrayList<>();
        JSONObject optJSONObject = jSONObject.optJSONObject("parameters");
        if (impressionArr != null) {
            boolean z = false;
            if ((!(impressionArr.length == 0)) && optJSONObject != null) {
                ArrayList arrayList2 = new ArrayList();
                int length = impressionArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Impression impression = impressionArr[i];
                    arrayList2.add(impression);
                    optJSONObject.put("impressions", new JSONArray(new GsonBuilder().create().toJson((Object) arrayList2)));
                    jSONObject.put("parameters", optJSONObject);
                    String jSONObject2 = jSONObject.toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject2, "eventJson.toString()");
                    if (getBytesSize(jSONObject2) > ClickstreamBase.Companion.getMAX_PAY_LOAD_SIZE_BYTES$clickstream_release()) {
                        if (arrayList2.size() <= 1) {
                            z = true;
                            break;
                        }
                        arrayList2.remove(impression);
                        optJSONObject.put("impressions", new JSONArray(new GsonBuilder().create().toJson((Object) arrayList2)));
                        jSONObject.put("parameters", optJSONObject);
                        arrayList.add(new JSONObject(jSONObject.toString()));
                        arrayList2.clear();
                        arrayList2.add(impression);
                    }
                    i++;
                }
                if (arrayList2.size() > 0) {
                    if (z) {
                        optJSONObject.put("impressions", new JSONArray(new GsonBuilder().create().toJson((Object) impressionArr)));
                    } else {
                        optJSONObject.put("impressions", new JSONArray(new GsonBuilder().create().toJson((Object) arrayList2)));
                    }
                    jSONObject.put("parameters", optJSONObject);
                    arrayList.add(jSONObject);
                }
            }
        }
        arrayList.add(jSONObject);
        return arrayList;
    }

    private final int getBytesSize(String str) {
        Charset charset = Charsets.UTF_8;
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes.length;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}
