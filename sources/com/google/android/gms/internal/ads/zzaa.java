package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.internal.ads.zzaq;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzaa<T> implements Comparable<zzaa<T>> {
    private final Object mLock;
    /* access modifiers changed from: private */
    public final zzaq.zza zzap;
    private final int zzaq;
    private final String zzar;
    private final int zzas;
    private zzai zzat;
    private Integer zzau;
    private zzae zzav;
    private boolean zzaw;
    private boolean zzax;
    private boolean zzay;
    private boolean zzaz;
    private zzan zzba;
    private zzn zzbb;
    private zzac zzbc;

    public zzaa(int i, String str, zzai zzai) {
        Uri parse;
        String host;
        this.zzap = zzaq.zza.zzbw ? new zzaq.zza() : null;
        this.mLock = new Object();
        this.zzaw = true;
        int i2 = 0;
        this.zzax = false;
        this.zzay = false;
        this.zzaz = false;
        this.zzbb = null;
        this.zzaq = i;
        this.zzar = str;
        this.zzat = zzai;
        this.zzba = new zzq();
        if (!(TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null)) {
            i2 = host.hashCode();
        }
        this.zzas = i2;
    }

    /* access modifiers changed from: protected */
    public abstract zzaj<T> zza(zzy zzy);

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    public byte[] zzg() throws zzl {
        return null;
    }

    public final int getMethod() {
        return this.zzaq;
    }

    public final int zzd() {
        return this.zzas;
    }

    public final void zzc(String str) {
        if (zzaq.zza.zzbw) {
            this.zzap.zza(str, Thread.currentThread().getId());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(String str) {
        zzae zzae = this.zzav;
        if (zzae != null) {
            zzae.zzf(this);
        }
        if (zzaq.zza.zzbw) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzad(this, str, id));
                return;
            }
            this.zzap.zza(str, id);
            this.zzap.zzd(toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i) {
        zzae zzae = this.zzav;
        if (zzae != null) {
            zzae.zza(this, i);
        }
    }

    public final zzaa<?> zza(zzae zzae) {
        this.zzav = zzae;
        return this;
    }

    public final zzaa<?> zze(int i) {
        this.zzau = Integer.valueOf(i);
        return this;
    }

    public final String getUrl() {
        return this.zzar;
    }

    public final String zze() {
        String str = this.zzar;
        int i = this.zzaq;
        if (i == 0 || i == -1) {
            return str;
        }
        String num = Integer.toString(i);
        StringBuilder sb = new StringBuilder(String.valueOf(num).length() + 1 + String.valueOf(str).length());
        sb.append(num);
        sb.append('-');
        sb.append(str);
        return sb.toString();
    }

    public final zzaa<?> zza(zzn zzn) {
        this.zzbb = zzn;
        return this;
    }

    public final zzn zzf() {
        return this.zzbb;
    }

    public final boolean isCanceled() {
        synchronized (this.mLock) {
        }
        return false;
    }

    public Map<String, String> getHeaders() throws zzl {
        return Collections.emptyMap();
    }

    public final boolean zzh() {
        return this.zzaw;
    }

    public final int zzi() {
        return this.zzba.zzb();
    }

    public final zzan zzj() {
        return this.zzba;
    }

    public final void zzk() {
        synchronized (this.mLock) {
            this.zzay = true;
        }
    }

    public final boolean zzl() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzay;
        }
        return z;
    }

    public final void zzb(zzao zzao) {
        zzai zzai;
        synchronized (this.mLock) {
            zzai = this.zzat;
        }
        if (zzai != null) {
            zzai.zzc(zzao);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzac zzac) {
        synchronized (this.mLock) {
            this.zzbc = zzac;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzaj<?> zzaj) {
        zzac zzac;
        synchronized (this.mLock) {
            zzac = this.zzbc;
        }
        if (zzac != null) {
            zzac.zza(this, zzaj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzm() {
        zzac zzac;
        synchronized (this.mLock) {
            zzac = this.zzbc;
        }
        if (zzac != null) {
            zzac.zza(this);
        }
    }

    public String toString() {
        String valueOf = String.valueOf(Integer.toHexString(this.zzas));
        String concat = valueOf.length() != 0 ? "0x".concat(valueOf) : new String("0x");
        isCanceled();
        String str = this.zzar;
        String valueOf2 = String.valueOf(zzaf.NORMAL);
        String valueOf3 = String.valueOf(this.zzau);
        StringBuilder sb = new StringBuilder("[ ] ".length() + 3 + String.valueOf(str).length() + String.valueOf(concat).length() + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("[ ] ");
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(concat);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(valueOf2);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(valueOf3);
        return sb.toString();
    }

    public /* synthetic */ int compareTo(Object obj) {
        zzaa zzaa = (zzaa) obj;
        zzaf zzaf = zzaf.NORMAL;
        zzaf zzaf2 = zzaf.NORMAL;
        return zzaf == zzaf2 ? this.zzau.intValue() - zzaa.zzau.intValue() : zzaf2.ordinal() - zzaf.ordinal();
    }
}
