package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzber implements zzhf, zzmy, zzpa<zzom>, zzqd {
    private static int zzekp;
    private static int zzekq;
    private int bytesTransferred;
    private final zzbdv zzejr;
    private final zzbeo zzekr;
    private final zzhx zzeks;
    private final zzhx zzekt;
    private final zzoa zzeku;
    private zzhc zzekv;
    private ByteBuffer zzekw;
    private boolean zzekx;
    private zzbey zzeky;
    private Set<WeakReference<zzbek>> zzekz = new HashSet();
    private final Context zzvr;

    public zzber(Context context, zzbdv zzbdv) {
        this.zzvr = context;
        this.zzejr = zzbdv;
        this.zzekr = new zzbeo();
        this.zzeks = new zzpx(this.zzvr, zzlw.zzbcw, 0, zzayu.zzeba, this, -1);
        this.zzekt = new zzjb(zzlw.zzbcw);
        this.zzeku = new zznv();
        if (zzayp.zzxa()) {
            String valueOf = String.valueOf(this);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 28);
            sb.append("ExoPlayerAdapter initialize ");
            sb.append(valueOf);
            zzayp.zzei(sb.toString());
        }
        zzekp++;
        zzhc zza = zzhg.zza(new zzhx[]{this.zzekt, this.zzeks}, this.zzeku, this.zzekr);
        this.zzekv = zza;
        zza.zza((zzhf) this);
    }

    public final void zza(Surface surface) {
    }

    public final void zza(zzhu zzhu) {
    }

    public final void zza(zzhy zzhy, Object obj) {
    }

    public final void zza(zznp zznp, zzod zzod) {
    }

    public final void zzd(String str, long j, long j2) {
    }

    public final void zze(int i, long j) {
    }

    public final void zze(zzjl zzjl) {
    }

    public final /* bridge */ /* synthetic */ void zze(Object obj) {
    }

    public final void zzen() {
    }

    public final void zzf(zzjl zzjl) {
    }

    public final void zzg(boolean z) {
    }

    public final void zzk(zzho zzho) {
    }

    public final zzhc zzaau() {
        return this.zzekv;
    }

    public static int zzaav() {
        return zzekp;
    }

    public static int zzaaw() {
        return zzekq;
    }

    public final void zza(zzbey zzbey) {
        this.zzeky = zzbey;
    }

    public final zzbeo zzaax() {
        return this.zzekr;
    }

    public final void zza(Uri[] uriArr, String str) {
        zza(uriArr, str, ByteBuffer.allocate(0), false);
    }

    public final void zza(Uri[] uriArr, String str, ByteBuffer byteBuffer, boolean z) {
        zzmz zzmz;
        if (this.zzekv != null) {
            this.zzekw = byteBuffer;
            this.zzekx = z;
            if (uriArr.length == 1) {
                zzmz = zzb(uriArr[0], str);
            } else {
                zzmz[] zzmzArr = new zzmz[uriArr.length];
                for (int i = 0; i < uriArr.length; i++) {
                    zzmzArr[i] = zzb(uriArr[i], str);
                }
                zzmz = new zzne(zzmzArr);
            }
            this.zzekv.zza(zzmz);
            zzekq++;
        }
    }

    public final void release() {
        zzhc zzhc = this.zzekv;
        if (zzhc != null) {
            zzhc.zzb((zzhf) this);
            this.zzekv.release();
            this.zzekv = null;
            zzekq--;
        }
    }

    public final long getBytesTransferred() {
        return (long) this.bytesTransferred;
    }

    public final void zzb(IOException iOException) {
        zzbey zzbey = this.zzeky;
        if (zzbey != null) {
            zzbey.zza("onLoadError", iOException);
        }
    }

    public final void zza(int i, int i2, int i3, float f) {
        zzbey zzbey = this.zzeky;
        if (zzbey != null) {
            zzbey.zzn(i, i2);
        }
    }

    public final void zza(boolean z, int i) {
        zzbey zzbey = this.zzeky;
        if (zzbey != null) {
            zzbey.zzdq(i);
        }
    }

    public final void zza(zzhd zzhd) {
        zzbey zzbey = this.zzeky;
        if (zzbey != null) {
            zzbey.zza("onPlayerError", zzhd);
        }
    }

    public final void zzdp(int i) {
        for (WeakReference<zzbek> weakReference : this.zzekz) {
            zzbek zzbek = (zzbek) weakReference.get();
            if (zzbek != null) {
                zzbek.setReceiveBufferSize(i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(Surface surface, boolean z) {
        if (this.zzekv != null) {
            zzhh zzhh = new zzhh(this.zzeks, 1, surface);
            if (z) {
                this.zzekv.zzb(zzhh);
                return;
            }
            this.zzekv.zza(zzhh);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(float f, boolean z) {
        if (this.zzekv != null) {
            zzhh zzhh = new zzhh(this.zzekt, 2, Float.valueOf(f));
            if (z) {
                this.zzekv.zzb(zzhh);
                return;
            }
            this.zzekv.zza(zzhh);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaw(boolean z) {
        if (this.zzekv != null) {
            for (int i = 0; i < this.zzekv.zzel(); i++) {
                this.zzeku.zzf(i, !z);
            }
        }
    }

    private final zzmz zzb(Uri uri, String str) {
        zzol zzol;
        zzka zzka;
        if (!this.zzekx || this.zzekw.limit() <= 0) {
            if (this.zzejr.zzeix > 0) {
                zzol = new zzbet(this, str);
            } else {
                zzol = new zzbes(this, str);
            }
            if (this.zzejr.zzeiy) {
                zzol = new zzbev(this, zzol);
            }
            if (this.zzekw.limit() > 0) {
                byte[] bArr = new byte[this.zzekw.limit()];
                this.zzekw.get(bArr);
                zzol = new zzbeu(zzol, bArr);
            }
        } else {
            byte[] bArr2 = new byte[this.zzekw.limit()];
            this.zzekw.get(bArr2);
            zzol = new zzbeq(bArr2);
        }
        zzol zzol2 = zzol;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcmf)).booleanValue()) {
            zzka = zzbex.zzeld;
        } else {
            zzka = zzbew.zzeld;
        }
        return new zzmv(uri, zzol2, zzka, this.zzejr.zzeiz, zzayu.zzeba, this, (String) null, this.zzejr.zzeiv);
    }

    public final void finalize() throws Throwable {
        zzekp--;
        if (zzayp.zzxa()) {
            String valueOf = String.valueOf(this);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26);
            sb.append("ExoPlayerAdapter finalize ");
            sb.append(valueOf);
            zzayp.zzei(sb.toString());
        }
    }

    public final /* synthetic */ void zzc(Object obj, int i) {
        this.bytesTransferred += i;
    }

    public final /* synthetic */ void zza(Object obj, zzon zzon) {
        this.bytesTransferred = 0;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzom zza(zzol zzol) {
        return new zzbem(this.zzvr, zzol.zzip(), this, new zzbez(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(boolean z, long j) {
        zzbey zzbey = this.zzeky;
        if (zzbey != null) {
            zzbey.zzb(z, j);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzom zzfl(String str) {
        return new zzop(str, (zzpm<String>) null, this.zzejr.zzeiy ? null : this, this.zzejr.zzeis, this.zzejr.zzeiu, true, (zzou) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzom zzfm(String str) {
        zzbek zzbek = new zzbek(str, this.zzejr.zzeiy ? null : this, this.zzejr.zzeis, this.zzejr.zzeiu, this.zzejr.zzeix);
        this.zzekz.add(new WeakReference(zzbek));
        return zzbek;
    }
}
