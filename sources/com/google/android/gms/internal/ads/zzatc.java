package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzatc> CREATOR = new zzatf();
    public final ApplicationInfo applicationInfo;
    public final String packageName;
    public final zzbbx zzdqm;
    public final PackageInfo zzdqs;
    public final List<String> zzdrc;
    public final String zzdrm;
    public final Bundle zzdty;
    public final boolean zzdtz;
    public final String zzdua;
    public zzdqg zzdub;
    public String zzduc;

    public zzatc(Bundle bundle, zzbbx zzbbx, ApplicationInfo applicationInfo2, String str, List<String> list, PackageInfo packageInfo, String str2, boolean z, String str3, zzdqg zzdqg, String str4) {
        this.zzdty = bundle;
        this.zzdqm = zzbbx;
        this.packageName = str;
        this.applicationInfo = applicationInfo2;
        this.zzdrc = list;
        this.zzdqs = packageInfo;
        this.zzdrm = str2;
        this.zzdtz = z;
        this.zzdua = str3;
        this.zzdub = zzdqg;
        this.zzduc = str4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzdty, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdqm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.applicationInfo, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzdrc, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdqs, i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdrm, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdtz);
        SafeParcelWriter.writeString(parcel, 9, this.zzdua, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzdub, i, false);
        SafeParcelWriter.writeString(parcel, 11, this.zzduc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
