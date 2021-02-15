package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzo extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zzo> CREATOR = new zzr();
    private Status zzv;
    private List<zzw> zzw;
    @Deprecated
    private String[] zzx;

    public zzo() {
    }

    public final Status getStatus() {
        return this.zzv;
    }

    zzo(Status status, List<zzw> list, String[] strArr) {
        this.zzv = status;
        this.zzw = list;
        this.zzx = strArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzv, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzw, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
