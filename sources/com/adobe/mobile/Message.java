package com.adobe.mobile;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.adobe.mobile.Messages;
import com.adobe.mobile.StaticMethods;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class Message {
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ADVERTISING_IDENTIFIER = "%adid%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ALL_JSON_ENCODED = "%all_json%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ALL_URL_ENCODED = "%all_url%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_ISO8601_TIMESTAMP = "%timestampz%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_MARKETING_CLOUD_ID = "%mcid%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_PUSH_IDENTIFIER = "%pushid%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_SDK_CACHEBUST = "%cachebust%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_SDK_VERSION = "%sdkver%";
    private static final String ADB_TEMPLATE_CALLBACK_TOKEN_UNIX_TIMESTAMP = "%timestampu%";
    private static final char ADB_TEMPLATE_TOKEN_END = '}';
    private static final char ADB_TEMPLATE_TOKEN_START = '{';
    protected static final String JSON_CONFIG_ASSETS = "assets";
    private static final String JSON_CONFIG_AUDIENCES = "audiences";
    private static final String JSON_CONFIG_END_DATE = "endDate";
    private static final String JSON_CONFIG_MESSAGE_ID = "messageId";
    private static final String JSON_CONFIG_SHOW_OFFLINE = "showOffline";
    private static final String JSON_CONFIG_SHOW_RULE = "showRule";
    private static final String JSON_CONFIG_START_DATE = "startDate";
    private static final String JSON_CONFIG_TEMPLATE = "template";
    private static final String JSON_CONFIG_TRIGGERS = "triggers";
    private static final boolean JSON_DEFAULT_SHOW_OFFLINE = false;
    private static final Long JSON_DEFAULT_START_DATE = 0L;
    private static final String MESSAGE_ENUM_STRING_UNKNOWN = "unknown";
    protected static final String MESSAGE_IMAGE_CACHE_DIR = "messageImages";
    protected static final String MESSAGE_JSON_PAYLOAD = "payload";
    private static final String MESSAGE_SHOW_RULE_STRING_ALWAYS = "always";
    private static final String MESSAGE_SHOW_RULE_STRING_ONCE = "once";
    private static final String MESSAGE_SHOW_RULE_STRING_UNTIL_CLICK = "untilClick";
    private static final String MESSAGE_TEMPLATE_STRING_ALERT = "alert";
    private static final String MESSAGE_TEMPLATE_STRING_CALLBACK = "callback";
    private static final String MESSAGE_TEMPLATE_STRING_FULLSCREEN = "fullscreen";
    private static final String MESSAGE_TEMPLATE_STRING_LOCAL_NOTIFICATION = "local";
    private static final String MESSAGE_TEMPLATE_STRING_OPEN_URL = "openUrl";
    private static final String MESSAGE_TEMPLATE_STRING_PII = "pii";
    private static final String SHARED_PREFERENCES_BLACK_LIST = "messagesBlackList";
    private static HashMap<String, Integer> _blacklist;
    private static final Object _blacklistMutex = new Object();
    private static final Map<String, Class> _messageTypeDictionary = new HashMap<String, Class>() {
        {
            put("local", MessageLocalNotification.class);
            put(Message.MESSAGE_TEMPLATE_STRING_ALERT, MessageAlert.class);
            put(Message.MESSAGE_TEMPLATE_STRING_FULLSCREEN, MessageFullScreen.class);
            put(Message.MESSAGE_TEMPLATE_STRING_CALLBACK, MessageTemplateCallback.class);
            put(Message.MESSAGE_TEMPLATE_STRING_PII, MessageTemplatePii.class);
            put(Message.MESSAGE_TEMPLATE_STRING_OPEN_URL, MessageOpenURL.class);
        }
    };
    private static final Map<String, Messages.MessageShowRule> _showRuleEnumDictionary = new HashMap<String, Messages.MessageShowRule>() {
        {
            put("unknown", Messages.MessageShowRule.MESSAGE_SHOW_RULE_UNKNOWN);
            put(Message.MESSAGE_SHOW_RULE_STRING_ALWAYS, Messages.MessageShowRule.MESSAGE_SHOW_RULE_ALWAYS);
            put(Message.MESSAGE_SHOW_RULE_STRING_ONCE, Messages.MessageShowRule.MESSAGE_SHOW_RULE_ONCE);
            put(Message.MESSAGE_SHOW_RULE_STRING_UNTIL_CLICK, Messages.MessageShowRule.MESSAGE_SHOW_RULE_UNTIL_CLICK);
        }
    };
    private static final boolean[] tokenDataMask = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true, false, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private HashMap<String, Object> _allVars;
    protected ArrayList<ArrayList<String>> assets;
    protected ArrayList<MessageMatcher> audiences;
    protected Date endDate;
    protected boolean isVisible;
    protected String messageId;
    protected int orientationWhenShown;
    private final SecureRandom randomGen = new SecureRandom();
    protected boolean showOffline;
    protected Messages.MessageShowRule showRule;
    protected Date startDate;
    protected ArrayList<MessageMatcher> triggers;

    Message() {
    }

    protected static Message messageWithJsonObject(JSONObject jSONObject) {
        try {
            Message message = (Message) _messageTypeDictionary.get(jSONObject.getString("template")).newInstance();
            if (message.initWithPayloadObject(jSONObject)) {
                return message;
            }
            return null;
        } catch (JSONException unused) {
            StaticMethods.logWarningFormat("Messages - template is required for in-app message", new Object[0]);
            return null;
        } catch (NullPointerException unused2) {
            StaticMethods.logWarningFormat("Messages - invalid template specified for message (%s)", "");
            return null;
        } catch (IllegalAccessException e) {
            StaticMethods.logWarningFormat("Messages - unable to create instance of message (%s)", e.getMessage());
            return null;
        } catch (InstantiationException e2) {
            StaticMethods.logWarningFormat("Messages - unable to create instance of message (%s)", e2.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean initWithPayloadObject(JSONObject jSONObject) {
        if (!(jSONObject == null || jSONObject.length() == 0)) {
            try {
                String string = jSONObject.getString("messageId");
                this.messageId = string;
                if (string.length() <= 0) {
                    StaticMethods.logWarningFormat("Messages - Unable to create message, messageId is empty", new Object[0]);
                    return false;
                }
                try {
                    String string2 = jSONObject.getString(JSON_CONFIG_SHOW_RULE);
                    Messages.MessageShowRule messageShowRuleFromString = messageShowRuleFromString(string2);
                    this.showRule = messageShowRuleFromString;
                    if (messageShowRuleFromString == null || messageShowRuleFromString == Messages.MessageShowRule.MESSAGE_SHOW_RULE_UNKNOWN) {
                        StaticMethods.logWarningFormat("Messages - Unable to create message \"%s\", showRule not valid (%s)", this.messageId, string2);
                        return false;
                    }
                    try {
                        this.startDate = new Date(jSONObject.getLong(JSON_CONFIG_START_DATE) * 1000);
                    } catch (JSONException unused) {
                        StaticMethods.logDebugFormat("Messages - Tried to read startDate from message \"%s\" but none found. Using default value", this.messageId);
                        this.startDate = new Date(JSON_DEFAULT_START_DATE.longValue() * 1000);
                    }
                    try {
                        this.endDate = new Date(jSONObject.getLong(JSON_CONFIG_END_DATE) * 1000);
                    } catch (JSONException unused2) {
                        StaticMethods.logDebugFormat("Messages - Tried to read endDate from message \"%s\" but none found. Using default value", this.messageId);
                    }
                    try {
                        this.showOffline = jSONObject.getBoolean(JSON_CONFIG_SHOW_OFFLINE);
                    } catch (JSONException unused3) {
                        StaticMethods.logDebugFormat("Messages - Tried to read showOffline from message \"%s\" but none found. Using default value", this.messageId);
                        this.showOffline = false;
                    }
                    this.audiences = new ArrayList<>();
                    try {
                        JSONArray jSONArray = jSONObject.getJSONArray(JSON_CONFIG_AUDIENCES);
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            this.audiences.add(MessageMatcher.messageMatcherWithJsonObject(jSONArray.getJSONObject(i)));
                        }
                    } catch (JSONException e) {
                        StaticMethods.logDebugFormat("Messages - failed to read audience for message \"%s\", error: %s", this.messageId, e.getMessage());
                    }
                    this.triggers = new ArrayList<>();
                    try {
                        JSONArray jSONArray2 = jSONObject.getJSONArray(JSON_CONFIG_TRIGGERS);
                        int length2 = jSONArray2.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            this.triggers.add(MessageMatcher.messageMatcherWithJsonObject(jSONArray2.getJSONObject(i2)));
                        }
                    } catch (JSONException e2) {
                        StaticMethods.logDebugFormat("Messages - failed to read trigger for message \"%s\", error: %s", this.messageId, e2.getMessage());
                    }
                    if (this.triggers.size() <= 0) {
                        StaticMethods.logWarningFormat("Messages - Unable to load message \"%s\" - at least one valid trigger is required for a message", this.messageId);
                        return false;
                    }
                    this.isVisible = false;
                    return true;
                } catch (JSONException unused4) {
                    StaticMethods.logWarningFormat("Messages - Unable to create message \"%s\", showRule is required", this.messageId);
                    return false;
                }
            } catch (JSONException unused5) {
                StaticMethods.logWarningFormat("Messages - Unable to create message, messageId is required", new Object[0]);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void blacklist() {
        synchronized (_blacklistMutex) {
            if (_blacklist == null) {
                _blacklist = loadBlacklist();
            }
            _blacklist.put(this.messageId, Integer.valueOf(this.showRule.getValue()));
            StaticMethods.logDebugFormat("Messages - adding message \"%s\" to blacklist", this.messageId);
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.putString(SHARED_PREFERENCES_BLACK_LIST, stringFromMap(_blacklist));
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Messages - Error persisting blacklist map (%s).", e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void removeFromBlacklist() {
        if (isBlacklisted()) {
            synchronized (_blacklistMutex) {
                _blacklist.remove(this.messageId);
                StaticMethods.logDebugFormat("Messages - removing message \"%s\" from blacklist", this.messageId);
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.putString(SHARED_PREFERENCES_BLACK_LIST, stringFromMap(_blacklist));
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException e) {
                    StaticMethods.logErrorFormat("Messages - Error persisting blacklist map (%s).", e.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isBlacklisted() {
        boolean z;
        synchronized (_blacklistMutex) {
            if (_blacklist == null) {
                _blacklist = loadBlacklist();
            }
            z = _blacklist.get(this.messageId) != null;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Integer> loadBlacklist() {
        try {
            String string = StaticMethods.getSharedPreferences().getString(SHARED_PREFERENCES_BLACK_LIST, (String) null);
            if (string == null) {
                return new HashMap<>();
            }
            return mapFromString(string);
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logDebugFormat("Messaging - Unable to get shared preferences while loading blacklist. (%s)", e.getMessage());
            return new HashMap<>();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowForVariables(Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        HashMap hashMap;
        if (this.isVisible && this.orientationWhenShown != StaticMethods.getCurrentOrientation() && (this instanceof MessageAlert)) {
            return true;
        }
        if (Messages.getCurrentMessage() != null && !(this instanceof MessageLocalNotification) && !(this instanceof MessageTemplateCallback)) {
            return false;
        }
        if (map2 == null) {
            hashMap = new HashMap();
        }
        if (map != null) {
            hashMap.putAll(map);
        }
        if (hashMap.size() <= 0) {
            return false;
        }
        hashMap.putAll(getExpansionTokensForVariables(hashMap));
        if (map3 != null) {
            hashMap.putAll(map3);
        }
        this._allVars = new HashMap<>(hashMap);
        if (isBlacklisted()) {
            return false;
        }
        if (!MobileConfig.getInstance().networkConnectivity() && MobileConfig.getInstance().reachabilityChecksEnabled() && !this.showOffline) {
            return false;
        }
        Date date = new Date(StaticMethods.getTimeSince1970() * 1000);
        if (date.before(this.startDate)) {
            return false;
        }
        Date date2 = this.endDate;
        if (date2 != null && date.after(date2)) {
            return false;
        }
        Iterator<MessageMatcher> it = this.audiences.iterator();
        while (it.hasNext()) {
            if (!it.next().matchesInMaps(map3)) {
                return false;
            }
        }
        Map<String, Object> cleanContextDataDictionary = StaticMethods.cleanContextDataDictionary(map2);
        Iterator<MessageMatcher> it2 = this.triggers.iterator();
        while (it2.hasNext()) {
            if (!it2.next().matchesInMaps(map, cleanContextDataDictionary)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void messageTriggered() {
        HashMap hashMap = new HashMap();
        hashMap.put("a.message.id", this.messageId);
        hashMap.put("a.message.triggered", 1);
        AnalyticsTrackInternal.trackInternal("In-App Message", hashMap, StaticMethods.getTimeSince1970());
    }

    /* access modifiers changed from: protected */
    public void show() {
        this.orientationWhenShown = StaticMethods.getCurrentOrientation();
        if (this.showRule == Messages.MessageShowRule.MESSAGE_SHOW_RULE_ONCE) {
            blacklist();
        }
        if ((this instanceof MessageAlert) || (this instanceof MessageFullScreen)) {
            Messages.setCurrentMessage(this);
        }
    }

    /* access modifiers changed from: protected */
    public void viewed() {
        HashMap hashMap = new HashMap();
        hashMap.put("a.message.id", this.messageId);
        hashMap.put("a.message.viewed", 1);
        AnalyticsTrackInternal.trackInternal("In-App Message", hashMap, StaticMethods.getTimeSince1970());
        Messages.setCurrentMessage((Message) null);
    }

    /* access modifiers changed from: protected */
    public void clickedThrough() {
        HashMap hashMap = new HashMap();
        hashMap.put("a.message.id", this.messageId);
        hashMap.put("a.message.clicked", 1);
        AnalyticsTrackInternal.trackInternal("In-App Message", hashMap, StaticMethods.getTimeSince1970());
        if (this.showRule == Messages.MessageShowRule.MESSAGE_SHOW_RULE_UNTIL_CLICK) {
            blacklist();
        }
        Messages.setCurrentMessage((Message) null);
    }

    private HashMap<String, Object> getExpansionTokensForVariables(Map<String, Object> map) {
        String str;
        HashMap<String, Object> hashMap = new HashMap<>(9);
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_SDK_VERSION, "4.17.5-AN");
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_SDK_CACHEBUST, String.valueOf(this.randomGen.nextInt(100000000)));
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_ADVERTISING_IDENTIFIER, StaticMethods.getAdvertisingIdentifier());
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_UNIX_TIMESTAMP, String.valueOf(StaticMethods.getTimeSince1970()));
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_ISO8601_TIMESTAMP, StaticMethods.getIso8601Date());
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_PUSH_IDENTIFIER, StaticMethods.getPushIdentifier());
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_MARKETING_CLOUD_ID, VisitorIDService.sharedInstance().getMarketingCloudID() != null ? VisitorIDService.sharedInstance().getMarketingCloudID() : "");
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            String str2 = (String) next.getKey();
            if (str2 != null) {
                Object value = next.getValue();
                if (value == null) {
                    str = "";
                } else {
                    str = value.toString();
                }
                arrayList.add(StaticMethods.URLEncode(str2) + "=" + StaticMethods.URLEncode(str));
                hashMap2.put(str2, str);
            }
        }
        hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_ALL_URL_ENCODED, TextUtils.join("&", arrayList));
        try {
            String jSONObject = new JSONObject(hashMap2).toString();
            if (jSONObject.length() > 0) {
                hashMap.put(ADB_TEMPLATE_CALLBACK_TOKEN_ALL_JSON_ENCODED, jSONObject);
            }
        } catch (NullPointerException e) {
            StaticMethods.logDebugFormat("Data Callback - unable to create json string for vars:  (%s)", e.getLocalizedMessage());
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> buildExpansionsForTokens(ArrayList<String> arrayList, boolean z) {
        String str;
        HashMap<String, String> hashMap = new HashMap<>(arrayList.size());
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            Object obj = this._allVars.get(next.substring(1, next.length() - 1).toLowerCase());
            if (obj == null) {
                str = "";
            } else {
                str = obj.toString();
            }
            if (z) {
                str = StaticMethods.URLEncode(str);
            }
            hashMap.put(next, str);
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public ArrayList<String> findTokensForExpansion(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>(32);
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (str.charAt(i) == '{') {
                int i2 = i + 1;
                while (i2 < length && str.charAt(i2) != '}') {
                    i2++;
                }
                if (i2 == length) {
                    break;
                }
                String substring = str.substring(i, i2 + 1);
                if (tokenIsValid(substring.substring(1, substring.length() - 1))) {
                    arrayList.add(substring);
                    i = i2;
                }
            }
            i++;
        }
        return arrayList;
    }

    private boolean tokenIsValid(String str) {
        try {
            for (byte b : str.getBytes("UTF-8")) {
                if (!tokenDataMask[b & 255]) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            StaticMethods.logErrorFormat("Data Callback - Unable to validate token (%s)", e.getLocalizedMessage());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public String description() {
        return "Message ID: " + this.messageId + "; Show Rule: " + this.showRule.toString() + "; Blacklisted: " + isBlacklisted();
    }

    private static Messages.MessageShowRule messageShowRuleFromString(String str) {
        return _showRuleEnumDictionary.get(str);
    }

    private HashMap<String, Integer> mapFromString(String str) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, Integer.valueOf(jSONObject.getInt(next)));
            }
        } catch (JSONException e) {
            StaticMethods.logErrorFormat("Messages- Unable to deserialize blacklist(%s)", e.getMessage());
        }
        return hashMap;
    }

    private String stringFromMap(Map<String, Integer> map) {
        return new JSONObject(map).toString();
    }
}
