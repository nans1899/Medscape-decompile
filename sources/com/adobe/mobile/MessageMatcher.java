package com.adobe.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class MessageMatcher {
    private static final String MESSAGE_JSON_KEY = "key";
    private static final String MESSAGE_JSON_MATCHES = "matches";
    private static final String MESSAGE_JSON_VALUES = "values";
    private static final String MESSAGE_MATCHER_STRING_CONTAINS = "co";
    private static final String MESSAGE_MATCHER_STRING_ENDS_WITH = "ew";
    private static final String MESSAGE_MATCHER_STRING_EQUALS = "eq";
    private static final String MESSAGE_MATCHER_STRING_EXISTS = "ex";
    private static final String MESSAGE_MATCHER_STRING_GREATER_THAN = "gt";
    private static final String MESSAGE_MATCHER_STRING_GREATER_THAN_OR_EQUALS = "ge";
    private static final String MESSAGE_MATCHER_STRING_LESS_THAN = "lt";
    private static final String MESSAGE_MATCHER_STRING_LESS_THAN_OR_EQUALS = "le";
    private static final String MESSAGE_MATCHER_STRING_NOT_CONTAINS = "nc";
    private static final String MESSAGE_MATCHER_STRING_NOT_EQUALS = "ne";
    private static final String MESSAGE_MATCHER_STRING_NOT_EXISTS = "nx";
    private static final String MESSAGE_MATCHER_STRING_STARTS_WITH = "sw";
    private static final Map<String, Class> _messageMatcherDictionary = new HashMap<String, Class>() {
        {
            put(MessageMatcher.MESSAGE_MATCHER_STRING_EQUALS, MessageMatcherEquals.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_NOT_EQUALS, MessageMatcherNotEquals.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_GREATER_THAN, MessageMatcherGreaterThan.class);
            put("ge", MessageMatcherGreaterThanOrEqual.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_LESS_THAN, MessageMatcherLessThan.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_LESS_THAN_OR_EQUALS, MessageMatcherLessThanOrEqual.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_CONTAINS, MessageMatcherContains.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_NOT_CONTAINS, MessageMatcherNotContains.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_STARTS_WITH, MessageMatcherStartsWith.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_ENDS_WITH, MessageMatcherEndsWith.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_EXISTS, MessageMatcherExists.class);
            put(MessageMatcher.MESSAGE_MATCHER_STRING_NOT_EXISTS, MessageMatcherNotExists.class);
        }
    };
    protected String key;
    protected ArrayList<Object> values;

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        return false;
    }

    MessageMatcher() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static com.adobe.mobile.MessageMatcher messageMatcherWithJsonObject(org.json.JSONObject r7) {
        /*
            java.lang.String r0 = "Messages - error creating matcher, key is required"
            java.lang.String r1 = "Messages - Error creating matcher (%s)"
            r2 = 0
            java.lang.String r3 = "matches"
            java.lang.String r3 = r7.getString(r3)     // Catch:{ JSONException -> 0x001b }
            if (r3 == 0) goto L_0x0024
            int r4 = r3.length()     // Catch:{ JSONException -> 0x001b }
            if (r4 > 0) goto L_0x0024
            java.lang.String r4 = "Messages - message matcher type is empty"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ JSONException -> 0x001b }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r5)     // Catch:{ JSONException -> 0x001b }
            goto L_0x0024
        L_0x001b:
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "Messages - message matcher type is required"
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r3)
            java.lang.String r3 = "UNKNOWN"
        L_0x0024:
            java.util.Map<java.lang.String, java.lang.Class> r4 = _messageMatcherDictionary
            java.lang.Object r4 = r4.get(r3)
            java.lang.Class r4 = (java.lang.Class) r4
            r5 = 1
            if (r4 != 0) goto L_0x003a
            java.lang.Class<com.adobe.mobile.MessageMatcherUnknown> r4 = com.adobe.mobile.MessageMatcherUnknown.class
            java.lang.Object[] r6 = new java.lang.Object[r5]
            r6[r2] = r3
            java.lang.String r3 = "Messages - message matcher type \"%s\" is invalid"
            com.adobe.mobile.StaticMethods.logWarningFormat(r3, r6)
        L_0x003a:
            java.lang.Object r3 = r4.newInstance()     // Catch:{ InstantiationException -> 0x004e, IllegalAccessException -> 0x0041 }
            com.adobe.mobile.MessageMatcher r3 = (com.adobe.mobile.MessageMatcher) r3     // Catch:{ InstantiationException -> 0x004e, IllegalAccessException -> 0x0041 }
            goto L_0x005b
        L_0x0041:
            r3 = move-exception
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.String r3 = r3.getMessage()
            r4[r2] = r3
            com.adobe.mobile.StaticMethods.logErrorFormat(r1, r4)
            goto L_0x005a
        L_0x004e:
            r3 = move-exception
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.String r3 = r3.getMessage()
            r4[r2] = r3
            com.adobe.mobile.StaticMethods.logErrorFormat(r1, r4)
        L_0x005a:
            r3 = 0
        L_0x005b:
            if (r3 == 0) goto L_0x00c1
            java.lang.String r1 = "key"
            java.lang.String r1 = r7.getString(r1)     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            r3.key = r1     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            if (r1 == 0) goto L_0x0084
            int r1 = r1.length()     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            if (r1 > 0) goto L_0x0084
            java.lang.String r1 = "Messages - error creating matcher, key is empty"
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r1, r4)     // Catch:{ JSONException -> 0x007f, NullPointerException -> 0x0079 }
            goto L_0x0084
        L_0x0079:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.adobe.mobile.StaticMethods.logWarningFormat(r0, r1)
            goto L_0x0084
        L_0x007f:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.adobe.mobile.StaticMethods.logWarningFormat(r0, r1)
        L_0x0084:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00ba }
            r0.<init>()     // Catch:{ JSONException -> 0x00ba }
            r3.values = r0     // Catch:{ JSONException -> 0x00ba }
            boolean r0 = r3 instanceof com.adobe.mobile.MessageMatcherExists     // Catch:{ JSONException -> 0x00ba }
            if (r0 == 0) goto L_0x0090
            return r3
        L_0x0090:
            java.lang.String r0 = "values"
            org.json.JSONArray r7 = r7.getJSONArray(r0)     // Catch:{ JSONException -> 0x00ba }
            int r0 = r7.length()     // Catch:{ JSONException -> 0x00ba }
            r1 = 0
        L_0x009c:
            if (r1 >= r0) goto L_0x00aa
            java.util.ArrayList<java.lang.Object> r4 = r3.values     // Catch:{ JSONException -> 0x00ba }
            java.lang.Object r5 = r7.get(r1)     // Catch:{ JSONException -> 0x00ba }
            r4.add(r5)     // Catch:{ JSONException -> 0x00ba }
            int r1 = r1 + 1
            goto L_0x009c
        L_0x00aa:
            java.util.ArrayList<java.lang.Object> r7 = r3.values     // Catch:{ JSONException -> 0x00ba }
            int r7 = r7.size()     // Catch:{ JSONException -> 0x00ba }
            if (r7 != 0) goto L_0x00c1
            java.lang.String r7 = "Messages - error creating matcher, values is empty"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ JSONException -> 0x00ba }
            com.adobe.mobile.StaticMethods.logWarningFormat(r7, r0)     // Catch:{ JSONException -> 0x00ba }
            goto L_0x00c1
        L_0x00ba:
            java.lang.Object[] r7 = new java.lang.Object[r2]
            java.lang.String r0 = "Messages - error creating matcher, values is required"
            com.adobe.mobile.StaticMethods.logWarningFormat(r0, r7)
        L_0x00c1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MessageMatcher.messageMatcherWithJsonObject(org.json.JSONObject):com.adobe.mobile.MessageMatcher");
    }

    /* access modifiers changed from: protected */
    public boolean matchesInMaps(Map<String, Object>... mapArr) {
        if (mapArr == null || mapArr.length <= 0) {
            return false;
        }
        Object obj = null;
        int length = mapArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Map<String, Object> map = mapArr[i];
            if (map != null && map.containsKey(this.key)) {
                obj = map.get(this.key);
                break;
            }
            i++;
        }
        if (obj == null || !matches(obj)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public Double tryParseDouble(Object obj) {
        try {
            return Double.valueOf(obj.toString());
        } catch (Exception unused) {
            return null;
        }
    }
}
