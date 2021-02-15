package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzar();
    private final float zzdv;
    public final String zzej;
    public final zzab zzep;
    private final zzab zzeq;
    public final String zzes;
    private final zzal[] zzey;
    private final boolean zzez;

    public zzao(zzal[] zzalArr, zzab zzab, zzab zzab2, String str, float f, String str2, boolean z) {
        this.zzey = zzalArr;
        this.zzep = zzab;
        this.zzeq = zzab2;
        this.zzes = str;
        this.zzdv = f;
        this.zzej = str2;
        this.zzez = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzey, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzep, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzeq, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzes, false);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzdv);
        SafeParcelWriter.writeString(parcel, 7, this.zzej, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzez);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
