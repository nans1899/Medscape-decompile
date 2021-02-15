package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgr extends zzgp {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zztq = true;
    private final int zztr;
    private int zzts;

    public zzgr(ByteBuffer byteBuffer, boolean z) {
        super((zzgo) null);
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zztr = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final boolean zzen() {
        return this.pos == this.limit;
    }

    public final int zzeo() throws IOException {
        if (zzen()) {
            return Integer.MAX_VALUE;
        }
        int zzfe = zzfe();
        this.tag = zzfe;
        if (zzfe == this.zzts) {
            return Integer.MAX_VALUE;
        }
        return zzfe >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002e A[LOOP:0: B:18:0x002e->B:21:0x003b, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzep() throws java.io.IOException {
        /*
            r7 = this;
            boolean r0 = r7.zzen()
            r1 = 0
            if (r0 != 0) goto L_0x0085
            int r0 = r7.tag
            int r2 = r7.zzts
            if (r0 != r2) goto L_0x000f
            goto L_0x0085
        L_0x000f:
            r3 = r0 & 7
            r4 = 1
            if (r3 == 0) goto L_0x0059
            if (r3 == r4) goto L_0x0053
            r1 = 2
            if (r3 == r1) goto L_0x004b
            r1 = 4
            r5 = 3
            if (r3 == r5) goto L_0x0029
            r0 = 5
            if (r3 != r0) goto L_0x0024
            r7.zzao(r1)
            return r4
        L_0x0024:
            com.google.android.gms.internal.vision.zzim r0 = com.google.android.gms.internal.vision.zzin.zzhm()
            throw r0
        L_0x0029:
            int r0 = r0 >>> r5
            int r0 = r0 << r5
            r0 = r0 | r1
            r7.zzts = r0
        L_0x002e:
            int r0 = r7.zzeo()
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r1) goto L_0x003d
            boolean r0 = r7.zzep()
            if (r0 != 0) goto L_0x002e
        L_0x003d:
            int r0 = r7.tag
            int r1 = r7.zzts
            if (r0 != r1) goto L_0x0046
            r7.zzts = r2
            return r4
        L_0x0046:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r0
        L_0x004b:
            int r0 = r7.zzfe()
            r7.zzao(r0)
            return r4
        L_0x0053:
            r0 = 8
            r7.zzao(r0)
            return r4
        L_0x0059:
            int r0 = r7.limit
            int r2 = r7.pos
            int r0 = r0 - r2
            r3 = 10
            if (r0 < r3) goto L_0x0074
            byte[] r0 = r7.buffer
            r5 = 0
        L_0x0065:
            if (r5 >= r3) goto L_0x0074
            int r6 = r2 + 1
            byte r2 = r0[r2]
            if (r2 < 0) goto L_0x0070
            r7.pos = r6
            goto L_0x007f
        L_0x0070:
            int r5 = r5 + 1
            r2 = r6
            goto L_0x0065
        L_0x0074:
            if (r1 >= r3) goto L_0x0080
            byte r0 = r7.readByte()
            if (r0 >= 0) goto L_0x007f
            int r1 = r1 + 1
            goto L_0x0074
        L_0x007f:
            return r4
        L_0x0080:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzhj()
            throw r0
        L_0x0085:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzgr.zzep():boolean");
    }

    public final double readDouble() throws IOException {
        zzaq(1);
        return Double.longBitsToDouble(zzfi());
    }

    public final float readFloat() throws IOException {
        zzaq(5);
        return Float.intBitsToFloat(zzfh());
    }

    public final long zzeq() throws IOException {
        zzaq(0);
        return zzff();
    }

    public final long zzer() throws IOException {
        zzaq(0);
        return zzff();
    }

    public final int zzes() throws IOException {
        zzaq(0);
        return zzfe();
    }

    public final long zzet() throws IOException {
        zzaq(1);
        return zzfi();
    }

    public final int zzeu() throws IOException {
        zzaq(5);
        return zzfh();
    }

    public final boolean zzev() throws IOException {
        zzaq(0);
        if (zzfe() != 0) {
            return true;
        }
        return false;
    }

    public final String readString() throws IOException {
        return zzj(false);
    }

    public final String zzew() throws IOException {
        return zzj(true);
    }

    private final String zzj(boolean z) throws IOException {
        zzaq(2);
        int zzfe = zzfe();
        if (zzfe == 0) {
            return "";
        }
        zzap(zzfe);
        if (z) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            if (!zzlf.zzf(bArr, i, i + zzfe)) {
                throw zzin.zzho();
            }
        }
        String str = new String(this.buffer, this.pos, zzfe, zzie.UTF_8);
        this.pos += zzfe;
        return str;
    }

    public final <T> T zza(Class<T> cls, zzho zzho) throws IOException {
        zzaq(2);
        return zzb(zzkb.zzik().zzf(cls), zzho);
    }

    public final <T> T zza(zzkf<T> zzkf, zzho zzho) throws IOException {
        zzaq(2);
        return zzb(zzkf, zzho);
    }

    private final <T> T zzb(zzkf<T> zzkf, zzho zzho) throws IOException {
        int zzfe = zzfe();
        zzap(zzfe);
        int i = this.limit;
        int i2 = this.pos + zzfe;
        this.limit = i2;
        try {
            T newInstance = zzkf.newInstance();
            zzkf.zza(newInstance, this, zzho);
            zzkf.zzj(newInstance);
            if (this.pos == i2) {
                return newInstance;
            }
            throw zzin.zzhn();
        } finally {
            this.limit = i;
        }
    }

    public final <T> T zzb(Class<T> cls, zzho zzho) throws IOException {
        zzaq(3);
        return zzd(zzkb.zzik().zzf(cls), zzho);
    }

    public final <T> T zzc(zzkf<T> zzkf, zzho zzho) throws IOException {
        zzaq(3);
        return zzd(zzkf, zzho);
    }

    private final <T> T zzd(zzkf<T> zzkf, zzho zzho) throws IOException {
        int i = this.zzts;
        this.zzts = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzkf.newInstance();
            zzkf.zza(newInstance, this, zzho);
            zzkf.zzj(newInstance);
            if (this.tag == this.zzts) {
                return newInstance;
            }
            throw zzin.zzhn();
        } finally {
            this.zzts = i;
        }
    }

    public final zzgs zzex() throws IOException {
        zzgs zzgs;
        zzaq(2);
        int zzfe = zzfe();
        if (zzfe == 0) {
            return zzgs.zztt;
        }
        zzap(zzfe);
        if (this.zztq) {
            zzgs = zzgs.zzb(this.buffer, this.pos, zzfe);
        } else {
            zzgs = zzgs.zza(this.buffer, this.pos, zzfe);
        }
        this.pos += zzfe;
        return zzgs;
    }

    public final int zzey() throws IOException {
        zzaq(0);
        return zzfe();
    }

    public final int zzez() throws IOException {
        zzaq(0);
        return zzfe();
    }

    public final int zzfa() throws IOException {
        zzaq(5);
        return zzfh();
    }

    public final long zzfb() throws IOException {
        zzaq(1);
        return zzfi();
    }

    public final int zzfc() throws IOException {
        zzaq(0);
        return zzhe.zzbb(zzfe());
    }

    public final long zzfd() throws IOException {
        zzaq(0);
        return zzhe.zzr(zzff());
    }

    public final void zza(List<Double> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhm) {
            zzhm zzhm = (zzhm) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzhm.zzc(readDouble());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = zzfe();
                zzar(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzhm.zzc(Double.longBitsToDouble(zzfk()));
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Double.valueOf(readDouble()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzfe2 = zzfe();
                zzar(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzfk())));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzb(List<Float> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzhz) {
            zzhz zzhz = (zzhz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzfe = zzfe();
                zzas(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzhz.zzu(Float.intBitsToFloat(zzfj()));
                }
            } else if (i3 == 5) {
                do {
                    zzhz.zzu(readFloat());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzfe2 = zzfe();
                zzas(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzfj())));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzjb.zzac(zzeq());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzjb.zzac(zzff());
                }
                zzat(zzfe);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzeq()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Long.valueOf(zzff()));
                }
                zzat(zzfe2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzjb.zzac(zzer());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzjb.zzac(zzff());
                }
                zzat(zzfe);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzer()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Long.valueOf(zzff()));
                }
                zzat(zzfe2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzif.zzbs(zzes());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzif.zzbs(zzfe());
                }
                zzat(zzfe);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzes()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Integer.valueOf(zzfe()));
                }
                zzat(zzfe2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzjb.zzac(zzet());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = zzfe();
                zzar(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzjb.zzac(zzfk());
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzet()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzfe2 = zzfe();
                zzar(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzfk()));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzg(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzfe = zzfe();
                zzas(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzif.zzbs(zzfj());
                }
            } else if (i3 == 5) {
                do {
                    zzif.zzbs(zzeu());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzfe2 = zzfe();
                zzas(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzfj()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzeu()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzgq) {
            zzgq zzgq = (zzgq) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgq.addBoolean(zzev());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzgq.addBoolean(zzfe() != 0);
                }
                zzat(zzfe);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(zzev()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Boolean.valueOf(zzfe() != 0));
                }
                zzat(zzfe2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzi(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int i;
        int i2;
        if ((this.tag & 7) != 2) {
            throw zzin.zzhm();
        } else if (!(list instanceof zziu) || z) {
            do {
                list.add(zzj(z));
                if (!zzen()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzfe() == this.tag);
            this.pos = i;
        } else {
            zziu zziu = (zziu) list;
            do {
                zziu.zzc(zzex());
                if (!zzen()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzfe() == this.tag);
            this.pos = i2;
        }
    }

    public final <T> void zza(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 2) {
            do {
                list.add(zzb(zzkf, zzho));
                if (!zzen()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzfe() == i2);
            this.pos = i;
            return;
        }
        throw zzin.zzhm();
    }

    public final <T> void zzb(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 3) {
            do {
                list.add(zzd(zzkf, zzho));
                if (!zzen()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzfe() == i2);
            this.pos = i;
            return;
        }
        throw zzin.zzhm();
    }

    public final void zzj(List<zzgs> list) throws IOException {
        int i;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzex());
                if (!zzen()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzfe() == this.tag);
            this.pos = i;
            return;
        }
        throw zzin.zzhm();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzif.zzbs(zzey());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzif.zzbs(zzfe());
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzey()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Integer.valueOf(zzfe()));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzif.zzbs(zzez());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzif.zzbs(zzfe());
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzez()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Integer.valueOf(zzfe()));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzfe = zzfe();
                zzas(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzif.zzbs(zzfj());
                }
            } else if (i3 == 5) {
                do {
                    zzif.zzbs(zzfa());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzfe2 = zzfe();
                zzas(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzfj()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzfa()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i3 = this.tag & 7;
            if (i3 == 1) {
                do {
                    zzjb.zzac(zzfb());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = zzfe();
                zzar(zzfe);
                int i4 = this.pos + zzfe;
                while (this.pos < i4) {
                    zzjb.zzac(zzfk());
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 1) {
                do {
                    list.add(Long.valueOf(zzfb()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i5 == 2) {
                int zzfe2 = zzfe();
                zzar(zzfe2);
                int i6 = this.pos + zzfe2;
                while (this.pos < i6) {
                    list.add(Long.valueOf(zzfk()));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzo(List<Integer> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzif.zzbs(zzfc());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzif.zzbs(zzhe.zzbb(zzfe()));
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzfc()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Integer.valueOf(zzhe.zzbb(zzfe())));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int i;
        int i2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzjb.zzac(zzfd());
                    if (!zzen()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzfe = this.pos + zzfe();
                while (this.pos < zzfe) {
                    zzjb.zzac(zzhe.zzr(zzff()));
                }
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzfd()));
                    if (!zzen()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzfe() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzfe2 = this.pos + zzfe();
                while (this.pos < zzfe2) {
                    list.add(Long.valueOf(zzhe.zzr(zzff())));
                }
            } else {
                throw zzin.zzhm();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:17|18|(2:20|36)(3:31|21|22)) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        if (zzep() != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        throw new com.google.android.gms.internal.vision.zzin("Unable to parse map entry.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.vision.zzje<K, V> r9, com.google.android.gms.internal.vision.zzho r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzaq(r0)
            int r1 = r7.zzfe()
            r7.zzap(r1)
            int r2 = r7.limit
            int r3 = r7.pos
            int r3 = r3 + r1
            r7.limit = r3
            K r1 = r9.zzaad     // Catch:{ all -> 0x005b }
            V r3 = r9.zzgk     // Catch:{ all -> 0x005b }
        L_0x0016:
            int r4 = r7.zzeo()     // Catch:{ all -> 0x005b }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x0055
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0040
            if (r4 == r0) goto L_0x0033
            boolean r4 = r7.zzep()     // Catch:{ zzim -> 0x0048 }
            if (r4 == 0) goto L_0x002d
            goto L_0x0016
        L_0x002d:
            com.google.android.gms.internal.vision.zzin r4 = new com.google.android.gms.internal.vision.zzin     // Catch:{ zzim -> 0x0048 }
            r4.<init>(r6)     // Catch:{ zzim -> 0x0048 }
            throw r4     // Catch:{ zzim -> 0x0048 }
        L_0x0033:
            com.google.android.gms.internal.vision.zzlk r4 = r9.zzaae     // Catch:{ zzim -> 0x0048 }
            V r5 = r9.zzgk     // Catch:{ zzim -> 0x0048 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzim -> 0x0048 }
            java.lang.Object r3 = r7.zza((com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzho) r10)     // Catch:{ zzim -> 0x0048 }
            goto L_0x0016
        L_0x0040:
            com.google.android.gms.internal.vision.zzlk r4 = r9.zzaac     // Catch:{ zzim -> 0x0048 }
            r5 = 0
            java.lang.Object r1 = r7.zza((com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzho) r5)     // Catch:{ zzim -> 0x0048 }
            goto L_0x0016
        L_0x0048:
            boolean r4 = r7.zzep()     // Catch:{ all -> 0x005b }
            if (r4 == 0) goto L_0x004f
            goto L_0x0016
        L_0x004f:
            com.google.android.gms.internal.vision.zzin r8 = new com.google.android.gms.internal.vision.zzin     // Catch:{ all -> 0x005b }
            r8.<init>(r6)     // Catch:{ all -> 0x005b }
            throw r8     // Catch:{ all -> 0x005b }
        L_0x0055:
            r8.put(r1, r3)     // Catch:{ all -> 0x005b }
            r7.limit = r2
            return
        L_0x005b:
            r8 = move-exception
            r7.limit = r2
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzgr.zza(java.util.Map, com.google.android.gms.internal.vision.zzje, com.google.android.gms.internal.vision.zzho):void");
    }

    private final Object zza(zzlk zzlk, Class<?> cls, zzho zzho) throws IOException {
        switch (zzgo.zztn[zzlk.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzev());
            case 2:
                return zzex();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzez());
            case 5:
                return Integer.valueOf(zzeu());
            case 6:
                return Long.valueOf(zzet());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzes());
            case 9:
                return Long.valueOf(zzer());
            case 10:
                return zza(cls, zzho);
            case 11:
                return Integer.valueOf(zzfa());
            case 12:
                return Long.valueOf(zzfb());
            case 13:
                return Integer.valueOf(zzfc());
            case 14:
                return Long.valueOf(zzfd());
            case 15:
                return zzj(true);
            case 16:
                return Integer.valueOf(zzey());
            case 17:
                return Long.valueOf(zzeq());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final int zzfe() throws IOException {
        byte b;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.pos = i3;
                return b2;
            } else if (i2 - i3 < 9) {
                return (int) zzfg();
            } else {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << Ascii.SO);
                    if (b4 >= 0) {
                        b = b4 ^ 16256;
                    } else {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            i5 = i4 + 1;
                            byte b6 = bArr[i4];
                            b = (b5 ^ (b6 << Ascii.FS)) ^ 266354560;
                            if (b6 < 0) {
                                i4 = i5 + 1;
                                if (bArr[i5] < 0) {
                                    i5 = i4 + 1;
                                    if (bArr[i4] < 0) {
                                        i4 = i5 + 1;
                                        if (bArr[i5] < 0) {
                                            i5 = i4 + 1;
                                            if (bArr[i4] < 0) {
                                                i4 = i5 + 1;
                                                if (bArr[i5] < 0) {
                                                    throw zzin.zzhj();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i4 = i5;
                }
                this.pos = i4;
                return b;
            }
        } else {
            throw zzin.zzhh();
        }
    }

    private final long zzff() throws IOException {
        long j;
        int i;
        long j2;
        long j3;
        byte b;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.pos = i4;
                return (long) b2;
            } else if (i3 - i4 < 9) {
                return zzfg();
            } else {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << Ascii.SO);
                    if (b4 >= 0) {
                        i = i6;
                        j = (long) (b4 ^ 16256);
                    } else {
                        i5 = i6 + 1;
                        byte b5 = b4 ^ (bArr[i6] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            long j4 = (long) b5;
                            int i7 = i5 + 1;
                            long j5 = j4 ^ (((long) bArr[i5]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                int i8 = i7 + 1;
                                long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i7 = i8 + 1;
                                    j5 = j6 ^ (((long) bArr[i8]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i8 = i7 + 1;
                                        j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            int i9 = i8 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i = i9 + 1;
                                                if (((long) bArr[i9]) < 0) {
                                                    throw zzin.zzhj();
                                                }
                                            } else {
                                                i = i9;
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                            }
                            j = j5 ^ j3;
                            i = i7;
                        }
                    }
                    this.pos = i;
                    return j;
                }
                j = (long) b;
                this.pos = i;
                return j;
            }
        } else {
            throw zzin.zzhh();
        }
    }

    private final long zzfg() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readByte = readByte();
            j |= ((long) (readByte & Byte.MAX_VALUE)) << i;
            if ((readByte & 128) == 0) {
                return j;
            }
        }
        throw zzin.zzhj();
    }

    private final byte readByte() throws IOException {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzin.zzhh();
    }

    private final int zzfh() throws IOException {
        zzap(4);
        return zzfj();
    }

    private final long zzfi() throws IOException {
        zzap(8);
        return zzfk();
    }

    private final int zzfj() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    private final long zzfk() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzao(int i) throws IOException {
        zzap(i);
        this.pos += i;
    }

    private final void zzap(int i) throws IOException {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzin.zzhh();
        }
    }

    private final void zzaq(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzin.zzhm();
        }
    }

    private final void zzar(int i) throws IOException {
        zzap(i);
        if ((i & 7) != 0) {
            throw zzin.zzhn();
        }
    }

    private final void zzas(int i) throws IOException {
        zzap(i);
        if ((i & 3) != 0) {
            throw zzin.zzhn();
        }
    }

    private final void zzat(int i) throws IOException {
        if (this.pos != i) {
            throw zzin.zzhh();
        }
    }
}
