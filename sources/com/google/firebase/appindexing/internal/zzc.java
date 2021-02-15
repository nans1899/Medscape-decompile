package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = new zzv();
    private int zzaq = 0;
    private final boolean zzel;
    private final boolean zzem;
    private final String zzes;
    private final String zzet;
    private final byte[] zzeu;

    zzc(int i, boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzaq = i;
        this.zzel = z;
        this.zzes = str;
        this.zzet = str2;
        this.zzeu = bArr;
        this.zzem = z2;
    }

    public zzc(boolean z, String str, String str2, byte[] bArr, boolean z2) {
        this.zzel = z;
        this.zzes = null;
        this.zzet = null;
        this.zzeu = null;
        this.zzem = false;
    }

    public final void zzf(int i) {
        this.zzaq = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzaq);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzel);
        SafeParcelWriter.writeString(parcel, 3, this.zzes, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzet, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.zzeu, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzem);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MetadataImpl { ");
        sb.append("{ eventStatus: '");
        sb.append(this.zzaq);
        sb.append("' } ");
        sb.append("{ uploadable: '");
        sb.append(this.zzel);
        sb.append("' } ");
        if (this.zzes != null) {
            sb.append("{ completionToken: '");
            sb.append(this.zzes);
            sb.append("' } ");
        }
        if (this.zzet != null) {
            sb.append("{ accountName: '");
            sb.append(this.zzet);
            sb.append("' } ");
        }
        if (this.zzeu != null) {
            sb.append("{ ssbContext: [ ");
            for (byte hexString : this.zzeu) {
                sb.append("0x");
                sb.append(Integer.toHexString(hexString));
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            sb.append("] } ");
        }
        sb.append("{ contextOnly: '");
        sb.append(this.zzem);
        sb.append("' } ");
        sb.append("}");
        return sb.toString();
    }
}
