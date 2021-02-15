package com.webmd.wbmdcmepulse.providers;

import android.content.Context;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.cmetracker.CMEItem;
import com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.RequestHelper;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import io.branch.referral.Branch;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CMETrackerDataProvider {
    public static String CE_CREDITS_KEY = "ce_credits";
    public static String CME_CREDITS_KEY = "cme_credits";
    public static String COMPLETED_KEY = OmnitureData.LINK_NAME_TRACKER_COMPLETE;
    public static String COMPLETED_LOC_KEY = "completed_loc";
    public static String COMPLETED_MOC_KEY = "completed_moc";
    public static String CREDITS_KEY = "credits";
    public static String IN_PROGRESS_KEY = OmnitureData.LINK_NAME_TRACKER_IN_PROGRESS;
    public static String LOC_CREDITS_KEY = "loc_credits";
    public static String MOC_CREDITS_KEY = "moc_credits";
    public static String RX_CREDITS_KEY = "rx_credits";
    static String TAG = CMETrackerDataProvider.class.getSimpleName();

    public static void getCMETrackerCredits(ICallbackEvent<CMETrackerResponse, String> iCallbackEvent, Context context) {
        getCMETrackerData((String) null, context, (String) null, iCallbackEvent);
    }

    public static void getCMETrackerData(String str, Context context, String str2, final ICallbackEvent<CMETrackerResponse, String> iCallbackEvent) {
        String cMETrackerUrl = Constants.Config.getCMETrackerUrl(context);
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            cMETrackerUrl = String.format("%s?fyear=%s&tyear=%s", new Object[]{Constants.Config.getCMETrackerUrl(context), str2, str});
        }
        RequestHelper.getInstance(context).addJSONObjectRequest(0, cMETrackerUrl, (JSONObject) null, (ICallbackEvent<JSONObject, String>) new ICallbackEvent<JSONObject, String>() {
            public void onError(String str) {
                iCallbackEvent.onError(str);
            }

            /* JADX WARNING: Removed duplicated region for block: B:13:0x0039 A[SYNTHETIC, Splitter:B:13:0x0039] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onCompleted(org.json.JSONObject r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "COMPLETED"
                    com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse r1 = new com.webmd.wbmdcmepulse.models.cmetracker.CMETrackerResponse     // Catch:{ JSONException -> 0x0071 }
                    r1.<init>()     // Catch:{ JSONException -> 0x0071 }
                    java.util.HashMap r2 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCreditsFromJSON(r5)     // Catch:{ JSONException -> 0x0071 }
                    r1.creditMap = r2     // Catch:{ JSONException -> 0x0071 }
                    java.lang.String r2 = "CME"
                    java.util.ArrayList r2 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r2, r0)     // Catch:{ JSONException -> 0x0071 }
                    r1.completedCmeItems = r2     // Catch:{ JSONException -> 0x0071 }
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    java.lang.String r3 = "NurseCE"
                    if (r2 == 0) goto L_0x002d
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    int r2 = r2.size()     // Catch:{ JSONException -> 0x0071 }
                    if (r2 <= 0) goto L_0x002d
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    java.util.ArrayList r3 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r3, r0)     // Catch:{ JSONException -> 0x0071 }
                    r2.addAll(r3)     // Catch:{ JSONException -> 0x0071 }
                    goto L_0x0033
                L_0x002d:
                    java.util.ArrayList r2 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r3, r0)     // Catch:{ JSONException -> 0x0071 }
                    r1.completedCmeItems = r2     // Catch:{ JSONException -> 0x0071 }
                L_0x0033:
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    java.lang.String r3 = "PharmacistCE"
                    if (r2 == 0) goto L_0x004b
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    int r2 = r2.size()     // Catch:{ JSONException -> 0x0071 }
                    if (r2 <= 0) goto L_0x004b
                    java.util.ArrayList<com.webmd.wbmdcmepulse.models.cmetracker.CMEItem> r2 = r1.completedCmeItems     // Catch:{ JSONException -> 0x0071 }
                    java.util.ArrayList r0 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r3, r0)     // Catch:{ JSONException -> 0x0071 }
                    r2.addAll(r0)     // Catch:{ JSONException -> 0x0071 }
                    goto L_0x0051
                L_0x004b:
                    java.util.ArrayList r0 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r3, r0)     // Catch:{ JSONException -> 0x0071 }
                    r1.completedCmeItems = r0     // Catch:{ JSONException -> 0x0071 }
                L_0x0051:
                    java.util.ArrayList r0 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getInProgressCME(r5)     // Catch:{ JSONException -> 0x0071 }
                    r1.inProgressCmeItems = r0     // Catch:{ JSONException -> 0x0071 }
                    java.lang.String r0 = "MOC"
                    java.lang.String r2 = "COMPLETE_MOC"
                    java.util.ArrayList r0 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r0, r2)     // Catch:{ JSONException -> 0x0071 }
                    r1.mocCmeItmes = r0     // Catch:{ JSONException -> 0x0071 }
                    java.lang.String r0 = "LOC"
                    java.lang.String r2 = "COMPLETE_LOC"
                    java.util.ArrayList r5 = com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.getCompletedCredit(r5, r0, r2)     // Catch:{ JSONException -> 0x0071 }
                    r1.locCmeItmes = r5     // Catch:{ JSONException -> 0x0071 }
                    com.wbmd.wbmdcommons.callbacks.ICallbackEvent r5 = r6     // Catch:{ JSONException -> 0x0071 }
                    r5.onCompleted(r1)     // Catch:{ JSONException -> 0x0071 }
                    goto L_0x0078
                L_0x0071:
                    com.wbmd.wbmdcommons.callbacks.ICallbackEvent r5 = r6
                    java.lang.String r0 = "Error parsing CME Tracker JSON."
                    r5.onError(r0)
                L_0x0078:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider.AnonymousClass1.onCompleted(org.json.JSONObject):void");
            }
        });
    }

    static ArrayList<CMEItem> getInProgressCME(JSONObject jSONObject) throws JSONException {
        ArrayList<CMEItem> arrayList = new ArrayList<>();
        JSONObject jSONObject2 = jSONObject.getJSONObject("InProgress");
        if (jSONObject2.has("CMEInprogress")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("CMEInprogress");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(parseCMEItem(jSONArray.getJSONObject(i), CMEItem.IN_PROGRESS));
            }
        }
        return arrayList;
    }

    static ArrayList<CMEItem> getCompletedCredit(JSONObject jSONObject, String str, String str2) throws JSONException {
        ArrayList<CMEItem> arrayList = new ArrayList<>();
        if (!Extensions.isStringNullOrEmpty(str) && str2 != null) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
            if (jSONObject2.has(str)) {
                JSONArray jSONArray = jSONObject2.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(parseCMEItem(jSONArray.getJSONObject(i), str2));
                }
            }
        }
        return arrayList;
    }

    static HashMap getCreditsFromJSON(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_COMPLETED);
        HashMap hashMap = new HashMap();
        boolean has = jSONObject2.has("CME");
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (has) {
            JSONArray jSONArray = jSONObject2.getJSONArray("CME");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                String str = CME_CREDITS_KEY + "-" + jSONObject3.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION).substring(6);
                if (hashMap.containsKey(str)) {
                    hashMap.put(str, Double.valueOf(((Double) hashMap.get(str)).doubleValue() + jSONObject3.getDouble(Branch.REFERRAL_CODE_TYPE)));
                } else {
                    hashMap.put(str, Double.valueOf(jSONObject3.getDouble(Branch.REFERRAL_CODE_TYPE)));
                }
                d += jSONObject3.getDouble(Branch.REFERRAL_CODE_TYPE);
            }
        }
        if (jSONObject2.has("NurseCE")) {
            JSONArray jSONArray2 = jSONObject2.getJSONArray("NurseCE");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject4 = jSONArray2.getJSONObject(i2);
                String substring = jSONObject4.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION).substring(6);
                String str2 = CE_CREDITS_KEY + "-" + substring;
                if (hashMap.containsKey(str2)) {
                    hashMap.put(str2, Double.valueOf(((Double) hashMap.get(str2)).doubleValue() + jSONObject4.getDouble(Branch.REFERRAL_CODE_TYPE)));
                } else {
                    hashMap.put(str2, Double.valueOf(jSONObject4.getDouble(Branch.REFERRAL_CODE_TYPE)));
                }
                String str3 = RX_CREDITS_KEY + "-" + substring;
                if (hashMap.containsKey(str3)) {
                    hashMap.put(str3, Double.valueOf(((Double) hashMap.get(str3)).doubleValue() + jSONObject4.getDouble("rxCredit")));
                } else {
                    hashMap.put(str3, Double.valueOf(jSONObject4.getDouble("rxCredit")));
                }
                d += jSONObject4.getDouble(Branch.REFERRAL_CODE_TYPE);
            }
        }
        if (jSONObject2.has("PharmacistCE")) {
            JSONArray jSONArray3 = jSONObject2.getJSONArray("PharmacistCE");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                JSONObject jSONObject5 = jSONArray3.getJSONObject(i3);
                String substring2 = jSONObject5.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION).substring(6);
                String str4 = CE_CREDITS_KEY + "-" + substring2;
                if (hashMap.containsKey(str4)) {
                    hashMap.put(str4, Double.valueOf(((Double) hashMap.get(str4)).doubleValue() + jSONObject5.getDouble(Branch.REFERRAL_CODE_TYPE)));
                } else {
                    hashMap.put(str4, Double.valueOf(jSONObject5.getDouble(Branch.REFERRAL_CODE_TYPE)));
                }
                String str5 = RX_CREDITS_KEY + "-" + substring2;
                if (hashMap.containsKey(str5)) {
                    hashMap.put(str5, Double.valueOf(((Double) hashMap.get(str5)).doubleValue() + jSONObject5.getDouble("rxCredit")));
                } else {
                    hashMap.put(str5, Double.valueOf(jSONObject5.getDouble("rxCredit")));
                }
                d += jSONObject5.getDouble(Branch.REFERRAL_CODE_TYPE);
            }
        }
        if (jSONObject2.has("MOC")) {
            JSONArray jSONArray4 = jSONObject2.getJSONArray("MOC");
            for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                JSONObject jSONObject6 = jSONArray4.getJSONObject(i4);
                String str6 = MOC_CREDITS_KEY + "-" + jSONObject6.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION).substring(6);
                if (hashMap.containsKey(str6)) {
                    hashMap.put(str6, Double.valueOf(((Double) hashMap.get(str6)).doubleValue() + jSONObject6.getDouble(Branch.REFERRAL_CODE_TYPE)));
                } else {
                    hashMap.put(str6, Double.valueOf(jSONObject6.getDouble(Branch.REFERRAL_CODE_TYPE)));
                }
            }
        }
        if (jSONObject2.has("LOC")) {
            JSONArray jSONArray5 = jSONObject2.getJSONArray("LOC");
            for (int i5 = 0; i5 < jSONArray5.length(); i5++) {
                JSONObject jSONObject7 = jSONArray5.getJSONObject(i5);
                String str7 = LOC_CREDITS_KEY + "-" + jSONObject7.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION).substring(6);
                if (hashMap.containsKey(str7)) {
                    hashMap.put(str7, Double.valueOf(((Double) hashMap.get(str7)).doubleValue() + jSONObject7.getDouble(Branch.REFERRAL_CODE_TYPE)));
                } else {
                    hashMap.put(str7, Double.valueOf(jSONObject7.getDouble(Branch.REFERRAL_CODE_TYPE)));
                }
                d += jSONObject7.getDouble(Branch.REFERRAL_CODE_TYPE);
            }
        }
        hashMap.put(CREDITS_KEY, Double.valueOf(d));
        return hashMap;
    }

    static CMEItem parseCMEItem(JSONObject jSONObject, String str) throws JSONException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
        CMEItem cMEItem = new CMEItem();
        cMEItem.setType(str);
        if (jSONObject.has("ti")) {
            cMEItem.setTitle(jSONObject.getString("ti"));
        }
        try {
            cMEItem.setExpirationDate(simpleDateFormat.parse(jSONObject.getString("expDate")));
            cMEItem.setParticipationDate(simpleDateFormat.parse(jSONObject.getString(CPEvent.ACTIVITY_NAME_PARTICIPATION)));
        } catch (ParseException unused) {
            Log.e(TAG, "Error parsing the date in the CME Tracker Item.");
        }
        if (jSONObject.has("credType")) {
            cMEItem.setCreditType(jSONObject.getString("credType"));
        }
        if (jSONObject.has(Branch.REFERRAL_CODE_TYPE)) {
            cMEItem.setCredit(jSONObject.getDouble(Branch.REFERRAL_CODE_TYPE));
        }
        if (jSONObject.has("rxCredit")) {
            cMEItem.setRxCredit(jSONObject.getDouble("rxCredit"));
        }
        if (jSONObject.has("Provider")) {
            cMEItem.setProvider(jSONObject.getString("Provider"));
        }
        if (jSONObject.has("format")) {
            cMEItem.setFormat(jSONObject.getString("format"));
        }
        if (jSONObject.has("cc")) {
            cMEItem.setCoreCompetency(jSONObject.getString("cc"));
        }
        if (jSONObject.has("resultId")) {
            cMEItem.setResultId(jSONObject.getLong("resultId"));
        }
        if (jSONObject.has("questionnaireId")) {
            cMEItem.setQuestionnaireId(jSONObject.getLong("questionnaireId"));
        }
        if (jSONObject.has("referrerUri")) {
            cMEItem.setReferrerUri(jSONObject.getString("referrerUri"));
        }
        if (jSONObject.has("continueActivityUri")) {
            cMEItem.setContinueActivityUri(jSONObject.getString("continueActivityUri"));
        }
        if (jSONObject.has("viewCertificateUri")) {
            cMEItem.setViewCertificateUri(jSONObject.getString("viewCertificateUri"));
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("errorCodes");
        if (optJSONObject != null) {
            ArrayList arrayList = new ArrayList();
            for (String add : jsonToMap(optJSONObject).keySet()) {
                arrayList.add(add);
            }
            cMEItem.setErrorCodeMap(arrayList);
        }
        String optString = jSONObject.optString("mocStatus");
        if (!Extensions.isStringNullOrEmpty(optString)) {
            cMEItem.setMOCStatus(optString);
        }
        return cMEItem;
    }

    public static Map<String, Object> jsonToMap(JSONObject jSONObject) throws JSONException {
        return jSONObject != JSONObject.NULL ? toMap(jSONObject) : new HashMap();
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public static List<Object> toList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
