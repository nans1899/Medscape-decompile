package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzua;
import java.util.ArrayList;
import net.media.android.bidder.base.models.internal.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsf {
    private static final SparseArray<zzua.zzo.zzb> zzgmq;
    private final zzbtm zzfsr;
    private final zzcsc zzgjt;
    private final TelephonyManager zzgmn;
    /* access modifiers changed from: private */
    public final zzcrw zzgmo;
    private zzuk zzgmp;
    private final Context zzvr;

    zzcsf(Context context, zzbtm zzbtm, zzcsc zzcsc, zzcrw zzcrw) {
        this.zzvr = context;
        this.zzfsr = zzbtm;
        this.zzgjt = zzcsc;
        this.zzgmo = zzcrw;
        this.zzgmn = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
    }

    private static zzuk zzbl(boolean z) {
        return z ? zzuk.ENUM_TRUE : zzuk.ENUM_FALSE;
    }

    /* access modifiers changed from: private */
    public final zzua.zzm zzk(Bundle bundle) {
        zzua.zzm.zzb zzb;
        zzua.zzm.zza zzog = zzua.zzm.zzog();
        int i = bundle.getInt("cnt", -2);
        int i2 = bundle.getInt("gnt", 0);
        if (i == -1) {
            this.zzgmp = zzuk.ENUM_TRUE;
        } else {
            this.zzgmp = zzuk.ENUM_FALSE;
            if (i == 0) {
                zzog.zza(zzua.zzm.zzc.CELL);
            } else if (i != 1) {
                zzog.zza(zzua.zzm.zzc.NETWORKTYPE_UNSPECIFIED);
            } else {
                zzog.zza(zzua.zzm.zzc.WIFI);
            }
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
                    zzb = zzua.zzm.zzb.TWO_G;
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                case 17:
                    zzb = zzua.zzm.zzb.THREE_G;
                    break;
                case 13:
                    zzb = zzua.zzm.zzb.LTE;
                    break;
                default:
                    zzb = zzua.zzm.zzb.CELLULAR_NETWORK_TYPE_UNSPECIFIED;
                    break;
            }
            zzog.zza(zzb);
        }
        return (zzua.zzm) ((zzejz) zzog.zzbgt());
    }

    /* access modifiers changed from: private */
    public static zzua.zzo.zzb zzl(Bundle bundle) {
        return zzgmq.get(zzdot.zza(zzdot.zza(bundle, "device"), Logger.NETWORK).getInt("active_network_state", -1), zzua.zzo.zzb.UNSPECIFIED);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.google.android.gms.internal.ads.zzua.zzc.zza> zzm(android.os.Bundle r7) {
        /*
            java.lang.String r0 = "ad_types"
            java.lang.Object r7 = r7.get(r0)
            boolean r0 = r7 instanceof java.util.List
            if (r0 == 0) goto L_0x000d
            java.util.List r7 = (java.util.List) r7
            goto L_0x0017
        L_0x000d:
            boolean r0 = r7 instanceof java.lang.String[]
            if (r0 == 0) goto L_0x003d
            java.lang.String[] r7 = (java.lang.String[]) r7
            java.util.List r7 = java.util.Arrays.asList(r7)
        L_0x0017:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r7.size()
            r0.<init>(r1)
            java.util.Iterator r7 = r7.iterator()
        L_0x0024:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0038
            java.lang.Object r1 = r7.next()
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0024
            java.lang.String r1 = (java.lang.String) r1
            r0.add(r1)
            goto L_0x0024
        L_0x0038:
            java.util.List r7 = java.util.Collections.unmodifiableList(r0)
            goto L_0x0041
        L_0x003d:
            java.util.List r7 = java.util.Collections.emptyList()
        L_0x0041:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x004a:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x00a3
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            r2 = -1
            int r3 = r1.hashCode()
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r3) {
                case -1396342996: goto L_0x0080;
                case -1052618729: goto L_0x0076;
                case -239580146: goto L_0x006c;
                case 604727084: goto L_0x0062;
                default: goto L_0x0061;
            }
        L_0x0061:
            goto L_0x0089
        L_0x0062:
            java.lang.String r3 = "interstitial"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
            r2 = 1
            goto L_0x0089
        L_0x006c:
            java.lang.String r3 = "rewarded"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
            r2 = 3
            goto L_0x0089
        L_0x0076:
            java.lang.String r3 = "native"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
            r2 = 2
            goto L_0x0089
        L_0x0080:
            java.lang.String r3 = "banner"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0089
            r2 = 0
        L_0x0089:
            if (r2 == 0) goto L_0x009d
            if (r2 == r6) goto L_0x009a
            if (r2 == r5) goto L_0x0097
            if (r2 == r4) goto L_0x0094
            com.google.android.gms.internal.ads.zzua$zzc$zza r1 = com.google.android.gms.internal.ads.zzua.zzc.zza.AD_FORMAT_TYPE_UNSPECIFIED
            goto L_0x009f
        L_0x0094:
            com.google.android.gms.internal.ads.zzua$zzc$zza r1 = com.google.android.gms.internal.ads.zzua.zzc.zza.REWARD_BASED_VIDEO_AD
            goto L_0x009f
        L_0x0097:
            com.google.android.gms.internal.ads.zzua$zzc$zza r1 = com.google.android.gms.internal.ads.zzua.zzc.zza.NATIVE_APP_INSTALL
            goto L_0x009f
        L_0x009a:
            com.google.android.gms.internal.ads.zzua$zzc$zza r1 = com.google.android.gms.internal.ads.zzua.zzc.zza.INTERSTITIAL
            goto L_0x009f
        L_0x009d:
            com.google.android.gms.internal.ads.zzua$zzc$zza r1 = com.google.android.gms.internal.ads.zzua.zzc.zza.BANNER
        L_0x009f:
            r0.add(r1)
            goto L_0x004a
        L_0x00a3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcsf.zzm(android.os.Bundle):java.util.ArrayList");
    }

    /* access modifiers changed from: private */
    public final byte[] zza(boolean z, ArrayList<zzua.zzc.zza> arrayList, zzua.zzm zzm, zzua.zzo.zzb zzb) {
        boolean z2 = true;
        zzua.zzo.zza.C0024zza zzes = zzua.zzo.zza.zzok().zze(arrayList).zzh(zzbl(zzp.zzkt().zzb(this.zzvr.getContentResolver()) != 0)).zzi(zzp.zzkt().zza(this.zzvr, this.zzgmn)).zzet(this.zzgjt.zzaqd()).zzeu(this.zzgjt.zzaqf()).zzcm(this.zzgjt.getResponseCode()).zzb(zzb).zzb(zzm).zzj(this.zzgmp).zzf(zzbl(z)).zzes(zzp.zzky().currentTimeMillis());
        if (zzp.zzkt().zza(this.zzvr.getContentResolver()) == 0) {
            z2 = false;
        }
        return ((zzua.zzo.zza) ((zzejz) zzes.zzg(zzbl(z2)).zzbgt())).toByteArray();
    }

    public final void zzbk(boolean z) {
        zzdyq.zza(this.zzfsr.zzajo(), new zzcsi(this, z), zzbbz.zzeeu);
    }

    static {
        SparseArray<zzua.zzo.zzb> sparseArray = new SparseArray<>();
        zzgmq = sparseArray;
        sparseArray.put(NetworkInfo.DetailedState.CONNECTED.ordinal(), zzua.zzo.zzb.CONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.AUTHENTICATING.ordinal(), zzua.zzo.zzb.CONNECTING);
        zzgmq.put(NetworkInfo.DetailedState.CONNECTING.ordinal(), zzua.zzo.zzb.CONNECTING);
        zzgmq.put(NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal(), zzua.zzo.zzb.CONNECTING);
        zzgmq.put(NetworkInfo.DetailedState.DISCONNECTING.ordinal(), zzua.zzo.zzb.DISCONNECTING);
        zzgmq.put(NetworkInfo.DetailedState.BLOCKED.ordinal(), zzua.zzo.zzb.DISCONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.DISCONNECTED.ordinal(), zzua.zzo.zzb.DISCONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.FAILED.ordinal(), zzua.zzo.zzb.DISCONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.IDLE.ordinal(), zzua.zzo.zzb.DISCONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.SCANNING.ordinal(), zzua.zzo.zzb.DISCONNECTED);
        zzgmq.put(NetworkInfo.DetailedState.SUSPENDED.ordinal(), zzua.zzo.zzb.SUSPENDED);
        if (Build.VERSION.SDK_INT >= 17) {
            zzgmq.put(NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal(), zzua.zzo.zzb.CONNECTING);
        }
        zzgmq.put(NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal(), zzua.zzo.zzb.CONNECTING);
    }
}
