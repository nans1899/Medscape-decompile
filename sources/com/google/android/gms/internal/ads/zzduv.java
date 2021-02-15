package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzduv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzduv> CREATOR = new zzduy();
    public final int status;
    private final int versionCode;
    public final byte[] zzhoe;

    zzduv(int i, byte[] bArr, int i2) {
        byte[] bArr2;
        this.versionCode = i;
        if (bArr == null) {
            bArr2 = null;
        } else {
            bArr2 = Arrays.copyOf(bArr, bArr.length);
        }
        this.zzhoe = bArr2;
        this.status = i2;
    }

    public zzduv(byte[] bArr, int i) {
        this(1, (byte[]) null, 1);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzhoe, false);
        SafeParcelWriter.writeInt(parcel, 3, this.status);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
