package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzejh implements zzelz {
    private int tag;
    private final zzeja zzifr;
    private int zzifs;
    private int zzift = 0;

    public static zzejh zza(zzeja zzeja) {
        if (zzeja.zzifg != null) {
            return zzeja.zzifg;
        }
        return new zzejh(zzeja);
    }

    private zzejh(zzeja zzeja) {
        zzeja zzeja2 = (zzeja) zzekb.zza(zzeja, "input");
        this.zzifr = zzeja2;
        zzeja2.zzifg = this;
    }

    public final int zzbfn() throws IOException {
        int i = this.zzift;
        if (i != 0) {
            this.tag = i;
            this.zzift = 0;
        } else {
            this.tag = this.zzifr.zzbep();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.zzifs) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final int getTag() {
        return this.tag;
    }

    public final boolean zzbfo() throws IOException {
        int i;
        if (this.zzifr.zzbff() || (i = this.tag) == this.zzifs) {
            return false;
        }
        return this.zzifr.zzfz(i);
    }

    private final void zzgi(int i) throws IOException {
        if ((this.tag & 7) != i) {
            throw zzekj.zzbhc();
        }
    }

    public final double readDouble() throws IOException {
        zzgi(1);
        return this.zzifr.readDouble();
    }

    public final float readFloat() throws IOException {
        zzgi(5);
        return this.zzifr.readFloat();
    }

    public final long zzbeq() throws IOException {
        zzgi(0);
        return this.zzifr.zzbeq();
    }

    public final long zzber() throws IOException {
        zzgi(0);
        return this.zzifr.zzber();
    }

    public final int zzbes() throws IOException {
        zzgi(0);
        return this.zzifr.zzbes();
    }

    public final long zzbet() throws IOException {
        zzgi(1);
        return this.zzifr.zzbet();
    }

    public final int zzbeu() throws IOException {
        zzgi(5);
        return this.zzifr.zzbeu();
    }

    public final boolean zzbev() throws IOException {
        zzgi(0);
        return this.zzifr.zzbev();
    }

    public final String readString() throws IOException {
        zzgi(2);
        return this.zzifr.readString();
    }

    public final String zzbew() throws IOException {
        zzgi(2);
        return this.zzifr.zzbew();
    }

    public final <T> T zza(zzemf<T> zzemf, zzejm zzejm) throws IOException {
        zzgi(2);
        return zzc(zzemf, zzejm);
    }

    public final <T> T zzb(zzemf<T> zzemf, zzejm zzejm) throws IOException {
        zzgi(3);
        return zzd(zzemf, zzejm);
    }

    private final <T> T zzc(zzemf<T> zzemf, zzejm zzejm) throws IOException {
        int zzbey = this.zzifr.zzbey();
        if (this.zzifr.zzifd < this.zzifr.zzife) {
            int zzga = this.zzifr.zzga(zzbey);
            T newInstance = zzemf.newInstance();
            this.zzifr.zzifd++;
            zzemf.zza(newInstance, this, zzejm);
            zzemf.zzaj(newInstance);
            this.zzifr.zzfy(0);
            zzeja zzeja = this.zzifr;
            zzeja.zzifd--;
            this.zzifr.zzgb(zzga);
            return newInstance;
        }
        throw new zzekj("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final <T> T zzd(zzemf<T> zzemf, zzejm zzejm) throws IOException {
        int i = this.zzifs;
        this.zzifs = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzemf.newInstance();
            zzemf.zza(newInstance, this, zzejm);
            zzemf.zzaj(newInstance);
            if (this.tag == this.zzifs) {
                return newInstance;
            }
            throw zzekj.zzbhe();
        } finally {
            this.zzifs = i;
        }
    }

    public final zzeip zzbex() throws IOException {
        zzgi(2);
        return this.zzifr.zzbex();
    }

    public final int zzbey() throws IOException {
        zzgi(0);
        return this.zzifr.zzbey();
    }

    public final int zzbez() throws IOException {
        zzgi(0);
        return this.zzifr.zzbez();
    }

    public final int zzbfa() throws IOException {
        zzgi(5);
        return this.zzifr.zzbfa();
    }

    public final long zzbfb() throws IOException {
        zzgi(1);
        return this.zzifr.zzbfb();
    }

    public final int zzbfc() throws IOException {
        zzgi(0);
        return this.zzifr.zzbfc();
    }

    public final long zzbfd() throws IOException {
        zzgi(0);
        return this.zzifr.zzbfd();
    }

    public final void zzl(List<Double> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzejk) {
            zzejk zzejk = (zzejk) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzejk.zze(this.zzifr.readDouble());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgj(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzejk.zze(this.zzifr.readDouble());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(this.zzifr.readDouble()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgj(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Double.valueOf(this.zzifr.readDouble()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzm(List<Float> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzejy) {
            zzejy zzejy = (zzejy) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgk(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzejy.zzh(this.zzifr.readFloat());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else if (i == 5) {
                do {
                    zzejy.zzh(this.zzifr.readFloat());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgk(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Float.valueOf(this.zzifr.readFloat()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(this.zzifr.readFloat()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzn(List<Long> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekx.zzfs(this.zzifr.zzbeq());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekx.zzfs(this.zzifr.zzbeq());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzifr.zzbeq()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Long.valueOf(this.zzifr.zzbeq()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzo(List<Long> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekx.zzfs(this.zzifr.zzber());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekx.zzfs(this.zzifr.zzber());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzifr.zzber()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Long.valueOf(this.zzifr.zzber()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzp(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekc.zzhc(this.zzifr.zzbes());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekc.zzhc(this.zzifr.zzbes());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbes()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbes()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzq(List<Long> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzekx.zzfs(this.zzifr.zzbet());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgj(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzekx.zzfs(this.zzifr.zzbet());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzifr.zzbet()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgj(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Long.valueOf(this.zzifr.zzbet()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzr(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgk(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzekc.zzhc(this.zzifr.zzbeu());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else if (i == 5) {
                do {
                    zzekc.zzhc(this.zzifr.zzbeu());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgk(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbeu()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbeu()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzs(List<Boolean> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzein) {
            zzein zzein = (zzein) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzein.addBoolean(this.zzifr.zzbev());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzein.addBoolean(this.zzifr.zzbev());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zzifr.zzbev()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Boolean.valueOf(this.zzifr.zzbev()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void readStringList(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzt(List<String> list) throws IOException {
        zza(list, true);
    }

    private final void zza(List<String> list, boolean z) throws IOException {
        int zzbep;
        int zzbep2;
        if ((this.tag & 7) != 2) {
            throw zzekj.zzbhc();
        } else if (!(list instanceof zzeku) || z) {
            do {
                list.add(z ? zzbew() : readString());
                if (!this.zzifr.zzbff()) {
                    zzbep = this.zzifr.zzbep();
                } else {
                    return;
                }
            } while (zzbep == this.tag);
            this.zzift = zzbep;
        } else {
            zzeku zzeku = (zzeku) list;
            do {
                zzeku.zzaj(zzbex());
                if (!this.zzifr.zzbff()) {
                    zzbep2 = this.zzifr.zzbep();
                } else {
                    return;
                }
            } while (zzbep2 == this.tag);
            this.zzift = zzbep2;
        }
    }

    public final <T> void zza(List<T> list, zzemf<T> zzemf, zzejm zzejm) throws IOException {
        int zzbep;
        int i = this.tag;
        if ((i & 7) == 2) {
            do {
                list.add(zzc(zzemf, zzejm));
                if (!this.zzifr.zzbff() && this.zzift == 0) {
                    zzbep = this.zzifr.zzbep();
                } else {
                    return;
                }
            } while (zzbep == i);
            this.zzift = zzbep;
            return;
        }
        throw zzekj.zzbhc();
    }

    public final <T> void zzb(List<T> list, zzemf<T> zzemf, zzejm zzejm) throws IOException {
        int zzbep;
        int i = this.tag;
        if ((i & 7) == 3) {
            do {
                list.add(zzd(zzemf, zzejm));
                if (!this.zzifr.zzbff() && this.zzift == 0) {
                    zzbep = this.zzifr.zzbep();
                } else {
                    return;
                }
            } while (zzbep == i);
            this.zzift = zzbep;
            return;
        }
        throw zzekj.zzbhc();
    }

    public final void zzu(List<zzeip> list) throws IOException {
        int zzbep;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzbex());
                if (!this.zzifr.zzbff()) {
                    zzbep = this.zzifr.zzbep();
                } else {
                    return;
                }
            } while (zzbep == this.tag);
            this.zzift = zzbep;
            return;
        }
        throw zzekj.zzbhc();
    }

    public final void zzv(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekc.zzhc(this.zzifr.zzbey());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekc.zzhc(this.zzifr.zzbey());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbey()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbey()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzw(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekc.zzhc(this.zzifr.zzbez());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekc.zzhc(this.zzifr.zzbez());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbez()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbez()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzx(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgk(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzekc.zzhc(this.zzifr.zzbfa());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else if (i == 5) {
                do {
                    zzekc.zzhc(this.zzifr.zzbfa());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgk(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbfa()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbfa()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzy(List<Long> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    zzekx.zzfs(this.zzifr.zzbfb());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbey = this.zzifr.zzbey();
                zzgj(zzbey);
                int zzbfg = this.zzifr.zzbfg() + zzbey;
                do {
                    zzekx.zzfs(this.zzifr.zzbfb());
                } while (this.zzifr.zzbfg() < zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(this.zzifr.zzbfb()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbey2 = this.zzifr.zzbey();
                zzgj(zzbey2);
                int zzbfg2 = this.zzifr.zzbfg() + zzbey2;
                do {
                    list.add(Long.valueOf(this.zzifr.zzbfb()));
                } while (this.zzifr.zzbfg() < zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzz(List<Integer> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekc) {
            zzekc zzekc = (zzekc) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekc.zzhc(this.zzifr.zzbfc());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekc.zzhc(this.zzifr.zzbfc());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbfc()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Integer.valueOf(this.zzifr.zzbfc()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    public final void zzaa(List<Long> list) throws IOException {
        int zzbep;
        int zzbep2;
        if (list instanceof zzekx) {
            zzekx zzekx = (zzekx) list;
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    zzekx.zzfs(this.zzifr.zzbfd());
                    if (!this.zzifr.zzbff()) {
                        zzbep2 = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep2 == this.tag);
                this.zzift = zzbep2;
            } else if (i == 2) {
                int zzbfg = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    zzekx.zzfs(this.zzifr.zzbfd());
                } while (this.zzifr.zzbfg() < zzbfg);
                zzgl(zzbfg);
            } else {
                throw zzekj.zzbhc();
            }
        } else {
            int i2 = this.tag & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(this.zzifr.zzbfd()));
                    if (!this.zzifr.zzbff()) {
                        zzbep = this.zzifr.zzbep();
                    } else {
                        return;
                    }
                } while (zzbep == this.tag);
                this.zzift = zzbep;
            } else if (i2 == 2) {
                int zzbfg2 = this.zzifr.zzbfg() + this.zzifr.zzbey();
                do {
                    list.add(Long.valueOf(this.zzifr.zzbfd()));
                } while (this.zzifr.zzbfg() < zzbfg2);
                zzgl(zzbfg2);
            } else {
                throw zzekj.zzbhc();
            }
        }
    }

    private static void zzgj(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzekj.zzbhe();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (zzbfo() != false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        throw new com.google.android.gms.internal.ads.zzekj("Unable to parse map entry.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.ads.zzele<K, V> r9, com.google.android.gms.internal.ads.zzejm r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzgi(r0)
            com.google.android.gms.internal.ads.zzeja r1 = r7.zzifr
            int r1 = r1.zzbey()
            com.google.android.gms.internal.ads.zzeja r2 = r7.zzifr
            int r1 = r2.zzga(r1)
            K r2 = r9.zzilj
            V r3 = r9.zzclg
        L_0x0014:
            int r4 = r7.zzbfn()     // Catch:{ all -> 0x0064 }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005b
            com.google.android.gms.internal.ads.zzeja r5 = r7.zzifr     // Catch:{ all -> 0x0064 }
            boolean r5 = r5.zzbff()     // Catch:{ all -> 0x0064 }
            if (r5 != 0) goto L_0x005b
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0046
            if (r4 == r0) goto L_0x0039
            boolean r4 = r7.zzbfo()     // Catch:{ zzekm -> 0x004e }
            if (r4 == 0) goto L_0x0033
            goto L_0x0014
        L_0x0033:
            com.google.android.gms.internal.ads.zzekj r4 = new com.google.android.gms.internal.ads.zzekj     // Catch:{ zzekm -> 0x004e }
            r4.<init>(r6)     // Catch:{ zzekm -> 0x004e }
            throw r4     // Catch:{ zzekm -> 0x004e }
        L_0x0039:
            com.google.android.gms.internal.ads.zzeno r4 = r9.zzilk     // Catch:{ zzekm -> 0x004e }
            V r5 = r9.zzclg     // Catch:{ zzekm -> 0x004e }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzekm -> 0x004e }
            java.lang.Object r3 = r7.zza((com.google.android.gms.internal.ads.zzeno) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.ads.zzejm) r10)     // Catch:{ zzekm -> 0x004e }
            goto L_0x0014
        L_0x0046:
            com.google.android.gms.internal.ads.zzeno r4 = r9.zzili     // Catch:{ zzekm -> 0x004e }
            r5 = 0
            java.lang.Object r2 = r7.zza((com.google.android.gms.internal.ads.zzeno) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.ads.zzejm) r5)     // Catch:{ zzekm -> 0x004e }
            goto L_0x0014
        L_0x004e:
            boolean r4 = r7.zzbfo()     // Catch:{ all -> 0x0064 }
            if (r4 == 0) goto L_0x0055
            goto L_0x0014
        L_0x0055:
            com.google.android.gms.internal.ads.zzekj r8 = new com.google.android.gms.internal.ads.zzekj     // Catch:{ all -> 0x0064 }
            r8.<init>(r6)     // Catch:{ all -> 0x0064 }
            throw r8     // Catch:{ all -> 0x0064 }
        L_0x005b:
            r8.put(r2, r3)     // Catch:{ all -> 0x0064 }
            com.google.android.gms.internal.ads.zzeja r8 = r7.zzifr
            r8.zzgb(r1)
            return
        L_0x0064:
            r8 = move-exception
            com.google.android.gms.internal.ads.zzeja r9 = r7.zzifr
            r9.zzgb(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzejh.zza(java.util.Map, com.google.android.gms.internal.ads.zzele, com.google.android.gms.internal.ads.zzejm):void");
    }

    private final Object zza(zzeno zzeno, Class<?> cls, zzejm zzejm) throws IOException {
        switch (zzejg.zzifq[zzeno.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzbev());
            case 2:
                return zzbex();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzbez());
            case 5:
                return Integer.valueOf(zzbeu());
            case 6:
                return Long.valueOf(zzbet());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzbes());
            case 9:
                return Long.valueOf(zzber());
            case 10:
                zzgi(2);
                return zzc(zzely.zzbic().zzh(cls), zzejm);
            case 11:
                return Integer.valueOf(zzbfa());
            case 12:
                return Long.valueOf(zzbfb());
            case 13:
                return Integer.valueOf(zzbfc());
            case 14:
                return Long.valueOf(zzbfd());
            case 15:
                return zzbew();
            case 16:
                return Integer.valueOf(zzbey());
            case 17:
                return Long.valueOf(zzbeq());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static void zzgk(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzekj.zzbhe();
        }
    }

    private final void zzgl(int i) throws IOException {
        if (this.zzifr.zzbfg() != i) {
            throw zzekj.zzbgx();
        }
    }
}
