package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmc implements Parcelable {
    public static final Parcelable.Creator<zzmc> CREATOR = new zzme();
    private final zza[] zzbde;

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public interface zza extends Parcelable {
    }

    public zzmc(List<? extends zza> list) {
        zza[] zzaArr = new zza[list.size()];
        this.zzbde = zzaArr;
        list.toArray(zzaArr);
    }

    public final int describeContents() {
        return 0;
    }

    zzmc(Parcel parcel) {
        this.zzbde = new zza[parcel.readInt()];
        int i = 0;
        while (true) {
            zza[] zzaArr = this.zzbde;
            if (i < zzaArr.length) {
                zzaArr[i] = (zza) parcel.readParcelable(zza.class.getClassLoader());
                i++;
            } else {
                return;
            }
        }
    }

    public final int length() {
        return this.zzbde.length;
    }

    public final zza zzba(int i) {
        return this.zzbde[i];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzbde, ((zzmc) obj).zzbde);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzbde);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zzbde.length);
        for (zza writeParcelable : this.zzbde) {
            parcel.writeParcelable(writeParcelable, 0);
        }
    }
}
