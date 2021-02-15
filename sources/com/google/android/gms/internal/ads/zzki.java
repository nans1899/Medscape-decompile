package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzki implements zzkj {
    private final byte[] zzapb = new byte[8];
    private final Stack<zzkk> zzapc = new Stack<>();
    private final zzks zzapd = new zzks();
    private zzkm zzape;
    private int zzapf;
    private int zzapg;
    private long zzaph;

    zzki() {
    }

    public final void zza(zzkm zzkm) {
        this.zzape = zzkm;
    }

    public final void reset() {
        this.zzapf = 0;
        this.zzapc.clear();
        this.zzapd.reset();
    }

    public final boolean zzb(zzjy zzjy) throws IOException, InterruptedException {
        String str;
        double d;
        int zzat;
        int zza;
        zzoz.checkState(this.zzape != null);
        while (true) {
            if (this.zzapc.isEmpty() || zzjy.getPosition() < this.zzapc.peek().zzapi) {
                if (this.zzapf == 0) {
                    long zza2 = this.zzapd.zza(zzjy, true, false, 4);
                    if (zza2 == -2) {
                        zzjy.zzgq();
                        while (true) {
                            zzjy.zza(this.zzapb, 0, 4);
                            zzat = zzks.zzat(this.zzapb[0]);
                            if (zzat != -1 && zzat <= 4) {
                                zza = (int) zzks.zza(this.zzapb, zzat, false);
                                if (this.zzape.zzao(zza)) {
                                    break;
                                }
                            }
                            zzjy.zzai(1);
                        }
                        zzjy.zzai(zzat);
                        zza2 = (long) zza;
                    }
                    if (zza2 == -1) {
                        return false;
                    }
                    this.zzapg = (int) zza2;
                    this.zzapf = 1;
                }
                if (this.zzapf == 1) {
                    this.zzaph = this.zzapd.zza(zzjy, false, true, 8);
                    this.zzapf = 2;
                }
                int zzan = this.zzape.zzan(this.zzapg);
                if (zzan == 0) {
                    zzjy.zzai((int) this.zzaph);
                    this.zzapf = 0;
                } else if (zzan == 1) {
                    long position = zzjy.getPosition();
                    this.zzapc.add(new zzkk(this.zzapg, this.zzaph + position));
                    this.zzape.zzd(this.zzapg, position, this.zzaph);
                    this.zzapf = 0;
                    return true;
                } else if (zzan == 2) {
                    long j = this.zzaph;
                    if (j <= 8) {
                        this.zzape.zzc(this.zzapg, zza(zzjy, (int) j));
                        this.zzapf = 0;
                        return true;
                    }
                    long j2 = this.zzaph;
                    StringBuilder sb = new StringBuilder(42);
                    sb.append("Invalid integer size: ");
                    sb.append(j2);
                    throw new zzhv(sb.toString());
                } else if (zzan == 3) {
                    long j3 = this.zzaph;
                    if (j3 <= 2147483647L) {
                        zzkm zzkm = this.zzape;
                        int i = this.zzapg;
                        int i2 = (int) j3;
                        if (i2 == 0) {
                            str = "";
                        } else {
                            byte[] bArr = new byte[i2];
                            zzjy.readFully(bArr, 0, i2);
                            str = new String(bArr);
                        }
                        zzkm.zza(i, str);
                        this.zzapf = 0;
                        return true;
                    }
                    long j4 = this.zzaph;
                    StringBuilder sb2 = new StringBuilder(41);
                    sb2.append("String element size: ");
                    sb2.append(j4);
                    throw new zzhv(sb2.toString());
                } else if (zzan == 4) {
                    this.zzape.zza(this.zzapg, (int) this.zzaph, zzjy);
                    this.zzapf = 0;
                    return true;
                } else if (zzan == 5) {
                    long j5 = this.zzaph;
                    if (j5 == 4 || j5 == 8) {
                        zzkm zzkm2 = this.zzape;
                        int i3 = this.zzapg;
                        int i4 = (int) this.zzaph;
                        long zza3 = zza(zzjy, i4);
                        if (i4 == 4) {
                            d = (double) Float.intBitsToFloat((int) zza3);
                        } else {
                            d = Double.longBitsToDouble(zza3);
                        }
                        zzkm2.zza(i3, d);
                        this.zzapf = 0;
                        return true;
                    }
                    long j6 = this.zzaph;
                    StringBuilder sb3 = new StringBuilder(40);
                    sb3.append("Invalid float size: ");
                    sb3.append(j6);
                    throw new zzhv(sb3.toString());
                } else {
                    StringBuilder sb4 = new StringBuilder(32);
                    sb4.append("Invalid element type ");
                    sb4.append(zzan);
                    throw new zzhv(sb4.toString());
                }
            } else {
                this.zzape.zzap(this.zzapc.pop().zzapg);
                return true;
            }
        }
    }

    private final long zza(zzjy zzjy, int i) throws IOException, InterruptedException {
        zzjy.readFully(this.zzapb, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.zzapb[i2] & 255));
        }
        return j;
    }
}
