package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmm extends zzmj {
    public static final Parcelable.Creator<zzmm> CREATOR = new zzml();
    private final String description;
    private final String value;

    public zzmm(String str, String str2, String str3) {
        super(str);
        this.description = null;
        this.value = str3;
    }

    zzmm(Parcel parcel) {
        super(parcel.readString());
        this.description = parcel.readString();
        this.value = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmm zzmm = (zzmm) obj;
            return this.zzagx.equals(zzmm.zzagx) && zzpq.zza(this.description, zzmm.description) && zzpq.zza(this.value, zzmm.value);
        }
    }

    public final int hashCode() {
        int hashCode = (this.zzagx.hashCode() + 527) * 31;
        String str = this.description;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.value;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzagx);
        parcel.writeString(this.description);
        parcel.writeString(this.value);
    }
}
