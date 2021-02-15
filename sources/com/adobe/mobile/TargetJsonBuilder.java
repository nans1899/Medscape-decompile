package com.adobe.mobile;

import com.adobe.mobile.StaticMethods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TargetJsonBuilder {
    TargetJsonBuilder() {
    }

    protected static JSONObject getRequestPayload(List<TargetPrefetchObject> list, List<TargetRequestObject> list2, Map<String, Object> map, List<JSONObject> list3, Map<String, Object> map2) throws JSONException {
        JSONObject defaultJsonObject = getDefaultJsonObject();
        JSONObject profileParameters = getProfileParameters(map);
        if (profileParameters != null && profileParameters.length() > 0) {
            defaultJsonObject.put("profileParameters", profileParameters);
        }
        JSONArray prefetchMboxes = getPrefetchMboxes(list, map2);
        if (prefetchMboxes != null && prefetchMboxes.length() > 0) {
            defaultJsonObject.put("prefetch", prefetchMboxes);
        }
        if (list3 != null && !list3.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (JSONObject put : list3) {
                jSONArray.put(put);
            }
            defaultJsonObject.put("notifications", jSONArray);
        }
        JSONArray requestedMboxJSON = getRequestedMboxJSON(list2, map2);
        if (requestedMboxJSON != null && requestedMboxJSON.length() > 0) {
            defaultJsonObject.put("mboxes", requestedMboxJSON);
        }
        return addPreviewParameters(defaultJsonObject);
    }

    protected static JSONObject getNotificationsJsonObject(JSONObject jSONObject, TargetRequestObject targetRequestObject, Map<String, Object> map) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject updateNotificationJson = updateNotificationJson(new JSONObject(jSONObject.toString()));
            updateNotificationJson.remove("clickToken");
            updateNotificationJson.put("type", "hit");
            JSONObject mboxParameters = getMboxParameters(targetRequestObject.getMboxParameters(), map);
            if (mboxParameters != null && mboxParameters.length() > 0) {
                updateNotificationJson.put("parameters", mboxParameters);
            }
            JSONObject orderParameters = getOrderParameters(targetRequestObject.getOrderParameters());
            if (orderParameters != null && orderParameters.length() > 0) {
                updateNotificationJson.put("order", orderParameters);
            }
            JSONObject productParameters = getProductParameters(targetRequestObject.getProductParameters());
            if (productParameters != null && productParameters.length() > 0) {
                updateNotificationJson.put("product", productParameters);
            }
            return updateNotificationJson;
        } catch (JSONException unused) {
            StaticMethods.logWarningFormat("Target - failed to create notification Json Node for %s", jSONObject.toString());
            return null;
        }
    }

    protected static JSONObject getClickNotificationJsonObject(JSONObject jSONObject, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Map<String, Object> map4) {
        if (jSONObject == null) {
            return null;
        }
        try {
            JSONObject updateNotificationJson = updateNotificationJson(new JSONObject(jSONObject.toString()));
            updateNotificationJson.remove("eventTokens");
            updateNotificationJson.put("type", "click");
            JSONObject mboxParameters = getMboxParameters(map, map4);
            if (mboxParameters != null && mboxParameters.length() > 0) {
                updateNotificationJson.put("parameters", mboxParameters);
            }
            JSONObject orderParameters = getOrderParameters(map2);
            if (orderParameters != null && orderParameters.length() > 0) {
                updateNotificationJson.put("order", orderParameters);
            }
            JSONObject productParameters = getProductParameters(map3);
            if (productParameters != null && productParameters.length() > 0) {
                updateNotificationJson.put("product", productParameters);
            }
            return updateNotificationJson;
        } catch (JSONException unused) {
            StaticMethods.logWarningFormat("Target - failed to create JSON object for location click notification (%s)", jSONObject.toString());
            return null;
        }
    }

    private static JSONObject updateNotificationJson(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        jSONObject2.put("timestamp", System.currentTimeMillis());
        jSONObject2.remove("content");
        jSONObject2.remove("clientSideAnalyticsLoggingPayload");
        jSONObject2.remove("errorType");
        jSONObject2.remove("parameters");
        jSONObject2.remove("order");
        jSONObject2.remove("product");
        return jSONObject2;
    }

    private static JSONObject getDefaultJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("client", MobileConfig.getInstance().getClientCode());
        jSONObject.put("contentAsJson", false);
        if (MobileConfig.getInstance().mobileUsingTarget()) {
            long environmentID = MobileConfig.getInstance().getEnvironmentID();
            if (environmentID != 0) {
                jSONObject.put("environmentId", environmentID);
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        HashMap<String, Object> targetParameterMap = VisitorIDService.sharedInstance().getTargetParameterMap();
        if (targetParameterMap != null && !targetParameterMap.isEmpty()) {
            for (Map.Entry next : targetParameterMap.entrySet()) {
                jSONObject2.put((String) next.getKey(), next.getValue());
            }
        }
        String GetDpuuid = AudienceManagerWorker.GetDpuuid();
        if (!isNullOrEmpty(GetDpuuid)) {
            jSONObject2.put("dataPartnerUserId", GetDpuuid);
        }
        String GetDpid = AudienceManagerWorker.GetDpid();
        if (!isNullOrEmpty(GetDpid)) {
            jSONObject2.put("dataPartnerId", GetDpid);
        }
        String str = null;
        try {
            str = StaticMethods.getSharedPreferences().getString("AAMUserId", (String) null);
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logDebugFormat("Target - Error getting uuid from shared preferences (%s).", e.getMessage());
        }
        if (!isNullOrEmpty(str)) {
            jSONObject2.put("uuid", str);
        }
        if (jSONObject2.length() > 0) {
            jSONObject.put("aamParameters", jSONObject2);
        }
        JSONObject jSONObject3 = new JSONObject();
        String tntId = TargetWorker.getTntId();
        if (!isNullOrEmpty(tntId)) {
            jSONObject3.put("tntId", tntId);
        }
        String thirdPartyId = TargetWorker.getThirdPartyId();
        if (!isNullOrEmpty(thirdPartyId)) {
            jSONObject3.put("thirdPartyId", thirdPartyId);
        }
        String marketingCloudID = VisitorIDService.sharedInstance().getMarketingCloudID();
        if (!isNullOrEmpty(marketingCloudID)) {
            jSONObject3.put("marketingCloudVisitorId", marketingCloudID);
        }
        List<VisitorID> identifiers = Visitor.getIdentifiers();
        if (identifiers != null && !identifiers.isEmpty()) {
            jSONObject3.put("customerIds", getCustomerIDs(identifiers));
        }
        if (jSONObject3.length() > 0) {
            jSONObject.put("id", jSONObject3);
        }
        return jSONObject;
    }

    private static JSONArray getRequestedMboxJSON(List<TargetRequestObject> list, Map<String, Object> map) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return null;
        }
        long j = 0;
        for (TargetRequestObject next : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("indexId", j);
                jSONObject.put("mbox", next.getMboxName());
                JSONObject orderParameters = getOrderParameters(next.getOrderParameters());
                if (orderParameters != null && orderParameters.length() > 0) {
                    jSONObject.put("order", orderParameters);
                }
                JSONObject mboxParameters = getMboxParameters(next.getMboxParameters(), map);
                if (mboxParameters != null && mboxParameters.length() > 0) {
                    jSONObject.put("parameters", mboxParameters);
                }
                JSONObject productParameters = getProductParameters(next.getProductParameters());
                if (productParameters != null && productParameters.length() > 0) {
                    jSONObject.put("product", productParameters);
                }
                jSONArray.put(jSONObject);
                j++;
            } catch (JSONException unused) {
                StaticMethods.logWarningFormat("Target - failed to create Json Node for mbox %s", next.getMboxName());
            }
        }
        return jSONArray;
    }

    private static JSONObject getProfileParameters(Map<String, Object> map) {
        return getJsonObjectFromMap(map);
    }

    private static JSONArray getCustomerIDs(List<VisitorID> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (VisitorID next : list) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", next.id);
            jSONObject.put("integrationCode", next.idType);
            jSONObject.put("authenticatedState", next.authenticationState.getTextValue());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private static JSONArray getPrefetchMboxes(List<TargetPrefetchObject> list, Map<String, Object> map) {
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return null;
        }
        long j = 0;
        for (TargetPrefetchObject next : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("indexId", j);
                jSONObject.put("mbox", next.getMboxName());
                JSONObject mboxParameters = getMboxParameters(next.getMboxParameters(), map);
                if (mboxParameters != null && mboxParameters.length() > 0) {
                    jSONObject.put("parameters", mboxParameters);
                }
                JSONObject orderParameters = getOrderParameters(next.getOrderParameters());
                if (orderParameters != null && orderParameters.length() > 0) {
                    jSONObject.put("order", orderParameters);
                }
                JSONObject productParameters = getProductParameters(next.getProductParameters());
                if (productParameters != null && productParameters.length() > 0) {
                    jSONObject.put("product", productParameters);
                }
                jSONArray.put(jSONObject);
                j++;
            } catch (JSONException unused) {
                StaticMethods.logWarningFormat("Target - failed to create Json Node for mbox %s", next.getMboxName());
            }
        }
        return jSONArray;
    }

    private static JSONObject getMboxParameters(Map<String, Object> map, Map<String, Object> map2) {
        JSONObject jsonObjectFromMap = getJsonObjectFromMap(map);
        if (jsonObjectFromMap == null) {
            jsonObjectFromMap = new JSONObject();
        }
        if (map2 != null) {
            try {
                if (!map2.isEmpty()) {
                    for (Map.Entry next : map2.entrySet()) {
                        jsonObjectFromMap.put((String) next.getKey(), next.getValue());
                    }
                }
            } catch (JSONException e) {
                StaticMethods.logWarningFormat("Target - Failed to append target internal parameters to the target request json (%s)", e);
            }
        }
        return jsonObjectFromMap;
    }

    private static JSONObject getOrderParameters(Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            JSONObject jSONObject = new JSONObject();
            Object obj = map.get("id");
            if (obj == null || obj.toString().isEmpty()) {
                obj = map.get(TargetLocationRequest.TARGET_PARAMETER_ORDER_ID);
            }
            Object obj2 = map.get("total");
            if (obj2 == null || obj2.toString().isEmpty()) {
                obj2 = map.get(TargetLocationRequest.TARGET_PARAMETER_ORDER_TOTAL);
            }
            Object obj3 = map.get(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID);
            if (obj != null) {
                try {
                    if (!obj.toString().isEmpty()) {
                        jSONObject.put("id", obj.toString());
                    }
                } catch (JSONException e) {
                    StaticMethods.logWarningFormat("Target - JSONException while creating order details (%s)", e.getLocalizedMessage());
                }
            }
            if (obj2 != null && !obj2.toString().isEmpty()) {
                try {
                    jSONObject.put("total", Double.parseDouble(obj2.toString()));
                } catch (NumberFormatException e2) {
                    StaticMethods.logWarningFormat("Target - NumberFormatException while creating order details (%s)", e2.getLocalizedMessage());
                }
            }
            if (obj3 instanceof List) {
                try {
                    List<String> cleanProductArrayToHaveOnlyStrings = cleanProductArrayToHaveOnlyStrings((List) obj3);
                    if (cleanProductArrayToHaveOnlyStrings != null && !cleanProductArrayToHaveOnlyStrings.isEmpty()) {
                        jSONObject.put(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID, new JSONArray(cleanProductArrayToHaveOnlyStrings));
                    }
                } catch (Exception unused) {
                    StaticMethods.logWarningFormat("Target - Unable to process productID's .Should be of type ArrayList<String>", new Object[0]);
                }
            } else if (!(obj3 instanceof String)) {
                StaticMethods.logWarningFormat("Target -Unknown type for order productID's. Should be either comma seperated string or arraylist ", new Object[0]);
            } else if (obj3 != null && !obj3.toString().isEmpty()) {
                String[] split = obj3.toString().split(",");
                JSONArray jSONArray = new JSONArray();
                for (String trim : split) {
                    jSONArray.put(trim.trim());
                }
                jSONObject.put(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID, jSONArray);
                StaticMethods.logWarningFormat("Target - Deprecated type for productPurchaseID. Use List<String> instead of comma separated array string", new Object[0]);
            }
            return jSONObject;
        }
        return null;
    }

    private static JSONObject getProductParameters(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            try {
                Object obj = map.get("id");
                if (obj != null) {
                    jSONObject.put("id", obj.toString());
                }
                Object obj2 = map.get(TargetLocationRequest.TARGET_PARAMETER_CATEGORY_ID);
                if (obj2 != null) {
                    jSONObject.put(TargetLocationRequest.TARGET_PARAMETER_CATEGORY_ID, obj2.toString());
                }
                return jSONObject;
            } catch (JSONException e) {
                StaticMethods.logWarningFormat("Target - Failed to append product parameters to the target request json (%s)", e);
            }
        }
        return null;
    }

    private static JSONObject getJsonObjectFromMap(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry next : map.entrySet()) {
                    if (next.getKey() != null) {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    }
                }
            }
            return jSONObject;
        } catch (JSONException e) {
            StaticMethods.logWarningFormat("Target - Error adding parameters to JSON Object (%s)", e.getLocalizedMessage());
            return null;
        }
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private static JSONObject addPreviewParameters(JSONObject jSONObject) {
        if (!(TargetPreviewManager.getInstance().getToken() == null || TargetPreviewManager.getInstance().getPreviewParams() == null)) {
            try {
                JSONObject jSONObject2 = new JSONObject(TargetPreviewManager.getInstance().getPreviewParams());
                Iterator<String> keys = jSONObject2.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        String next = keys.next();
                        jSONObject.put(next, jSONObject2.get(next));
                    }
                }
            } catch (Exception e) {
                StaticMethods.logErrorFormat("Target - Could not compile the target preview params with the Target request! (%s)", e);
            }
        }
        return jSONObject;
    }

    private static List<String> cleanProductArrayToHaveOnlyStrings(List list) {
        ArrayList arrayList = new ArrayList();
        for (Object next : list) {
            if (next instanceof String) {
                arrayList.add(next.toString());
            } else {
                StaticMethods.logErrorFormat("Target - Unknown Format of purchased Product ID (%s). Should be String", next);
            }
        }
        return arrayList;
    }
}
