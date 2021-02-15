package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdut extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdut> CREATOR = new zzduw();
    private final int versionCode;
    private final String zzhmn;
    private final String zzhmo;
    private final int zzhmp;
    private final int zzhod;

    zzdut(int i, int i2, int i3, String str, String str2) {
        this.versionCode = i;
        this.zzhmp = i2;
        this.zzhmn = str;
        this.zzhmo = str2;
        this.zzhod = i3;
    }

    public zzdut(int i, zzgo zzgo, String str, String str2) {
        this(1, i, zzgo.zzv(), str, str2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.zzhmp);
        SafeParcelWriter.writeString(parcel, 3, this.zzhmn, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzhmo, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzhod);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
