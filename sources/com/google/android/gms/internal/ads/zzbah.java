package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbah {
    /* access modifiers changed from: private */
    public final List<String> zzecp = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzecq = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzecr = new ArrayList();

    public final zzbah zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzecp.size()) {
            double doubleValue = this.zzecr.get(i).doubleValue();
            double doubleValue2 = this.zzecq.get(i).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.zzecp.add(i, str);
        this.zzecr.add(i, Double.valueOf(d));
        this.zzecq.add(i, Double.valueOf(d2));
        return this;
    }

    public final zzbac zzyi() {
        return new zzbac(this);
    }
}
