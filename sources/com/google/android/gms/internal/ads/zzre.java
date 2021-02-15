package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzre {
    private final Object lock = new Object();
    private int score;
    private final int zzbsf;
    private final int zzbsg;
    private final int zzbsh;
    private final boolean zzbsi;
    private final zzrp zzbsj;
    private final zzsa zzbsk;
    private ArrayList<String> zzbsl = new ArrayList<>();
    private ArrayList<String> zzbsm = new ArrayList<>();
    private ArrayList<zzrn> zzbsn = new ArrayList<>();
    private int zzbso = 0;
    private int zzbsp = 0;
    private int zzbsq = 0;
    private String zzbsr = "";
    private String zzbss = "";
    private String zzbst = "";

    public zzre(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        this.zzbsf = i;
        this.zzbsg = i2;
        this.zzbsh = i3;
        this.zzbsi = z;
        this.zzbsj = new zzrp(i4);
        this.zzbsk = new zzsa(i5, i6, i7);
    }

    public final boolean zzly() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzbsq == 0;
        }
        return z;
    }

    public final String zzlz() {
        return this.zzbsr;
    }

    public final String zzma() {
        return this.zzbss;
    }

    public final String zzmb() {
        return this.zzbst;
    }

    public final void zzmc() {
        synchronized (this.lock) {
            this.score -= 100;
        }
    }

    public final void zzmd() {
        synchronized (this.lock) {
            this.zzbsq--;
        }
    }

    public final void zzme() {
        synchronized (this.lock) {
            this.zzbsq++;
        }
    }

    public final void zza(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
        synchronized (this.lock) {
            if (this.zzbsq < 0) {
                zzayp.zzef("ActivityContent: negative number of WebViews.");
            }
            zzmg();
        }
    }

    public final void zzb(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
    }

    private final void zzc(String str, boolean z, float f, float f2, float f3, float f4) {
        if (str != null && str.length() >= this.zzbsh) {
            synchronized (this.lock) {
                this.zzbsl.add(str);
                this.zzbso += str.length();
                if (z) {
                    this.zzbsm.add(str);
                    this.zzbsn.add(new zzrn(f, f2, f3, f4, this.zzbsm.size() - 1));
                }
            }
        }
    }

    public final void zzmf() {
        synchronized (this.lock) {
            int zzh = zzh(this.zzbso, this.zzbsp);
            if (zzh > this.score) {
                this.score = zzh;
            }
        }
    }

    public final void zzmg() {
        synchronized (this.lock) {
            int zzh = zzh(this.zzbso, this.zzbsp);
            if (zzh > this.score) {
                this.score = zzh;
                if (!zzp.zzkv().zzwt().zzxe()) {
                    this.zzbsr = this.zzbsj.zza(this.zzbsl);
                    this.zzbss = this.zzbsj.zza(this.zzbsm);
                }
                if (!zzp.zzkv().zzwt().zzxg()) {
                    this.zzbst = this.zzbsk.zza(this.zzbsm, this.zzbsn);
                }
            }
        }
    }

    private final int zzh(int i, int i2) {
        if (this.zzbsi) {
            return this.zzbsg;
        }
        return (i * this.zzbsf) + (i2 * this.zzbsg);
    }

    public final int getScore() {
        return this.score;
    }

    public final void zzbw(int i) {
        this.zzbsp = i;
    }

    private static String zza(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            sb.append((String) obj);
            sb.append(' ');
            if (sb.length() > 100) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        String sb2 = sb.toString();
        if (sb2.length() < 100) {
            return sb2;
        }
        return sb2.substring(0, 100);
    }

    public final String toString() {
        int i = this.zzbsp;
        int i2 = this.score;
        int i3 = this.zzbso;
        String zza = zza(this.zzbsl, 100);
        String zza2 = zza(this.zzbsm, 100);
        String str = this.zzbsr;
        String str2 = this.zzbss;
        String str3 = this.zzbst;
        StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 165 + String.valueOf(zza2).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append("ActivityContent fetchId: ");
        sb.append(i);
        sb.append(" score:");
        sb.append(i2);
        sb.append(" total_length:");
        sb.append(i3);
        sb.append("\n text: ");
        sb.append(zza);
        sb.append("\n viewableText");
        sb.append(zza2);
        sb.append("\n signture: ");
        sb.append(str);
        sb.append("\n viewableSignture: ");
        sb.append(str2);
        sb.append("\n viewableSignatureForVertical: ");
        sb.append(str3);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final int zzmh() {
        return this.zzbso;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzre)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        String str = ((zzre) obj).zzbsr;
        return str != null && str.equals(this.zzbsr);
    }

    public final int hashCode() {
        return this.zzbsr.hashCode();
    }
}
