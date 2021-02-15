package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzajc> CREATOR = new zzajb();
    public final int versionCode;
    public final int zzbno;
    public final int zzdgf;
    public final String zzdgg;

    public zzajc(zzajo zzajo) {
        this(2, 1, zzajo.zztf(), zzajo.getMediaAspectRatio());
    }

    public zzajc(int i, int i2, String str, int i3) {
        this.versionCode = i;
        this.zzdgf = i2;
        this.zzdgg = str;
        this.zzbno = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdgf);
        SafeParcelWriter.writeString(parcel, 2, this.zzdgg, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbno);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
