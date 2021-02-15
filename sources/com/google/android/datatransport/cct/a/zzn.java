package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzt;

final class zzn extends zzt {
    private final zzt.zzc zza;
    private final zzt.zzb zzb;

    static final class zza extends zzt.zza {
        private zzt.zzc zza;
        private zzt.zzb zzb;

        zza() {
        }

        public zzt.zza zza(zzt.zzc zzc) {
            this.zza = zzc;
            return this;
        }

        public zzt.zza zza(zzt.zzb zzb2) {
            this.zzb = zzb2;
            return this;
        }

        public zzt zza() {
            return new zzn(this.zza, this.zzb, (zzm) null);
        }
    }

    /* synthetic */ zzn(zzt.zzc zzc, zzt.zzb zzb2, zzm zzm) {
        this.zza = zzc;
        this.zzb = zzb2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzt)) {
            return false;
        }
        zzt.zzc zzc = this.zza;
        if (zzc != null ? zzc.equals(((zzn) obj).zza) : ((zzn) obj).zza == null) {
            zzt.zzb zzb2 = this.zzb;
            if (zzb2 == null) {
                if (((zzn) obj).zzb == null) {
                    return true;
                }
            } else if (zzb2.equals(((zzn) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzt.zzc zzc = this.zza;
        int i = 0;
        int hashCode = ((zzc == null ? 0 : zzc.hashCode()) ^ 1000003) * 1000003;
        zzt.zzb zzb2 = this.zzb;
        if (zzb2 != null) {
            i = zzb2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "NetworkConnectionInfo{networkType=" + this.zza + ", mobileSubtype=" + this.zzb + "}";
    }

    public zzt.zzb zzb() {
        return this.zzb;
    }

    public zzt.zzc zzc() {
        return this.zza;
    }
}
