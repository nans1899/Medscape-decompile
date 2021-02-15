package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaum extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaum> CREATOR = new zzaup();
    public final String zzbuu;
    public final zzvg zzdqr;

    public zzaum(zzvg zzvg, String str) {
        this.zzdqr = zzvg;
        this.zzbuu = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdqr, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbuu, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
