package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzasr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzasr> CREATOR = new zzasq();
    public final boolean zzdtr;
    public final List<String> zzdts;

    public zzasr() {
        this(false, Collections.emptyList());
    }

    public zzasr(boolean z, List<String> list) {
        this.zzdtr = z;
        this.zzdts = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzdtr);
        SafeParcelWriter.writeStringList(parcel, 3, this.zzdts, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
