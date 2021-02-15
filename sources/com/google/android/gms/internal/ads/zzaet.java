package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaet extends NativeAppInstallAd {
    private final VideoController zzcjt = new VideoController();
    private final zzaes zzdds;
    private final List<NativeAd.Image> zzddt = new ArrayList();
    private final zzaef zzddu;
    private final NativeAd.AdChoicesInfo zzddv;

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074 A[Catch:{ RemoteException -> 0x0081 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzaet(com.google.android.gms.internal.ads.zzaes r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzddt = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r5.zzcjt = r1
            r5.zzdds = r6
            r1 = 0
            java.util.List r6 = r6.getImages()     // Catch:{ RemoteException -> 0x0053 }
            if (r6 == 0) goto L_0x0057
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x0053 }
        L_0x0020:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x0053 }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0053 }
            if (r3 == 0) goto L_0x0045
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0045
            java.lang.String r3 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)     // Catch:{ RemoteException -> 0x0053 }
            boolean r4 = r3 instanceof com.google.android.gms.internal.ads.zzaee     // Catch:{ RemoteException -> 0x0053 }
            if (r4 == 0) goto L_0x003f
            com.google.android.gms.internal.ads.zzaee r3 = (com.google.android.gms.internal.ads.zzaee) r3     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x003f:
            com.google.android.gms.internal.ads.zzaeg r3 = new com.google.android.gms.internal.ads.zzaeg     // Catch:{ RemoteException -> 0x0053 }
            r3.<init>(r2)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x0045:
            r3 = r1
        L_0x0046:
            if (r3 == 0) goto L_0x0020
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r5.zzddt     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.internal.ads.zzaef r4 = new com.google.android.gms.internal.ads.zzaef     // Catch:{ RemoteException -> 0x0053 }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x0053 }
            r2.add(r4)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0020
        L_0x0053:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x0057:
            com.google.android.gms.internal.ads.zzaes r6 = r5.zzdds     // Catch:{ RemoteException -> 0x0065 }
            com.google.android.gms.internal.ads.zzaee r6 = r6.zzsl()     // Catch:{ RemoteException -> 0x0065 }
            if (r6 == 0) goto L_0x0069
            com.google.android.gms.internal.ads.zzaef r2 = new com.google.android.gms.internal.ads.zzaef     // Catch:{ RemoteException -> 0x0065 }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x0065 }
            goto L_0x006a
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x0069:
            r2 = r1
        L_0x006a:
            r5.zzddu = r2
            com.google.android.gms.internal.ads.zzaes r6 = r5.zzdds     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzadw r6 = r6.zzsm()     // Catch:{ RemoteException -> 0x0081 }
            if (r6 == 0) goto L_0x0085
            com.google.android.gms.internal.ads.zzadx r6 = new com.google.android.gms.internal.ads.zzadx     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzaes r2 = r5.zzdds     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzadw r2 = r2.zzsm()     // Catch:{ RemoteException -> 0x0081 }
            r6.<init>(r2)     // Catch:{ RemoteException -> 0x0081 }
            r1 = r6
            goto L_0x0085
        L_0x0081:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x0085:
            r5.zzddv = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaet.<init>(com.google.android.gms.internal.ads.zzaes):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzsk */
    public final IObjectWrapper zzjs() {
        try {
            return this.zzdds.zzsk();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzdds.performClick(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzdds.recordImpression(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzdds.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final CharSequence getHeadline() {
        try {
            return this.zzdds.getHeadline();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzddt;
    }

    public final CharSequence getBody() {
        try {
            return this.zzdds.getBody();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzddu;
    }

    public final CharSequence getCallToAction() {
        try {
            return this.zzdds.getCallToAction();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzdds.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final CharSequence getStore() {
        try {
            return this.zzdds.getStore();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final CharSequence getPrice() {
        try {
            return this.zzdds.getPrice();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzdds.getVideoController() != null) {
                this.zzcjt.zza(this.zzdds.getVideoController());
            }
        } catch (RemoteException e) {
            zzbbq.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzcjt;
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzddv;
    }

    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzdds.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            return this.zzdds.getExtras();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final void destroy() {
        try {
            this.zzdds.destroy();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
