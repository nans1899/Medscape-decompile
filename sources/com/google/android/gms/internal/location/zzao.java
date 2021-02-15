package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzbe;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzao extends zzb implements zzal {
    zzao(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
    }

    public final void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) geofencingRequest);
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzd.zza(b_, (IInterface) zzaj);
        zzb(57, b_);
    }

    public final void zza(PendingIntent pendingIntent, zzaj zzaj, String str) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzd.zza(b_, (IInterface) zzaj);
        b_.writeString(str);
        zzb(2, b_);
    }

    public final void zza(String[] strArr, zzaj zzaj, String str) throws RemoteException {
        Parcel b_ = b_();
        b_.writeStringArray(strArr);
        zzd.zza(b_, (IInterface) zzaj);
        b_.writeString(str);
        zzb(3, b_);
    }

    public final void zza(zzbe zzbe, zzaj zzaj) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) zzbe);
        zzd.zza(b_, (IInterface) zzaj);
        zzb(74, b_);
    }

    public final void zza(long j, boolean z, PendingIntent pendingIntent) throws RemoteException {
        Parcel b_ = b_();
        b_.writeLong(j);
        zzd.zza(b_, true);
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzb(5, b_);
    }

    public final void zza(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) activityTransitionRequest);
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzd.zza(b_, (IInterface) iStatusCallback);
        zzb(72, b_);
    }

    public final void zza(PendingIntent pendingIntent, IStatusCallback iStatusCallback) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzd.zza(b_, (IInterface) iStatusCallback);
        zzb(73, b_);
    }

    public final void zza(PendingIntent pendingIntent) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) pendingIntent);
        zzb(6, b_);
    }

    public final Location zza() throws RemoteException {
        Parcel zza = zza(7, b_());
        Location location = (Location) zzd.zza(zza, Location.CREATOR);
        zza.recycle();
        return location;
    }

    public final Location zza(String str) throws RemoteException {
        Parcel b_ = b_();
        b_.writeString(str);
        Parcel zza = zza(80, b_);
        Location location = (Location) zzd.zza(zza, Location.CREATOR);
        zza.recycle();
        return location;
    }

    public final void zza(zzbe zzbe) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) zzbe);
        zzb(59, b_);
    }

    public final void zza(boolean z) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, z);
        zzb(12, b_);
    }

    public final void zza(Location location) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) location);
        zzb(13, b_);
    }

    public final void zza(zzai zzai) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (IInterface) zzai);
        zzb(67, b_);
    }

    public final LocationAvailability zzb(String str) throws RemoteException {
        Parcel b_ = b_();
        b_.writeString(str);
        Parcel zza = zza(34, b_);
        LocationAvailability locationAvailability = (LocationAvailability) zzd.zza(zza, LocationAvailability.CREATOR);
        zza.recycle();
        return locationAvailability;
    }

    public final void zza(LocationSettingsRequest locationSettingsRequest, zzan zzan, String str) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) locationSettingsRequest);
        zzd.zza(b_, (IInterface) zzan);
        b_.writeString(str);
        zzb(63, b_);
    }

    public final void zza(zzl zzl) throws RemoteException {
        Parcel b_ = b_();
        zzd.zza(b_, (Parcelable) zzl);
        zzb(75, b_);
    }
}
