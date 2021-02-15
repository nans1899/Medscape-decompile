package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.MediaContent;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzagf extends UnifiedNativeAd {
    private final VideoController zzcjt = new VideoController();
    private final List<NativeAd.Image> zzddt = new ArrayList();
    private final zzaef zzddu;
    private final NativeAd.AdChoicesInfo zzddv;
    private final zzage zzdeb;
    private final List<MuteThisAdReason> zzdec = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ae A[Catch:{ RemoteException -> 0x00bb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzagf(com.google.android.gms.internal.ads.zzage r6) {
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
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzdec = r1
            r5.zzdeb = r6
            r1 = 0
            java.util.List r6 = r6.getImages()     // Catch:{ RemoteException -> 0x005a }
            if (r6 == 0) goto L_0x005e
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x005a }
        L_0x0027:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x005a }
            if (r2 == 0) goto L_0x005e
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x005a }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x005a }
            if (r3 == 0) goto L_0x004c
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x005a }
            if (r2 == 0) goto L_0x004c
            java.lang.String r3 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)     // Catch:{ RemoteException -> 0x005a }
            boolean r4 = r3 instanceof com.google.android.gms.internal.ads.zzaee     // Catch:{ RemoteException -> 0x005a }
            if (r4 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzaee r3 = (com.google.android.gms.internal.ads.zzaee) r3     // Catch:{ RemoteException -> 0x005a }
            goto L_0x004d
        L_0x0046:
            com.google.android.gms.internal.ads.zzaeg r3 = new com.google.android.gms.internal.ads.zzaeg     // Catch:{ RemoteException -> 0x005a }
            r3.<init>(r2)     // Catch:{ RemoteException -> 0x005a }
            goto L_0x004d
        L_0x004c:
            r3 = r1
        L_0x004d:
            if (r3 == 0) goto L_0x0027
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r5.zzddt     // Catch:{ RemoteException -> 0x005a }
            com.google.android.gms.internal.ads.zzaef r4 = new com.google.android.gms.internal.ads.zzaef     // Catch:{ RemoteException -> 0x005a }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x005a }
            r2.add(r4)     // Catch:{ RemoteException -> 0x005a }
            goto L_0x0027
        L_0x005a:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x005e:
            com.google.android.gms.internal.ads.zzage r6 = r5.zzdeb     // Catch:{ RemoteException -> 0x008d }
            java.util.List r6 = r6.getMuteThisAdReasons()     // Catch:{ RemoteException -> 0x008d }
            if (r6 == 0) goto L_0x0091
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x008d }
        L_0x006a:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x008d }
            if (r2 == 0) goto L_0x0091
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x008d }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x008d }
            if (r3 == 0) goto L_0x007f
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x008d }
            com.google.android.gms.internal.ads.zzyd r2 = com.google.android.gms.internal.ads.zzyc.zzg(r2)     // Catch:{ RemoteException -> 0x008d }
            goto L_0x0080
        L_0x007f:
            r2 = r1
        L_0x0080:
            if (r2 == 0) goto L_0x006a
            java.util.List<com.google.android.gms.ads.MuteThisAdReason> r3 = r5.zzdec     // Catch:{ RemoteException -> 0x008d }
            com.google.android.gms.internal.ads.zzye r4 = new com.google.android.gms.internal.ads.zzye     // Catch:{ RemoteException -> 0x008d }
            r4.<init>(r2)     // Catch:{ RemoteException -> 0x008d }
            r3.add(r4)     // Catch:{ RemoteException -> 0x008d }
            goto L_0x006a
        L_0x008d:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x0091:
            com.google.android.gms.internal.ads.zzage r6 = r5.zzdeb     // Catch:{ RemoteException -> 0x009f }
            com.google.android.gms.internal.ads.zzaee r6 = r6.zzsl()     // Catch:{ RemoteException -> 0x009f }
            if (r6 == 0) goto L_0x00a3
            com.google.android.gms.internal.ads.zzaef r2 = new com.google.android.gms.internal.ads.zzaef     // Catch:{ RemoteException -> 0x009f }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x009f }
            goto L_0x00a4
        L_0x009f:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x00a3:
            r2 = r1
        L_0x00a4:
            r5.zzddu = r2
            com.google.android.gms.internal.ads.zzage r6 = r5.zzdeb     // Catch:{ RemoteException -> 0x00bb }
            com.google.android.gms.internal.ads.zzadw r6 = r6.zzsm()     // Catch:{ RemoteException -> 0x00bb }
            if (r6 == 0) goto L_0x00bf
            com.google.android.gms.internal.ads.zzadx r6 = new com.google.android.gms.internal.ads.zzadx     // Catch:{ RemoteException -> 0x00bb }
            com.google.android.gms.internal.ads.zzage r2 = r5.zzdeb     // Catch:{ RemoteException -> 0x00bb }
            com.google.android.gms.internal.ads.zzadw r2 = r2.zzsm()     // Catch:{ RemoteException -> 0x00bb }
            r6.<init>(r2)     // Catch:{ RemoteException -> 0x00bb }
            r1 = r6
            goto L_0x00bf
        L_0x00bb:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzbbq.zzc(r0, r6)
        L_0x00bf:
            r5.zzddv = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzagf.<init>(com.google.android.gms.internal.ads.zzage):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzsk */
    public final IObjectWrapper zzjs() {
        try {
            return this.zzdeb.zzsk();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final Object zzjx() {
        try {
            IObjectWrapper zzsn = this.zzdeb.zzsn();
            if (zzsn != null) {
                return ObjectWrapper.unwrap(zzsn);
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzdeb.performClick(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzdeb.recordImpression(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzdeb.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final String getHeadline() {
        try {
            return this.zzdeb.getHeadline();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzddt;
    }

    public final String getBody() {
        try {
            return this.zzdeb.getBody();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzddu;
    }

    public final String getCallToAction() {
        try {
            return this.zzdeb.getCallToAction();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzdeb.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final String getStore() {
        try {
            return this.zzdeb.getStore();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final String getPrice() {
        try {
            return this.zzdeb.getPrice();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzdeb.getVideoController() != null) {
                this.zzcjt.zza(this.zzdeb.getVideoController());
            }
        } catch (RemoteException e) {
            zzbbq.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzcjt;
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzddv;
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzdeb.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzdeb.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
        return new Bundle();
    }

    public final void enableCustomClickGesture() {
        try {
            this.zzdeb.zzsv();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zzdeb.isCustomClickGestureEnabled();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void recordCustomClickGesture() {
        try {
            this.zzdeb.recordCustomClickGesture();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zzdec;
    }

    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zzdeb.isCustomMuteThisAdEnabled();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return false;
        }
    }

    public final void destroy() {
        try {
            this.zzdeb.destroy();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void setUnconfirmedClickListener(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzdeb.zza((zzafz) new zzagr(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzbbq.zzc("Failed to setUnconfirmedClickListener", e);
        }
    }

    public final void muteThisAd(MuteThisAdReason muteThisAdReason) {
        try {
            if (!isCustomMuteThisAdEnabled()) {
                zzbbq.zzfc("Ad is not custom mute enabled");
            } else if (muteThisAdReason == null) {
                this.zzdeb.zza((zzyd) null);
            } else if (muteThisAdReason instanceof zzye) {
                this.zzdeb.zza(((zzye) muteThisAdReason).zzql());
            } else {
                zzbbq.zzfc("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
            }
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zzdeb.zza((zzxz) new zzya(muteThisAdListener));
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zzdeb.cancelUnconfirmedClick();
        } catch (RemoteException e) {
            zzbbq.zzc("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final String getAdvertiser() {
        try {
            return this.zzdeb.getAdvertiser();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final MediaContent getMediaContent() {
        try {
            if (this.zzdeb.zzsw() != null) {
                return new zzzm(this.zzdeb.zzsw());
            }
            return null;
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            return null;
        }
    }

    public final ResponseInfo getResponseInfo() {
        zzyn zzyn;
        try {
            zzyn = this.zzdeb.zzki();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            zzyn = null;
        }
        return ResponseInfo.zza(zzyn);
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zzdeb.zza((zzyi) new zzaab(onPaidEventListener));
        } catch (RemoteException e) {
            zzbbq.zzc("Failed to setOnPaidEventListener", e);
        }
    }
}
