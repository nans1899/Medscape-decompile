package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzz();
    public String zza;
    public String zzb;
    public zzkr zzc;
    public long zzd;
    public boolean zze;
    public String zzf;
    public zzar zzg;
    public long zzh;
    public zzar zzi;
    public long zzj;
    public zzar zzk;

    zzw(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        this.zza = zzw.zza;
        this.zzb = zzw.zzb;
        this.zzc = zzw.zzc;
        this.zzd = zzw.zzd;
        this.zze = zzw.zze;
        this.zzf = zzw.zzf;
        this.zzg = zzw.zzg;
        this.zzh = zzw.zzh;
        this.zzi = zzw.zzi;
        this.zzj = zzw.zzj;
        this.zzk = zzw.zzk;
    }

    zzw(String str, String str2, zzkr zzkr, long j, boolean z, String str3, zzar zzar, long j2, zzar zzar2, long j3, zzar zzar3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzkr;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzar;
        this.zzh = j2;
        this.zzi = zzar2;
        this.zzj = j3;
        this.zzk = zzar3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
