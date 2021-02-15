package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzagt;
import com.google.android.gms.internal.ads.zzagv;
import com.google.android.gms.internal.ads.zzbbx;
import com.google.android.gms.internal.ads.zzbgj;
import com.google.android.gms.internal.ads.zzux;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new zzn();
    public final int orientation;
    public final String url;
    public final zzbbx zzbpe;
    public final zzux zzcgv;
    public final zzagt zzdep;
    public final zzagv zzdeq;
    public final zzbgj zzdgy;
    public final zzb zzdpl;
    public final zzp zzdpm;
    public final String zzdpn;
    public final boolean zzdpo;
    public final String zzdpp;
    public final zzv zzdpq;
    public final int zzdpr;
    public final String zzdps;
    public final zzi zzdpt;

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzd(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception unused) {
            return null;
        }
    }

    public AdOverlayInfoParcel(zzux zzux, zzp zzp, zzv zzv, zzbgj zzbgj, int i, zzbbx zzbbx, String str, zzi zzi, String str2, String str3) {
        this.zzdpl = null;
        this.zzcgv = null;
        this.zzdpm = zzp;
        this.zzdgy = zzbgj;
        this.zzdep = null;
        this.zzdeq = null;
        this.zzdpn = str2;
        this.zzdpo = false;
        this.zzdpp = str3;
        this.zzdpq = null;
        this.orientation = i;
        this.zzdpr = 1;
        this.url = null;
        this.zzbpe = zzbbx;
        this.zzdps = str;
        this.zzdpt = zzi;
    }

    public AdOverlayInfoParcel(zzux zzux, zzp zzp, zzv zzv, zzbgj zzbgj, boolean z, int i, zzbbx zzbbx) {
        this.zzdpl = null;
        this.zzcgv = zzux;
        this.zzdpm = zzp;
        this.zzdgy = zzbgj;
        this.zzdep = null;
        this.zzdeq = null;
        this.zzdpn = null;
        this.zzdpo = z;
        this.zzdpp = null;
        this.zzdpq = zzv;
        this.orientation = i;
        this.zzdpr = 2;
        this.url = null;
        this.zzbpe = zzbbx;
        this.zzdps = null;
        this.zzdpt = null;
    }

    public AdOverlayInfoParcel(zzux zzux, zzp zzp, zzagt zzagt, zzagv zzagv, zzv zzv, zzbgj zzbgj, boolean z, int i, String str, zzbbx zzbbx) {
        this.zzdpl = null;
        this.zzcgv = zzux;
        this.zzdpm = zzp;
        this.zzdgy = zzbgj;
        this.zzdep = zzagt;
        this.zzdeq = zzagv;
        this.zzdpn = null;
        this.zzdpo = z;
        this.zzdpp = null;
        this.zzdpq = zzv;
        this.orientation = i;
        this.zzdpr = 3;
        this.url = str;
        this.zzbpe = zzbbx;
        this.zzdps = null;
        this.zzdpt = null;
    }

    public AdOverlayInfoParcel(zzux zzux, zzp zzp, zzagt zzagt, zzagv zzagv, zzv zzv, zzbgj zzbgj, boolean z, int i, String str, String str2, zzbbx zzbbx) {
        this.zzdpl = null;
        this.zzcgv = zzux;
        this.zzdpm = zzp;
        this.zzdgy = zzbgj;
        this.zzdep = zzagt;
        this.zzdeq = zzagv;
        this.zzdpn = str2;
        this.zzdpo = z;
        this.zzdpp = str;
        this.zzdpq = zzv;
        this.orientation = i;
        this.zzdpr = 3;
        this.url = null;
        this.zzbpe = zzbbx;
        this.zzdps = null;
        this.zzdpt = null;
    }

    public AdOverlayInfoParcel(zzb zzb, zzux zzux, zzp zzp, zzv zzv, zzbbx zzbbx) {
        this.zzdpl = zzb;
        this.zzcgv = zzux;
        this.zzdpm = zzp;
        this.zzdgy = null;
        this.zzdep = null;
        this.zzdeq = null;
        this.zzdpn = null;
        this.zzdpo = false;
        this.zzdpp = null;
        this.zzdpq = zzv;
        this.orientation = -1;
        this.zzdpr = 4;
        this.url = null;
        this.zzbpe = zzbbx;
        this.zzdps = null;
        this.zzdpt = null;
    }

    AdOverlayInfoParcel(zzb zzb, IBinder iBinder, IBinder iBinder2, IBinder iBinder3, IBinder iBinder4, String str, boolean z, String str2, IBinder iBinder5, int i, int i2, String str3, zzbbx zzbbx, String str4, zzi zzi, IBinder iBinder6) {
        this.zzdpl = zzb;
        this.zzcgv = (zzux) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzdpm = (zzp) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder2));
        this.zzdgy = (zzbgj) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder3));
        this.zzdep = (zzagt) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder6));
        this.zzdeq = (zzagv) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder4));
        this.zzdpn = str;
        this.zzdpo = z;
        this.zzdpp = str2;
        this.zzdpq = (zzv) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder5));
        this.orientation = i;
        this.zzdpr = i2;
        this.url = str3;
        this.zzbpe = zzbbx;
        this.zzdps = str4;
        this.zzdpt = zzi;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdpl, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, ObjectWrapper.wrap(this.zzcgv).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzdpm).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 5, ObjectWrapper.wrap(this.zzdgy).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, ObjectWrapper.wrap(this.zzdeq).asBinder(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdpn, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdpo);
        SafeParcelWriter.writeString(parcel, 9, this.zzdpp, false);
        SafeParcelWriter.writeIBinder(parcel, 10, ObjectWrapper.wrap(this.zzdpq).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel, 12, this.zzdpr);
        SafeParcelWriter.writeString(parcel, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzbpe, i, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzdps, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.zzdpt, i, false);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap(this.zzdep).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
