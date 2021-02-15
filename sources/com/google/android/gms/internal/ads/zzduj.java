package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzduj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzduj> CREATOR = new zzdum();
    private final String packageName;
    private final int versionCode;
    private final String zzhnp;

    zzduj(int i, String str, String str2) {
        this.versionCode = i;
        this.packageName = str;
        this.zzhnp = str2;
    }

    public zzduj(String str, String str2) {
        this(1, str, str2);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzhnp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
