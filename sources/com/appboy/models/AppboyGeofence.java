package com.appboy.models;

import bo.app.ge;
import bo.app.gg;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.location.Geofence;
import org.json.JSONException;
import org.json.JSONObject;

public class AppboyGeofence implements IPutIntoJson<JSONObject>, Comparable<AppboyGeofence> {
    final int a;
    final boolean b;
    final boolean c;
    final int d;
    double e;
    private final JSONObject f;
    private final String g;
    private final double h;
    private final double i;
    private final int j;
    private final int k;
    private final boolean l;
    private final boolean m;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppboyGeofence(org.json.JSONObject r17) {
        /*
            r16 = this;
            r1 = r17
            java.lang.String r0 = "id"
            java.lang.String r2 = r1.getString(r0)
            java.lang.String r0 = "latitude"
            double r3 = r1.getDouble(r0)
            java.lang.String r0 = "longitude"
            double r5 = r1.getDouble(r0)
            java.lang.String r0 = "radius"
            int r7 = r1.getInt(r0)
            java.lang.String r0 = "cooldown_enter"
            int r8 = r1.getInt(r0)
            java.lang.String r0 = "cooldown_exit"
            int r9 = r1.getInt(r0)
            java.lang.String r0 = "analytics_enabled_enter"
            boolean r10 = r1.getBoolean(r0)
            java.lang.String r0 = "analytics_enabled_exit"
            boolean r11 = r1.getBoolean(r0)
            java.lang.String r0 = "enter_events"
            r12 = 1
            boolean r13 = r1.optBoolean(r0, r12)
            java.lang.String r0 = "exit_events"
            boolean r14 = r1.optBoolean(r0, r12)
            java.lang.String r0 = "notification_responsiveness"
            r12 = 30000(0x7530, float:4.2039E-41)
            int r15 = r1.optInt(r0, r12)
            r0 = r16
            r12 = r13
            r13 = r14
            r14 = r15
            r0.<init>(r1, r2, r3, r5, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.models.AppboyGeofence.<init>(org.json.JSONObject):void");
    }

    AppboyGeofence(JSONObject jSONObject, String str, double d2, double d3, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5) {
        this.e = -1.0d;
        this.f = jSONObject;
        this.g = str;
        this.h = d2;
        this.i = d3;
        this.a = i2;
        this.j = i3;
        this.k = i4;
        this.m = z;
        this.l = z2;
        this.b = z3;
        this.c = z4;
        this.d = i5;
    }

    public String getId() {
        return this.g;
    }

    public boolean getAnalyticsEnabledEnter() {
        return this.m;
    }

    public boolean getAnalyticsEnabledExit() {
        return this.l;
    }

    public int getCooldownEnterSeconds() {
        return this.j;
    }

    public int getCooldownExitSeconds() {
        return this.k;
    }

    public double getLatitude() {
        return this.h;
    }

    public double getLongitude() {
        return this.i;
    }

    public double getRadiusMeters() {
        return (double) this.a;
    }

    public void setDistanceFromGeofenceRefresh(double d2) {
        this.e = d2;
    }

    public double getDistanceFromGeofenceRefresh() {
        return this.e;
    }

    public Geofence toGeofence() {
        Geofence.Builder builder = new Geofence.Builder();
        builder.setRequestId(this.g).setCircularRegion(this.h, this.i, (float) this.a).setNotificationResponsiveness(this.d).setExpirationDuration(-1);
        int i2 = this.b ? 1 : 0;
        if (this.c) {
            i2 |= 2;
        }
        builder.setTransitionTypes(i2);
        return builder.build();
    }

    public boolean equivalentServerData(AppboyGeofence appboyGeofence) {
        try {
            ge.a(appboyGeofence.forJsonPut(), this.f, gg.LENIENT);
            return true;
        } catch (AssertionError | JSONException unused) {
            return false;
        }
    }

    public String toString() {
        return "AppboyGeofence{" + "id=" + this.g + ", latitude='" + this.h + ", longitude=" + this.i + ", radiusMeters=" + this.a + ", cooldownEnterSeconds=" + this.j + ", cooldownExitSeconds=" + this.k + ", analyticsEnabledEnter=" + this.m + ", analyticsEnabledExit=" + this.l + ", enterEvents=" + this.b + ", exitEvents=" + this.c + ", notificationResponsivenessMs=" + this.d + ", distanceFromGeofenceRefresh=" + this.e + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }

    public JSONObject forJsonPut() {
        return this.f;
    }

    public int compareTo(AppboyGeofence appboyGeofence) {
        double d2 = this.e;
        if (d2 != -1.0d && d2 < appboyGeofence.getDistanceFromGeofenceRefresh()) {
            return -1;
        }
        return 1;
    }
}
