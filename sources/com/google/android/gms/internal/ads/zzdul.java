package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.ads.zzcf;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzdul extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdul> CREATOR = new zzduo();
    private final int versionCode;
    private zzcf.zza zzhnr = null;
    private byte[] zzhns;

    zzdul(int i, byte[] bArr) {
        this.versionCode = i;
        this.zzhns = bArr;
        zzawo();
    }

    public final zzcf.zza zzawn() {
        if (!(this.zzhnr != null)) {
            try {
                this.zzhnr = zzcf.zza.zza(this.zzhns, zzejm.zzbfu());
                this.zzhns = null;
            } catch (zzekj e) {
                throw new IllegalStateException(e);
            }
        }
        zzawo();
        return this.zzhnr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        byte[] bArr = this.zzhns;
        if (bArr == null) {
            bArr = this.zzhnr.toByteArray();
        }
        SafeParcelWriter.writeByteArray(parcel, 2, bArr, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final void zzawo() {
        if (this.zzhnr == null && this.zzhns != null) {
            return;
        }
        if (this.zzhnr != null && this.zzhns == null) {
            return;
        }
        if (this.zzhnr != null && this.zzhns != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzhnr == null && this.zzhns == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }
}
