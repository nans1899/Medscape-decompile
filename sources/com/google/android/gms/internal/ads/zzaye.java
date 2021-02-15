package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.AdActivity;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaye {
    private final Object lock = new Object();
    private long zzdzl = -1;
    private long zzdzm = -1;
    private int zzdzn = -1;
    int zzdzo = -1;
    private long zzdzp = 0;
    private final String zzdzq;
    private final zzayr zzdzr;
    private int zzdzs = 0;
    private int zzdzt = 0;

    public zzaye(String str, zzayr zzayr) {
        this.zzdzq = str;
        this.zzdzr = zzayr;
    }

    public final void zzwb() {
        synchronized (this.lock) {
            this.zzdzs++;
        }
    }

    public final void zzwa() {
        synchronized (this.lock) {
            this.zzdzt++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0076, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzvg r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.lock
            monitor-enter(r0)
            com.google.android.gms.internal.ads.zzayr r1 = r10.zzdzr     // Catch:{ all -> 0x0077 }
            long r1 = r1.zzxl()     // Catch:{ all -> 0x0077 }
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzp.zzky()     // Catch:{ all -> 0x0077 }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x0077 }
            long r5 = r10.zzdzm     // Catch:{ all -> 0x0077 }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0040
            long r1 = r3 - r1
            com.google.android.gms.internal.ads.zzaaq<java.lang.Long> r5 = com.google.android.gms.internal.ads.zzabb.zzcop     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.ads.zzaax r6 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0077 }
            java.lang.Object r5 = r6.zzd(r5)     // Catch:{ all -> 0x0077 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x0077 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0077 }
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0033
            r1 = -1
            r10.zzdzo = r1     // Catch:{ all -> 0x0077 }
            goto L_0x003b
        L_0x0033:
            com.google.android.gms.internal.ads.zzayr r1 = r10.zzdzr     // Catch:{ all -> 0x0077 }
            int r1 = r1.zzxm()     // Catch:{ all -> 0x0077 }
            r10.zzdzo = r1     // Catch:{ all -> 0x0077 }
        L_0x003b:
            r10.zzdzm = r12     // Catch:{ all -> 0x0077 }
            r10.zzdzl = r12     // Catch:{ all -> 0x0077 }
            goto L_0x0042
        L_0x0040:
            r10.zzdzl = r12     // Catch:{ all -> 0x0077 }
        L_0x0042:
            r12 = 1
            if (r11 == 0) goto L_0x0056
            android.os.Bundle r13 = r11.extras     // Catch:{ all -> 0x0077 }
            if (r13 == 0) goto L_0x0056
            android.os.Bundle r11 = r11.extras     // Catch:{ all -> 0x0077 }
            java.lang.String r13 = "gw"
            r1 = 2
            int r11 = r11.getInt(r13, r1)     // Catch:{ all -> 0x0077 }
            if (r11 != r12) goto L_0x0056
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return
        L_0x0056:
            int r11 = r10.zzdzn     // Catch:{ all -> 0x0077 }
            int r11 = r11 + r12
            r10.zzdzn = r11     // Catch:{ all -> 0x0077 }
            int r11 = r10.zzdzo     // Catch:{ all -> 0x0077 }
            int r11 = r11 + r12
            r10.zzdzo = r11     // Catch:{ all -> 0x0077 }
            if (r11 != 0) goto L_0x006c
            r11 = 0
            r10.zzdzp = r11     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.ads.zzayr r11 = r10.zzdzr     // Catch:{ all -> 0x0077 }
            r11.zzfa(r3)     // Catch:{ all -> 0x0077 }
            goto L_0x0075
        L_0x006c:
            com.google.android.gms.internal.ads.zzayr r11 = r10.zzdzr     // Catch:{ all -> 0x0077 }
            long r11 = r11.zzxn()     // Catch:{ all -> 0x0077 }
            long r3 = r3 - r11
            r10.zzdzp = r3     // Catch:{ all -> 0x0077 }
        L_0x0075:
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            return
        L_0x0077:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0077 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaye.zza(com.google.android.gms.internal.ads.zzvg, long):void");
    }

    public final Bundle zzp(Context context, String str) {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzdzq);
            bundle.putLong("basets", this.zzdzm);
            bundle.putLong("currts", this.zzdzl);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzdzn);
            bundle.putInt("preqs_in_session", this.zzdzo);
            bundle.putLong("time_in_session", this.zzdzp);
            bundle.putInt("pclick", this.zzdzs);
            bundle.putInt("pimp", this.zzdzt);
            bundle.putBoolean("support_transparent_background", zzan(context));
        }
        return bundle;
    }

    private static boolean zzan(Context context) {
        Context zzab = zzatx.zzab(context);
        int identifier = zzab.getResources().getIdentifier("Theme.Translucent", AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, "android");
        if (identifier == 0) {
            zzayp.zzfd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == zzab.getPackageManager().getActivityInfo(new ComponentName(zzab.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzayp.zzfd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            zzayp.zzfe("Fail to fetch AdActivity theme");
            zzayp.zzfd("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }
}
