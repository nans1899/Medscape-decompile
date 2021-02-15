package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzsz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzsz> CREATOR = new zztc();
    private ParcelFileDescriptor zzbvd;

    public zzsz() {
        this((ParcelFileDescriptor) null);
    }

    public zzsz(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzbvd = parcelFileDescriptor;
    }

    public final synchronized boolean zzmw() {
        return this.zzbvd != null;
    }

    public final synchronized InputStream zzmx() {
        if (this.zzbvd == null) {
            return null;
        }
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zzbvd);
        this.zzbvd = null;
        return autoCloseInputStream;
    }

    private final synchronized ParcelFileDescriptor zzmy() {
        return this.zzbvd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, zzmy(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
