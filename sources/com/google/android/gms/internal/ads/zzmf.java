package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmf implements Parcelable.Creator<zzmg> {
    zzmf() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzmg[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zzmg(parcel);
    }
}
