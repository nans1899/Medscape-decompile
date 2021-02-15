package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.enums.Gender;
import com.appboy.enums.Month;
import com.appboy.enums.NotificationSubscriptionType;
import com.appboy.models.outgoing.AttributionData;
import com.appboy.models.outgoing.FacebookUser;
import com.appboy.models.outgoing.TwitterUser;
import com.appboy.support.AppboyLogger;
import com.appboy.support.CustomAttributeValidationUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import com.facebook.AccessToken;
import com.facebook.appevents.UserDataStore;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.medscape.android.settings.Settings;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class dt extends dd<ck> {
    private static final String c = AppboyLogger.getAppboyLogTag(dt.class);
    final SharedPreferences a;
    final SharedPreferences b;
    private final bu d;
    private final dq e;
    private final String f;
    private final dp g;

    public dt(Context context, bu buVar, dq dqVar, dp dpVar) {
        this(context, (String) null, (String) null, buVar, dqVar, dpVar);
    }

    public dt(Context context, String str, String str2, bu buVar, dq dqVar, dp dpVar) {
        String cacheFileSuffix = StringUtils.getCacheFileSuffix(context, str, str2);
        this.a = context.getSharedPreferences("com.appboy.storage.user_cache.v3" + cacheFileSuffix, 0);
        this.b = context.getSharedPreferences("com.appboy.storage.user_cache.push_token_store" + cacheFileSuffix, 0);
        this.d = buVar;
        this.e = dqVar;
        this.f = str;
        this.g = dpVar;
    }

    public synchronized void a(String str) {
        c(AccessToken.USER_ID_KEY, str);
    }

    public synchronized void b(String str) {
        c("first_name", str);
    }

    public synchronized void c(String str) {
        c("last_name", str);
    }

    public synchronized boolean d(String str) {
        if (str != null) {
            try {
                str = str.trim();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (str != null) {
            if (!ValidationUtils.isValidEmailAddress(str)) {
                String str2 = c;
                AppboyLogger.w(str2, "Email address is not valid: " + str);
                return false;
            }
        }
        return c("email", str);
    }

    public synchronized void a(Gender gender) {
        if (gender == null) {
            c(Settings.GENDER, (Object) null);
        } else {
            c(Settings.GENDER, gender.forJsonPut());
        }
    }

    public synchronized boolean a(int i, Month month, int i2) {
        if (month == null) {
            AppboyLogger.w(c, "Month cannot be null.");
            return false;
        }
        return c("dob", du.a(du.a(i, month.getValue(), i2), u.SHORT));
    }

    public synchronized void e(String str) {
        c(UserDataStore.COUNTRY, str);
    }

    public synchronized void f(String str) {
        c("home_city", str);
    }

    public synchronized void g(String str) {
        c("language", str);
    }

    public synchronized void a(NotificationSubscriptionType notificationSubscriptionType) {
        if (notificationSubscriptionType == null) {
            c("email_subscribe", (Object) null);
        } else {
            c("email_subscribe", notificationSubscriptionType.forJsonPut());
        }
    }

    public synchronized void b(NotificationSubscriptionType notificationSubscriptionType) {
        if (notificationSubscriptionType == null) {
            c("push_subscribe", (Object) null);
        } else {
            c("push_subscribe", notificationSubscriptionType.forJsonPut());
        }
    }

    public synchronized boolean h(String str) {
        if (str != null) {
            try {
                str = str.trim();
            } catch (Throwable th) {
                throw th;
            }
        }
        if (str != null) {
            if (!ValidationUtils.isValidPhoneNumber(str)) {
                String str2 = c;
                AppboyLogger.w(str2, "Phone number contains invalid characters (allowed are digits, spaces, or any of the following +.-()): " + str);
                return false;
            }
        }
        return c(PlaceFields.PHONE, str);
    }

    public synchronized void a(TwitterUser twitterUser) {
        a("twitter", twitterUser != null ? twitterUser.forJsonPut() : null);
    }

    public synchronized void a(FacebookUser facebookUser) {
        a("facebook", facebookUser != null ? facebookUser.forJsonPut() : null);
    }

    public synchronized void a(AttributionData attributionData) {
        a("ab_install_attribution", attributionData != null ? attributionData.forJsonPut() : null);
    }

    public synchronized void i(String str) {
        c(MessengerShareContentUtility.IMAGE_URL, str);
    }

    public synchronized boolean a(String str, Object obj) {
        if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.e.i())) {
            AppboyLogger.w(c, "Custom attribute key cannot be null.");
            return false;
        }
        String ensureAppboyFieldLength = ValidationUtils.ensureAppboyFieldLength(str);
        if (!(obj instanceof Boolean) && !(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Long)) {
            if (!(obj instanceof Double)) {
                if (obj instanceof String) {
                    return b(ensureAppboyFieldLength, ValidationUtils.ensureAppboyFieldLength((String) obj));
                } else if (obj instanceof Date) {
                    return b(ensureAppboyFieldLength, du.a((Date) obj, u.LONG));
                } else if (obj instanceof String[]) {
                    return b(ensureAppboyFieldLength, ed.a((T[]) (String[]) obj));
                } else {
                    String str2 = c;
                    AppboyLogger.w(str2, "Could not add unsupported custom attribute type with key: " + ensureAppboyFieldLength + " and value: " + obj);
                    return false;
                }
            }
        }
        return b(ensureAppboyFieldLength, obj);
    }

    public synchronized boolean a(String str, long j) {
        return a(str, (Object) du.a(j));
    }

    public synchronized boolean j(String str) {
        if (!CustomAttributeValidationUtils.isValidCustomAttributeKey(str, this.e.i())) {
            AppboyLogger.w(c, "Custom attribute key cannot be null.");
            return false;
        }
        return b(ValidationUtils.ensureAppboyFieldLength(str), JSONObject.NULL);
    }

    public synchronized void d() {
        AppboyLogger.v(c, "Push token cache cleared.");
        this.b.edit().clear().apply();
    }

    /* access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        String a2 = this.d.a();
        if (a2 == null) {
            AppboyLogger.d(c, "Cannot add null push token to attributes object.");
            return;
        }
        String string = this.b.getString("push_token", (String) null);
        if (string == null || !a2.equals(string)) {
            jSONObject.put("push_token", a2);
        }
    }

    /* renamed from: e */
    public ck a() {
        if (!StringUtils.isNullOrEmpty(this.f)) {
            a(this.f);
        }
        JSONObject f2 = f();
        try {
            a(f2);
        } catch (JSONException e2) {
            AppboyLogger.e(c, "Couldn't add push token to outbound json", e2);
        }
        this.a.edit().clear().apply();
        return new ck(f2);
    }

    /* access modifiers changed from: package-private */
    public void a(ck ckVar, boolean z) {
        if (ckVar == null || ckVar.a() == null) {
            AppboyLogger.d(c, "Tried to confirm with a null outbound user. Doing nothing.");
            return;
        }
        JSONObject a2 = ckVar.a();
        if (!z) {
            JSONObject f2 = f();
            JSONObject a3 = ed.a(a2, f2);
            a3.remove("push_token");
            JSONObject optJSONObject = f2.optJSONObject("custom");
            JSONObject optJSONObject2 = a2.optJSONObject("custom");
            if (optJSONObject != null && optJSONObject2 != null) {
                try {
                    a3.put("custom", ed.a(optJSONObject2, optJSONObject));
                } catch (JSONException e2) {
                    AppboyLogger.w(c, "Failed to add merged custom attributes back to user object.", e2);
                }
            } else if (optJSONObject != null) {
                a3.put("custom", optJSONObject);
            } else if (optJSONObject2 != null) {
                a3.put("custom", optJSONObject2);
            }
            SharedPreferences.Editor edit = this.a.edit();
            edit.putString("user_cache_attributes_object", a3.toString());
            edit.apply();
        } else if (a2.has("push_token")) {
            SharedPreferences.Editor edit2 = this.b.edit();
            edit2.putString("push_token", a2.optString("push_token"));
            edit2.apply();
        }
    }

    /* access modifiers changed from: package-private */
    public JSONObject f() {
        String string = this.a.getString("user_cache_attributes_object", (String) null);
        if (string == null) {
            return new JSONObject();
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e2) {
            String str = c;
            AppboyLogger.e(str, "Failed to load user object json from prefs with json string: " + string, e2);
            return new JSONObject();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject g() {
        /*
            r3 = this;
            org.json.JSONObject r0 = r3.f()
            java.lang.String r1 = "custom"
            boolean r2 = r0.has(r1)
            if (r2 == 0) goto L_0x0019
            org.json.JSONObject r0 = r0.getJSONObject(r1)     // Catch:{ JSONException -> 0x0011 }
            goto L_0x001a
        L_0x0011:
            r0 = move-exception
            java.lang.String r1 = c
            java.lang.String r2 = "Could not create custom attributes json object from preferences."
            com.appboy.support.AppboyLogger.e(r1, r2, r0)
        L_0x0019:
            r0 = 0
        L_0x001a:
            if (r0 != 0) goto L_0x0021
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
        L_0x0021:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.dt.g():org.json.JSONObject");
    }

    private boolean b(JSONObject jSONObject) {
        if (this.g.a()) {
            AppboyLogger.w(c, "SDK is disabled. Not writing to user cache.");
            return false;
        }
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString("user_cache_attributes_object", jSONObject.toString());
        edit.apply();
        return true;
    }

    private boolean c(String str, Object obj) {
        JSONObject f2 = f();
        if (obj == null) {
            try {
                f2.put(str, JSONObject.NULL);
            } catch (JSONException unused) {
                String str2 = c;
                AppboyLogger.w(str2, "Failed to write to user object json from prefs with key: [" + str + "] value: [" + obj + "] ");
                return false;
            }
        } else {
            f2.put(str, obj);
        }
        return b(f2);
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, JSONObject jSONObject) {
        JSONObject f2 = f();
        if (jSONObject == null) {
            try {
                f2.put(str, JSONObject.NULL);
            } catch (JSONException unused) {
                String str2 = c;
                AppboyLogger.w(str2, "Failed to write to user object json from prefs with key: [" + str + "] value: [" + jSONObject + "] ");
                return false;
            }
        } else {
            JSONObject optJSONObject = f2.optJSONObject(str);
            if (optJSONObject != null) {
                f2.put(str, ed.a(optJSONObject, jSONObject));
            } else {
                f2.put(str, jSONObject);
            }
        }
        return b(f2);
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str, Object obj) {
        JSONObject g2 = g();
        if (obj == null) {
            try {
                g2.put(str, JSONObject.NULL);
            } catch (JSONException e2) {
                String str2 = c;
                AppboyLogger.w(str2, "Could not write to custom attributes json object with key: [" + str + "] value: [" + obj + "] ", e2);
                return false;
            }
        } else {
            g2.put(str, obj);
        }
        return c("custom", g2);
    }
}
