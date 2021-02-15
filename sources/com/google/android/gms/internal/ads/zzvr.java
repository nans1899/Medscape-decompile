package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvr> CREATOR = new zzvq();
    public String zzcib;
    public long zzcic;
    public zzva zzcid;
    public Bundle zzcie;

    public zzvr(String str, long j, zzva zzva, Bundle bundle) {
        this.zzcib = str;
        this.zzcic = j;
        this.zzcid = zzva;
        this.zzcie = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzcib, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzcic);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzcid, i, false);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzcie, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
