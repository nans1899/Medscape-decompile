package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public interface zzej extends IInterface {
    List<zzkr> zza(zzn zzn, boolean z) throws RemoteException;

    List<zzw> zza(String str, String str2, zzn zzn) throws RemoteException;

    List<zzw> zza(String str, String str2, String str3) throws RemoteException;

    List<zzkr> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzkr> zza(String str, String str2, boolean z, zzn zzn) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(Bundle bundle, zzn zzn) throws RemoteException;

    void zza(zzar zzar, zzn zzn) throws RemoteException;

    void zza(zzar zzar, String str, String str2) throws RemoteException;

    void zza(zzkr zzkr, zzn zzn) throws RemoteException;

    void zza(zzn zzn) throws RemoteException;

    void zza(zzw zzw) throws RemoteException;

    void zza(zzw zzw, zzn zzn) throws RemoteException;

    byte[] zza(zzar zzar, String str) throws RemoteException;

    void zzb(zzn zzn) throws RemoteException;

    String zzc(zzn zzn) throws RemoteException;

    void zzd(zzn zzn) throws RemoteException;

    void zze(zzn zzn) throws RemoteException;
}
