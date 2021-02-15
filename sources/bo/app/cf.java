package bo.app;

import com.appboy.Constants;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.models.MessageButton;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import com.medscape.android.cache.Cache;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cf implements bz {
    private static final String a = AppboyLogger.getAppboyLogTag(cf.class);
    private final w b;
    private final JSONObject c;
    private final double d;
    private final String e;
    private String f;
    private cd g;

    private cf(w wVar, JSONObject jSONObject) {
        this(wVar, jSONObject, du.b());
    }

    private cf(w wVar, JSONObject jSONObject, double d2) {
        this(wVar, jSONObject, d2, UUID.randomUUID().toString());
    }

    private cf(w wVar, JSONObject jSONObject, double d2, String str) {
        this.f = null;
        this.g = null;
        if (jSONObject == null) {
            throw new NullPointerException("Event data cannot be null");
        } else if (wVar.forJsonPut() != null) {
            this.b = wVar;
            this.c = jSONObject;
            this.d = d2;
            this.e = str;
        } else {
            throw new NullPointerException("Event type cannot be null");
        }
    }

    private cf(w wVar, JSONObject jSONObject, double d2, String str, String str2, String str3) {
        this.f = null;
        this.g = null;
        if (jSONObject == null) {
            throw new NullPointerException("Event data cannot be null");
        } else if (wVar.forJsonPut() != null) {
            this.b = wVar;
            this.c = jSONObject;
            this.d = d2;
            this.e = str;
            this.f = str2;
            if (str3 != null) {
                this.g = cd.a(str3);
            }
        } else {
            throw new NullPointerException("Event type cannot be null");
        }
    }

    public static cf a(String str, AppboyProperties appboyProperties) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID, StringUtils.checkNotNullOrEmpty(str));
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put("p", appboyProperties.forJsonPut());
        }
        return new cf(w.CUSTOM_EVENT, jSONObject);
    }

    public static cf a(String str, String str2, BigDecimal bigDecimal, int i, AppboyProperties appboyProperties) {
        BigDecimal a2 = ef.a(bigDecimal);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pid", str);
        jSONObject.put("c", str2);
        jSONObject.put("p", a2.doubleValue());
        jSONObject.put("q", i);
        if (appboyProperties != null && appboyProperties.size() > 0) {
            jSONObject.put("pr", appboyProperties.forJsonPut());
        }
        return new cf(w.PURCHASE, jSONObject);
    }

    public static cf a(ca caVar) {
        return new cf(w.LOCATION_RECORDED, (JSONObject) caVar.forJsonPut());
    }

    public static cf a(Throwable th, cd cdVar) {
        String b2 = b(th, cdVar);
        String a2 = a(th);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", b2 + IOUtils.LINE_SEPARATOR_UNIX + a2);
        return new cf(w.INTERNAL_ERROR, jSONObject);
    }

    public static cf a(av avVar, cd cdVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("nop", true);
        String b2 = b((Throwable) avVar, cdVar);
        String a2 = a((Throwable) avVar);
        jSONObject.put("e", b2 + IOUtils.LINE_SEPARATOR_UNIX + a2);
        return new cf(w.INTERNAL_ERROR, jSONObject);
    }

    public static cf a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_CONTENT_KEY, str);
        jSONObject.put("l", str2);
        return new cf(w.USER_ALIAS, jSONObject);
    }

    public static cf b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        jSONObject.put(Constants.APPBOY_PUSH_CONTENT_KEY, str2);
        return new cf(w.PUSH_STORY_PAGE_CLICK, jSONObject);
    }

    public static cf b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        return new cf(w.PUSH_NOTIFICATION_TRACKING, jSONObject);
    }

    public static cf c(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        jSONObject.put(Constants.APPBOY_PUSH_CONTENT_KEY, str2);
        return new cf(w.PUSH_NOTIFICATION_ACTION_TRACKING, jSONObject);
    }

    public static cf c(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.NEWS_FEED_CARD_IMPRESSION, jSONObject);
    }

    public static cf d(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.NEWS_FEED_CARD_CLICK, jSONObject);
    }

    public static cf e(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.CONTENT_CARDS_IMPRESSION, jSONObject);
    }

    public static cf f(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.CONTENT_CARDS_CONTROL_IMPRESSION, jSONObject);
    }

    public static cf g(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.CONTENT_CARDS_CLICK, jSONObject);
    }

    public static cf h(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(str);
        jSONObject.put("ids", jSONArray);
        return new cf(w.CONTENT_CARDS_DISMISS, jSONObject);
    }

    public static cf d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("geo_id", str);
        jSONObject.put("event_type", str2);
        return new cf(w.GEOFENCE, jSONObject);
    }

    public static cf a(String str, String str2, String str3) {
        return new cf(w.INAPP_MESSAGE_CONTROL_IMPRESSION, d(str, str2, str3));
    }

    public static cf b(String str, String str2, String str3) {
        return new cf(w.INAPP_MESSAGE_IMPRESSION, d(str, str2, str3));
    }

    public static cf c(String str, String str2, String str3) {
        return new cf(w.INAPP_MESSAGE_CLICK, d(str, str2, str3));
    }

    public static cf a(String str, String str2, String str3, MessageButton messageButton) {
        return new cf(w.INAPP_MESSAGE_BUTTON_CLICK, a(str, str2, str3, a(messageButton), (InAppMessageFailureType) null));
    }

    public static cf a(String str, String str2, String str3, String str4) {
        return new cf(w.INAPP_MESSAGE_BUTTON_CLICK, a(str, str2, str3, str4, (InAppMessageFailureType) null));
    }

    public static cf a(String str, String str2, String str3, InAppMessageFailureType inAppMessageFailureType) {
        return new cf(w.INAPP_MESSAGE_DISPLAY_FAILURE, b(str, str2, str3, inAppMessageFailureType));
    }

    static JSONObject d(String str, String str2, String str3) {
        return a(str, str2, str3, (String) null, (InAppMessageFailureType) null);
    }

    static JSONObject b(String str, String str2, String str3, InAppMessageFailureType inAppMessageFailureType) {
        return a(str, str2, str3, (String) null, inAppMessageFailureType);
    }

    static JSONObject a(String str, String str2, String str3, String str4, InAppMessageFailureType inAppMessageFailureType) {
        JSONObject jSONObject = new JSONObject();
        if (!StringUtils.isNullOrEmpty(str)) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("campaign_ids", jSONArray);
        }
        if (!StringUtils.isNullOrEmpty(str2)) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(str2);
            jSONObject.put("card_ids", jSONArray2);
        }
        if (!StringUtils.isNullOrEmpty(str3)) {
            JSONArray jSONArray3 = new JSONArray();
            jSONArray3.put(str3);
            jSONObject.put("trigger_ids", jSONArray3);
        }
        if (!StringUtils.isNullOrEmpty(str4)) {
            jSONObject.put("bid", str4);
        }
        if (inAppMessageFailureType != null) {
            String forJsonPut = inAppMessageFailureType.forJsonPut();
            if (!StringUtils.isNullOrEmpty(forJsonPut)) {
                jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, forJsonPut);
            }
        }
        return jSONObject;
    }

    public static String a(MessageButton messageButton) {
        if (messageButton != null) {
            return String.valueOf(messageButton.getId());
        }
        return null;
    }

    public static cf h() {
        return i("content_cards_displayed");
    }

    public static cf i() {
        return i("feed_displayed");
    }

    public static cf j() {
        return i("feedback_displayed");
    }

    public static cf i(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID, str);
        return new cf(w.INTERNAL, jSONObject);
    }

    public static cf a(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("value", i);
        return new cf(w.INCREMENT, jSONObject);
    }

    public static cf e(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("value", str2);
        return new cf(w.ADD_TO_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static cf f(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("value", str2);
        return new cf(w.REMOVE_FROM_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static cf a(String str, String[] strArr) {
        JSONArray jSONArray = strArr == null ? null : new JSONArray();
        if (strArr != null) {
            for (String put : strArr) {
                jSONArray.put(put);
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        if (strArr == null) {
            jSONObject.put("value", JSONObject.NULL);
        } else {
            jSONObject.put("value", jSONArray);
        }
        return new cf(w.SET_CUSTOM_ATTRIBUTE_ARRAY, jSONObject);
    }

    public static cf k() {
        return new cf(w.SESSION_START, new JSONObject());
    }

    public static cf a(long j) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE, j);
        return new cf(w.SESSION_END, jSONObject);
    }

    public static cf j(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cid", str);
        return new cf(w.PUSH_DELIVERY, jSONObject);
    }

    public static cf a(String str, double d2, double d3) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        jSONObject.put("latitude", d2);
        jSONObject.put("longitude", d3);
        return new cf(w.LOCATION_CUSTOM_ATTRIBUTE_ADD, jSONObject);
    }

    public static cf k(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        return new cf(w.LOCATION_CUSTOM_ATTRIBUTE_REMOVE, jSONObject);
    }

    public static bz g(String str, String str2) {
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("name");
        w a2 = w.a(string);
        if (a2 != null) {
            return new cf(a2, jSONObject.getJSONObject("data"), jSONObject.getDouble(Cache.Caches.TIME), str2, jSONObject.optString(AccessToken.USER_ID_KEY, (String) null), jSONObject.optString("session_id", (String) null));
        }
        throw new IllegalArgumentException("Cannot parse eventType " + string + ". Event json: " + jSONObject);
    }

    public static cf a(String str, String str2, double d2, String str3, String str4, String str5) {
        w a2 = w.a(str);
        if (a2 != null) {
            return new cf(a2, new JSONObject(str2), d2, str3, str4, str5);
        }
        throw new IllegalArgumentException("Cannot parse eventType " + str);
    }

    public w b() {
        return this.b;
    }

    public JSONObject c() {
        return this.c;
    }

    public double a() {
        return this.d;
    }

    public String e() {
        return forJsonPut().toString();
    }

    static String b(Throwable th, cd cdVar) {
        StringBuilder sb = new StringBuilder();
        String th2 = th.toString();
        if (th2.length() > 5000) {
            th2 = th2.substring(0, 5000);
        }
        sb.append("exception_class: ");
        sb.append(th2);
        sb.append(",");
        sb.append("session_id: ");
        sb.append(cdVar != null ? cdVar.toString() : null);
        return sb.toString();
    }

    static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String obj = stringWriter.toString();
        return obj.length() > 5000 ? obj.substring(0, 5000) : obj;
    }

    public void a(cd cdVar) {
        if (this.g == null) {
            this.g = cdVar;
            return;
        }
        String str = a;
        AppboyLogger.d(str, "Session id can only be set once. Doing nothing. Given session id: " + cdVar);
    }

    public void a(String str) {
        if (this.f == null) {
            this.f = str;
            return;
        }
        String str2 = a;
        AppboyLogger.d(str2, "User id can only be set once. Doing nothing. Given user id: " + str);
    }

    public cd g() {
        return this.g;
    }

    public String f() {
        return this.f;
    }

    public String d() {
        return this.e;
    }

    /* renamed from: l */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.b.forJsonPut());
            jSONObject.put("data", this.c);
            jSONObject.put(Cache.Caches.TIME, this.d);
            if (!StringUtils.isNullOrEmpty(this.f)) {
                jSONObject.put(AccessToken.USER_ID_KEY, this.f);
            }
            if (this.g != null) {
                jSONObject.put("session_id", this.g.forJsonPut());
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating Braze event Json.", e2);
        }
        return jSONObject;
    }

    public String toString() {
        JSONObject l = forJsonPut();
        return (l == null || l.length() <= 0) ? "" : l.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.e.equals(((cf) obj).e);
    }

    public int hashCode() {
        return this.e.hashCode();
    }
}
