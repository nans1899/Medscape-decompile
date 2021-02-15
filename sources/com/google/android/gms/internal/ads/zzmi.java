package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmi extends zzmj {
    public static final Parcelable.Creator<zzmi> CREATOR = new zzmh();
    public final String description;
    public final String text;
    private final String zzaht;

    public zzmi(String str, String str2, String str3) {
        super("COMM");
        this.zzaht = str;
        this.description = str2;
        this.text = str3;
    }

    zzmi(Parcel parcel) {
        super("COMM");
        this.zzaht = parcel.readString();
        this.description = parcel.readString();
        this.text = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmi zzmi = (zzmi) obj;
            return zzpq.zza(this.description, zzmi.description) && zzpq.zza(this.zzaht, zzmi.zzaht) && zzpq.zza(this.text, zzmi.text);
        }
    }

    public final int hashCode() {
        String str = this.zzaht;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 527) * 31;
        String str2 = this.description;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.text;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzagx);
        parcel.writeString(this.zzaht);
        parcel.writeString(this.text);
    }
}
