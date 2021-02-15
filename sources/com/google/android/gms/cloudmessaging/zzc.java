package com.google.android.gms.cloudmessaging;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@16.0.0 */
final class zzc implements Parcelable.Creator<zza> {
    zzc() {
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zza[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new zza(parcel.readStrongBinder());
    }
}
