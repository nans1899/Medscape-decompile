package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzate extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzate> CREATOR = new zzath();
    String zzdud;

    public zzate(String str) {
        this.zzdud = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdud, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
