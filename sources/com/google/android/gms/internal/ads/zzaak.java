package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaak> CREATOR = new zzaaj();
    public final boolean zzadv;
    public final boolean zzadw;
    public final boolean zzadx;

    public zzaak(VideoOptions videoOptions) {
        this(videoOptions.getStartMuted(), videoOptions.getCustomControlsRequested(), videoOptions.getClickToExpandRequested());
    }

    public zzaak(boolean z, boolean z2, boolean z3) {
        this.zzadv = z;
        this.zzadw = z2;
        this.zzadx = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzadv);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzadw);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzadx);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
