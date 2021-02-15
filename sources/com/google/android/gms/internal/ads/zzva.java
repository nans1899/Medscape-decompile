package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzva extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzva> CREATOR = new zzvd();
    public final int errorCode;
    public final String zzcgw;
    public final String zzcgx;
    public zzva zzcgy;
    public IBinder zzcgz;

    public zzva(int i, String str, String str2, zzva zzva, IBinder iBinder) {
        this.errorCode = i;
        this.zzcgw = str;
        this.zzcgx = str2;
        this.zzcgy = zzva;
        this.zzcgz = iBinder;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.errorCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzcgw, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzcgx, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzcgy, i, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.zzcgz, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final AdError zzph() {
        zzva zzva = this.zzcgy;
        return new AdError(this.errorCode, this.zzcgw, this.zzcgx, zzva == null ? null : new AdError(zzva.errorCode, zzva.zzcgw, zzva.zzcgx));
    }

    public final LoadAdError zzpi() {
        zzva zzva = this.zzcgy;
        zzyn zzyn = null;
        AdError adError = zzva == null ? null : new AdError(zzva.errorCode, zzva.zzcgw, zzva.zzcgx);
        int i = this.errorCode;
        String str = this.zzcgw;
        String str2 = this.zzcgx;
        IBinder iBinder = this.zzcgz;
        if (iBinder != null) {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
            if (queryLocalInterface instanceof zzyn) {
                zzyn = (zzyn) queryLocalInterface;
            } else {
                zzyn = new zzyp(iBinder);
            }
        }
        return new LoadAdError(i, str, str2, adError, ResponseInfo.zza(zzyn));
    }
}
