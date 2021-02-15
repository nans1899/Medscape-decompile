package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzua;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdqc implements zzdpz {
    private final ConcurrentHashMap<zzdqj, zzdqa> zzhhe;
    private zzdqg zzhhf;
    private zzdqe zzhhg = new zzdqe();

    public zzdqc(zzdqg zzdqg) {
        this.zzhhe = new ConcurrentHashMap<>(zzdqg.zzhhz);
        this.zzhhf = zzdqg;
    }

    public final synchronized zzdqk<?> zza(zzdqj zzdqj) {
        zzdqk<?> zzdqk;
        zzdqa zzdqa = this.zzhhe.get(zzdqj);
        zzdqk = null;
        if (zzdqa != null) {
            zzdqk = zzdqa.zzatz();
            if (zzdqk == null) {
                this.zzhhg.zzauh();
            }
            zza(zzdqk, zzdqa.zzaud());
        } else {
            this.zzhhg.zzaug();
            zza((zzdqk<?>) null, (zzdra) null);
        }
        return zzdqk;
    }

    public final synchronized boolean zza(zzdqj zzdqj, zzdqk<?> zzdqk) {
        boolean zzb;
        zzdqa zzdqa = this.zzhhe.get(zzdqj);
        zzdqk.zzhis = zzp.zzky().currentTimeMillis();
        if (zzdqa == null) {
            zzdqa = new zzdqa(this.zzhhf.zzhhz, this.zzhhf.zzhia * 1000);
            if (this.zzhhe.size() == this.zzhhf.zzhhy) {
                int i = zzdqb.zzhhd[this.zzhhf.zzhid - 1];
                long j = Long.MAX_VALUE;
                zzdqj zzdqj2 = null;
                if (i == 1) {
                    for (Map.Entry next : this.zzhhe.entrySet()) {
                        if (((zzdqa) next.getValue()).getCreationTimeMillis() < j) {
                            j = ((zzdqa) next.getValue()).getCreationTimeMillis();
                            zzdqj2 = (zzdqj) next.getKey();
                        }
                    }
                    if (zzdqj2 != null) {
                        this.zzhhe.remove(zzdqj2);
                    }
                } else if (i == 2) {
                    for (Map.Entry next2 : this.zzhhe.entrySet()) {
                        if (((zzdqa) next2.getValue()).zzaua() < j) {
                            j = ((zzdqa) next2.getValue()).zzaua();
                            zzdqj2 = (zzdqj) next2.getKey();
                        }
                    }
                    if (zzdqj2 != null) {
                        this.zzhhe.remove(zzdqj2);
                    }
                } else if (i == 3) {
                    int i2 = Integer.MAX_VALUE;
                    for (Map.Entry next3 : this.zzhhe.entrySet()) {
                        if (((zzdqa) next3.getValue()).zzaub() < i2) {
                            i2 = ((zzdqa) next3.getValue()).zzaub();
                            zzdqj2 = (zzdqj) next3.getKey();
                        }
                    }
                    if (zzdqj2 != null) {
                        this.zzhhe.remove(zzdqj2);
                    }
                }
                this.zzhhg.zzauj();
            }
            this.zzhhe.put(zzdqj, zzdqa);
            this.zzhhg.zzaui();
        }
        zzb = zzdqa.zzb(zzdqk);
        this.zzhhg.zzauk();
        zzdqd zzaul = this.zzhhg.zzaul();
        zzdra zzaud = zzdqa.zzaud();
        if (zzdqk != null) {
            zzdqk.zzhiq.zzaji().zzd((zzua.zzb) ((zzejz) zzua.zzb.zzni().zza(zzua.zzb.zza.zzng().zzb(zzua.zzb.zzc.IN_MEMORY).zza(zzua.zzb.zze.zznm().zzu(zzaul.zzhhh).zzv(zzaul.zzhhi).zzcd(zzaud.zzhjn))).zzbgt()));
        }
        dumpToLog();
        return zzb;
    }

    public final synchronized boolean zzb(zzdqj zzdqj) {
        zzdqa zzdqa = this.zzhhe.get(zzdqj);
        if (zzdqa == null) {
            return true;
        }
        if (zzdqa.size() < this.zzhhf.zzhhz) {
            return true;
        }
        return false;
    }

    @Deprecated
    public final zzdqj zza(zzvg zzvg, String str, zzvs zzvs) {
        return new zzdqm(zzvg, str, new zzatk(this.zzhhf.zzvr).zzvn().zzdus, this.zzhhf.zzhib, zzvs);
    }

    public final zzdqg zzaty() {
        return this.zzhhf;
    }

    private final void zza(zzdqk<?> zzdqk, zzdra zzdra) {
        if (zzdqk != null) {
            zzdqk.zzhiq.zzaji().zzc((zzua.zzb) ((zzejz) zzua.zzb.zzni().zza(zzua.zzb.zza.zzng().zzb(zzua.zzb.zzc.IN_MEMORY).zza(zzua.zzb.zzd.zznk().zzt(zzdra.zzhjo).zzcc(zzdra.zzhjn))).zzbgt()));
        }
        dumpToLog();
    }

    private final void dumpToLog() {
        if (zzdqg.zzaun()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.zzhhf.zzhhx);
            sb.append(" PoolCollection");
            sb.append(this.zzhhg.zzaum());
            int i = 0;
            for (Map.Entry next : this.zzhhe.entrySet()) {
                i++;
                sb.append(i);
                sb.append(". ");
                sb.append(next.getValue());
                sb.append("#");
                sb.append(((zzdqj) next.getKey()).hashCode());
                sb.append("    ");
                for (int i2 = 0; i2 < ((zzdqa) next.getValue()).size(); i2++) {
                    sb.append("[O]");
                }
                for (int size = ((zzdqa) next.getValue()).size(); size < this.zzhhf.zzhhz; size++) {
                    sb.append("[ ]");
                }
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append(((zzdqa) next.getValue()).zzauc());
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
            while (i < this.zzhhf.zzhhy) {
                i++;
                sb.append(i);
                sb.append(".\n");
            }
            zzayp.zzef(sb.toString());
        }
    }
}
