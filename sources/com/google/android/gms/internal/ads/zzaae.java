package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaae> CREATOR = new zzaad();
    private final int zzadm;
    private final int zzadn;

    public zzaae(int i, int i2) {
        this.zzadm = i;
        this.zzadn = i2;
    }

    public zzaae(RequestConfiguration requestConfiguration) {
        this.zzadm = requestConfiguration.getTagForChildDirectedTreatment();
        this.zzadn = requestConfiguration.getTagForUnderAgeOfConsent();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzadm);
        SafeParcelWriter.writeInt(parcel, 2, this.zzadn);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
