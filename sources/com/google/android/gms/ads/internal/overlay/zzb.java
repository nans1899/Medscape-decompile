package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR = new zzc();
    public final Intent intent;
    public final String mimeType;
    public final String packageName;
    public final String url;
    private final String zzdoi;
    public final String zzdoj;
    public final String zzdok;
    private final String zzdol;

    public zzb(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, (Intent) null);
    }

    public zzb(Intent intent2) {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, intent2);
    }

    public zzb(String str, String str2, String str3, String str4, String str5, String str6, String str7, Intent intent2) {
        this.zzdoi = str;
        this.url = str2;
        this.mimeType = str3;
        this.packageName = str4;
        this.zzdoj = str5;
        this.zzdok = str6;
        this.zzdol = str7;
        this.intent = intent2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdoi, false);
        SafeParcelWriter.writeString(parcel, 3, this.url, false);
        SafeParcelWriter.writeString(parcel, 4, this.mimeType, false);
        SafeParcelWriter.writeString(parcel, 5, this.packageName, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzdoj, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdok, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdol, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.intent, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
