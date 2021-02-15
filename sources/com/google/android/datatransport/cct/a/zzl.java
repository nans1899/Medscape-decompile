package com.google.android.datatransport.cct.a;

final class zzl extends zzs {
    private final long zza;

    zzl(long j) {
        this.zza = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzs) || this.zza != ((zzs) obj).zza()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.zza;
        return 1000003 ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "LogResponse{nextRequestWaitMillis=" + this.zza + "}";
    }

    public long zza() {
        return this.zza;
    }
}
