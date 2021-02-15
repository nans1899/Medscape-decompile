package com.google.firebase.appindexing.internal;

import android.os.Parcelable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzx implements Parcelable.Creator<zzy> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzy[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r12) {
        /*
            r11 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r12)
            r1 = 0
            r2 = 0
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r4 = 0
        L_0x000d:
            int r1 = r12.dataPosition()
            if (r1 >= r0) goto L_0x004f
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r12)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x004a;
                case 2: goto L_0x0040;
                case 3: goto L_0x003b;
                case 4: goto L_0x001e;
                case 5: goto L_0x0036;
                case 6: goto L_0x002c;
                case 7: goto L_0x0027;
                case 8: goto L_0x0022;
                default: goto L_0x001e;
            }
        L_0x001e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r12, r1)
            goto L_0x000d
        L_0x0022:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x0027:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r12, r1)
            goto L_0x000d
        L_0x002c:
            android.os.Parcelable$Creator<com.google.firebase.appindexing.internal.zza> r2 = com.google.firebase.appindexing.internal.zza.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r12, r1, r2)
            r8 = r1
            com.google.firebase.appindexing.internal.zza r8 = (com.google.firebase.appindexing.internal.zza) r8
            goto L_0x000d
        L_0x0036:
            java.lang.String[] r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r12, r1)
            goto L_0x000d
        L_0x003b:
            java.lang.String[] r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringArray(r12, r1)
            goto L_0x000d
        L_0x0040:
            android.os.Parcelable$Creator<com.google.firebase.appindexing.internal.Thing> r2 = com.google.firebase.appindexing.internal.Thing.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r12, r1, r2)
            r5 = r1
            com.google.firebase.appindexing.internal.Thing[] r5 = (com.google.firebase.appindexing.internal.Thing[]) r5
            goto L_0x000d
        L_0x004a:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r12, r1)
            goto L_0x000d
        L_0x004f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r12, r0)
            com.google.firebase.appindexing.internal.zzy r12 = new com.google.firebase.appindexing.internal.zzy
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzx.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
