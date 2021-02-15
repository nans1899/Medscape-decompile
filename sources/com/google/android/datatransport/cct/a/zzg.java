package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzp;

final class zzg extends zzp {
    private final zzp.zzb zza;
    private final zza zzb;

    static final class zza extends zzp.zza {
        private zzp.zzb zza;
        private zza zzb;

        zza() {
        }

        public zzp.zza zza(zzp.zzb zzb2) {
            this.zza = zzb2;
            return this;
        }

        public zzp.zza zza(zza zza2) {
            this.zzb = zza2;
            return this;
        }

        public zzp zza() {
            return new zzg(this.zza, this.zzb, (zzf) null);
        }
    }

    /* synthetic */ zzg(zzp.zzb zzb2, zza zza2, zzf zzf) {
        this.zza = zzb2;
        this.zzb = zza2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzp)) {
            return false;
        }
        zzp.zzb zzb2 = this.zza;
        if (zzb2 != null ? zzb2.equals(((zzg) obj).zza) : ((zzg) obj).zza == null) {
            zza zza2 = this.zzb;
            if (zza2 == null) {
                if (((zzg) obj).zzb == null) {
                    return true;
                }
            } else if (zza2.equals(((zzg) obj).zzb)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        zzp.zzb zzb2 = this.zza;
        int i = 0;
        int hashCode = ((zzb2 == null ? 0 : zzb2.hashCode()) ^ 1000003) * 1000003;
        zza zza2 = this.zzb;
        if (zza2 != null) {
            i = zza2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return "ClientInfo{clientType=" + this.zza + ", androidClientInfo=" + this.zzb + "}";
    }

    public zza zzb() {
        return this.zzb;
    }

    public zzp.zzb zzc() {
        return this.zza;
    }
}
