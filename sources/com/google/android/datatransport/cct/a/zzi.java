package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzq;
import java.util.Arrays;

final class zzi extends zzq {
    private final long zza;
    private final Integer zzb;
    private final long zzc;
    private final byte[] zzd;
    private final String zze;
    private final long zzf;
    private final zzt zzg;

    static final class zza extends zzq.zza {
        private Long zza;
        private Integer zzb;
        private Long zzc;
        private byte[] zzd;
        private String zze;
        private Long zzf;
        private zzt zzg;

        zza() {
        }

        public zzq.zza zza(long j) {
            this.zza = Long.valueOf(j);
            return this;
        }

        public zzq.zza zzb(long j) {
            this.zzc = Long.valueOf(j);
            return this;
        }

        public zzq.zza zzc(long j) {
            this.zzf = Long.valueOf(j);
            return this;
        }

        public zzq.zza zza(Integer num) {
            this.zzb = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        public zzq.zza zza(byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        /* access modifiers changed from: package-private */
        public zzq.zza zza(String str) {
            this.zze = str;
            return this;
        }

        public zzq.zza zza(zzt zzt) {
            this.zzg = zzt;
            return this;
        }

        public zzq zza() {
            String str = "";
            if (this.zza == null) {
                str = str + " eventTimeMs";
            }
            if (this.zzc == null) {
                str = str + " eventUptimeMs";
            }
            if (this.zzf == null) {
                str = str + " timezoneOffsetSeconds";
            }
            if (str.isEmpty()) {
                return new zzi(this.zza.longValue(), this.zzb, this.zzc.longValue(), this.zzd, this.zze, this.zzf.longValue(), this.zzg, (zzh) null);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }

    /* synthetic */ zzi(long j, Integer num, long j2, byte[] bArr, String str, long j3, zzt zzt, zzh zzh) {
        this.zza = j;
        this.zzb = num;
        this.zzc = j2;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = j3;
        this.zzg = zzt;
    }

    public boolean equals(Object obj) {
        Integer num;
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq zzq = (zzq) obj;
        if (this.zza == zzq.zzb() && ((num = this.zzb) != null ? num.equals(((zzi) zzq).zzb) : ((zzi) zzq).zzb == null) && this.zzc == zzq.zzc()) {
            if (Arrays.equals(this.zzd, zzq instanceof zzi ? ((zzi) zzq).zzd : zzq.zze()) && ((str = this.zze) != null ? str.equals(((zzi) zzq).zze) : ((zzi) zzq).zze == null) && this.zzf == zzq.zzg()) {
                zzt zzt = this.zzg;
                if (zzt == null) {
                    if (((zzi) zzq).zzg == null) {
                        return true;
                    }
                } else if (zzt.equals(((zzi) zzq).zzg)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zza;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        Integer num = this.zzb;
        int i2 = 0;
        int hashCode = num == null ? 0 : num.hashCode();
        long j2 = this.zzc;
        int hashCode2 = (((((i ^ hashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ Arrays.hashCode(this.zzd)) * 1000003;
        String str = this.zze;
        int hashCode3 = str == null ? 0 : str.hashCode();
        long j3 = this.zzf;
        int i3 = (((hashCode2 ^ hashCode3) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003;
        zzt zzt = this.zzg;
        if (zzt != null) {
            i2 = zzt.hashCode();
        }
        return i3 ^ i2;
    }

    public String toString() {
        return "LogEvent{eventTimeMs=" + this.zza + ", eventCode=" + this.zzb + ", eventUptimeMs=" + this.zzc + ", sourceExtension=" + Arrays.toString(this.zzd) + ", sourceExtensionJsonProto3=" + this.zze + ", timezoneOffsetSeconds=" + this.zzf + ", networkConnectionInfo=" + this.zzg + "}";
    }

    public Integer zza() {
        return this.zzb;
    }

    public long zzb() {
        return this.zza;
    }

    public long zzc() {
        return this.zzc;
    }

    public zzt zzd() {
        return this.zzg;
    }

    public byte[] zze() {
        return this.zzd;
    }

    public String zzf() {
        return this.zze;
    }

    public long zzg() {
        return this.zzf;
    }
}
