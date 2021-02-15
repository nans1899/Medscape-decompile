package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzadu> CREATOR = new zzadt();
    public final int versionCode;
    public final int zzbnn;
    public final int zzbno;
    public final boolean zzbnp;
    public final int zzbnq;
    public final boolean zzbns;
    public final boolean zzddm;
    public final zzaak zzddn;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzadu(NativeAdOptions nativeAdOptions) {
        this(4, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzaak(nativeAdOptions.getVideoOptions()) : null, nativeAdOptions.zzjt(), nativeAdOptions.getMediaAspectRatio());
    }

    public zzadu(int i, boolean z, int i2, boolean z2, int i3, zzaak zzaak, boolean z3, int i4) {
        this.versionCode = i;
        this.zzddm = z;
        this.zzbnn = i2;
        this.zzbnp = z2;
        this.zzbnq = i3;
        this.zzddn = zzaak;
        this.zzbns = z3;
        this.zzbno = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzddm);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbnn);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbnp);
        SafeParcelWriter.writeInt(parcel, 5, this.zzbnq);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzddn, i, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbns);
        SafeParcelWriter.writeInt(parcel, 8, this.zzbno);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
