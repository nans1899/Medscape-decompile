package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmg extends zzmj {
    public static final Parcelable.Creator<zzmg> CREATOR = new zzmf();
    private final String description;
    private final String mimeType;
    private final int zzbdh;
    private final byte[] zzbdi;

    public zzmg(String str, String str2, int i, byte[] bArr) {
        super("APIC");
        this.mimeType = str;
        this.description = null;
        this.zzbdh = 3;
        this.zzbdi = bArr;
    }

    zzmg(Parcel parcel) {
        super("APIC");
        this.mimeType = parcel.readString();
        this.description = parcel.readString();
        this.zzbdh = parcel.readInt();
        this.zzbdi = parcel.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzmg zzmg = (zzmg) obj;
            return this.zzbdh == zzmg.zzbdh && zzpq.zza(this.mimeType, zzmg.mimeType) && zzpq.zza(this.description, zzmg.description) && Arrays.equals(this.zzbdi, zzmg.zzbdi);
        }
    }

    public final int hashCode() {
        int i = (this.zzbdh + 527) * 31;
        String str = this.mimeType;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.description;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((hashCode + i2) * 31) + Arrays.hashCode(this.zzbdi);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mimeType);
        parcel.writeString(this.description);
        parcel.writeInt(this.zzbdh);
        parcel.writeByteArray(this.zzbdi);
    }
}
