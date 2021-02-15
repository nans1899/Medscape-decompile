package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjn implements Parcelable, Comparator<zza> {
    public static final Parcelable.Creator<zzjn> CREATOR = new zzjm();
    private int zzahv;
    private final zza[] zzaok;
    public final int zzaol;

    public zzjn(List<zza> list) {
        this(false, (zza[]) list.toArray(new zza[list.size()]));
    }

    public final int describeContents() {
        return 0;
    }

    /* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
    public static final class zza implements Parcelable {
        public static final Parcelable.Creator<zza> CREATOR = new zzjo();
        private final byte[] data;
        private final String mimeType;
        /* access modifiers changed from: private */
        public final UUID uuid;
        private int zzahv;
        public final boolean zzaom;

        public zza(UUID uuid2, String str, byte[] bArr) {
            this(uuid2, str, bArr, false);
        }

        public final int describeContents() {
            return 0;
        }

        private zza(UUID uuid2, String str, byte[] bArr, boolean z) {
            this.uuid = (UUID) zzoz.checkNotNull(uuid2);
            this.mimeType = (String) zzoz.checkNotNull(str);
            this.data = (byte[]) zzoz.checkNotNull(bArr);
            this.zzaom = false;
        }

        zza(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.zzaom = parcel.readByte() != 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            zza zza = (zza) obj;
            return this.mimeType.equals(zza.mimeType) && zzpq.zza(this.uuid, zza.uuid) && Arrays.equals(this.data, zza.data);
        }

        public final int hashCode() {
            if (this.zzahv == 0) {
                this.zzahv = (((this.uuid.hashCode() * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.zzahv;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte(this.zzaom ? (byte) 1 : 0);
        }
    }

    public zzjn(zza... zzaArr) {
        this(true, zzaArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: com.google.android.gms.internal.ads.zzjn$zza[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzjn(boolean r3, com.google.android.gms.internal.ads.zzjn.zza... r4) {
        /*
            r2 = this;
            r2.<init>()
            if (r3 == 0) goto L_0x000c
            java.lang.Object r3 = r4.clone()
            r4 = r3
            com.google.android.gms.internal.ads.zzjn$zza[] r4 = (com.google.android.gms.internal.ads.zzjn.zza[]) r4
        L_0x000c:
            java.util.Arrays.sort(r4, r2)
            r3 = 1
        L_0x0010:
            int r0 = r4.length
            if (r3 >= r0) goto L_0x0055
            int r0 = r3 + -1
            r0 = r4[r0]
            java.util.UUID r0 = r0.uuid
            r1 = r4[r3]
            java.util.UUID r1 = r1.uuid
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x002a
            int r3 = r3 + 1
            goto L_0x0010
        L_0x002a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r3 = r4[r3]
            java.util.UUID r3 = r3.uuid
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = java.lang.String.valueOf(r3)
            int r4 = r4.length()
            int r4 = r4 + 25
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            java.lang.String r4 = "Duplicate data for uuid: "
            r1.append(r4)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L_0x0055:
            r2.zzaok = r4
            int r3 = r4.length
            r2.zzaol = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjn.<init>(boolean, com.google.android.gms.internal.ads.zzjn$zza[]):void");
    }

    zzjn(Parcel parcel) {
        zza[] zzaArr = (zza[]) parcel.createTypedArray(zza.CREATOR);
        this.zzaok = zzaArr;
        this.zzaol = zzaArr.length;
    }

    public final zza zzag(int i) {
        return this.zzaok[i];
    }

    public final int hashCode() {
        if (this.zzahv == 0) {
            this.zzahv = Arrays.hashCode(this.zzaok);
        }
        return this.zzahv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzaok, ((zzjn) obj).zzaok);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.zzaok, 0);
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zza zza2 = (zza) obj;
        zza zza3 = (zza) obj2;
        if (zzha.UUID_NIL.equals(zza2.uuid)) {
            return zzha.UUID_NIL.equals(zza3.uuid) ? 0 : 1;
        }
        return zza2.uuid.compareTo(zza3.uuid);
    }
}
