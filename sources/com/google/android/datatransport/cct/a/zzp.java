package com.google.android.datatransport.cct.a;

import com.google.android.datatransport.cct.a.zzg;

public abstract class zzp {

    public static abstract class zza {
        public abstract zza zza(zza zza);

        public abstract zza zza(zzb zzb);

        public abstract zzp zza();
    }

    public enum zzb {
        UNKNOWN(0),
        ANDROID_FIREBASE(23);

        static {
            UNKNOWN = new zzb("UNKNOWN", 0, 0);
            ANDROID_FIREBASE = new zzb("ANDROID_FIREBASE", 1, 23);
        }

        private zzb(int i) {
        }
    }

    public static zza zza() {
        return new zzg.zza();
    }

    public abstract zza zzb();

    public abstract zzb zzc();
}
