package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzi> CREATOR = new zzh();
    public final boolean zzbov;
    public final boolean zzbow;
    private final String zzbox;
    public final boolean zzboy;
    public final float zzboz;
    public final int zzbpa;
    public final boolean zzbpb;
    public final boolean zzbpc;
    public final boolean zzbpd;

    public zzi(boolean z, boolean z2, boolean z3, float f, int i, boolean z4, boolean z5, boolean z6) {
        this(z, z2, (String) null, false, 0.0f, -1, z4, z5, z6);
    }

    zzi(boolean z, boolean z2, String str, boolean z3, float f, int i, boolean z4, boolean z5, boolean z6) {
        this.zzbov = z;
        this.zzbow = z2;
        this.zzbox = str;
        this.zzboy = z3;
        this.zzboz = f;
        this.zzbpa = i;
        this.zzbpb = z4;
        this.zzbpc = z5;
        this.zzbpd = z6;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbov);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbow);
        SafeParcelWriter.writeString(parcel, 4, this.zzbox, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzboy);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzboz);
        SafeParcelWriter.writeInt(parcel, 7, this.zzbpa);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbpb);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzbpc);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzbpd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
