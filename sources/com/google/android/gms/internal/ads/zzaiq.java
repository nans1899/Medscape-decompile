package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaiq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaiq> CREATOR = new zzaip();
    public final String description;
    public final String zzdfz;
    public final boolean zzdga;
    public final int zzdgb;

    public zzaiq(String str, boolean z, int i, String str2) {
        this.zzdfz = str;
        this.zzdga = z;
        this.zzdgb = i;
        this.description = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzdfz, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdga);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdgb);
        SafeParcelWriter.writeString(parcel, 4, this.description, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
