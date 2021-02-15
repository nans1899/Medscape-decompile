package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaie extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaie> CREATOR = new zzaid();
    public final byte[] data;
    public final int statusCode;
    public final boolean zzan;
    public final long zzao;
    public final String zzcgw;
    public final String[] zzdft;
    public final String[] zzdfu;
    public final boolean zzdfv;

    zzaie(boolean z, String str, int i, byte[] bArr, String[] strArr, String[] strArr2, boolean z2, long j) {
        this.zzdfv = z;
        this.zzcgw = str;
        this.statusCode = i;
        this.data = bArr;
        this.zzdft = strArr;
        this.zzdfu = strArr2;
        this.zzan = z2;
        this.zzao = j;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzdfv);
        SafeParcelWriter.writeString(parcel, 2, this.zzcgw, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzdft, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzdfu, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzan);
        SafeParcelWriter.writeLong(parcel, 8, this.zzao);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
