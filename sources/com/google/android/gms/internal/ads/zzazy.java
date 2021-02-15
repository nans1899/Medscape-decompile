package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzazy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzazy> CREATOR = new zzbaa();
    public final int errorCode;
    public final String zzacm;

    public static zzazy zzc(Throwable th) {
        String str;
        zzva zzh = zzdpe.zzh(th);
        if (zzdwf.zzas(th.getMessage())) {
            str = zzh.zzcgw;
        } else {
            str = th.getMessage();
        }
        return new zzazy(str, zzh.errorCode);
    }

    zzazy(String str, int i) {
        this.zzacm = str == null ? "" : str;
        this.errorCode = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzacm, false);
        SafeParcelWriter.writeInt(parcel, 2, this.errorCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
