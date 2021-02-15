package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhj implements zzkc {
    private int tag;
    private int zzts;
    private final zzhe zzul;
    private int zzum = 0;

    public static zzhj zza(zzhe zzhe) {
        if (zzhe.zzue != null) {
            return zzhe.zzue;
        }
        return new zzhj(zzhe);
    }

    private zzhj(zzhe zzhe) {
        zzhe zzhe2 = (zzhe) zzie.zza(zzhe, "input");
        this.zzul = zzhe2;
        zzhe2.zzue = this;
    }

    public final int zzeo() throws IOException {
        int i = this.zzum;
        if (i != 0) {
            this.tag = i;
            this.zzum = 0;
        } else {
            this.tag = this.zzul.zzfr();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzts) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzep() throws IOException {
        int i;
        if (this.zzul.zzen() || (i = this.tag) == this.zzts) {
            return false;
        }
        return this.zzul.zzay(i);
    }

    private final void zzaq(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzin.zzhm();
        }
    }

    public final double readDouble() throws IOException {
        zzaq(1);
        return this.zzul.readDouble();
    }

    public final float readFloat() throws IOException {
        zzaq(5);
        return this.zzul.readFloat();
    }

    public final long zzeq() throws IOException {
        zzaq(0);
        return this.zzul.zzeq();
    }

    public final long zzer() throws IOException {
        zzaq(0);
        return this.zzul.zzer();
    }

    public final int zzes() throws IOException {
        zzaq(0);
        return this.zzul.zzes();
    }

    public final long zzet() throws IOException {
        zzaq(1);
        return this.zzul.zzet();
    }

    public final int zzeu() throws IOException {
        zzaq(5);
        return this.zzul.zzeu();
    }

    public final boolean zzev() throws IOException {
        zzaq(0);
        return this.zzul.zzev();
    }

    public final String readString() throws IOException {
        zzaq(2);
        return this.zzul.readString();
    }

    public final String zzew() throws IOException {
        zzaq(2);
        return this.zzul.zzew();
    }

    public final <T> T zza(Class<T> cls, zzho zzho) throws IOException {
        zzaq(2);
        return zzb(zzkb.zzik().zzf(cls), zzho);
    }

    public final <T> T zza(zzkf<T> zzkf, zzho zzho) throws IOException {
        zzaq(2);
        return zzb(zzkf, zzho);
    }

    public final <T> T zzb(Class<T> cls, zzho zzho) throws IOException {
        zzaq(3);
        return zzd(zzkb.zzik().zzf(cls), zzho);
    }

    public final <T> T zzc(zzkf<T> zzkf, zzho zzho) throws IOException {
        zzaq(3);
        return zzd(zzkf, zzho);
    }

    private final <T> T zzb(zzkf<T> zzkf, zzho zzho) throws IOException {
        int zzey = this.zzul.zzey();
        if (this.zzul.zzub < this.zzul.zzuc) {
            int zzaz = this.zzul.zzaz(zzey);
            T newInstance = zzkf.newInstance();
            this.zzul.zzub++;
            zzkf.zza(newInstance, this, zzho);
            zzkf.zzj(newInstance);
            this.zzul.zzax(0);
            zzhe zzhe = this.zzul;
            zzhe.zzub--;
            this.zzul.zzba(zzaz);
            return newInstance;
        }
        throw new zzin("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
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
        zzaq(2);
        return this.zzul.zzex();
    }

    public final int zzey() throws IOException {
        zzaq(0);
        return this.zzul.zzey();
    }

    public final int zzez() throws IOException {
        zzaq(0);
        return this.zzul.zzez();
    }

    public final int zzfa() throws IOException {
        zzaq(5);
        return this.zzul.zzfa();
    }

    public final long zzfb() throws IOException {
        zzaq(1);
        return this.zzul.zzfb();
    }

    public final int zzfc() throws IOException {
        zzaq(0);
        return this.zzul.zzfc();
    }

    public final long zzfd() throws IOException {
        zzaq(0);
        return this.zzul.zzfd();
    }

    public final void zza(List<Double> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzhm) {
            zzhm zzhm = (zzhm) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzhm.zzc(this.zzul.readDouble());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzey = this.zzul.zzey();
                zzar(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzhm.zzc(this.zzul.readDouble());
                } while (this.zzul.zzft() < zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zzul.readDouble()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzar(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Double.valueOf(this.zzul.readDouble()));
                } while (this.zzul.zzft() < zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzb(List<Float> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzhz) {
            zzhz zzhz = (zzhz) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzey = this.zzul.zzey();
                zzas(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzhz.zzu(this.zzul.readFloat());
                } while (this.zzul.zzft() < zzft);
            } else if (i == 5) {
                do {
                    zzhz.zzu(this.zzul.readFloat());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzas(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Float.valueOf(this.zzul.readFloat()));
                } while (this.zzul.zzft() < zzft2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzul.readFloat()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzc(List<Long> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzjb.zzac(this.zzul.zzeq());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzjb.zzac(this.zzul.zzeq());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzul.zzeq()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Long.valueOf(this.zzul.zzeq()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzd(List<Long> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzjb.zzac(this.zzul.zzer());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzjb.zzac(this.zzul.zzer());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzul.zzer()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Long.valueOf(this.zzul.zzer()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zze(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzif.zzbs(this.zzul.zzes());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzif.zzbs(this.zzul.zzes());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzes()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Integer.valueOf(this.zzul.zzes()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzjb.zzac(this.zzul.zzet());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzey = this.zzul.zzey();
                zzar(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzjb.zzac(this.zzul.zzet());
                } while (this.zzul.zzft() < zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzul.zzet()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzar(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Long.valueOf(this.zzul.zzet()));
                } while (this.zzul.zzft() < zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzg(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzey = this.zzul.zzey();
                zzas(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzif.zzbs(this.zzul.zzeu());
                } while (this.zzul.zzft() < zzft);
            } else if (i == 5) {
                do {
                    zzif.zzbs(this.zzul.zzeu());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzas(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Integer.valueOf(this.zzul.zzeu()));
                } while (this.zzul.zzft() < zzft2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzeu()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzh(List<Boolean> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzgq) {
            zzgq zzgq = (zzgq) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzgq.addBoolean(this.zzul.zzev());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzgq.addBoolean(this.zzul.zzev());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzul.zzev()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Boolean.valueOf(this.zzul.zzev()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
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
        int zzfr;
        int zzfr2;
        if ((this.tag & 7) != 2) {
            throw zzin.zzhm();
        } else if (!(list instanceof zziu) || z) {
            do {
                list.add(z ? zzew() : readString());
                if (!this.zzul.zzen()) {
                    zzfr = this.zzul.zzfr();
                } else {
                    return;
                }
            } while (zzfr == this.tag);
            this.zzum = zzfr;
        } else {
            zziu zziu = (zziu) list;
            do {
                zziu.zzc(zzex());
                if (!this.zzul.zzen()) {
                    zzfr2 = this.zzul.zzfr();
                } else {
                    return;
                }
            } while (zzfr2 == this.tag);
            this.zzum = zzfr2;
        }
    }

    public final <T> void zza(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException {
        int zzfr;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzb(zzkf, zzho));
                if (!this.zzul.zzen() && this.zzum == 0) {
                    zzfr = this.zzul.zzfr();
                } else {
                    return;
                }
            } while (zzfr == i);
            this.zzum = zzfr;
            return;
        }
        throw zzin.zzhm();
    }

    public final <T> void zzb(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException {
        int zzfr;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzkf, zzho));
                if (!this.zzul.zzen() && this.zzum == 0) {
                    zzfr = this.zzul.zzfr();
                } else {
                    return;
                }
            } while (zzfr == i);
            this.zzum = zzfr;
            return;
        }
        throw zzin.zzhm();
    }

    public final void zzj(List<zzgs> list) throws IOException {
        int zzfr;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzex());
                if (!this.zzul.zzen()) {
                    zzfr = this.zzul.zzfr();
                } else {
                    return;
                }
            } while (zzfr == this.tag);
            this.zzum = zzfr;
            return;
        }
        throw zzin.zzhm();
    }

    public final void zzk(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzif.zzbs(this.zzul.zzey());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzif.zzbs(this.zzul.zzey());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzey()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Integer.valueOf(this.zzul.zzey()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzif.zzbs(this.zzul.zzez());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzif.zzbs(this.zzul.zzez());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzez()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Integer.valueOf(this.zzul.zzez()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzm(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzey = this.zzul.zzey();
                zzas(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzif.zzbs(this.zzul.zzfa());
                } while (this.zzul.zzft() < zzft);
            } else if (i == 5) {
                do {
                    zzif.zzbs(this.zzul.zzfa());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzas(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Integer.valueOf(this.zzul.zzfa()));
                } while (this.zzul.zzft() < zzft2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzfa()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzjb.zzac(this.zzul.zzfb());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzey = this.zzul.zzey();
                zzar(zzey);
                int zzft = this.zzul.zzft() + zzey;
                do {
                    zzjb.zzac(this.zzul.zzfb());
                } while (this.zzul.zzft() < zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzul.zzfb()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzey2 = this.zzul.zzey();
                zzar(zzey2);
                int zzft2 = this.zzul.zzft() + zzey2;
                do {
                    list.add(Long.valueOf(this.zzul.zzfb()));
                } while (this.zzul.zzft() < zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzo(List<Integer> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzif) {
            zzif zzif = (zzif) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzif.zzbs(this.zzul.zzfc());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzif.zzbs(this.zzul.zzfc());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzul.zzfc()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Integer.valueOf(this.zzul.zzfc()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    public final void zzp(List<Long> list) throws IOException {
        int zzfr;
        int zzfr2;
        if (list instanceof zzjb) {
            zzjb zzjb = (zzjb) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzjb.zzac(this.zzul.zzfd());
                    if (!this.zzul.zzen()) {
                        zzfr2 = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr2 == this.tag);
                this.zzum = zzfr2;
            } else if (i == 2) {
                int zzft = this.zzul.zzft() + this.zzul.zzey();
                do {
                    zzjb.zzac(this.zzul.zzfd());
                } while (this.zzul.zzft() < zzft);
                zzat(zzft);
            } else {
                throw zzin.zzhm();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzul.zzfd()));
                    if (!this.zzul.zzen()) {
                        zzfr = this.zzul.zzfr();
                    } else {
                        return;
                    }
                } while (zzfr == this.tag);
                this.zzum = zzfr;
            } else if (i2 == 2) {
                int zzft2 = this.zzul.zzft() + this.zzul.zzey();
                do {
                    list.add(Long.valueOf(this.zzul.zzfd()));
                } while (this.zzul.zzft() < zzft2);
                zzat(zzft2);
            } else {
                throw zzin.zzhm();
            }
        }
    }

    private static void zzar(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzin.zzhn();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (zzep() != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        throw new com.google.android.gms.internal.vision.zzin("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.vision.zzje<K, V> r9, com.google.android.gms.internal.vision.zzho r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzaq(r0)
            com.google.android.gms.internal.vision.zzhe r1 = r7.zzul
            int r1 = r1.zzey()
            com.google.android.gms.internal.vision.zzhe r2 = r7.zzul
            int r1 = r2.zzaz(r1)
            K r2 = r9.zzaad
            V r3 = r9.zzgk
        L_0x0014:
            int r4 = r7.zzeo()     // Catch:{ all -> 0x0064 }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005b
            com.google.android.gms.internal.vision.zzhe r5 = r7.zzul     // Catch:{ all -> 0x0064 }
            boolean r5 = r5.zzen()     // Catch:{ all -> 0x0064 }
            if (r5 != 0) goto L_0x005b
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0046
            if (r4 == r0) goto L_0x0039
            boolean r4 = r7.zzep()     // Catch:{ zzim -> 0x004e }
            if (r4 == 0) goto L_0x0033
            goto L_0x0014
        L_0x0033:
            com.google.android.gms.internal.vision.zzin r4 = new com.google.android.gms.internal.vision.zzin     // Catch:{ zzim -> 0x004e }
            r4.<init>(r6)     // Catch:{ zzim -> 0x004e }
            throw r4     // Catch:{ zzim -> 0x004e }
        L_0x0039:
            com.google.android.gms.internal.vision.zzlk r4 = r9.zzaae     // Catch:{ zzim -> 0x004e }
            V r5 = r9.zzgk     // Catch:{ zzim -> 0x004e }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzim -> 0x004e }
            java.lang.Object r3 = r7.zza((com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzho) r10)     // Catch:{ zzim -> 0x004e }
            goto L_0x0014
        L_0x0046:
            com.google.android.gms.internal.vision.zzlk r4 = r9.zzaac     // Catch:{ zzim -> 0x004e }
            r5 = 0
            java.lang.Object r2 = r7.zza((com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzho) r5)     // Catch:{ zzim -> 0x004e }
            goto L_0x0014
        L_0x004e:
            boolean r4 = r7.zzep()     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0055
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.vision.zzin r8 = new com.google.android.gms.internal.vision.zzin     // Catch:{ all -> 0x0064 }
            r8.<init>(r6)     // Catch:{ all -> 0x0064 }
            throw r8     // Catch:{ all -> 0x0064 }
        L_0x005b:
            r8.put(r2, r3)     // Catch:{ all -> 0x0064 }
            com.google.android.gms.internal.vision.zzhe r8 = r7.zzul
            r8.zzba(r1)
            return
        L_0x0064:
            r8 = move-exception
            com.google.android.gms.internal.vision.zzhe r9 = r7.zzul
            r9.zzba(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhj.zza(java.util.Map, com.google.android.gms.internal.vision.zzje, com.google.android.gms.internal.vision.zzho):void");
    }

    private final Object zza(zzlk zzlk, Class<?> cls, zzho zzho) throws IOException {
        switch (zzhi.zztn[zzlk.ordinal()]) {
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
                return zzew();
            case 16:
                return Integer.valueOf(zzey());
            case 17:
                return Long.valueOf(zzeq());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzas(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzin.zzhn();
        }
    }

    private final void zzat(int i) throws IOException {
        if (this.zzul.zzft() != i) {
            throw zzin.zzhh();
        }
    }
}
