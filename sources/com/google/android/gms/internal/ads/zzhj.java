package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzhj implements zzhc {
    private int repeatMode;
    private final zzhx[] zzaep;
    private final zzog zzaeq;
    private final zzod zzaer;
    private final Handler zzaes;
    private final zzhl zzaet;
    private final CopyOnWriteArraySet<zzhf> zzaeu;
    private final zzid zzaev;
    private final zzia zzaew;
    private boolean zzaex;
    private boolean zzaey;
    private int zzaez;
    private int zzafa;
    private int zzafb;
    private boolean zzafc;
    private zzhy zzafd;
    private Object zzafe;
    private zznp zzaff;
    private zzod zzafg;
    private zzhu zzafh;
    private zzhn zzafi;
    private int zzafj;
    private int zzafk;
    private long zzafl;

    public zzhj(zzhx[] zzhxArr, zzog zzog, zzhs zzhs) {
        String str = zzpq.zzbkm;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 26);
        sb.append("Init ExoPlayerLib/2.4.2 [");
        sb.append(str);
        sb.append("]");
        Log.i("ExoPlayerImpl", sb.toString());
        zzoz.checkState(zzhxArr.length > 0);
        this.zzaep = (zzhx[]) zzoz.checkNotNull(zzhxArr);
        this.zzaeq = (zzog) zzoz.checkNotNull(zzog);
        this.zzaey = false;
        this.repeatMode = 0;
        this.zzaez = 1;
        this.zzaeu = new CopyOnWriteArraySet<>();
        this.zzaer = new zzod(new zzob[zzhxArr.length]);
        this.zzafd = zzhy.zzaid;
        this.zzaev = new zzid();
        this.zzaew = new zzia();
        this.zzaff = zznp.zzbgu;
        this.zzafg = this.zzaer;
        this.zzafh = zzhu.zzahz;
        this.zzaes = new zzhi(this, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        zzhn zzhn = new zzhn(0, 0);
        this.zzafi = zzhn;
        this.zzaet = new zzhl(zzhxArr, zzog, zzhs, this.zzaey, 0, this.zzaes, zzhn, this);
    }

    public final void zza(zzhf zzhf) {
        this.zzaeu.add(zzhf);
    }

    public final void zzb(zzhf zzhf) {
        this.zzaeu.remove(zzhf);
    }

    public final int getPlaybackState() {
        return this.zzaez;
    }

    public final void zza(zzmz zzmz) {
        if (!this.zzafd.isEmpty() || this.zzafe != null) {
            this.zzafd = zzhy.zzaid;
            this.zzafe = null;
            Iterator<zzhf> it = this.zzaeu.iterator();
            while (it.hasNext()) {
                it.next().zza(this.zzafd, this.zzafe);
            }
        }
        if (this.zzaex) {
            this.zzaex = false;
            this.zzaff = zznp.zzbgu;
            this.zzafg = this.zzaer;
            this.zzaeq.zzd((Object) null);
            Iterator<zzhf> it2 = this.zzaeu.iterator();
            while (it2.hasNext()) {
                it2.next().zza(this.zzaff, this.zzafg);
            }
        }
        this.zzafb++;
        this.zzaet.zza(zzmz, true);
    }

    public final void zzf(boolean z) {
        if (this.zzaey != z) {
            this.zzaey = z;
            this.zzaet.zzf(z);
            Iterator<zzhf> it = this.zzaeu.iterator();
            while (it.hasNext()) {
                it.next().zza(z, this.zzaez);
            }
        }
    }

    public final boolean zzek() {
        return this.zzaey;
    }

    public final void seekTo(long j) {
        long j2;
        int zzeo = zzeo();
        if (zzeo < 0 || (!this.zzafd.isEmpty() && zzeo >= this.zzafd.zzff())) {
            throw new zzht(this.zzafd, zzeo, j);
        }
        this.zzafa++;
        this.zzafj = zzeo;
        if (!this.zzafd.isEmpty()) {
            this.zzafd.zza(zzeo, this.zzaev, false);
            if (j == -9223372036854775807L) {
                j2 = this.zzaev.zzaiw;
            } else {
                j2 = zzha.zzdn(j);
            }
            long j3 = this.zzaev.zzaix + j2;
            long j4 = this.zzafd.zza(0, this.zzaew, false).zzaih;
            if (j4 != -9223372036854775807L) {
                int i = (j3 > j4 ? 1 : (j3 == j4 ? 0 : -1));
            }
        }
        this.zzafk = 0;
        if (j == -9223372036854775807L) {
            this.zzafl = 0;
            this.zzaet.zza(this.zzafd, zzeo, -9223372036854775807L);
            return;
        }
        this.zzafl = j;
        this.zzaet.zza(this.zzafd, zzeo, zzha.zzdn(j));
        Iterator<zzhf> it = this.zzaeu.iterator();
        while (it.hasNext()) {
            it.next().zzen();
        }
    }

    public final void stop() {
        this.zzaet.stop();
    }

    public final void release() {
        this.zzaet.release();
        this.zzaes.removeCallbacksAndMessages((Object) null);
    }

    public final void zza(zzhh... zzhhArr) {
        this.zzaet.zza(zzhhArr);
    }

    public final void zzb(zzhh... zzhhArr) {
        this.zzaet.zzb(zzhhArr);
    }

    private final int zzeo() {
        if (this.zzafd.isEmpty() || this.zzafa > 0) {
            return this.zzafj;
        }
        this.zzafd.zza(this.zzafi.zzafr, this.zzaew, false);
        return 0;
    }

    public final long getDuration() {
        if (this.zzafd.isEmpty()) {
            return -9223372036854775807L;
        }
        return zzha.zzdm(this.zzafd.zza(zzeo(), this.zzaev, false).zzaih);
    }

    public final long zzem() {
        if (this.zzafd.isEmpty() || this.zzafa > 0) {
            return this.zzafl;
        }
        this.zzafd.zza(this.zzafi.zzafr, this.zzaew, false);
        return this.zzaew.zzfh() + zzha.zzdm(this.zzafi.zzagv);
    }

    public final long getBufferedPosition() {
        if (this.zzafd.isEmpty() || this.zzafa > 0) {
            return this.zzafl;
        }
        this.zzafd.zza(this.zzafi.zzafr, this.zzaew, false);
        return this.zzaew.zzfh() + zzha.zzdm(this.zzafi.zzagw);
    }

    public final int zzel() {
        return this.zzaep.length;
    }

    /* access modifiers changed from: package-private */
    public final void zza(Message message) {
        boolean z = true;
        switch (message.what) {
            case 0:
                this.zzafb--;
                return;
            case 1:
                this.zzaez = message.arg1;
                Iterator<zzhf> it = this.zzaeu.iterator();
                while (it.hasNext()) {
                    it.next().zza(this.zzaey, this.zzaez);
                }
                return;
            case 2:
                if (message.arg1 == 0) {
                    z = false;
                }
                this.zzafc = z;
                Iterator<zzhf> it2 = this.zzaeu.iterator();
                while (it2.hasNext()) {
                    it2.next().zzg(this.zzafc);
                }
                return;
            case 3:
                if (this.zzafb == 0) {
                    zzoi zzoi = (zzoi) message.obj;
                    this.zzaex = true;
                    this.zzaff = zzoi.zzbhw;
                    this.zzafg = zzoi.zzbhx;
                    this.zzaeq.zzd(zzoi.zzbhy);
                    Iterator<zzhf> it3 = this.zzaeu.iterator();
                    while (it3.hasNext()) {
                        it3.next().zza(this.zzaff, this.zzafg);
                    }
                    return;
                }
                return;
            case 4:
                int i = this.zzafa - 1;
                this.zzafa = i;
                if (i == 0) {
                    this.zzafi = (zzhn) message.obj;
                    if (message.arg1 != 0) {
                        Iterator<zzhf> it4 = this.zzaeu.iterator();
                        while (it4.hasNext()) {
                            it4.next().zzen();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.zzafa == 0) {
                    this.zzafi = (zzhn) message.obj;
                    Iterator<zzhf> it5 = this.zzaeu.iterator();
                    while (it5.hasNext()) {
                        it5.next().zzen();
                    }
                    return;
                }
                return;
            case 6:
                zzhp zzhp = (zzhp) message.obj;
                this.zzafa -= zzhp.zzahw;
                if (this.zzafb == 0) {
                    this.zzafd = zzhp.zzafd;
                    this.zzafe = zzhp.zzafe;
                    this.zzafi = zzhp.zzafi;
                    Iterator<zzhf> it6 = this.zzaeu.iterator();
                    while (it6.hasNext()) {
                        it6.next().zza(this.zzafd, this.zzafe);
                    }
                    return;
                }
                return;
            case 7:
                zzhu zzhu = (zzhu) message.obj;
                if (!this.zzafh.equals(zzhu)) {
                    this.zzafh = zzhu;
                    Iterator<zzhf> it7 = this.zzaeu.iterator();
                    while (it7.hasNext()) {
                        it7.next().zza(zzhu);
                    }
                    return;
                }
                return;
            case 8:
                zzhd zzhd = (zzhd) message.obj;
                Iterator<zzhf> it8 = this.zzaeu.iterator();
                while (it8.hasNext()) {
                    it8.next().zza(zzhd);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
