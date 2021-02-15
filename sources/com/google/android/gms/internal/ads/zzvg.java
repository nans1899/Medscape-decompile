package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvg> CREATOR = new zzvi();
    public final Bundle extras;
    public final int versionCode;
    public final int zzadm;
    public final int zzadn;
    public final String zzado;
    public final boolean zzbny;
    @Deprecated
    public final long zzchb;
    @Deprecated
    public final int zzchc;
    public final List<String> zzchd;
    public final boolean zzche;
    public final String zzchf;
    public final zzaag zzchg;
    public final String zzchh;
    public final Bundle zzchi;
    public final Bundle zzchj;
    public final List<String> zzchk;
    public final String zzchl;
    public final String zzchm;
    @Deprecated
    public final boolean zzchn;
    public final zzuy zzcho;
    public final List<String> zzchp;
    public final Location zznb;

    public zzvg(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, zzaag zzaag, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3, zzuy zzuy, int i4, String str5, List<String> list3) {
        this.versionCode = i;
        this.zzchb = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzchc = i2;
        this.zzchd = list;
        this.zzche = z;
        this.zzadm = i3;
        this.zzbny = z2;
        this.zzchf = str;
        this.zzchg = zzaag;
        this.zznb = location;
        this.zzchh = str2;
        this.zzchi = bundle2 == null ? new Bundle() : bundle2;
        this.zzchj = bundle3;
        this.zzchk = list2;
        this.zzchl = str3;
        this.zzchm = str4;
        this.zzchn = z3;
        this.zzcho = zzuy;
        this.zzadn = i4;
        this.zzado = str5;
        this.zzchp = list3 == null ? new ArrayList<>() : list3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzchb);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzchc);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzchd, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzche);
        SafeParcelWriter.writeInt(parcel, 7, this.zzadm);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbny);
        SafeParcelWriter.writeString(parcel, 9, this.zzchf, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzchg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zznb, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzchh, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzchi, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzchj, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzchk, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzchl, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzchm, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzchn);
        SafeParcelWriter.writeParcelable(parcel, 19, this.zzcho, i, false);
        SafeParcelWriter.writeInt(parcel, 20, this.zzadn);
        SafeParcelWriter.writeString(parcel, 21, this.zzado, false);
        SafeParcelWriter.writeStringList(parcel, 22, this.zzchp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzvg)) {
            return false;
        }
        zzvg zzvg = (zzvg) obj;
        if (this.versionCode != zzvg.versionCode || this.zzchb != zzvg.zzchb || !Objects.equal(this.extras, zzvg.extras) || this.zzchc != zzvg.zzchc || !Objects.equal(this.zzchd, zzvg.zzchd) || this.zzche != zzvg.zzche || this.zzadm != zzvg.zzadm || this.zzbny != zzvg.zzbny || !Objects.equal(this.zzchf, zzvg.zzchf) || !Objects.equal(this.zzchg, zzvg.zzchg) || !Objects.equal(this.zznb, zzvg.zznb) || !Objects.equal(this.zzchh, zzvg.zzchh) || !Objects.equal(this.zzchi, zzvg.zzchi) || !Objects.equal(this.zzchj, zzvg.zzchj) || !Objects.equal(this.zzchk, zzvg.zzchk) || !Objects.equal(this.zzchl, zzvg.zzchl) || !Objects.equal(this.zzchm, zzvg.zzchm) || this.zzchn != zzvg.zzchn || this.zzadn != zzvg.zzadn || !Objects.equal(this.zzado, zzvg.zzado) || !Objects.equal(this.zzchp, zzvg.zzchp)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzchb), this.extras, Integer.valueOf(this.zzchc), this.zzchd, Boolean.valueOf(this.zzche), Integer.valueOf(this.zzadm), Boolean.valueOf(this.zzbny), this.zzchf, this.zzchg, this.zznb, this.zzchh, this.zzchi, this.zzchj, this.zzchk, this.zzchl, this.zzchm, Boolean.valueOf(this.zzchn), Integer.valueOf(this.zzadn), this.zzado, this.zzchp);
    }
}
