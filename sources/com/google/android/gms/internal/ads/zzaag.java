package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaag> CREATOR = new zzaaf();
    public final String zzbqw;

    public zzaag(SearchAdRequest searchAdRequest) {
        this.zzbqw = searchAdRequest.getQuery();
    }

    zzaag(String str) {
        this.zzbqw = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 15, this.zzbqw, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
