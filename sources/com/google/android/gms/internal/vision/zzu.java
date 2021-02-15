package com.google.android.gms.internal.vision;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzu> CREATOR = new zzt();
    public int height;
    public int id;
    public int rotation;
    public int width;
    public long zzaz;

    public zzu() {
    }

    public zzu(int i, int i2, int i3, long j, int i4) {
        this.width = i;
        this.height = i2;
        this.id = i3;
        this.zzaz = j;
        this.rotation = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.width);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.id);
        SafeParcelWriter.writeLong(parcel, 5, this.zzaz);
        SafeParcelWriter.writeInt(parcel, 6, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static zzu zzd(Frame frame) {
        zzu zzu = new zzu();
        zzu.width = frame.getMetadata().getWidth();
        zzu.height = frame.getMetadata().getHeight();
        zzu.rotation = frame.getMetadata().getRotation();
        zzu.id = frame.getMetadata().getId();
        zzu.zzaz = frame.getMetadata().getTimestampMillis();
        return zzu;
    }
}
