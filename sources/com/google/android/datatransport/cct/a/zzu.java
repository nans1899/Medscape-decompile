package com.google.android.datatransport.cct.a;

import android.util.SparseArray;
import com.facebook.share.internal.MessengerShareContentUtility;

public enum zzu {
    zza(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4),
    UNRECOGNIZED(-1);
    
    private static final SparseArray<zzu> zzg = null;

    static {
        zza = new zzu(MessengerShareContentUtility.PREVIEW_DEFAULT, 0, 0);
        UNMETERED_ONLY = new zzu("UNMETERED_ONLY", 1, 1);
        UNMETERED_OR_DAILY = new zzu("UNMETERED_OR_DAILY", 2, 2);
        FAST_IF_RADIO_AWAKE = new zzu("FAST_IF_RADIO_AWAKE", 3, 3);
        NEVER = new zzu("NEVER", 4, 4);
        UNRECOGNIZED = new zzu("UNRECOGNIZED", 5, -1);
        SparseArray<zzu> sparseArray = new SparseArray<>();
        zzg = sparseArray;
        sparseArray.put(0, zza);
        zzg.put(1, UNMETERED_ONLY);
        zzg.put(2, UNMETERED_OR_DAILY);
        zzg.put(3, FAST_IF_RADIO_AWAKE);
        zzg.put(4, NEVER);
        zzg.put(-1, UNRECOGNIZED);
    }

    private zzu(int i) {
    }
}
