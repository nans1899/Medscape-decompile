package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzhv;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
abstract class zzhq<T extends zzhv<T>> {
    zzhq() {
    }

    /* access modifiers changed from: package-private */
    public abstract int zza(Map.Entry<?, ?> entry);

    /* access modifiers changed from: package-private */
    public abstract Object zza(zzho zzho, zzjn zzjn, int i);

    /* access modifiers changed from: package-private */
    public abstract <UT, UB> UB zza(zzkc zzkc, Object obj, zzho zzho, zzht<T> zzht, UB ub, zzkx<UT, UB> zzkx) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(zzgs zzgs, Object obj, zzho zzho, zzht<T> zzht) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(zzkc zzkc, Object obj, zzho zzho, zzht<T> zzht) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void zza(zzlq zzlq, Map.Entry<?, ?> entry) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean zze(zzjn zzjn);

    /* access modifiers changed from: package-private */
    public abstract zzht<T> zzh(Object obj);

    /* access modifiers changed from: package-private */
    public abstract zzht<T> zzi(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void zzj(Object obj);
}
