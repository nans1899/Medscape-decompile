package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaaq<T> {
    private final int zzclf;
    private final T zzclg;
    private final String zzcn;

    private zzaaq(int i, String str, T t) {
        this.zzclf = i;
        this.zzcn = str;
        this.zzclg = t;
        zzwm.zzpw().zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract T zza(Bundle bundle);

    public abstract void zza(SharedPreferences.Editor editor, T t);

    /* access modifiers changed from: protected */
    public abstract T zzb(JSONObject jSONObject);

    public final String getKey() {
        return this.zzcn;
    }

    public final T zzrf() {
        return this.zzclg;
    }

    public static zzaaq<Boolean> zza(int i, String str, Boolean bool) {
        return new zzaap(i, str, bool);
    }

    public static zzaaq<Integer> zza(int i, String str, int i2) {
        return new zzaas(1, str, Integer.valueOf(i2));
    }

    public static zzaaq<Long> zzb(int i, String str, long j) {
        return new zzaar(1, str, Long.valueOf(j));
    }

    public static zzaaq<Float> zza(int i, String str, float f) {
        return new zzaau(1, str, Float.valueOf(0.0f));
    }

    public static zzaaq<String> zza(int i, String str, String str2) {
        return new zzaat(1, str, str2);
    }

    public static zzaaq<String> zzb(int i, String str) {
        zzaaq<String> zza = zza(1, str, (String) null);
        zzwm.zzpw().zzc(zza);
        return zza;
    }

    public final int getSource() {
        return this.zzclf;
    }

    /* synthetic */ zzaaq(int i, String str, Object obj, zzaap zzaap) {
        this(i, str, obj);
    }
}
