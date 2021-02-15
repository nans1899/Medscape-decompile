package bo.app;

import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public final class ch implements cb, IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(ch.class);
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private String g;
    private final Boolean h;
    private final AppboyConfigurationProvider i;

    public static ch a(AppboyConfigurationProvider appboyConfigurationProvider, JSONObject jSONObject) {
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        Boolean bool = null;
        for (v vVar : v.values()) {
            switch (AnonymousClass1.a[vVar.ordinal()]) {
                case 1:
                    str5 = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 2:
                    str2 = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 3:
                    str = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 4:
                    str6 = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 5:
                    str4 = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 6:
                    str3 = StringUtils.emptyToNull(jSONObject.optString(vVar.a()));
                    break;
                case 7:
                    if (!jSONObject.has(vVar.a())) {
                        break;
                    } else {
                        bool = Boolean.valueOf(jSONObject.optBoolean(vVar.a(), true));
                        break;
                    }
                default:
                    AppboyLogger.e(a, "Unknown key encountered in Device createFromJson " + vVar);
                    break;
            }
        }
        return new ch(appboyConfigurationProvider, str, str2, str3, str4, str5, str6, bool);
    }

    /* renamed from: bo.app.ch$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                bo.app.v[] r0 = bo.app.v.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                bo.app.v r1 = bo.app.v.TIMEZONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                bo.app.v r1 = bo.app.v.CARRIER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                bo.app.v r1 = bo.app.v.ANDROID_VERSION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                bo.app.v r1 = bo.app.v.RESOLUTION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                bo.app.v r1 = bo.app.v.LOCALE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                bo.app.v r1 = bo.app.v.MODEL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0054 }
                bo.app.v r1 = bo.app.v.NOTIFICATIONS_ENABLED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.ch.AnonymousClass1.<clinit>():void");
        }
    }

    public ch(AppboyConfigurationProvider appboyConfigurationProvider, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool) {
        this.i = appboyConfigurationProvider;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.g = str5;
        this.f = str6;
        this.h = bool;
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            a(this.i, jSONObject, v.ANDROID_VERSION, this.b);
            a(this.i, jSONObject, v.CARRIER, this.c);
            a(this.i, jSONObject, v.MODEL, this.d);
            a(this.i, jSONObject, v.RESOLUTION, this.f);
            a(this.i, jSONObject, v.LOCALE, this.e);
            a(this.i, jSONObject, v.NOTIFICATIONS_ENABLED, this.h);
            if (!StringUtils.isNullOrBlank(this.g)) {
                a(this.i, jSONObject, v.TIMEZONE, this.g);
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating device Json.", e2);
        }
        return jSONObject;
    }

    public boolean b() {
        return forJsonPut().length() == 0;
    }

    static void a(AppboyConfigurationProvider appboyConfigurationProvider, JSONObject jSONObject, v vVar, Object obj) {
        if (!appboyConfigurationProvider.getIsDeviceObjectWhitelistEnabled() || appboyConfigurationProvider.getDeviceObjectWhitelist().contains(vVar)) {
            jSONObject.putOpt(vVar.a(), obj);
        }
    }
}
