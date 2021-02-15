package com.google.android.datatransport.cct.a;

import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class zze extends zzo {
    private final List<zzr> zza;

    zze(List<zzr> list) {
        if (list != null) {
            this.zza = list;
            return;
        }
        throw new NullPointerException("Null logRequests");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzo) {
            return this.zza.equals(((zzo) obj).zza());
        }
        return false;
    }

    public int hashCode() {
        return this.zza.hashCode() ^ 1000003;
    }

    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.zza + "}";
    }

    @Encodable.Field(name = "logRequest")
    public List<zzr> zza() {
        return this.zza;
    }
}
