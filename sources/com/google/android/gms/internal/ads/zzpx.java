package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.nio.ByteBuffer;
import org.jetbrains.anko.DimensionsKt;
import org.joda.time.DateTimeConstants;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpx extends zzlu {
    private static final int[] zzblb = {1920, 1600, DateTimeConstants.MINUTES_PER_DAY, 1280, 960, 854, DimensionsKt.XXXHDPI, 540, DimensionsKt.XXHDPI};
    private int zzaif;
    private boolean zzalm;
    private final zzqb zzblc;
    private final zzqg zzbld;
    private final long zzble;
    private final int zzblf;
    private final boolean zzblg;
    private final long[] zzblh;
    private zzho[] zzbli;
    private zzpz zzblj;
    private Surface zzblk;
    private Surface zzbll;
    private int zzblm;
    private boolean zzbln;
    private long zzblo;
    private long zzblp;
    private int zzblq;
    private int zzblr;
    private int zzbls;
    private float zzblt;
    private int zzblu;
    private int zzblv;
    private int zzblw;
    private float zzblx;
    private int zzbly;
    private int zzblz;
    private int zzbma;
    private float zzbmb;
    zzqc zzbmc;
    private long zzbmd;
    private int zzbme;
    private final Context zzvr;

    public zzpx(Context context, zzlw zzlw, long j, Handler handler, zzqd zzqd, int i) {
        this(context, zzlw, 0, (zzjs<zzju>) null, false, handler, zzqd, -1);
    }

    private static boolean zzem(long j) {
        return j < -30000;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzpx(Context context, zzlw zzlw, long j, zzjs<zzju> zzjs, boolean z, Handler handler, zzqd zzqd, int i) {
        super(2, zzlw, (zzjs<zzju>) null, false);
        boolean z2 = false;
        this.zzble = 0;
        this.zzblf = -1;
        this.zzvr = context.getApplicationContext();
        this.zzblc = new zzqb(context);
        this.zzbld = new zzqg(handler, zzqd);
        if (zzpq.SDK_INT <= 22 && "foster".equals(zzpq.DEVICE) && "NVIDIA".equals(zzpq.MANUFACTURER)) {
            z2 = true;
        }
        this.zzblg = z2;
        this.zzblh = new long[10];
        this.zzbmd = -9223372036854775807L;
        this.zzblo = -9223372036854775807L;
        this.zzblu = -1;
        this.zzblv = -1;
        this.zzblx = -1.0f;
        this.zzblt = -1.0f;
        this.zzblm = 1;
        zzjl();
    }

    /* access modifiers changed from: protected */
    public final int zza(zzlw zzlw, zzho zzho) throws zzlz {
        boolean z;
        String str = zzho.zzahc;
        int i = 0;
        if (!zzpg.zzbg(str)) {
            return 0;
        }
        zzjn zzjn = zzho.zzahf;
        if (zzjn != null) {
            z = false;
            for (int i2 = 0; i2 < zzjn.zzaol; i2++) {
                z |= zzjn.zzag(i2).zzaom;
            }
        } else {
            z = false;
        }
        zzlr zzc = zzlw.zzc(str, z);
        boolean z2 = true;
        if (zzc == null) {
            return 1;
        }
        boolean zzbc = zzc.zzbc(zzho.zzagz);
        if (zzbc && zzho.width > 0 && zzho.height > 0) {
            if (zzpq.SDK_INT >= 21) {
                zzbc = zzc.zza(zzho.width, zzho.height, (double) zzho.zzahg);
            } else {
                if (zzho.width * zzho.height > zzly.zzhk()) {
                    z2 = false;
                }
                if (!z2) {
                    int i3 = zzho.width;
                    int i4 = zzho.height;
                    String str2 = zzpq.zzbkm;
                    StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 56);
                    sb.append("FalseCheck [legacyFrameSize, ");
                    sb.append(i3);
                    sb.append("x");
                    sb.append(i4);
                    sb.append("] [");
                    sb.append(str2);
                    sb.append("]");
                    Log.d("MediaCodecVideoRenderer", sb.toString());
                }
                zzbc = z2;
            }
        }
        int i5 = zzc.zzbbe ? 8 : 4;
        if (zzc.zzalm) {
            i = 16;
        }
        return (zzbc ? 3 : 2) | i5 | i;
    }

    /* access modifiers changed from: protected */
    public final void zze(boolean z) throws zzhd {
        super.zze(z);
        int i = zzei().zzaif;
        this.zzaif = i;
        this.zzalm = i != 0;
        this.zzbld.zzc(this.zzbcv);
        this.zzblc.enable();
    }

    /* access modifiers changed from: protected */
    public final void zza(zzho[] zzhoArr, long j) throws zzhd {
        this.zzbli = zzhoArr;
        if (this.zzbmd == -9223372036854775807L) {
            this.zzbmd = j;
        } else {
            int i = this.zzbme;
            long[] jArr = this.zzblh;
            if (i == jArr.length) {
                long j2 = jArr[i - 1];
                StringBuilder sb = new StringBuilder(65);
                sb.append("Too many stream changes, so dropping offset: ");
                sb.append(j2);
                Log.w("MediaCodecVideoRenderer", sb.toString());
            } else {
                this.zzbme = i + 1;
            }
            this.zzblh[this.zzbme - 1] = j;
        }
        super.zza(zzhoArr, j);
    }

    /* access modifiers changed from: protected */
    public final void zza(long j, boolean z) throws zzhd {
        super.zza(j, z);
        zzjj();
        this.zzblr = 0;
        int i = this.zzbme;
        if (i != 0) {
            this.zzbmd = this.zzblh[i - 1];
            this.zzbme = 0;
        }
        if (z) {
            zzji();
        } else {
            this.zzblo = -9223372036854775807L;
        }
    }

    public final boolean isReady() {
        Surface surface;
        if (super.isReady() && (this.zzbln || (((surface = this.zzbll) != null && this.zzblk == surface) || zzhe() == null))) {
            this.zzblo = -9223372036854775807L;
            return true;
        } else if (this.zzblo == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.zzblo) {
                return true;
            }
            this.zzblo = -9223372036854775807L;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void onStarted() {
        super.onStarted();
        this.zzblq = 0;
        this.zzblp = SystemClock.elapsedRealtime();
        this.zzblo = -9223372036854775807L;
    }

    /* access modifiers changed from: protected */
    public final void onStopped() {
        zzjo();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public final void zzeh() {
        this.zzblu = -1;
        this.zzblv = -1;
        this.zzblx = -1.0f;
        this.zzblt = -1.0f;
        this.zzbmd = -9223372036854775807L;
        this.zzbme = 0;
        zzjl();
        zzjj();
        this.zzblc.disable();
        this.zzbmc = null;
        this.zzalm = false;
        try {
            super.zzeh();
        } finally {
            this.zzbcv.zzgn();
            this.zzbld.zzd(this.zzbcv);
        }
    }

    public final void zza(int i, Object obj) throws zzhd {
        if (i == 1) {
            Surface surface = (Surface) obj;
            if (surface == null) {
                Surface surface2 = this.zzbll;
                if (surface2 != null) {
                    surface = surface2;
                } else {
                    zzlr zzhf = zzhf();
                    if (zzhf != null && zzn(zzhf.zzbbf)) {
                        surface = zzpt.zzc(this.zzvr, zzhf.zzbbf);
                        this.zzbll = surface;
                    }
                }
            }
            if (this.zzblk != surface) {
                this.zzblk = surface;
                int state = getState();
                if (state == 1 || state == 2) {
                    MediaCodec zzhe = zzhe();
                    if (zzpq.SDK_INT < 23 || zzhe == null || surface == null) {
                        zzhg();
                        zzhd();
                    } else {
                        zzhe.setOutputSurface(surface);
                    }
                }
                if (surface == null || surface == this.zzbll) {
                    zzjl();
                    zzjj();
                    return;
                }
                zzjn();
                zzjj();
                if (state == 2) {
                    zzji();
                }
            } else if (surface != null && surface != this.zzbll) {
                zzjn();
                if (this.zzbln) {
                    this.zzbld.zzb(this.zzblk);
                }
            }
        } else if (i == 4) {
            this.zzblm = ((Integer) obj).intValue();
            MediaCodec zzhe2 = zzhe();
            if (zzhe2 != null) {
                zzhe2.setVideoScalingMode(this.zzblm);
            }
        } else {
            super.zza(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzlr zzlr) {
        return this.zzblk != null || zzn(zzlr.zzbbf);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzlr zzlr, MediaCodec mediaCodec, zzho zzho, MediaCrypto mediaCrypto) throws zzlz {
        zzpz zzpz;
        Point point;
        zzlr zzlr2 = zzlr;
        MediaCodec mediaCodec2 = mediaCodec;
        zzho zzho2 = zzho;
        zzho[] zzhoArr = this.zzbli;
        int i = zzho2.width;
        int i2 = zzho2.height;
        int zzi = zzi(zzho);
        if (zzhoArr.length == 1) {
            zzpz = new zzpz(i, i2, zzi);
        } else {
            boolean z = false;
            for (zzho zzho3 : zzhoArr) {
                if (zza(zzlr2.zzbbe, zzho2, zzho3)) {
                    z |= zzho3.width == -1 || zzho3.height == -1;
                    i = Math.max(i, zzho3.width);
                    i2 = Math.max(i2, zzho3.height);
                    zzi = Math.max(zzi, zzi(zzho3));
                }
            }
            if (z) {
                StringBuilder sb = new StringBuilder(66);
                sb.append("Resolutions unknown. Codec max resolution: ");
                sb.append(i);
                sb.append("x");
                sb.append(i2);
                Log.w("MediaCodecVideoRenderer", sb.toString());
                boolean z2 = zzho2.height > zzho2.width;
                int i3 = z2 ? zzho2.height : zzho2.width;
                int i4 = z2 ? zzho2.width : zzho2.height;
                float f = ((float) i4) / ((float) i3);
                int[] iArr = zzblb;
                int length = iArr.length;
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        break;
                    }
                    int i6 = length;
                    int i7 = iArr[i5];
                    int[] iArr2 = iArr;
                    int i8 = (int) (((float) i7) * f);
                    if (i7 <= i3 || i8 <= i4) {
                        break;
                    }
                    int i9 = i3;
                    int i10 = i4;
                    if (zzpq.SDK_INT >= 21) {
                        int i11 = z2 ? i8 : i7;
                        if (!z2) {
                            i7 = i8;
                        }
                        Point zzd = zzlr2.zzd(i11, i7);
                        Point point2 = zzd;
                        if (zzlr2.zza(zzd.x, zzd.y, (double) zzho2.zzahg)) {
                            point = point2;
                            break;
                        }
                    } else {
                        int zzf = zzpq.zzf(i7, 16) << 4;
                        int zzf2 = zzpq.zzf(i8, 16) << 4;
                        if (zzf * zzf2 <= zzly.zzhk()) {
                            int i12 = z2 ? zzf2 : zzf;
                            if (!z2) {
                                zzf = zzf2;
                            }
                            point = new Point(i12, zzf);
                        }
                    }
                    i5++;
                    length = i6;
                    iArr = iArr2;
                    i3 = i9;
                    i4 = i10;
                }
                point = null;
                if (point != null) {
                    i = Math.max(i, point.x);
                    i2 = Math.max(i2, point.y);
                    zzi = Math.max(zzi, zza(zzho2.zzahc, i, i2));
                    StringBuilder sb2 = new StringBuilder(57);
                    sb2.append("Codec max resolution adjusted to: ");
                    sb2.append(i);
                    sb2.append("x");
                    sb2.append(i2);
                    Log.w("MediaCodecVideoRenderer", sb2.toString());
                }
            }
            zzpz = new zzpz(i, i2, zzi);
        }
        this.zzblj = zzpz;
        boolean z3 = this.zzblg;
        int i13 = this.zzaif;
        MediaFormat zzfa = zzho.zzfa();
        zzfa.setInteger("max-width", zzpz.width);
        zzfa.setInteger("max-height", zzpz.height);
        if (zzpz.zzbmf != -1) {
            zzfa.setInteger("max-input-size", zzpz.zzbmf);
        }
        if (z3) {
            zzfa.setInteger("auto-frc", 0);
        }
        if (i13 != 0) {
            zzfa.setFeatureEnabled("tunneled-playback", true);
            zzfa.setInteger("audio-session-id", i13);
        }
        if (this.zzblk == null) {
            zzoz.checkState(zzn(zzlr2.zzbbf));
            if (this.zzbll == null) {
                this.zzbll = zzpt.zzc(this.zzvr, zzlr2.zzbbf);
            }
            this.zzblk = this.zzbll;
        }
        mediaCodec2.configure(zzfa, this.zzblk, (MediaCrypto) null, 0);
        if (zzpq.SDK_INT >= 23 && this.zzalm) {
            this.zzbmc = new zzqc(this, mediaCodec2);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzhg() {
        try {
            super.zzhg();
        } finally {
            Surface surface = this.zzbll;
            if (surface != null) {
                if (this.zzblk == surface) {
                    this.zzblk = null;
                }
                this.zzbll.release();
                this.zzbll = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzc(String str, long j, long j2) {
        this.zzbld.zzb(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzho zzho) throws zzhd {
        super.zzd(zzho);
        this.zzbld.zzc(zzho);
        this.zzblt = zzho.zzahi == -1.0f ? 1.0f : zzho.zzahi;
        this.zzbls = zzj(zzho);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzjk zzjk) {
        if (zzpq.SDK_INT < 23 && this.zzalm) {
            zzjk();
        }
    }

    /* access modifiers changed from: protected */
    public final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            i = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i = mediaFormat.getInteger(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH);
        }
        this.zzblu = i;
        if (z) {
            i2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i2 = mediaFormat.getInteger(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT);
        }
        this.zzblv = i2;
        this.zzblx = this.zzblt;
        if (zzpq.SDK_INT >= 21) {
            int i3 = this.zzbls;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.zzblu;
                this.zzblu = this.zzblv;
                this.zzblv = i4;
                this.zzblx = 1.0f / this.zzblx;
            }
        } else {
            this.zzblw = this.zzbls;
        }
        mediaCodec.setVideoScalingMode(this.zzblm);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(MediaCodec mediaCodec, boolean z, zzho zzho, zzho zzho2) {
        return zza(z, zzho, zzho2) && zzho2.width <= this.zzblj.width && zzho2.height <= this.zzblj.height && zzho2.zzahd <= this.zzblj.zzbmf;
    }

    /* access modifiers changed from: protected */
    public final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) {
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j4 = j3;
        while (true) {
            int i4 = this.zzbme;
            if (i4 == 0) {
                break;
            }
            long[] jArr = this.zzblh;
            if (j4 < jArr[0]) {
                break;
            }
            this.zzbmd = jArr[0];
            int i5 = i4 - 1;
            this.zzbme = i5;
            System.arraycopy(jArr, 1, jArr, 0, i5);
        }
        long j5 = j4 - this.zzbmd;
        if (z) {
            zza(mediaCodec2, i3, j5);
            return true;
        }
        long j6 = j4 - j;
        if (this.zzblk == this.zzbll) {
            if (!zzem(j6)) {
                return false;
            }
            zza(mediaCodec2, i3, j5);
            return true;
        } else if (!this.zzbln) {
            if (zzpq.SDK_INT >= 21) {
                zza(mediaCodec, i, j5, System.nanoTime());
            } else {
                zzb(mediaCodec2, i3, j5);
            }
            return true;
        } else if (getState() != 2) {
            return false;
        } else {
            long elapsedRealtime = j6 - ((SystemClock.elapsedRealtime() * 1000) - j2);
            long nanoTime = System.nanoTime();
            long zzf = this.zzblc.zzf(j4, (elapsedRealtime * 1000) + nanoTime);
            long j7 = (zzf - nanoTime) / 1000;
            if (zzem(j7)) {
                zzpn.beginSection("dropVideoBuffer");
                mediaCodec2.releaseOutputBuffer(i3, false);
                zzpn.endSection();
                this.zzbcv.zzaoi++;
                this.zzblq++;
                this.zzblr++;
                this.zzbcv.zzaoj = Math.max(this.zzblr, this.zzbcv.zzaoj);
                if (this.zzblq == this.zzblf) {
                    zzjo();
                }
                return true;
            }
            if (zzpq.SDK_INT >= 21) {
                if (j7 < 50000) {
                    zza(mediaCodec, i, j5, zzf);
                    return true;
                }
            } else if (j7 < 30000) {
                if (j7 > 11000) {
                    try {
                        Thread.sleep((j7 - 10000) / 1000);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                zzb(mediaCodec2, i3, j5);
                return true;
            }
            return false;
        }
    }

    private final void zza(MediaCodec mediaCodec, int i, long j) {
        zzpn.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        zzpn.endSection();
        this.zzbcv.zzaoh++;
    }

    private final void zzb(MediaCodec mediaCodec, int i, long j) {
        zzjm();
        zzpn.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        zzpn.endSection();
        this.zzbcv.zzaog++;
        this.zzblr = 0;
        zzjk();
    }

    private final void zza(MediaCodec mediaCodec, int i, long j, long j2) {
        zzjm();
        zzpn.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        zzpn.endSection();
        this.zzbcv.zzaog++;
        this.zzblr = 0;
        zzjk();
    }

    private final boolean zzn(boolean z) {
        if (zzpq.SDK_INT < 23 || this.zzalm) {
            return false;
        }
        return !z || zzpt.zzc(this.zzvr);
    }

    private final void zzji() {
        this.zzblo = this.zzble > 0 ? SystemClock.elapsedRealtime() + this.zzble : -9223372036854775807L;
    }

    private final void zzjj() {
        MediaCodec zzhe;
        this.zzbln = false;
        if (zzpq.SDK_INT >= 23 && this.zzalm && (zzhe = zzhe()) != null) {
            this.zzbmc = new zzqc(this, zzhe);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzjk() {
        if (!this.zzbln) {
            this.zzbln = true;
            this.zzbld.zzb(this.zzblk);
        }
    }

    private final void zzjl() {
        this.zzbly = -1;
        this.zzblz = -1;
        this.zzbmb = -1.0f;
        this.zzbma = -1;
    }

    private final void zzjm() {
        if (this.zzbly != this.zzblu || this.zzblz != this.zzblv || this.zzbma != this.zzblw || this.zzbmb != this.zzblx) {
            this.zzbld.zzb(this.zzblu, this.zzblv, this.zzblw, this.zzblx);
            this.zzbly = this.zzblu;
            this.zzblz = this.zzblv;
            this.zzbma = this.zzblw;
            this.zzbmb = this.zzblx;
        }
    }

    private final void zzjn() {
        if (this.zzbly != -1 || this.zzblz != -1) {
            this.zzbld.zzb(this.zzblu, this.zzblv, this.zzblw, this.zzblx);
        }
    }

    private final void zzjo() {
        if (this.zzblq > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbld.zzf(this.zzblq, elapsedRealtime - this.zzblp);
            this.zzblq = 0;
            this.zzblp = elapsedRealtime;
        }
    }

    private static int zzi(zzho zzho) {
        if (zzho.zzahd != -1) {
            return zzho.zzahd;
        }
        return zza(zzho.zzahc, zzho.width, zzho.height);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(java.lang.String r7, int r8, int r9) {
        /*
            r0 = -1
            if (r8 == r0) goto L_0x0086
            if (r9 != r0) goto L_0x0007
            goto L_0x0086
        L_0x0007:
            int r1 = r7.hashCode()
            r2 = 5
            r3 = 1
            r4 = 3
            r5 = 4
            r6 = 2
            switch(r1) {
                case -1664118616: goto L_0x0046;
                case -1662541442: goto L_0x003c;
                case 1187890754: goto L_0x0032;
                case 1331836730: goto L_0x0028;
                case 1599127256: goto L_0x001e;
                case 1599127257: goto L_0x0014;
                default: goto L_0x0013;
            }
        L_0x0013:
            goto L_0x0050
        L_0x0014:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 5
            goto L_0x0051
        L_0x001e:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 3
            goto L_0x0051
        L_0x0028:
            java.lang.String r1 = "video/avc"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 2
            goto L_0x0051
        L_0x0032:
            java.lang.String r1 = "video/mp4v-es"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 1
            goto L_0x0051
        L_0x003c:
            java.lang.String r1 = "video/hevc"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 4
            goto L_0x0051
        L_0x0046:
            java.lang.String r1 = "video/3gpp"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0050
            r7 = 0
            goto L_0x0051
        L_0x0050:
            r7 = -1
        L_0x0051:
            if (r7 == 0) goto L_0x007d
            if (r7 == r3) goto L_0x007d
            if (r7 == r6) goto L_0x0061
            if (r7 == r4) goto L_0x007d
            if (r7 == r5) goto L_0x005e
            if (r7 == r2) goto L_0x005e
            return r0
        L_0x005e:
            int r8 = r8 * r9
            goto L_0x0080
        L_0x0061:
            java.lang.String r7 = com.google.android.gms.internal.ads.zzpq.MODEL
            java.lang.String r1 = "BRAVIA 4K 2015"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x006c
            return r0
        L_0x006c:
            r7 = 16
            int r8 = com.google.android.gms.internal.ads.zzpq.zzf(r8, r7)
            int r7 = com.google.android.gms.internal.ads.zzpq.zzf(r9, r7)
            int r8 = r8 * r7
            int r7 = r8 << 4
            int r8 = r7 << 4
            goto L_0x007f
        L_0x007d:
            int r8 = r8 * r9
        L_0x007f:
            r5 = 2
        L_0x0080:
            int r8 = r8 * 3
            int r5 = r5 * 2
            int r8 = r8 / r5
            return r8
        L_0x0086:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpx.zza(java.lang.String, int, int):int");
    }

    private static boolean zza(boolean z, zzho zzho, zzho zzho2) {
        if (!zzho.zzahc.equals(zzho2.zzahc) || zzj(zzho) != zzj(zzho2)) {
            return false;
        }
        if (!z) {
            return zzho.width == zzho2.width && zzho.height == zzho2.height;
        }
        return true;
    }

    private static int zzj(zzho zzho) {
        if (zzho.zzahh == -1) {
            return 0;
        }
        return zzho.zzahh;
    }
}
