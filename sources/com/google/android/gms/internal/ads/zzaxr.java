package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaxr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaxr> CREATOR = new zzaxq();
    public final String zzbrb;
    @Deprecated
    public final String zzbuu;
    @Deprecated
    public final zzvn zzdye;
    public final zzvg zzdyf;

    public zzaxr(String str, String str2, zzvn zzvn, zzvg zzvg) {
        this.zzbuu = str;
        this.zzbrb = str2;
        this.zzdye = zzvn;
        this.zzdyf = zzvg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzbuu, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzbrb, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdye, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdyf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
