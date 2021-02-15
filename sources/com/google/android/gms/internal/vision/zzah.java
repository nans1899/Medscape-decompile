package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzah> CREATOR = new zzag();
    private final float zzdv;
    public final String zzej;
    public final zzao[] zzeo;
    public final zzab zzep;
    private final zzab zzeq;
    private final zzab zzer;
    public final String zzes;
    private final int zzet;
    public final boolean zzeu;
    public final int zzev;
    public final int zzew;

    public zzah(zzao[] zzaoArr, zzab zzab, zzab zzab2, zzab zzab3, String str, float f, String str2, int i, boolean z, int i2, int i3) {
        this.zzeo = zzaoArr;
        this.zzep = zzab;
        this.zzeq = zzab2;
        this.zzer = zzab3;
        this.zzes = str;
        this.zzdv = f;
        this.zzej = str2;
        this.zzet = i;
        this.zzeu = z;
        this.zzev = i2;
        this.zzew = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzeo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzep, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzeq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzer, i, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzes, false);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzdv);
        SafeParcelWriter.writeString(parcel, 8, this.zzej, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzet);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzeu);
        SafeParcelWriter.writeInt(parcel, 11, this.zzev);
        SafeParcelWriter.writeInt(parcel, 12, this.zzew);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
