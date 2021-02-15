package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzuy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuy> CREATOR = new zzvb();
    public final String zzcgt;
    public final String zzcgu;

    public zzuy(String str, String str2) {
        this.zzcgt = str;
        this.zzcgu = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzcgt, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzcgu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
