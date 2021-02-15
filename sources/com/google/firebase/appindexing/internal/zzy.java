package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzy> CREATOR = new zzx();
    private final int type;
    private final Thing[] zzfo;
    private final String[] zzfp;
    private final String[] zzfq;
    private final zza zzfr;
    private final String zzfs;
    private final String zzft;

    zzy(int i, Thing[] thingArr, String[] strArr, String[] strArr2, zza zza, String str, String str2) {
        if (!(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7)) {
            i = 0;
        }
        this.type = i;
        this.zzfo = thingArr;
        this.zzfp = strArr;
        this.zzfq = strArr2;
        this.zzfr = zza;
        this.zzfs = str;
        this.zzft = str2;
    }

    zzy(int i, Thing[] thingArr) {
        this(1, thingArr, (String[]) null, (String[]) null, (zza) null, (String) null, (String) null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.type);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzfo, i, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzfp, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzfq, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzfr, i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzfs, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzft, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
