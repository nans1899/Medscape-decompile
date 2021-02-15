package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzlu extends zzhb {
    private static final byte[] zzbbl = zzpq.zzbm("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private zzho zzahx;
    private ByteBuffer[] zzald;
    private final zzlw zzbbm;
    private final zzjs<zzju> zzbbn;
    private final boolean zzbbo;
    private final zzjk zzbbp;
    private final zzjk zzbbq;
    private final zzhq zzbbr;
    private final List<Long> zzbbs;
    private final MediaCodec.BufferInfo zzbbt;
    private zzjq<zzju> zzbbu;
    private zzjq<zzju> zzbbv;
    private MediaCodec zzbbw;
    private zzlr zzbbx;
    private boolean zzbby;
    private boolean zzbbz;
    private boolean zzbca;
    private boolean zzbcb;
    private boolean zzbcc;
    private boolean zzbcd;
    private boolean zzbce;
    private boolean zzbcf;
    private boolean zzbcg;
    private ByteBuffer[] zzbch;
    private long zzbci;
    private int zzbcj;
    private int zzbck;
    private boolean zzbcl;
    private boolean zzbcm;
    private int zzbcn;
    private int zzbco;
    private boolean zzbcp;
    private boolean zzbcq;
    private boolean zzbcr;
    private boolean zzbcs;
    private boolean zzbct;
    private boolean zzbcu;
    protected zzjl zzbcv;

    public zzlu(int i, zzlw zzlw, zzjs<zzju> zzjs, boolean z) {
        super(i);
        zzoz.checkState(zzpq.SDK_INT >= 16);
        this.zzbbm = (zzlw) zzoz.checkNotNull(zzlw);
        this.zzbbn = zzjs;
        this.zzbbo = z;
        this.zzbbp = new zzjk(0);
        this.zzbbq = new zzjk(0);
        this.zzbbr = new zzhq();
        this.zzbbs = new ArrayList();
        this.zzbbt = new MediaCodec.BufferInfo();
        this.zzbcn = 0;
        this.zzbco = 0;
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzhd {
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
    }

    /* access modifiers changed from: protected */
    public abstract int zza(zzlw zzlw, zzho zzho) throws zzlz;

    /* access modifiers changed from: protected */
    public void zza(zzjk zzjk) {
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzlr zzlr, MediaCodec mediaCodec, zzho zzho, MediaCrypto mediaCrypto) throws zzlz;

    /* access modifiers changed from: protected */
    public abstract boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzhd;

    /* access modifiers changed from: protected */
    public boolean zza(MediaCodec mediaCodec, boolean z, zzho zzho, zzho zzho2) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzlr zzlr) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void zzc(String str, long j, long j2) {
    }

    public final int zzeg() {
        return 4;
    }

    /* access modifiers changed from: protected */
    public void zzge() throws zzhd {
    }

    public final int zza(zzho zzho) throws zzhd {
        try {
            return zza(this.zzbbm, zzho);
        } catch (zzlz e) {
            throw zzhd.zza(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    public zzlr zza(zzlw zzlw, zzho zzho, boolean z) throws zzlz {
        return zzlw.zzc(zzho.zzahc, z);
    }

    /* access modifiers changed from: protected */
    public final void zzhd() throws zzhd {
        zzho zzho;
        if (this.zzbbw == null && (zzho = this.zzahx) != null) {
            this.zzbbu = this.zzbbv;
            String str = zzho.zzahc;
            zzjq<zzju> zzjq = this.zzbbu;
            if (zzjq != null) {
                int state = zzjq.getState();
                if (state == 0) {
                    throw zzhd.zza(this.zzbbu.zzgp(), getIndex());
                } else if (state == 3 || state == 4) {
                    zzju zzgo = this.zzbbu.zzgo();
                    throw new NoSuchMethodError();
                }
            } else {
                if (this.zzbbx == null) {
                    try {
                        this.zzbbx = zza(this.zzbbm, this.zzahx, false);
                    } catch (zzlz e) {
                        zza(new zzlt(this.zzahx, (Throwable) e, false, -49998));
                    }
                    if (this.zzbbx == null) {
                        zza(new zzlt(this.zzahx, (Throwable) null, false, -49999));
                    }
                }
                if (zza(this.zzbbx)) {
                    String str2 = this.zzbbx.name;
                    this.zzbby = zzpq.SDK_INT < 21 && this.zzahx.zzahe.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str2);
                    this.zzbbz = zzpq.SDK_INT < 18 || (zzpq.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(str2) || "OMX.SEC.avc.dec.secure".equals(str2))) || (zzpq.SDK_INT == 19 && zzpq.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str2) || "OMX.Exynos.avc.dec.secure".equals(str2)));
                    this.zzbca = zzpq.SDK_INT < 24 && ("OMX.Nvidia.h264.decode".equals(str2) || "OMX.Nvidia.h264.decode.secure".equals(str2)) && ("flounder".equals(zzpq.DEVICE) || "flounder_lte".equals(zzpq.DEVICE) || "grouper".equals(zzpq.DEVICE) || "tilapia".equals(zzpq.DEVICE));
                    this.zzbcb = zzpq.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str2) || "OMX.allwinner.video.decoder.avc".equals(str2));
                    this.zzbcc = (zzpq.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str2)) || (zzpq.SDK_INT <= 19 && "hb2000".equals(zzpq.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str2) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str2)));
                    this.zzbcd = zzpq.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str2);
                    this.zzbce = zzpq.SDK_INT <= 18 && this.zzahx.zzahm == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str2);
                    try {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        String valueOf = String.valueOf(str2);
                        zzpn.beginSection(valueOf.length() != 0 ? "createCodec:".concat(valueOf) : new String("createCodec:"));
                        this.zzbbw = MediaCodec.createByCodecName(str2);
                        zzpn.endSection();
                        zzpn.beginSection("configureCodec");
                        zza(this.zzbbx, this.zzbbw, this.zzahx, (MediaCrypto) null);
                        zzpn.endSection();
                        zzpn.beginSection("startCodec");
                        this.zzbbw.start();
                        zzpn.endSection();
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        zzc(str2, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                        this.zzbch = this.zzbbw.getInputBuffers();
                        this.zzald = this.zzbbw.getOutputBuffers();
                    } catch (Exception e2) {
                        zza(new zzlt(this.zzahx, (Throwable) e2, false, str2));
                    }
                    this.zzbci = getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : -9223372036854775807L;
                    this.zzbcj = -1;
                    this.zzbck = -1;
                    this.zzbcu = true;
                    this.zzbcv.zzaod++;
                }
            }
        }
    }

    private final void zza(zzlt zzlt) throws zzhd {
        throw zzhd.zza(zzlt, getIndex());
    }

    /* access modifiers changed from: protected */
    public final MediaCodec zzhe() {
        return this.zzbbw;
    }

    /* access modifiers changed from: protected */
    public final zzlr zzhf() {
        return this.zzbbx;
    }

    /* access modifiers changed from: protected */
    public void zze(boolean z) throws zzhd {
        this.zzbcv = new zzjl();
    }

    /* access modifiers changed from: protected */
    public void zza(long j, boolean z) throws zzhd {
        this.zzbcr = false;
        this.zzbcs = false;
        if (this.zzbbw != null) {
            this.zzbci = -9223372036854775807L;
            this.zzbcj = -1;
            this.zzbck = -1;
            this.zzbcu = true;
            this.zzbct = false;
            this.zzbcl = false;
            this.zzbbs.clear();
            this.zzbcf = false;
            this.zzbcg = false;
            if (this.zzbbz || (this.zzbcc && this.zzbcq)) {
                zzhg();
                zzhd();
            } else if (this.zzbco != 0) {
                zzhg();
                zzhd();
            } else {
                this.zzbbw.flush();
                this.zzbcp = false;
            }
            if (this.zzbcm && this.zzahx != null) {
                this.zzbcn = 1;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzeh() {
        this.zzahx = null;
        try {
            zzhg();
            try {
                if (this.zzbbu != null) {
                    this.zzbbn.zza(this.zzbbu);
                }
                try {
                    if (!(this.zzbbv == null || this.zzbbv == this.zzbbu)) {
                        this.zzbbn.zza(this.zzbbv);
                    }
                } finally {
                    this.zzbbu = null;
                    this.zzbbv = null;
                }
            } catch (Throwable th) {
                if (!(this.zzbbv == null || this.zzbbv == this.zzbbu)) {
                    this.zzbbn.zza(this.zzbbv);
                }
                throw th;
            } finally {
                this.zzbbu = null;
                this.zzbbv = null;
            }
        } catch (Throwable th2) {
            try {
                if (!(this.zzbbv == null || this.zzbbv == this.zzbbu)) {
                    this.zzbbn.zza(this.zzbbv);
                }
                throw th2;
            } finally {
                this.zzbbu = null;
                this.zzbbv = null;
            }
        } finally {
        }
    }

    /* access modifiers changed from: protected */
    public void zzhg() {
        this.zzbci = -9223372036854775807L;
        this.zzbcj = -1;
        this.zzbck = -1;
        this.zzbct = false;
        this.zzbcl = false;
        this.zzbbs.clear();
        this.zzbch = null;
        this.zzald = null;
        this.zzbbx = null;
        this.zzbcm = false;
        this.zzbcp = false;
        this.zzbby = false;
        this.zzbbz = false;
        this.zzbca = false;
        this.zzbcb = false;
        this.zzbcc = false;
        this.zzbce = false;
        this.zzbcf = false;
        this.zzbcg = false;
        this.zzbcq = false;
        this.zzbcn = 0;
        this.zzbco = 0;
        this.zzbbp.zzdd = null;
        if (this.zzbbw != null) {
            this.zzbcv.zzaoe++;
            try {
                this.zzbbw.stop();
                try {
                    this.zzbbw.release();
                    this.zzbbw = null;
                    zzjq<zzju> zzjq = this.zzbbu;
                    if (zzjq != null && this.zzbbv != zzjq) {
                        try {
                            this.zzbbn.zza(zzjq);
                        } finally {
                            this.zzbbu = null;
                        }
                    }
                } catch (Throwable th) {
                    this.zzbbw = null;
                    zzjq<zzju> zzjq2 = this.zzbbu;
                    if (!(zzjq2 == null || this.zzbbv == zzjq2)) {
                        this.zzbbn.zza(zzjq2);
                    }
                    throw th;
                } finally {
                    this.zzbbu = null;
                }
            } catch (Throwable th2) {
                this.zzbbw = null;
                zzjq<zzju> zzjq3 = this.zzbbu;
                if (!(zzjq3 == null || this.zzbbv == zzjq3)) {
                    try {
                        this.zzbbn.zza(zzjq3);
                    } finally {
                        this.zzbbu = null;
                    }
                }
                throw th2;
            } finally {
            }
        }
    }

    public final void zzb(long j, long j2) throws zzhd {
        if (this.zzbcs) {
            zzge();
            return;
        }
        if (this.zzahx == null) {
            this.zzbbq.clear();
            int zza = zza(this.zzbbr, this.zzbbq, true);
            if (zza == -5) {
                zzd(this.zzbbr.zzahx);
            } else if (zza == -4) {
                zzoz.checkState(this.zzbbq.zzgl());
                this.zzbcr = true;
                zzhi();
                return;
            } else {
                return;
            }
        }
        zzhd();
        if (this.zzbbw != null) {
            zzpn.beginSection("drainAndFeed");
            do {
            } while (zzd(j, j2));
            do {
            } while (zzhh());
            zzpn.endSection();
        } else {
            zzdp(j);
            this.zzbbq.clear();
            int zza2 = zza(this.zzbbr, this.zzbbq, false);
            if (zza2 == -5) {
                zzd(this.zzbbr.zzahx);
            } else if (zza2 == -4) {
                zzoz.checkState(this.zzbbq.zzgl());
                this.zzbcr = true;
                zzhi();
            }
        }
        this.zzbcv.zzgn();
    }

    /* JADX WARNING: Removed duplicated region for block: B:84:0x0149 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x014a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzhh() throws com.google.android.gms.internal.ads.zzhd {
        /*
            r14 = this;
            android.media.MediaCodec r0 = r14.zzbbw
            r1 = 0
            if (r0 == 0) goto L_0x01d4
            int r2 = r14.zzbco
            r3 = 2
            if (r2 == r3) goto L_0x01d4
            boolean r2 = r14.zzbcr
            if (r2 == 0) goto L_0x0010
            goto L_0x01d4
        L_0x0010:
            int r2 = r14.zzbcj
            if (r2 >= 0) goto L_0x002c
            r4 = 0
            int r0 = r0.dequeueInputBuffer(r4)
            r14.zzbcj = r0
            if (r0 >= 0) goto L_0x001f
            return r1
        L_0x001f:
            com.google.android.gms.internal.ads.zzjk r2 = r14.zzbbp
            java.nio.ByteBuffer[] r4 = r14.zzbch
            r0 = r4[r0]
            r2.zzdd = r0
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            r0.clear()
        L_0x002c:
            int r0 = r14.zzbco
            r2 = -1
            r4 = 1
            if (r0 != r4) goto L_0x0049
            boolean r0 = r14.zzbcb
            if (r0 != 0) goto L_0x0046
            r14.zzbcq = r4
            android.media.MediaCodec r5 = r14.zzbbw
            int r6 = r14.zzbcj
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 4
            r5.queueInputBuffer(r6, r7, r8, r9, r11)
            r14.zzbcj = r2
        L_0x0046:
            r14.zzbco = r3
            return r1
        L_0x0049:
            boolean r0 = r14.zzbcf
            if (r0 == 0) goto L_0x006b
            r14.zzbcf = r1
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            java.nio.ByteBuffer r0 = r0.zzdd
            byte[] r1 = zzbbl
            r0.put(r1)
            android.media.MediaCodec r5 = r14.zzbbw
            int r6 = r14.zzbcj
            r7 = 0
            byte[] r0 = zzbbl
            int r8 = r0.length
            r9 = 0
            r11 = 0
            r5.queueInputBuffer(r6, r7, r8, r9, r11)
            r14.zzbcj = r2
            r14.zzbcp = r4
            return r4
        L_0x006b:
            boolean r0 = r14.zzbct
            if (r0 == 0) goto L_0x0072
            r0 = -4
            r5 = 0
            goto L_0x00aa
        L_0x0072:
            int r0 = r14.zzbcn
            if (r0 != r4) goto L_0x0097
            r0 = 0
        L_0x0077:
            com.google.android.gms.internal.ads.zzho r5 = r14.zzahx
            java.util.List<byte[]> r5 = r5.zzahe
            int r5 = r5.size()
            if (r0 >= r5) goto L_0x0095
            com.google.android.gms.internal.ads.zzho r5 = r14.zzahx
            java.util.List<byte[]> r5 = r5.zzahe
            java.lang.Object r5 = r5.get(r0)
            byte[] r5 = (byte[]) r5
            com.google.android.gms.internal.ads.zzjk r6 = r14.zzbbp
            java.nio.ByteBuffer r6 = r6.zzdd
            r6.put(r5)
            int r0 = r0 + 1
            goto L_0x0077
        L_0x0095:
            r14.zzbcn = r3
        L_0x0097:
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            java.nio.ByteBuffer r0 = r0.zzdd
            int r0 = r0.position()
            com.google.android.gms.internal.ads.zzhq r5 = r14.zzbbr
            com.google.android.gms.internal.ads.zzjk r6 = r14.zzbbp
            int r5 = r14.zza((com.google.android.gms.internal.ads.zzhq) r5, (com.google.android.gms.internal.ads.zzjk) r6, (boolean) r1)
            r13 = r5
            r5 = r0
            r0 = r13
        L_0x00aa:
            r6 = -3
            if (r0 != r6) goto L_0x00ae
            return r1
        L_0x00ae:
            r6 = -5
            if (r0 != r6) goto L_0x00c4
            int r0 = r14.zzbcn
            if (r0 != r3) goto L_0x00bc
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            r0.clear()
            r14.zzbcn = r4
        L_0x00bc:
            com.google.android.gms.internal.ads.zzhq r0 = r14.zzbbr
            com.google.android.gms.internal.ads.zzho r0 = r0.zzahx
            r14.zzd(r0)
            return r4
        L_0x00c4:
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            boolean r0 = r0.zzgl()
            if (r0 == 0) goto L_0x0100
            int r0 = r14.zzbcn
            if (r0 != r3) goto L_0x00d7
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            r0.clear()
            r14.zzbcn = r4
        L_0x00d7:
            r14.zzbcr = r4
            boolean r0 = r14.zzbcp
            if (r0 != 0) goto L_0x00e1
            r14.zzhi()
            return r1
        L_0x00e1:
            boolean r0 = r14.zzbcb     // Catch:{ CryptoException -> 0x00f6 }
            if (r0 != 0) goto L_0x00f5
            r14.zzbcq = r4     // Catch:{ CryptoException -> 0x00f6 }
            android.media.MediaCodec r5 = r14.zzbbw     // Catch:{ CryptoException -> 0x00f6 }
            int r6 = r14.zzbcj     // Catch:{ CryptoException -> 0x00f6 }
            r7 = 0
            r8 = 0
            r9 = 0
            r11 = 4
            r5.queueInputBuffer(r6, r7, r8, r9, r11)     // Catch:{ CryptoException -> 0x00f6 }
            r14.zzbcj = r2     // Catch:{ CryptoException -> 0x00f6 }
        L_0x00f5:
            return r1
        L_0x00f6:
            r0 = move-exception
            int r1 = r14.getIndex()
            com.google.android.gms.internal.ads.zzhd r0 = com.google.android.gms.internal.ads.zzhd.zza(r0, r1)
            throw r0
        L_0x0100:
            boolean r0 = r14.zzbcu
            if (r0 == 0) goto L_0x0118
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            boolean r0 = r0.zzgm()
            if (r0 != 0) goto L_0x0118
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            r0.clear()
            int r0 = r14.zzbcn
            if (r0 != r3) goto L_0x0117
            r14.zzbcn = r4
        L_0x0117:
            return r4
        L_0x0118:
            r14.zzbcu = r1
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp
            boolean r0 = r0.isEncrypted()
            com.google.android.gms.internal.ads.zzjq<com.google.android.gms.internal.ads.zzju> r3 = r14.zzbbu
            if (r3 == 0) goto L_0x0144
            int r3 = r3.getState()
            if (r3 == 0) goto L_0x0135
            r6 = 4
            if (r3 == r6) goto L_0x0144
            if (r0 != 0) goto L_0x0133
            boolean r3 = r14.zzbbo
            if (r3 != 0) goto L_0x0144
        L_0x0133:
            r3 = 1
            goto L_0x0145
        L_0x0135:
            com.google.android.gms.internal.ads.zzjq<com.google.android.gms.internal.ads.zzju> r0 = r14.zzbbu
            com.google.android.gms.internal.ads.zzjp r0 = r0.zzgp()
            int r1 = r14.getIndex()
            com.google.android.gms.internal.ads.zzhd r0 = com.google.android.gms.internal.ads.zzhd.zza(r0, r1)
            throw r0
        L_0x0144:
            r3 = 0
        L_0x0145:
            r14.zzbct = r3
            if (r3 == 0) goto L_0x014a
            return r1
        L_0x014a:
            boolean r3 = r14.zzbby
            if (r3 == 0) goto L_0x0164
            if (r0 != 0) goto L_0x0164
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp
            java.nio.ByteBuffer r3 = r3.zzdd
            com.google.android.gms.internal.ads.zzpf.zzp(r3)
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp
            java.nio.ByteBuffer r3 = r3.zzdd
            int r3 = r3.position()
            if (r3 != 0) goto L_0x0162
            return r4
        L_0x0162:
            r14.zzbby = r1
        L_0x0164:
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            long r10 = r3.zzaob     // Catch:{ CryptoException -> 0x01ca }
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            boolean r3 = r3.zzgk()     // Catch:{ CryptoException -> 0x01ca }
            if (r3 == 0) goto L_0x0179
            java.util.List<java.lang.Long> r3 = r14.zzbbs     // Catch:{ CryptoException -> 0x01ca }
            java.lang.Long r6 = java.lang.Long.valueOf(r10)     // Catch:{ CryptoException -> 0x01ca }
            r3.add(r6)     // Catch:{ CryptoException -> 0x01ca }
        L_0x0179:
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            java.nio.ByteBuffer r3 = r3.zzdd     // Catch:{ CryptoException -> 0x01ca }
            r3.flip()     // Catch:{ CryptoException -> 0x01ca }
            com.google.android.gms.internal.ads.zzjk r3 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            r14.zza((com.google.android.gms.internal.ads.zzjk) r3)     // Catch:{ CryptoException -> 0x01ca }
            if (r0 == 0) goto L_0x01ab
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            com.google.android.gms.internal.ads.zzjg r0 = r0.zzaoa     // Catch:{ CryptoException -> 0x01ca }
            android.media.MediaCodec$CryptoInfo r9 = r0.zzgj()     // Catch:{ CryptoException -> 0x01ca }
            if (r5 != 0) goto L_0x0192
            goto L_0x01a1
        L_0x0192:
            int[] r0 = r9.numBytesOfClearData     // Catch:{ CryptoException -> 0x01ca }
            if (r0 != 0) goto L_0x019a
            int[] r0 = new int[r4]     // Catch:{ CryptoException -> 0x01ca }
            r9.numBytesOfClearData = r0     // Catch:{ CryptoException -> 0x01ca }
        L_0x019a:
            int[] r0 = r9.numBytesOfClearData     // Catch:{ CryptoException -> 0x01ca }
            r3 = r0[r1]     // Catch:{ CryptoException -> 0x01ca }
            int r3 = r3 + r5
            r0[r1] = r3     // Catch:{ CryptoException -> 0x01ca }
        L_0x01a1:
            android.media.MediaCodec r6 = r14.zzbbw     // Catch:{ CryptoException -> 0x01ca }
            int r7 = r14.zzbcj     // Catch:{ CryptoException -> 0x01ca }
            r8 = 0
            r12 = 0
            r6.queueSecureInputBuffer(r7, r8, r9, r10, r12)     // Catch:{ CryptoException -> 0x01ca }
            goto L_0x01bc
        L_0x01ab:
            android.media.MediaCodec r6 = r14.zzbbw     // Catch:{ CryptoException -> 0x01ca }
            int r7 = r14.zzbcj     // Catch:{ CryptoException -> 0x01ca }
            r8 = 0
            com.google.android.gms.internal.ads.zzjk r0 = r14.zzbbp     // Catch:{ CryptoException -> 0x01ca }
            java.nio.ByteBuffer r0 = r0.zzdd     // Catch:{ CryptoException -> 0x01ca }
            int r9 = r0.limit()     // Catch:{ CryptoException -> 0x01ca }
            r12 = 0
            r6.queueInputBuffer(r7, r8, r9, r10, r12)     // Catch:{ CryptoException -> 0x01ca }
        L_0x01bc:
            r14.zzbcj = r2     // Catch:{ CryptoException -> 0x01ca }
            r14.zzbcp = r4     // Catch:{ CryptoException -> 0x01ca }
            r14.zzbcn = r1     // Catch:{ CryptoException -> 0x01ca }
            com.google.android.gms.internal.ads.zzjl r0 = r14.zzbcv     // Catch:{ CryptoException -> 0x01ca }
            int r1 = r0.zzaof     // Catch:{ CryptoException -> 0x01ca }
            int r1 = r1 + r4
            r0.zzaof = r1     // Catch:{ CryptoException -> 0x01ca }
            return r4
        L_0x01ca:
            r0 = move-exception
            int r1 = r14.getIndex()
            com.google.android.gms.internal.ads.zzhd r0 = com.google.android.gms.internal.ads.zzhd.zza(r0, r1)
            throw r0
        L_0x01d4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzlu.zzhh():boolean");
    }

    /* access modifiers changed from: protected */
    public void zzd(zzho zzho) throws zzhd {
        zzjn zzjn;
        MediaCodec mediaCodec;
        zzho zzho2 = this.zzahx;
        this.zzahx = zzho;
        zzjn zzjn2 = zzho.zzahf;
        if (zzho2 == null) {
            zzjn = null;
        } else {
            zzjn = zzho2.zzahf;
        }
        boolean zza = zzpq.zza(zzjn2, zzjn);
        boolean z = true;
        if (!zza) {
            if (this.zzahx.zzahf != null) {
                zzjs<zzju> zzjs = this.zzbbn;
                if (zzjs != null) {
                    zzjq<zzju> zza2 = zzjs.zza(Looper.myLooper(), this.zzahx.zzahf);
                    this.zzbbv = zza2;
                    if (zza2 == this.zzbbu) {
                        this.zzbbn.zza(zza2);
                    }
                } else {
                    throw zzhd.zza(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
            } else {
                this.zzbbv = null;
            }
        }
        if (this.zzbbv == this.zzbbu && (mediaCodec = this.zzbbw) != null && zza(mediaCodec, this.zzbbx.zzbbe, zzho2, this.zzahx)) {
            this.zzbcm = true;
            this.zzbcn = 1;
            if (!(this.zzbca && this.zzahx.width == zzho2.width && this.zzahx.height == zzho2.height)) {
                z = false;
            }
            this.zzbcf = z;
        } else if (this.zzbcp) {
            this.zzbco = 1;
        } else {
            zzhg();
            zzhd();
        }
    }

    public boolean zzfe() {
        return this.zzbcs;
    }

    public boolean isReady() {
        if (this.zzahx == null || this.zzbct) {
            return false;
        }
        if (zzej() || this.zzbck >= 0) {
            return true;
        }
        return this.zzbci != -9223372036854775807L && SystemClock.elapsedRealtime() < this.zzbci;
    }

    private final boolean zzd(long j, long j2) throws zzhd {
        boolean z;
        boolean z2;
        if (this.zzbck < 0) {
            if (!this.zzbcd || !this.zzbcq) {
                this.zzbck = this.zzbbw.dequeueOutputBuffer(this.zzbbt, 0);
            } else {
                try {
                    this.zzbck = this.zzbbw.dequeueOutputBuffer(this.zzbbt, 0);
                } catch (IllegalStateException unused) {
                    zzhi();
                    if (this.zzbcs) {
                        zzhg();
                    }
                    return false;
                }
            }
            int i = this.zzbck;
            if (i >= 0) {
                if (this.zzbcg) {
                    this.zzbcg = false;
                    this.zzbbw.releaseOutputBuffer(i, false);
                    this.zzbck = -1;
                    return true;
                } else if ((this.zzbbt.flags & 4) != 0) {
                    zzhi();
                    this.zzbck = -1;
                    return false;
                } else {
                    ByteBuffer byteBuffer = this.zzald[this.zzbck];
                    if (byteBuffer != null) {
                        byteBuffer.position(this.zzbbt.offset);
                        byteBuffer.limit(this.zzbbt.offset + this.zzbbt.size);
                    }
                    long j3 = this.zzbbt.presentationTimeUs;
                    int size = this.zzbbs.size();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= size) {
                            z2 = false;
                            break;
                        } else if (this.zzbbs.get(i2).longValue() == j3) {
                            this.zzbbs.remove(i2);
                            z2 = true;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    this.zzbcl = z2;
                }
            } else if (i == -2) {
                MediaFormat outputFormat = this.zzbbw.getOutputFormat();
                if (this.zzbca && outputFormat.getInteger(GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH) == 32 && outputFormat.getInteger(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT) == 32) {
                    this.zzbcg = true;
                } else {
                    if (this.zzbce) {
                        outputFormat.setInteger("channel-count", 1);
                    }
                    onOutputFormatChanged(this.zzbbw, outputFormat);
                }
                return true;
            } else if (i == -3) {
                this.zzald = this.zzbbw.getOutputBuffers();
                return true;
            } else {
                if (this.zzbcb && (this.zzbcr || this.zzbco == 2)) {
                    zzhi();
                }
                return false;
            }
        }
        if (!this.zzbcd || !this.zzbcq) {
            MediaCodec mediaCodec = this.zzbbw;
            ByteBuffer[] byteBufferArr = this.zzald;
            int i3 = this.zzbck;
            z = zza(j, j2, mediaCodec, byteBufferArr[i3], i3, this.zzbbt.flags, this.zzbbt.presentationTimeUs, this.zzbcl);
        } else {
            try {
                z = zza(j, j2, this.zzbbw, this.zzald[this.zzbck], this.zzbck, this.zzbbt.flags, this.zzbbt.presentationTimeUs, this.zzbcl);
            } catch (IllegalStateException unused2) {
                zzhi();
                if (this.zzbcs) {
                    zzhg();
                }
                return false;
            }
        }
        if (!z) {
            return false;
        }
        long j4 = this.zzbbt.presentationTimeUs;
        this.zzbck = -1;
        return true;
    }

    private final void zzhi() throws zzhd {
        if (this.zzbco == 2) {
            zzhg();
            zzhd();
            return;
        }
        this.zzbcs = true;
        zzge();
    }
}
