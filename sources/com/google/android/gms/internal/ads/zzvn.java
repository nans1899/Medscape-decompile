package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzvn> CREATOR = new zzvm();
    public final int height;
    public final int heightPixels;
    public final int width;
    public final int widthPixels;
    public final String zzacy;
    public final boolean zzbrg;
    public final boolean zzcht;
    public final zzvn[] zzchu;
    public final boolean zzchv;
    public boolean zzchw;
    public boolean zzchx;
    private boolean zzchy;
    public boolean zzchz;
    public boolean zzcia;

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (((float) zzd(displayMetrics)) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zzvn zzpk() {
        return new zzvn("320x50_mb", 0, 0, false, 0, 0, (zzvn[]) null, true, false, false, false, false, false, false);
    }

    public static zzvn zzpl() {
        return new zzvn("reward_mb", 0, 0, true, 0, 0, (zzvn[]) null, false, false, false, false, false, false, false);
    }

    public static zzvn zzpm() {
        return new zzvn("interstitial_mb", 0, 0, false, 0, 0, (zzvn[]) null, false, false, false, false, true, false, false);
    }

    public zzvn() {
        this("interstitial_mb", 0, 0, true, 0, 0, (zzvn[]) null, false, false, false, false, false, false, false);
    }

    public static zzvn zzpn() {
        return new zzvn("invalid", 0, 0, false, 0, 0, (zzvn[]) null, false, false, false, true, false, false, false);
    }

    public zzvn(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzvn(android.content.Context r13, com.google.android.gms.ads.AdSize[] r14) {
        /*
            r12 = this;
            r12.<init>()
            r0 = 0
            r1 = r14[r0]
            r12.zzcht = r0
            boolean r2 = r1.isFluid()
            r12.zzchv = r2
            boolean r2 = com.google.android.gms.ads.zzb.zza(r1)
            r12.zzchz = r2
            boolean r2 = com.google.android.gms.ads.zzb.zzb(r1)
            r12.zzcia = r2
            boolean r3 = r12.zzchv
            if (r3 == 0) goto L_0x002f
            com.google.android.gms.ads.AdSize r2 = com.google.android.gms.ads.AdSize.BANNER
            int r2 = r2.getWidth()
            r12.width = r2
            com.google.android.gms.ads.AdSize r2 = com.google.android.gms.ads.AdSize.BANNER
            int r2 = r2.getHeight()
            r12.height = r2
            goto L_0x004a
        L_0x002f:
            if (r2 == 0) goto L_0x003e
            int r2 = r1.getWidth()
            r12.width = r2
            int r2 = com.google.android.gms.ads.zzb.zzc(r1)
            r12.height = r2
            goto L_0x004a
        L_0x003e:
            int r2 = r1.getWidth()
            r12.width = r2
            int r2 = r1.getHeight()
            r12.height = r2
        L_0x004a:
            int r2 = r12.width
            r3 = -1
            r4 = 1
            if (r2 != r3) goto L_0x0052
            r2 = 1
            goto L_0x0053
        L_0x0052:
            r2 = 0
        L_0x0053:
            int r3 = r12.height
            r5 = -2
            if (r3 != r5) goto L_0x005a
            r3 = 1
            goto L_0x005b
        L_0x005a:
            r3 = 0
        L_0x005b:
            android.content.res.Resources r5 = r13.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            if (r2 == 0) goto L_0x009e
            com.google.android.gms.internal.ads.zzwm.zzpt()
            boolean r6 = com.google.android.gms.internal.ads.zzbbg.zzbq(r13)
            if (r6 == 0) goto L_0x0084
            com.google.android.gms.internal.ads.zzwm.zzpt()
            boolean r6 = com.google.android.gms.internal.ads.zzbbg.zzbr(r13)
            if (r6 == 0) goto L_0x0084
            int r6 = r5.widthPixels
            com.google.android.gms.internal.ads.zzwm.zzpt()
            int r7 = com.google.android.gms.internal.ads.zzbbg.zzbs(r13)
            int r6 = r6 - r7
            r12.widthPixels = r6
            goto L_0x0088
        L_0x0084:
            int r6 = r5.widthPixels
            r12.widthPixels = r6
        L_0x0088:
            int r6 = r12.widthPixels
            float r6 = (float) r6
            float r7 = r5.density
            float r6 = r6 / r7
            double r6 = (double) r6
            int r8 = (int) r6
            double r9 = (double) r8
            double r6 = r6 - r9
            r9 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r11 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r11 < 0) goto L_0x00ab
            int r8 = r8 + 1
            goto L_0x00ab
        L_0x009e:
            int r8 = r12.width
            com.google.android.gms.internal.ads.zzwm.zzpt()
            int r6 = r12.width
            int r6 = com.google.android.gms.internal.ads.zzbbg.zza((android.util.DisplayMetrics) r5, (int) r6)
            r12.widthPixels = r6
        L_0x00ab:
            if (r3 == 0) goto L_0x00b2
            int r6 = zzd(r5)
            goto L_0x00b4
        L_0x00b2:
            int r6 = r12.height
        L_0x00b4:
            com.google.android.gms.internal.ads.zzwm.zzpt()
            int r5 = com.google.android.gms.internal.ads.zzbbg.zza((android.util.DisplayMetrics) r5, (int) r6)
            r12.heightPixels = r5
            java.lang.String r5 = "_as"
            java.lang.String r7 = "x"
            r9 = 26
            if (r2 != 0) goto L_0x00f8
            if (r3 == 0) goto L_0x00c8
            goto L_0x00f8
        L_0x00c8:
            boolean r2 = r12.zzcia
            if (r2 == 0) goto L_0x00e8
            int r1 = r12.width
            int r2 = r12.height
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r9)
            r3.append(r1)
            r3.append(r7)
            r3.append(r2)
            r3.append(r5)
            java.lang.String r1 = r3.toString()
            r12.zzacy = r1
            goto L_0x010f
        L_0x00e8:
            boolean r2 = r12.zzchv
            if (r2 == 0) goto L_0x00f1
            java.lang.String r1 = "320x50_mb"
            r12.zzacy = r1
            goto L_0x010f
        L_0x00f1:
            java.lang.String r1 = r1.toString()
            r12.zzacy = r1
            goto L_0x010f
        L_0x00f8:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r9)
            r1.append(r8)
            r1.append(r7)
            r1.append(r6)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r12.zzacy = r1
        L_0x010f:
            int r1 = r14.length
            if (r1 <= r4) goto L_0x0129
            int r1 = r14.length
            com.google.android.gms.internal.ads.zzvn[] r1 = new com.google.android.gms.internal.ads.zzvn[r1]
            r12.zzchu = r1
            r1 = 0
        L_0x0118:
            int r2 = r14.length
            if (r1 >= r2) goto L_0x012c
            com.google.android.gms.internal.ads.zzvn[] r2 = r12.zzchu
            com.google.android.gms.internal.ads.zzvn r3 = new com.google.android.gms.internal.ads.zzvn
            r4 = r14[r1]
            r3.<init>((android.content.Context) r13, (com.google.android.gms.ads.AdSize) r4)
            r2[r1] = r3
            int r1 = r1 + 1
            goto L_0x0118
        L_0x0129:
            r13 = 0
            r12.zzchu = r13
        L_0x012c:
            r12.zzbrg = r0
            r12.zzchw = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzvn.<init>(android.content.Context, com.google.android.gms.ads.AdSize[]):void");
    }

    zzvn(String str, int i, int i2, boolean z, int i3, int i4, zzvn[] zzvnArr, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        this.zzacy = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzcht = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzchu = zzvnArr;
        this.zzbrg = z2;
        this.zzchv = z3;
        this.zzchw = z4;
        this.zzchx = z5;
        this.zzchy = z6;
        this.zzchz = z7;
        this.zzcia = z8;
    }

    public final AdSize zzpo() {
        return zzb.zza(this.width, this.height, this.zzacy);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzacy, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcht);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzchu, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzbrg);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzchv);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzchw);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzchx);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzchy);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzchz);
        SafeParcelWriter.writeBoolean(parcel, 15, this.zzcia);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
