package com.google.android.gms.internal.ads;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzio {
    private static boolean zzajn = false;
    private static boolean zzajo = false;
    private int streamType;
    private zzhu zzafh;
    private int zzahn;
    private final zzif zzajp = null;
    private final zziz zzajq;
    private final zzje zzajr;
    private final zzie[] zzajs;
    private final zziu zzajt;
    /* access modifiers changed from: private */
    public final ConditionVariable zzaju;
    private final long[] zzajv;
    private final zziq zzajw;
    private final LinkedList<zzix> zzajx;
    private AudioTrack zzajy;
    private int zzajz;
    private int zzaka;
    private int zzakb;
    private boolean zzakc;
    private int zzakd;
    private long zzake;
    private zzhu zzakf;
    private long zzakg;
    private long zzakh;
    private ByteBuffer zzaki;
    private int zzakj;
    private int zzakk;
    private int zzakl;
    private long zzakm;
    private long zzakn;
    private boolean zzako;
    private long zzakp;
    private Method zzakq;
    private int zzakr;
    private long zzaks;
    private long zzakt;
    private int zzaku;
    private long zzakv;
    private long zzakw;
    private int zzakx;
    private int zzaky;
    private long zzakz;
    private long zzala;
    private long zzalb;
    private zzie[] zzalc;
    private ByteBuffer[] zzald;
    private ByteBuffer zzale;
    private ByteBuffer zzalf;
    private byte[] zzalg;
    private int zzalh;
    private int zzali;
    private boolean zzalj;
    private boolean zzalk;
    private int zzall;
    private boolean zzalm;
    private boolean zzaln;
    private long zzalo;
    private float zzdj;

    public zzio(zzif zzif, zzie[] zzieArr, zziu zziu) {
        this.zzajt = zziu;
        this.zzaju = new ConditionVariable(true);
        if (zzpq.SDK_INT >= 18) {
            try {
                this.zzakq = AudioTrack.class.getMethod("getLatency", (Class[]) null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (zzpq.SDK_INT >= 19) {
            this.zzajw = new zzit();
        } else {
            this.zzajw = new zziq((zzir) null);
        }
        this.zzajq = new zziz();
        this.zzajr = new zzje();
        zzie[] zzieArr2 = new zzie[(zzieArr.length + 3)];
        this.zzajs = zzieArr2;
        zzieArr2[0] = new zzjc();
        zzie[] zzieArr3 = this.zzajs;
        zzieArr3[1] = this.zzajq;
        System.arraycopy(zzieArr, 0, zzieArr3, 2, zzieArr.length);
        this.zzajs[zzieArr.length + 2] = this.zzajr;
        this.zzajv = new long[10];
        this.zzdj = 1.0f;
        this.zzaky = 0;
        this.streamType = 3;
        this.zzall = 0;
        this.zzafh = zzhu.zzahz;
        this.zzali = -1;
        this.zzalc = new zzie[0];
        this.zzald = new ByteBuffer[0];
        this.zzajx = new LinkedList<>();
    }

    public final boolean zzay(String str) {
        zzif zzif = this.zzajp;
        return zzif != null && zzif.zzw(zzaz(str));
    }

    public final long zzj(boolean z) {
        long j;
        long j2;
        long j3;
        long j4;
        if (!(isInitialized() && this.zzaky != 0)) {
            return Long.MIN_VALUE;
        }
        if (this.zzajy.getPlayState() == 3) {
            long zzfz = this.zzajw.zzfz();
            if (zzfz != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - this.zzakn >= 30000) {
                    long[] jArr = this.zzajv;
                    int i = this.zzakk;
                    jArr[i] = zzfz - nanoTime;
                    this.zzakk = (i + 1) % 10;
                    int i2 = this.zzakl;
                    if (i2 < 10) {
                        this.zzakl = i2 + 1;
                    }
                    this.zzakn = nanoTime;
                    this.zzakm = 0;
                    int i3 = 0;
                    while (true) {
                        int i4 = this.zzakl;
                        if (i3 >= i4) {
                            break;
                        }
                        this.zzakm += this.zzajv[i3] / ((long) i4);
                        i3++;
                    }
                }
                if (!zzfx() && nanoTime - this.zzakp >= 500000) {
                    boolean zzga = this.zzajw.zzga();
                    this.zzako = zzga;
                    if (zzga) {
                        long zzgb = this.zzajw.zzgb() / 1000;
                        long zzgc = this.zzajw.zzgc();
                        if (zzgb < this.zzala) {
                            this.zzako = false;
                        } else if (Math.abs(zzgb - nanoTime) > 5000000) {
                            StringBuilder sb = new StringBuilder(136);
                            sb.append("Spurious audio timestamp (system clock mismatch): ");
                            sb.append(zzgc);
                            sb.append(", ");
                            sb.append(zzgb);
                            sb.append(", ");
                            sb.append(nanoTime);
                            sb.append(", ");
                            sb.append(zzfz);
                            Log.w("AudioTrack", sb.toString());
                            this.zzako = false;
                        } else if (Math.abs(zzdw(zzgc) - zzfz) > 5000000) {
                            StringBuilder sb2 = new StringBuilder(138);
                            sb2.append("Spurious audio timestamp (frame position mismatch): ");
                            sb2.append(zzgc);
                            sb2.append(", ");
                            sb2.append(zzgb);
                            sb2.append(", ");
                            sb2.append(nanoTime);
                            sb2.append(", ");
                            sb2.append(zzfz);
                            Log.w("AudioTrack", sb2.toString());
                            this.zzako = false;
                        }
                    }
                    Method method = this.zzakq;
                    if (method != null && !this.zzakc) {
                        try {
                            long intValue = (((long) ((Integer) method.invoke(this.zzajy, (Object[]) null)).intValue()) * 1000) - this.zzake;
                            this.zzalb = intValue;
                            long max = Math.max(intValue, 0);
                            this.zzalb = max;
                            if (max > 5000000) {
                                StringBuilder sb3 = new StringBuilder(61);
                                sb3.append("Ignoring impossibly large audio latency: ");
                                sb3.append(max);
                                Log.w("AudioTrack", sb3.toString());
                                this.zzalb = 0;
                            }
                        } catch (Exception unused) {
                            this.zzakq = null;
                        }
                    }
                    this.zzakp = nanoTime;
                }
            }
        }
        long nanoTime2 = System.nanoTime() / 1000;
        if (this.zzako) {
            j = zzdw(this.zzajw.zzgc() + zzdx(nanoTime2 - (this.zzajw.zzgb() / 1000)));
        } else {
            if (this.zzakl == 0) {
                j = this.zzajw.zzfz();
            } else {
                j = nanoTime2 + this.zzakm;
            }
            if (!z) {
                j -= this.zzalb;
            }
        }
        long j5 = this.zzakz;
        while (!this.zzajx.isEmpty() && j >= this.zzajx.getFirst().zzagv) {
            zzix remove = this.zzajx.remove();
            this.zzafh = remove.zzafh;
            this.zzakh = remove.zzagv;
            this.zzakg = remove.zzame - this.zzakz;
        }
        if (this.zzafh.zzaia == 1.0f) {
            j2 = (j + this.zzakg) - this.zzakh;
        } else {
            if (!this.zzajx.isEmpty() || this.zzajr.zzgg() < 1024) {
                j3 = this.zzakg;
                j4 = (long) (((double) this.zzafh.zzaia) * ((double) (j - this.zzakh)));
            } else {
                j3 = this.zzakg;
                j4 = zzpq.zza(j - this.zzakh, this.zzajr.zzgf(), this.zzajr.zzgg());
            }
            j2 = j4 + j3;
        }
        return j5 + j2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d3 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r8, int r9, int r10, int r11, int r12, int[] r13) throws com.google.android.gms.internal.ads.zzis {
        /*
            r7 = this;
            java.lang.String r12 = "audio/raw"
            boolean r12 = r12.equals(r8)
            r0 = 1
            r12 = r12 ^ r0
            if (r12 == 0) goto L_0x000f
            int r8 = zzaz(r8)
            goto L_0x0010
        L_0x000f:
            r8 = r11
        L_0x0010:
            r1 = 0
            if (r12 != 0) goto L_0x004a
            int r11 = com.google.android.gms.internal.ads.zzpq.zzg(r11, r9)
            r7.zzakr = r11
            com.google.android.gms.internal.ads.zziz r11 = r7.zzajq
            r11.zzb(r13)
            com.google.android.gms.internal.ads.zzie[] r11 = r7.zzajs
            int r13 = r11.length
            r2 = 0
            r3 = 0
        L_0x0023:
            if (r2 >= r13) goto L_0x0044
            r4 = r11[r2]
            boolean r5 = r4.zzb(r10, r9, r8)     // Catch:{ zzih -> 0x003d }
            r3 = r3 | r5
            boolean r5 = r4.isActive()
            if (r5 == 0) goto L_0x003a
            int r9 = r4.zzfj()
            int r8 = r4.zzfk()
        L_0x003a:
            int r2 = r2 + 1
            goto L_0x0023
        L_0x003d:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzis r9 = new com.google.android.gms.internal.ads.zzis
            r9.<init>((java.lang.Throwable) r8)
            throw r9
        L_0x0044:
            if (r3 == 0) goto L_0x004b
            r7.zzfn()
            goto L_0x004b
        L_0x004a:
            r3 = 0
        L_0x004b:
            r11 = 252(0xfc, float:3.53E-43)
            r13 = 12
            switch(r9) {
                case 1: goto L_0x0080;
                case 2: goto L_0x007d;
                case 3: goto L_0x007a;
                case 4: goto L_0x0077;
                case 5: goto L_0x0074;
                case 6: goto L_0x0071;
                case 7: goto L_0x006e;
                case 8: goto L_0x006b;
                default: goto L_0x0052;
            }
        L_0x0052:
            com.google.android.gms.internal.ads.zzis r8 = new com.google.android.gms.internal.ads.zzis
            r10 = 38
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r10)
            java.lang.String r10 = "Unsupported channel count: "
            r11.append(r10)
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            r8.<init>((java.lang.String) r9)
            throw r8
        L_0x006b:
            int r2 = com.google.android.gms.internal.ads.zzha.CHANNEL_OUT_7POINT1_SURROUND
            goto L_0x0081
        L_0x006e:
            r2 = 1276(0x4fc, float:1.788E-42)
            goto L_0x0081
        L_0x0071:
            r2 = 252(0xfc, float:3.53E-43)
            goto L_0x0081
        L_0x0074:
            r2 = 220(0xdc, float:3.08E-43)
            goto L_0x0081
        L_0x0077:
            r2 = 204(0xcc, float:2.86E-43)
            goto L_0x0081
        L_0x007a:
            r2 = 28
            goto L_0x0081
        L_0x007d:
            r2 = 12
            goto L_0x0081
        L_0x0080:
            r2 = 4
        L_0x0081:
            int r4 = com.google.android.gms.internal.ads.zzpq.SDK_INT
            r5 = 23
            r6 = 5
            if (r4 > r5) goto L_0x00a8
            java.lang.String r4 = com.google.android.gms.internal.ads.zzpq.DEVICE
            java.lang.String r5 = "foster"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x00a8
            java.lang.String r4 = com.google.android.gms.internal.ads.zzpq.MANUFACTURER
            java.lang.String r5 = "NVIDIA"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x00a8
            r4 = 3
            if (r9 == r4) goto L_0x00a9
            if (r9 == r6) goto L_0x00a9
            r11 = 7
            if (r9 == r11) goto L_0x00a5
            goto L_0x00a8
        L_0x00a5:
            int r11 = com.google.android.gms.internal.ads.zzha.CHANNEL_OUT_7POINT1_SURROUND
            goto L_0x00a9
        L_0x00a8:
            r11 = r2
        L_0x00a9:
            int r2 = com.google.android.gms.internal.ads.zzpq.SDK_INT
            r4 = 25
            if (r2 > r4) goto L_0x00be
            java.lang.String r2 = com.google.android.gms.internal.ads.zzpq.DEVICE
            java.lang.String r4 = "fugu"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x00be
            if (r12 == 0) goto L_0x00be
            if (r9 != r0) goto L_0x00be
            goto L_0x00bf
        L_0x00be:
            r13 = r11
        L_0x00bf:
            if (r3 != 0) goto L_0x00d4
            boolean r11 = r7.isInitialized()
            if (r11 == 0) goto L_0x00d4
            int r11 = r7.zzaka
            if (r11 != r8) goto L_0x00d4
            int r11 = r7.zzahn
            if (r11 != r10) goto L_0x00d4
            int r11 = r7.zzajz
            if (r11 != r13) goto L_0x00d4
            return
        L_0x00d4:
            r7.reset()
            r7.zzaka = r8
            r7.zzakc = r12
            r7.zzahn = r10
            r7.zzajz = r13
            r11 = 2
            if (r12 == 0) goto L_0x00e3
            goto L_0x00e4
        L_0x00e3:
            r8 = 2
        L_0x00e4:
            r7.zzakb = r8
            int r8 = com.google.android.gms.internal.ads.zzpq.zzg(r11, r9)
            r7.zzaku = r8
            if (r12 == 0) goto L_0x00fd
            int r8 = r7.zzakb
            if (r8 == r6) goto L_0x00fa
            r9 = 6
            if (r8 != r9) goto L_0x00f6
            goto L_0x00fa
        L_0x00f6:
            r8 = 49152(0xc000, float:6.8877E-41)
            goto L_0x0133
        L_0x00fa:
            r8 = 20480(0x5000, float:2.8699E-41)
            goto L_0x0133
        L_0x00fd:
            int r8 = r7.zzakb
            int r8 = android.media.AudioTrack.getMinBufferSize(r10, r13, r8)
            r9 = -2
            if (r8 == r9) goto L_0x0107
            goto L_0x0108
        L_0x0107:
            r0 = 0
        L_0x0108:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            int r9 = r8 << 2
            r10 = 250000(0x3d090, double:1.235164E-318)
            long r10 = r7.zzdx(r10)
            int r11 = (int) r10
            int r10 = r7.zzaku
            int r10 = r10 * r11
            long r0 = (long) r8
            r2 = 750000(0xb71b0, double:3.70549E-318)
            long r2 = r7.zzdx(r2)
            int r8 = r7.zzaku
            long r4 = (long) r8
            long r2 = r2 * r4
            long r0 = java.lang.Math.max(r0, r2)
            int r8 = (int) r0
            if (r9 >= r10) goto L_0x012f
            r8 = r10
            goto L_0x0133
        L_0x012f:
            if (r9 <= r8) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r8 = r9
        L_0x0133:
            r7.zzakd = r8
            if (r12 == 0) goto L_0x013d
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x0145
        L_0x013d:
            int r9 = r7.zzaku
            int r8 = r8 / r9
            long r8 = (long) r8
            long r8 = r7.zzdw(r8)
        L_0x0145:
            r7.zzake = r8
            com.google.android.gms.internal.ads.zzhu r8 = r7.zzafh
            r7.zzb(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzio.zza(java.lang.String, int, int, int, int, int[]):void");
    }

    private final void zzfn() {
        ArrayList arrayList = new ArrayList();
        for (zzie zzie : this.zzajs) {
            if (zzie.isActive()) {
                arrayList.add(zzie);
            } else {
                zzie.flush();
            }
        }
        int size = arrayList.size();
        this.zzalc = (zzie[]) arrayList.toArray(new zzie[size]);
        this.zzald = new ByteBuffer[size];
        for (int i = 0; i < size; i++) {
            zzie zzie2 = this.zzalc[i];
            zzie2.flush();
            this.zzald[i] = zzie2.zzfm();
        }
    }

    public final void play() {
        this.zzalk = true;
        if (isInitialized()) {
            this.zzala = System.nanoTime() / 1000;
            this.zzajy.play();
        }
    }

    public final void zzfo() {
        if (this.zzaky == 1) {
            this.zzaky = 2;
        }
    }

    public final boolean zzb(ByteBuffer byteBuffer, long j) throws zziv, zziw {
        int i;
        int i2;
        ByteBuffer byteBuffer2 = byteBuffer;
        long j2 = j;
        ByteBuffer byteBuffer3 = this.zzale;
        zzoz.checkArgument(byteBuffer3 == null || byteBuffer2 == byteBuffer3);
        if (!isInitialized()) {
            this.zzaju.block();
            if (this.zzalm) {
                this.zzajy = new AudioTrack(new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(16).build(), new AudioFormat.Builder().setChannelMask(this.zzajz).setEncoding(this.zzakb).setSampleRate(this.zzahn).build(), this.zzakd, 1, this.zzall);
            } else if (this.zzall == 0) {
                this.zzajy = new AudioTrack(this.streamType, this.zzahn, this.zzajz, this.zzakb, this.zzakd, 1);
            } else {
                this.zzajy = new AudioTrack(this.streamType, this.zzahn, this.zzajz, this.zzakb, this.zzakd, 1, this.zzall);
            }
            int state = this.zzajy.getState();
            if (state == 1) {
                int audioSessionId = this.zzajy.getAudioSessionId();
                if (this.zzall != audioSessionId) {
                    this.zzall = audioSessionId;
                    this.zzajt.zzx(audioSessionId);
                }
                this.zzajw.zza(this.zzajy, zzfx());
                zzfu();
                this.zzaln = false;
                if (this.zzalk) {
                    play();
                }
            } else {
                try {
                    this.zzajy.release();
                } catch (Exception unused) {
                } finally {
                    this.zzajy = null;
                }
                throw new zziv(state, this.zzahn, this.zzajz, this.zzakd);
            }
        }
        if (zzfx()) {
            if (this.zzajy.getPlayState() == 2) {
                this.zzaln = false;
                return false;
            } else if (this.zzajy.getPlayState() == 1 && this.zzajw.zzfy() != 0) {
                return false;
            }
        }
        boolean z = this.zzaln;
        boolean zzfr = zzfr();
        this.zzaln = zzfr;
        if (z && !zzfr && this.zzajy.getPlayState() != 1) {
            this.zzajt.zzc(this.zzakd, zzha.zzdm(this.zzake), SystemClock.elapsedRealtime() - this.zzalo);
        }
        if (this.zzale == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.zzakc && this.zzakx == 0) {
                int i3 = this.zzakb;
                if (i3 == 7 || i3 == 8) {
                    i2 = zziy.zzo(byteBuffer);
                } else if (i3 == 5) {
                    i2 = zzic.zzfi();
                } else if (i3 == 6) {
                    i2 = zzic.zzm(byteBuffer);
                } else {
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unexpected audio encoding: ");
                    sb.append(i3);
                    throw new IllegalStateException(sb.toString());
                }
                this.zzakx = i2;
            }
            if (this.zzakf != null) {
                if (!zzfq()) {
                    return false;
                }
                LinkedList<zzix> linkedList = this.zzajx;
                zzix zzix = r11;
                zzix zzix2 = new zzix(this.zzakf, Math.max(0, j2), zzdw(zzfv()), (zzir) null);
                linkedList.add(zzix);
                this.zzakf = null;
                zzfn();
            }
            if (this.zzaky == 0) {
                this.zzakz = Math.max(0, j2);
                this.zzaky = 1;
            } else {
                long zzdw = this.zzakz + zzdw(this.zzakc ? this.zzakt : this.zzaks / ((long) this.zzakr));
                if (this.zzaky != 1 || Math.abs(zzdw - j2) <= 200000) {
                    i = 2;
                } else {
                    StringBuilder sb2 = new StringBuilder(80);
                    sb2.append("Discontinuity detected [expected ");
                    sb2.append(zzdw);
                    sb2.append(", got ");
                    sb2.append(j2);
                    sb2.append("]");
                    Log.e("AudioTrack", sb2.toString());
                    i = 2;
                    this.zzaky = 2;
                }
                if (this.zzaky == i) {
                    this.zzakz += j2 - zzdw;
                    this.zzaky = 1;
                    this.zzajt.zzen();
                }
            }
            if (this.zzakc) {
                this.zzakt += (long) this.zzakx;
            } else {
                this.zzaks += (long) byteBuffer.remaining();
            }
            this.zzale = byteBuffer2;
        }
        if (this.zzakc) {
            zzc(this.zzale, j2);
        } else {
            zzdv(j2);
        }
        if (this.zzale.hasRemaining()) {
            return false;
        }
        this.zzale = null;
        return true;
    }

    private final void zzdv(long j) throws zziw {
        ByteBuffer byteBuffer;
        int length = this.zzalc.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.zzald[i - 1];
            } else {
                byteBuffer = this.zzale;
                if (byteBuffer == null) {
                    byteBuffer = zzie.zzaiy;
                }
            }
            if (i == length) {
                zzc(byteBuffer, j);
            } else {
                zzie zzie = this.zzalc[i];
                zzie.zzn(byteBuffer);
                ByteBuffer zzfm = zzie.zzfm();
                this.zzald[i] = zzfm;
                if (zzfm.hasRemaining()) {
                    i++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i--;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00d7, code lost:
        if (r11 < r10) goto L_0x0076;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzc(java.nio.ByteBuffer r9, long r10) throws com.google.android.gms.internal.ads.zziw {
        /*
            r8 = this;
            boolean r0 = r9.hasRemaining()
            r1 = 1
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.nio.ByteBuffer r0 = r8.zzalf
            r2 = 21
            r3 = 0
            if (r0 == 0) goto L_0x0018
            if (r0 != r9) goto L_0x0013
            r0 = 1
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            com.google.android.gms.internal.ads.zzoz.checkArgument(r0)
            goto L_0x003b
        L_0x0018:
            r8.zzalf = r9
            int r0 = com.google.android.gms.internal.ads.zzpq.SDK_INT
            if (r0 >= r2) goto L_0x003b
            int r0 = r9.remaining()
            byte[] r4 = r8.zzalg
            if (r4 == 0) goto L_0x0029
            int r4 = r4.length
            if (r4 >= r0) goto L_0x002d
        L_0x0029:
            byte[] r4 = new byte[r0]
            r8.zzalg = r4
        L_0x002d:
            int r4 = r9.position()
            byte[] r5 = r8.zzalg
            r9.get(r5, r3, r0)
            r9.position(r4)
            r8.zzalh = r3
        L_0x003b:
            int r0 = r9.remaining()
            int r4 = com.google.android.gms.internal.ads.zzpq.SDK_INT
            if (r4 >= r2) goto L_0x0079
            long r10 = r8.zzakv
            com.google.android.gms.internal.ads.zziq r2 = r8.zzajw
            long r4 = r2.zzfy()
            int r2 = r8.zzaku
            long r6 = (long) r2
            long r4 = r4 * r6
            long r10 = r10 - r4
            int r11 = (int) r10
            int r10 = r8.zzakd
            int r10 = r10 - r11
            if (r10 <= 0) goto L_0x0076
            int r10 = java.lang.Math.min(r0, r10)
            android.media.AudioTrack r11 = r8.zzajy
            byte[] r2 = r8.zzalg
            int r4 = r8.zzalh
            int r10 = r11.write(r2, r4, r10)
            if (r10 <= 0) goto L_0x00f0
            int r11 = r8.zzalh
            int r11 = r11 + r10
            r8.zzalh = r11
            int r11 = r9.position()
            int r11 = r11 + r10
            r9.position(r11)
            goto L_0x00f0
        L_0x0076:
            r10 = 0
            goto L_0x00f0
        L_0x0079:
            boolean r2 = r8.zzalm
            if (r2 == 0) goto L_0x00ea
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x0088
            r2 = 1
            goto L_0x0089
        L_0x0088:
            r2 = 0
        L_0x0089:
            com.google.android.gms.internal.ads.zzoz.checkState(r2)
            android.media.AudioTrack r2 = r8.zzajy
            java.nio.ByteBuffer r4 = r8.zzaki
            if (r4 != 0) goto L_0x00a7
            r4 = 16
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)
            r8.zzaki = r4
            java.nio.ByteOrder r5 = java.nio.ByteOrder.BIG_ENDIAN
            r4.order(r5)
            java.nio.ByteBuffer r4 = r8.zzaki
            r5 = 1431633921(0x55550001, float:1.46372496E13)
            r4.putInt(r5)
        L_0x00a7:
            int r4 = r8.zzakj
            if (r4 != 0) goto L_0x00c3
            java.nio.ByteBuffer r4 = r8.zzaki
            r5 = 4
            r4.putInt(r5, r0)
            java.nio.ByteBuffer r4 = r8.zzaki
            r5 = 8
            r6 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 * r6
            r4.putLong(r5, r10)
            java.nio.ByteBuffer r10 = r8.zzaki
            r10.position(r3)
            r8.zzakj = r0
        L_0x00c3:
            java.nio.ByteBuffer r10 = r8.zzaki
            int r10 = r10.remaining()
            if (r10 <= 0) goto L_0x00da
            java.nio.ByteBuffer r11 = r8.zzaki
            int r11 = r2.write(r11, r10, r1)
            if (r11 >= 0) goto L_0x00d7
            r8.zzakj = r3
            r10 = r11
            goto L_0x00f0
        L_0x00d7:
            if (r11 >= r10) goto L_0x00da
            goto L_0x0076
        L_0x00da:
            int r9 = r2.write(r9, r0, r1)
            if (r9 >= 0) goto L_0x00e3
            r8.zzakj = r3
            goto L_0x00e8
        L_0x00e3:
            int r10 = r8.zzakj
            int r10 = r10 - r9
            r8.zzakj = r10
        L_0x00e8:
            r10 = r9
            goto L_0x00f0
        L_0x00ea:
            android.media.AudioTrack r10 = r8.zzajy
            int r10 = r10.write(r9, r0, r1)
        L_0x00f0:
            long r4 = android.os.SystemClock.elapsedRealtime()
            r8.zzalo = r4
            if (r10 < 0) goto L_0x0115
            boolean r9 = r8.zzakc
            if (r9 != 0) goto L_0x0102
            long r4 = r8.zzakv
            long r6 = (long) r10
            long r4 = r4 + r6
            r8.zzakv = r4
        L_0x0102:
            if (r10 != r0) goto L_0x0114
            boolean r9 = r8.zzakc
            if (r9 == 0) goto L_0x0110
            long r9 = r8.zzakw
            int r11 = r8.zzakx
            long r2 = (long) r11
            long r9 = r9 + r2
            r8.zzakw = r9
        L_0x0110:
            r9 = 0
            r8.zzalf = r9
            return r1
        L_0x0114:
            return r3
        L_0x0115:
            com.google.android.gms.internal.ads.zziw r9 = new com.google.android.gms.internal.ads.zziw
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzio.zzc(java.nio.ByteBuffer, long):boolean");
    }

    public final void zzfp() throws zziw {
        if (!this.zzalj && isInitialized() && zzfq()) {
            this.zzajw.zzdy(zzfv());
            this.zzakj = 0;
            this.zzalj = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzfq() throws com.google.android.gms.internal.ads.zziw {
        /*
            r9 = this;
            int r0 = r9.zzali
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x0014
            boolean r0 = r9.zzakc
            if (r0 == 0) goto L_0x000f
            com.google.android.gms.internal.ads.zzie[] r0 = r9.zzalc
            int r0 = r0.length
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            r9.zzali = r0
        L_0x0012:
            r0 = 1
            goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            int r4 = r9.zzali
            com.google.android.gms.internal.ads.zzie[] r5 = r9.zzalc
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L_0x0038
            r4 = r5[r4]
            if (r0 == 0) goto L_0x0028
            r4.zzfl()
        L_0x0028:
            r9.zzdv(r7)
            boolean r0 = r4.zzfe()
            if (r0 != 0) goto L_0x0032
            return r3
        L_0x0032:
            int r0 = r9.zzali
            int r0 = r0 + r2
            r9.zzali = r0
            goto L_0x0012
        L_0x0038:
            java.nio.ByteBuffer r0 = r9.zzalf
            if (r0 == 0) goto L_0x0044
            r9.zzc(r0, r7)
            java.nio.ByteBuffer r0 = r9.zzalf
            if (r0 == 0) goto L_0x0044
            return r3
        L_0x0044:
            r9.zzali = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzio.zzfq():boolean");
    }

    public final boolean zzfe() {
        if (isInitialized()) {
            return this.zzalj && !zzfr();
        }
        return true;
    }

    public final boolean zzfr() {
        if (isInitialized()) {
            if (zzfv() <= this.zzajw.zzfy()) {
                if (zzfx() && this.zzajy.getPlayState() == 2 && this.zzajy.getPlaybackHeadPosition() == 0) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    public final zzhu zzb(zzhu zzhu) {
        if (this.zzakc) {
            zzhu zzhu2 = zzhu.zzahz;
            this.zzafh = zzhu2;
            return zzhu2;
        }
        zzhu zzhu3 = new zzhu(this.zzajr.zza(zzhu.zzaia), this.zzajr.zzb(zzhu.zzaib));
        zzhu zzhu4 = this.zzakf;
        if (zzhu4 == null) {
            if (!this.zzajx.isEmpty()) {
                zzhu4 = this.zzajx.getLast().zzafh;
            } else {
                zzhu4 = this.zzafh;
            }
        }
        if (!zzhu3.equals(zzhu4)) {
            if (isInitialized()) {
                this.zzakf = zzhu3;
            } else {
                this.zzafh = zzhu3;
            }
        }
        return this.zzafh;
    }

    public final zzhu zzfs() {
        return this.zzafh;
    }

    public final void setStreamType(int i) {
        if (this.streamType != i) {
            this.streamType = i;
            if (!this.zzalm) {
                reset();
                this.zzall = 0;
            }
        }
    }

    public final void zzz(int i) {
        zzoz.checkState(zzpq.SDK_INT >= 21);
        if (!this.zzalm || this.zzall != i) {
            this.zzalm = true;
            this.zzall = i;
            reset();
        }
    }

    public final void zzft() {
        if (this.zzalm) {
            this.zzalm = false;
            this.zzall = 0;
            reset();
        }
    }

    public final void setVolume(float f) {
        if (this.zzdj != f) {
            this.zzdj = f;
            zzfu();
        }
    }

    private final void zzfu() {
        if (!isInitialized()) {
            return;
        }
        if (zzpq.SDK_INT >= 21) {
            this.zzajy.setVolume(this.zzdj);
            return;
        }
        AudioTrack audioTrack = this.zzajy;
        float f = this.zzdj;
        audioTrack.setStereoVolume(f, f);
    }

    public final void pause() {
        this.zzalk = false;
        if (isInitialized()) {
            zzfw();
            this.zzajw.pause();
        }
    }

    public final void reset() {
        if (isInitialized()) {
            this.zzaks = 0;
            this.zzakt = 0;
            this.zzakv = 0;
            this.zzakw = 0;
            this.zzakx = 0;
            zzhu zzhu = this.zzakf;
            if (zzhu != null) {
                this.zzafh = zzhu;
                this.zzakf = null;
            } else if (!this.zzajx.isEmpty()) {
                this.zzafh = this.zzajx.getLast().zzafh;
            }
            this.zzajx.clear();
            this.zzakg = 0;
            this.zzakh = 0;
            this.zzale = null;
            this.zzalf = null;
            int i = 0;
            while (true) {
                zzie[] zzieArr = this.zzalc;
                if (i >= zzieArr.length) {
                    break;
                }
                zzie zzie = zzieArr[i];
                zzie.flush();
                this.zzald[i] = zzie.zzfm();
                i++;
            }
            this.zzalj = false;
            this.zzali = -1;
            this.zzaki = null;
            this.zzakj = 0;
            this.zzaky = 0;
            this.zzalb = 0;
            zzfw();
            if (this.zzajy.getPlayState() == 3) {
                this.zzajy.pause();
            }
            AudioTrack audioTrack = this.zzajy;
            this.zzajy = null;
            this.zzajw.zza((AudioTrack) null, false);
            this.zzaju.close();
            new zzir(this, audioTrack).start();
        }
    }

    public final void release() {
        reset();
        for (zzie reset : this.zzajs) {
            reset.reset();
        }
        this.zzall = 0;
        this.zzalk = false;
    }

    private final boolean isInitialized() {
        return this.zzajy != null;
    }

    private final long zzdw(long j) {
        return (j * 1000000) / ((long) this.zzahn);
    }

    private final long zzdx(long j) {
        return (j * ((long) this.zzahn)) / 1000000;
    }

    private final long zzfv() {
        return this.zzakc ? this.zzakw : this.zzakv / ((long) this.zzaku);
    }

    private final void zzfw() {
        this.zzakm = 0;
        this.zzakl = 0;
        this.zzakk = 0;
        this.zzakn = 0;
        this.zzako = false;
        this.zzakp = 0;
    }

    private final boolean zzfx() {
        if (zzpq.SDK_INT >= 23) {
            return false;
        }
        int i = this.zzakb;
        return i == 5 || i == 6;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzaz(java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1095064472: goto L_0x002a;
                case 187078296: goto L_0x0020;
                case 1504578661: goto L_0x0016;
                case 1505942594: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0034
        L_0x000c:
            java.lang.String r0 = "audio/vnd.dts.hd"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 3
            goto L_0x0035
        L_0x0016:
            java.lang.String r0 = "audio/eac3"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 1
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "audio/ac3"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 0
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "audio/vnd.dts"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0034
            r5 = 2
            goto L_0x0035
        L_0x0034:
            r5 = -1
        L_0x0035:
            if (r5 == 0) goto L_0x0045
            if (r5 == r4) goto L_0x0043
            if (r5 == r3) goto L_0x0041
            if (r5 == r2) goto L_0x003e
            return r1
        L_0x003e:
            r5 = 8
            return r5
        L_0x0041:
            r5 = 7
            return r5
        L_0x0043:
            r5 = 6
            return r5
        L_0x0045:
            r5 = 5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzio.zzaz(java.lang.String):int");
    }
}
