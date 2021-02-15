package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdqg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdqg> CREATOR = new zzdqh();
    private final zzdqf[] zzhht;
    private final int[] zzhhu;
    private final int[] zzhhv;
    private final int zzhhw;
    public final zzdqf zzhhx;
    public final int zzhhy;
    public final int zzhhz;
    public final int zzhia;
    public final String zzhib;
    private final int zzhic;
    public final int zzhid;
    private final int zzhie;
    private final int zzhif;
    @Nullable
    public final Context zzvr;

    private zzdqg(@Nullable Context context, zzdqf zzdqf, int i, int i2, int i3, String str, String str2, String str3) {
        int i4;
        this.zzhht = zzdqf.values();
        this.zzhhu = zzdqi.zzauo();
        this.zzhhv = zzdqi.zzaup();
        this.zzvr = context;
        this.zzhhw = zzdqf.ordinal();
        this.zzhhx = zzdqf;
        this.zzhhy = i;
        this.zzhhz = i2;
        this.zzhia = i3;
        this.zzhib = str;
        if ("oldest".equals(str2)) {
            i4 = zzdqi.zzhig;
        } else if ("lru".equals(str2) || !"lfu".equals(str2)) {
            i4 = zzdqi.zzhih;
        } else {
            i4 = zzdqi.zzhii;
        }
        this.zzhid = i4;
        this.zzhic = i4 - 1;
        "onAdClosed".equals(str3);
        int i5 = zzdqi.zzhik;
        this.zzhif = i5;
        this.zzhie = i5 - 1;
    }

    public zzdqg(int i, int i2, int i3, int i4, String str, int i5, int i6) {
        this.zzhht = zzdqf.values();
        this.zzhhu = zzdqi.zzauo();
        int[] zzaup = zzdqi.zzaup();
        this.zzhhv = zzaup;
        this.zzvr = null;
        this.zzhhw = i;
        this.zzhhx = this.zzhht[i];
        this.zzhhy = i2;
        this.zzhhz = i3;
        this.zzhia = i4;
        this.zzhib = str;
        this.zzhic = i5;
        this.zzhid = this.zzhhu[i5];
        this.zzhie = i6;
        this.zzhif = zzaup[i6];
    }

    public static zzdqg zza(zzdqf zzdqf, Context context) {
        if (zzdqf == zzdqf.Rewarded) {
            return new zzdqg(context, zzdqf, ((Integer) zzwm.zzpx().zzd(zzabb.zzcwr)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcwx)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcwz)).intValue(), (String) zzwm.zzpx().zzd(zzabb.zzcxb), (String) zzwm.zzpx().zzd(zzabb.zzcwt), (String) zzwm.zzpx().zzd(zzabb.zzcwv));
        } else if (zzdqf == zzdqf.Interstitial) {
            return new zzdqg(context, zzdqf, ((Integer) zzwm.zzpx().zzd(zzabb.zzcws)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcwy)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcxa)).intValue(), (String) zzwm.zzpx().zzd(zzabb.zzcxc), (String) zzwm.zzpx().zzd(zzabb.zzcwu), (String) zzwm.zzpx().zzd(zzabb.zzcww));
        } else if (zzdqf != zzdqf.AppOpen) {
            return null;
        } else {
            return new zzdqg(context, zzdqf, ((Integer) zzwm.zzpx().zzd(zzabb.zzcxf)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcxh)).intValue(), ((Integer) zzwm.zzpx().zzd(zzabb.zzcxi)).intValue(), (String) zzwm.zzpx().zzd(zzabb.zzcxd), (String) zzwm.zzpx().zzd(zzabb.zzcxe), (String) zzwm.zzpx().zzd(zzabb.zzcxg));
        }
    }

    public static boolean zzaun() {
        return ((Boolean) zzwm.zzpx().zzd(zzabb.zzcwq)).booleanValue();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzhhw);
        SafeParcelWriter.writeInt(parcel, 2, this.zzhhy);
        SafeParcelWriter.writeInt(parcel, 3, this.zzhhz);
        SafeParcelWriter.writeInt(parcel, 4, this.zzhia);
        SafeParcelWriter.writeString(parcel, 5, this.zzhib, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzhic);
        SafeParcelWriter.writeInt(parcel, 7, this.zzhie);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
