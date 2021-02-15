package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjb extends zzlu implements zzpd {
    private int zzahm;
    private int zzaho;
    /* access modifiers changed from: private */
    public final zzij zzamo;
    private final zzio zzamp;
    private boolean zzamq;
    private boolean zzamr;
    private MediaFormat zzams;
    private long zzamt;
    /* access modifiers changed from: private */
    public boolean zzamu;

    public zzjb(zzlw zzlw) {
        this(zzlw, (zzjs<zzju>) null, true);
    }

    protected static void zza(int i, long j, long j2) {
    }

    protected static void zzgd() {
    }

    protected static void zzx(int i) {
    }

    public final zzpd zzea() {
        return this;
    }

    private zzjb(zzlw zzlw, zzjs<zzju> zzjs, boolean z) {
        this(zzlw, (zzjs<zzju>) null, true, (Handler) null, (zzig) null);
    }

    private zzjb(zzlw zzlw, zzjs<zzju> zzjs, boolean z, Handler handler, zzig zzig) {
        this(zzlw, (zzjs<zzju>) null, true, (Handler) null, (zzig) null, (zzif) null, new zzie[0]);
    }

    private zzjb(zzlw zzlw, zzjs<zzju> zzjs, boolean z, Handler handler, zzig zzig, zzif zzif, zzie... zzieArr) {
        super(1, zzlw, zzjs, z);
        this.zzamp = new zzio((zzif) null, zzieArr, new zzjd(this));
        this.zzamo = new zzij((Handler) null, (zzig) null);
    }

    /* access modifiers changed from: protected */
    public final int zza(zzlw zzlw, zzho zzho) throws zzlz {
        String str = zzho.zzahc;
        boolean z = false;
        if (!zzpg.zzbf(str)) {
            return 0;
        }
        int i = zzpq.SDK_INT >= 21 ? 16 : 0;
        int i2 = 3;
        if (zzba(str) && zzlw.zzhj() != null) {
            return i | 4 | 3;
        }
        zzlr zzc = zzlw.zzc(str, false);
        if (zzc == null) {
            return 1;
        }
        if (zzpq.SDK_INT < 21 || ((zzho.zzahn == -1 || zzc.zzay(zzho.zzahn)) && (zzho.zzahm == -1 || zzc.zzaz(zzho.zzahm)))) {
            z = true;
        }
        if (!z) {
            i2 = 2;
        }
        return i | 4 | i2;
    }

    /* access modifiers changed from: protected */
    public final zzlr zza(zzlw zzlw, zzho zzho, boolean z) throws zzlz {
        zzlr zzhj;
        if (!zzba(zzho.zzahc) || (zzhj = zzlw.zzhj()) == null) {
            this.zzamq = false;
            return super.zza(zzlw, zzho, z);
        }
        this.zzamq = true;
        return zzhj;
    }

    private final boolean zzba(String str) {
        return this.zzamp.zzay(str);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzlr zzlr, MediaCodec mediaCodec, zzho zzho, MediaCrypto mediaCrypto) {
        this.zzamr = zzpq.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(zzlr.name) && "samsung".equals(zzpq.MANUFACTURER) && (zzpq.DEVICE.startsWith("zeroflte") || zzpq.DEVICE.startsWith("herolte") || zzpq.DEVICE.startsWith("heroqlte"));
        if (this.zzamq) {
            MediaFormat zzfa = zzho.zzfa();
            this.zzams = zzfa;
            zzfa.setString("mime", "audio/raw");
            mediaCodec.configure(this.zzams, (Surface) null, (MediaCrypto) null, 0);
            this.zzams.setString("mime", zzho.zzahc);
            return;
        }
        mediaCodec.configure(zzho.zzfa(), (Surface) null, (MediaCrypto) null, 0);
        this.zzams = null;
    }

    /* access modifiers changed from: protected */
    public final void zzc(String str, long j, long j2) {
        this.zzamo.zzb(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzho zzho) throws zzhd {
        super.zzd(zzho);
        this.zzamo.zzc(zzho);
        this.zzaho = "audio/raw".equals(zzho.zzahc) ? zzho.zzaho : 2;
        this.zzahm = zzho.zzahm;
    }

    /* access modifiers changed from: protected */
    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzhd {
        int[] iArr;
        int i;
        boolean z = this.zzams != null;
        String string = z ? this.zzams.getString("mime") : "audio/raw";
        if (z) {
            mediaFormat = this.zzams;
        }
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (!this.zzamr || integer != 6 || (i = this.zzahm) >= 6) {
            iArr = null;
        } else {
            iArr = new int[i];
            for (int i2 = 0; i2 < this.zzahm; i2++) {
                iArr[i2] = i2;
            }
        }
        try {
            this.zzamp.zza(string, integer, integer2, this.zzaho, 0, iArr);
        } catch (zzis e) {
            throw zzhd.zza(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    public final void zze(boolean z) throws zzhd {
        super.zze(z);
        this.zzamo.zzc(this.zzbcv);
        int i = zzei().zzaif;
        if (i != 0) {
            this.zzamp.zzz(i);
        } else {
            this.zzamp.zzft();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(long j, boolean z) throws zzhd {
        super.zza(j, z);
        this.zzamp.reset();
        this.zzamt = j;
        this.zzamu = true;
    }

    /* access modifiers changed from: protected */
    public final void onStarted() {
        super.onStarted();
        this.zzamp.play();
    }

    /* access modifiers changed from: protected */
    public final void onStopped() {
        this.zzamp.pause();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public final void zzeh() {
        try {
            this.zzamp.release();
            try {
                super.zzeh();
            } finally {
                this.zzbcv.zzgn();
                this.zzamo.zzd(this.zzbcv);
            }
        } catch (Throwable th) {
            super.zzeh();
            throw th;
        } finally {
            this.zzbcv.zzgn();
            this.zzamo.zzd(this.zzbcv);
        }
    }

    public final boolean zzfe() {
        return super.zzfe() && this.zzamp.zzfe();
    }

    public final boolean isReady() {
        return this.zzamp.zzfr() || super.isReady();
    }

    public final long zzfz() {
        long zzj = this.zzamp.zzj(zzfe());
        if (zzj != Long.MIN_VALUE) {
            if (!this.zzamu) {
                zzj = Math.max(this.zzamt, zzj);
            }
            this.zzamt = zzj;
            this.zzamu = false;
        }
        return this.zzamt;
    }

    public final zzhu zzb(zzhu zzhu) {
        return this.zzamp.zzb(zzhu);
    }

    public final zzhu zzfs() {
        return this.zzamp.zzfs();
    }

    /* access modifiers changed from: protected */
    public final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzhd {
        if (this.zzamq && (i2 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        } else if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.zzbcv.zzaoh++;
            this.zzamp.zzfo();
            return true;
        } else {
            try {
                if (!this.zzamp.zzb(byteBuffer, j3)) {
                    return false;
                }
                mediaCodec.releaseOutputBuffer(i, false);
                this.zzbcv.zzaog++;
                return true;
            } catch (zziv | zziw e) {
                throw zzhd.zza(e, getIndex());
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzge() throws zzhd {
        try {
            this.zzamp.zzfp();
        } catch (zziw e) {
            throw zzhd.zza(e, getIndex());
        }
    }

    public final void zza(int i, Object obj) throws zzhd {
        if (i == 2) {
            this.zzamp.setVolume(((Float) obj).floatValue());
        } else if (i != 3) {
            super.zza(i, obj);
        } else {
            this.zzamp.setStreamType(((Integer) obj).intValue());
        }
    }
}
