package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzr;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class zzk extends zzr {
    private final long zza;
    private final long zzb;
    private final zzp zzc;
    private final Integer zzd;
    private final String zze;
    private final List<zzq> zzf;
    private final zzu zzg;

    static final class zza extends zzr.zza {
        private Long zza;
        private Long zzb;
        private zzp zzc;
        private Integer zzd;
        private String zze;
        private List<zzq> zzf;
        private zzu zzg;

        zza() {
        }

        public zzr.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        public zzr.zza zzb(long j) {
            this.zzb = Long.valueOf(j);
            return this;
        }

        public zzr.zza zza(zzp zzp) {
            this.zzc = zzp;
            return this;
        }

        /* access modifiers changed from: package-private */
        public zzr.zza zza(Integer num) {
            this.zzd = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        public zzr.zza zza(String str) {
            this.zze = str;
            return this;
        }

        public zzr.zza zza(List<zzq> list) {
            this.zzf = list;
            return this;
        }

        public zzr.zza zza(zzu zzu) {
            this.zzg = zzu;
            return this;
        }

        public zzr zza() {
            String str = "";
            if (this.zza == null) {
                str = str + " requestTimeMs";
            }
            if (this.zzb == null) {
                str = str + " requestUptimeMs";
            }
            if (str.isEmpty()) {
                return new zzk(this.zza.longValue(), this.zzb.longValue(), this.zzc, this.zzd, this.zze, this.zzf, this.zzg, (zzj) null);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }

    /* synthetic */ zzk(long j, long j2, zzp zzp, Integer num, String str, List list, zzu zzu, zzj zzj) {
        this.zza = j;
        this.zzb = j2;
        this.zzc = zzp;
        this.zzd = num;
        this.zze = str;
        this.zzf = list;
        this.zzg = zzu;
    }

    public boolean equals(Object obj) {
        zzp zzp;
        Integer num;
        String str;
        List<zzq> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzr)) {
            return false;
        }
        zzr zzr = (zzr) obj;
        if (this.zza == zzr.zzg() && this.zzb == zzr.zzh() && ((zzp = this.zzc) != null ? zzp.equals(((zzk) zzr).zzc) : ((zzk) zzr).zzc == null) && ((num = this.zzd) != null ? num.equals(((zzk) zzr).zzd) : ((zzk) zzr).zzd == null) && ((str = this.zze) != null ? str.equals(((zzk) zzr).zze) : ((zzk) zzr).zze == null) && ((list = this.zzf) != null ? list.equals(((zzk) zzr).zzf) : ((zzk) zzr).zzf == null)) {
            zzu zzu = this.zzg;
            if (zzu == null) {
                if (((zzk) zzr).zzg == null) {
                    return true;
                }
            } else if (zzu.equals(((zzk) zzr).zzg)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        long j2 = this.zzb;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        zzp zzp = this.zzc;
        int i2 = 0;
        int hashCode = (i ^ (zzp == null ? 0 : zzp.hashCode())) * 1000003;
        Integer num = this.zzd;
        int hashCode2 = (hashCode ^ (num == null ? 0 : num.hashCode())) * 1000003;
        String str = this.zze;
        int hashCode3 = (hashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        List<zzq> list = this.zzf;
        int hashCode4 = (hashCode3 ^ (list == null ? 0 : list.hashCode())) * 1000003;
        zzu zzu = this.zzg;
        if (zzu != null) {
            i2 = zzu.hashCode();
        }
        return hashCode4 ^ i2;
    }

    public String toString() {
        return "LogRequest{requestTimeMs=" + this.zza + ", requestUptimeMs=" + this.zzb + ", clientInfo=" + this.zzc + ", logSource=" + this.zzd + ", logSourceName=" + this.zze + ", logEvents=" + this.zzf + ", qosTier=" + this.zzg + "}";
    }

    public zzp zzb() {
        return this.zzc;
    }

    @Encodable.Field(name = "logEvent")
    public List<zzq> zzc() {
        return this.zzf;
    }

    public Integer zzd() {
        return this.zzd;
    }

    public String zze() {
        return this.zze;
    }

    public zzu zzf() {
        return this.zzg;
    }

    public long zzg() {
        return this.zza;
    }

    public long zzh() {
        return this.zzb;
    }
}
