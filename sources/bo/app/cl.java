package bo.app;

import com.appboy.models.AppboyGeofence;
import com.appboy.models.IInAppMessage;
import com.appboy.models.response.ResponseError;
import com.appboy.support.AppboyLogger;
import java.util.List;
import org.json.JSONArray;

public class cl {
    private static final String a = AppboyLogger.getAppboyLogTag(cl.class);
    private final JSONArray b;
    private final cm c;
    private final IInAppMessage d;
    private final List<ek> e;
    private final cn f;
    private final List<AppboyGeofence> g;
    private final ResponseError h;

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0083 A[SYNTHETIC, Splitter:B:23:0x0083] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cl(org.json.JSONObject r6, bo.app.cv r7, bo.app.bq r8) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.String r0 = "error"
            r1 = 0
            java.lang.String r0 = r6.optString(r0, r1)
            if (r0 == 0) goto L_0x0014
            com.appboy.models.response.ResponseError r2 = new com.appboy.models.response.ResponseError
            r2.<init>(r0)
            r5.h = r2
            goto L_0x0016
        L_0x0014:
            r5.h = r1
        L_0x0016:
            java.lang.String r0 = "feed"
            org.json.JSONArray r0 = r6.optJSONArray(r0)
            if (r0 == 0) goto L_0x0021
            r5.b = r0
            goto L_0x0023
        L_0x0021:
            r5.b = r1
        L_0x0023:
            com.appboy.models.response.ResponseError r0 = r5.h
            if (r0 != 0) goto L_0x006c
            boolean r7 = r7 instanceof bo.app.cq
            if (r7 == 0) goto L_0x006c
            bo.app.cm r7 = new bo.app.cm     // Catch:{ JSONException -> 0x004d, Exception -> 0x0031 }
            r7.<init>((org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x004d, Exception -> 0x0031 }
            goto L_0x0069
        L_0x0031:
            r7 = move-exception
            java.lang.String r0 = a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Encountered Exception processing Content Cards response: "
            r2.append(r3)
            java.lang.String r3 = r6.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.appboy.support.AppboyLogger.w(r0, r2, r7)
            goto L_0x0068
        L_0x004d:
            r7 = move-exception
            java.lang.String r0 = a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Encountered JSONException processing Content Cards response: "
            r2.append(r3)
            java.lang.String r3 = r6.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.appboy.support.AppboyLogger.w(r0, r2, r7)
        L_0x0068:
            r7 = r1
        L_0x0069:
            r5.c = r7
            goto L_0x006e
        L_0x006c:
            r5.c = r1
        L_0x006e:
            java.lang.String r7 = "triggers"
            org.json.JSONArray r7 = r6.optJSONArray(r7)
            java.util.List r7 = bo.app.gb.a((org.json.JSONArray) r7, (bo.app.bq) r8)
            r5.e = r7
            java.lang.String r7 = "config"
            org.json.JSONObject r7 = r6.optJSONObject(r7)
            if (r7 == 0) goto L_0x00c1
            bo.app.cn r0 = new bo.app.cn     // Catch:{ JSONException -> 0x00a6, Exception -> 0x008a }
            r0.<init>(r7)     // Catch:{ JSONException -> 0x00a6, Exception -> 0x008a }
            r1 = r0
            goto L_0x00c1
        L_0x008a:
            r0 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Encountered Exception processing server config: "
            r3.append(r4)
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            com.appboy.support.AppboyLogger.w(r2, r7, r0)
            goto L_0x00c1
        L_0x00a6:
            r0 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Encountered JSONException processing server config: "
            r3.append(r4)
            java.lang.String r7 = r7.toString()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            com.appboy.support.AppboyLogger.w(r2, r7, r0)
        L_0x00c1:
            r5.f = r1
            java.lang.String r7 = "templated_message"
            org.json.JSONObject r7 = r6.optJSONObject(r7)
            com.appboy.models.IInAppMessage r7 = bo.app.gb.a((org.json.JSONObject) r7, (bo.app.bq) r8)
            r5.d = r7
            java.lang.String r7 = "geofences"
            org.json.JSONArray r6 = r6.optJSONArray(r7)
            java.util.List r6 = bo.app.dw.a(r6)
            r5.g = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.cl.<init>(org.json.JSONObject, bo.app.cv, bo.app.bq):void");
    }

    public boolean a() {
        return this.b != null;
    }

    public boolean b() {
        return this.d != null;
    }

    public boolean c() {
        return this.f != null;
    }

    public boolean d() {
        return this.e != null;
    }

    public boolean e() {
        return this.h != null;
    }

    public boolean f() {
        return this.g != null;
    }

    public boolean g() {
        return this.c != null;
    }

    public JSONArray h() {
        return this.b;
    }

    public IInAppMessage i() {
        return this.d;
    }

    public cn j() {
        return this.f;
    }

    public List<ek> k() {
        return this.e;
    }

    public List<AppboyGeofence> l() {
        return this.g;
    }

    public cm m() {
        return this.c;
    }

    public ResponseError n() {
        return this.h;
    }
}
