package com.google.android.gms.internal.ads;

import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzny {
    public final int viewportHeight;
    public final int viewportWidth;
    public final String zzbhc;
    public final String zzbhd;
    public final boolean zzbhe;
    public final boolean zzbhf;
    public final int zzbhg;
    public final int zzbhh;
    public final int zzbhi;
    public final boolean zzbhj;
    public final boolean zzbhk;
    public final boolean zzbhl;

    public zzny() {
        this((String) null, (String) null, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
    }

    private zzny(String str, String str2, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, int i4, int i5, boolean z5) {
        this.zzbhc = null;
        this.zzbhd = null;
        this.zzbhe = false;
        this.zzbhf = true;
        this.zzbhg = Integer.MAX_VALUE;
        this.zzbhh = Integer.MAX_VALUE;
        this.zzbhi = Integer.MAX_VALUE;
        this.zzbhj = true;
        this.zzbhk = true;
        this.viewportWidth = Integer.MAX_VALUE;
        this.viewportHeight = Integer.MAX_VALUE;
        this.zzbhl = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzny zzny = (zzny) obj;
            return this.zzbhf == zzny.zzbhf && this.zzbhg == zzny.zzbhg && this.zzbhh == zzny.zzbhh && this.zzbhj == zzny.zzbhj && this.zzbhk == zzny.zzbhk && this.zzbhl == zzny.zzbhl && this.viewportWidth == zzny.viewportWidth && this.viewportHeight == zzny.viewportHeight && this.zzbhi == zzny.zzbhi && TextUtils.equals((CharSequence) null, (CharSequence) null) && TextUtils.equals((CharSequence) null, (CharSequence) null);
        }
    }

    public final int hashCode() {
        String str = null;
        return (((((((((((((((((((str.hashCode() * 31) + str.hashCode()) * 31 * 31) + (this.zzbhf ? 1 : 0)) * 31) + this.zzbhg) * 31) + this.zzbhh) * 31) + this.zzbhi) * 31) + (this.zzbhj ? 1 : 0)) * 31) + (this.zzbhk ? 1 : 0)) * 31) + (this.zzbhl ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight;
    }
}
