package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.InputDeviceCompat;
import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.medscape.android.cache.Cache;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbdh extends FrameLayout implements zzbdc {
    private final zzbdu zzegh;
    private final FrameLayout zzegi;
    private final zzabs zzegj;
    private final zzbdw zzegk;
    private final long zzegl;
    private zzbdf zzegm;
    private boolean zzegn;
    private boolean zzego;
    private boolean zzegp;
    private boolean zzegq;
    private long zzegr;
    private long zzegs;
    private String zzegt;
    private String[] zzegu;
    private Bitmap zzegv;
    private ImageView zzegw;
    private boolean zzegx;

    public static void zzb(zzbdu zzbdu) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        zzbdu.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(zzbdu zzbdu, Map<String, List<Map<String, Object>>> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("mimeTypes", map);
        zzbdu.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    public static void zza(zzbdu zzbdu, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "decoderProps");
        hashMap.put("error", str);
        zzbdu.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbdh(Context context, zzbdu zzbdu, int i, boolean z, zzabs zzabs, zzbdv zzbdv) {
        super(context);
        Context context2 = context;
        zzbdu zzbdu2 = zzbdu;
        this.zzegh = zzbdu2;
        zzabs zzabs2 = zzabs;
        this.zzegj = zzabs2;
        FrameLayout frameLayout = new FrameLayout(context);
        this.zzegi = frameLayout;
        addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
        Preconditions.checkNotNull(zzbdu.zzzv());
        zzbdf zza = zzbdu.zzzv().zzboj.zza(context, zzbdu2, i, z, zzabs2, zzbdv);
        this.zzegm = zza;
        if (zza != null) {
            this.zzegi.addView(zza, new FrameLayout.LayoutParams(-1, -1, 17));
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcmo)).booleanValue()) {
                zzzj();
            }
        }
        this.zzegw = new ImageView(context);
        this.zzegl = ((Long) zzwm.zzpx().zzd(zzabb.zzcms)).longValue();
        boolean booleanValue = ((Boolean) zzwm.zzpx().zzd(zzabb.zzcmq)).booleanValue();
        this.zzegq = booleanValue;
        zzabs zzabs3 = this.zzegj;
        if (zzabs3 != null) {
            zzabs3.zzh("spinner_used", booleanValue ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.zzegk = new zzbdw(this);
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.zza(this);
        }
        if (this.zzegm == null) {
            zzm("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
            layoutParams.setMargins(i, i2, 0, 0);
            this.zzegi.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public final void zzc(String str, String[] strArr) {
        this.zzegt = str;
        this.zzegu = strArr;
    }

    public final void zza(float f, float f2) {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.zza(f, f2);
        }
    }

    public final void zzhy() {
        if (this.zzegm != null) {
            if (!TextUtils.isEmpty(this.zzegt)) {
                this.zzegm.zzb(this.zzegt, this.zzegu);
            } else {
                zzd("no_src", new String[0]);
            }
        }
    }

    public final void pause() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.pause();
        }
    }

    public final void play() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.play();
        }
    }

    public final void seekTo(int i) {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.seekTo(i);
        }
    }

    public final void zzzh() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.zzegf.setMuted(true);
            zzbdf.zzzb();
        }
    }

    public final void zzzi() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.zzegf.setMuted(false);
            zzbdf.zzzb();
        }
    }

    public final void setVolume(float f) {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.zzegf.setVolume(f);
            zzbdf.zzzb();
        }
    }

    public final void zzdl(int i) {
        this.zzegm.zzdl(i);
    }

    public final void zzdm(int i) {
        this.zzegm.zzdm(i);
    }

    public final void zzdn(int i) {
        this.zzegm.zzdn(i);
    }

    public final void zzdo(int i) {
        this.zzegm.zzdo(i);
    }

    public final void zzdp(int i) {
        this.zzegm.zzdp(i);
    }

    public final void zze(MotionEvent motionEvent) {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.dispatchTouchEvent(motionEvent);
        }
    }

    public final void zzzj() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            TextView textView = new TextView(zzbdf.getContext());
            String valueOf = String.valueOf(this.zzegm.zzyx());
            textView.setText(valueOf.length() != 0 ? "AdMob - ".concat(valueOf) : new String("AdMob - "));
            textView.setTextColor(-65536);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzegi.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
            this.zzegi.bringChildToFront(textView);
        }
    }

    public final void zzzc() {
        this.zzegk.resume();
        zzayu.zzeba.post(new zzbdi(this));
    }

    public final void zzfb() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null && this.zzegs == 0) {
            zzd("canplaythrough", "duration", String.valueOf(((float) zzbdf.getDuration()) / 1000.0f), "videoWidth", String.valueOf(this.zzegm.getVideoWidth()), "videoHeight", String.valueOf(this.zzegm.getVideoHeight()));
        }
    }

    public final void zzzd() {
        if (this.zzegh.zzzu() != null && !this.zzego) {
            boolean z = (this.zzegh.zzzu().getWindow().getAttributes().flags & 128) != 0;
            this.zzegp = z;
            if (!z) {
                this.zzegh.zzzu().getWindow().addFlags(128);
                this.zzego = true;
            }
        }
        this.zzegn = true;
    }

    public final void onPaused() {
        zzd(OmnitureData.LINK_NAME_VIDEO_PAUSE, new String[0]);
        zzzm();
        this.zzegn = false;
    }

    public final void zzze() {
        zzd("ended", new String[0]);
        zzzm();
    }

    public final void zzm(String str, String str2) {
        zzd("error", "what", str, Constants.APPBOY_PUSH_EXTRAS_KEY, str2);
    }

    public final void zzzf() {
        if (this.zzegx && this.zzegv != null && !zzzl()) {
            this.zzegw.setImageBitmap(this.zzegv);
            this.zzegw.invalidate();
            this.zzegi.addView(this.zzegw, new FrameLayout.LayoutParams(-1, -1));
            this.zzegi.bringChildToFront(this.zzegw);
        }
        this.zzegk.pause();
        this.zzegs = this.zzegr;
        zzayu.zzeba.post(new zzbdl(this));
    }

    public final void destroy() {
        this.zzegk.pause();
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            zzbdf.stop();
        }
        zzzm();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzegk.pause();
            if (this.zzegm != null) {
                zzbdf zzbdf = this.zzegm;
                zzdzb zzdzb = zzbbz.zzeet;
                zzbdf.getClass();
                zzdzb.execute(zzbdg.zza(zzbdf));
            }
        } finally {
            super.finalize();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzzk() {
        zzbdf zzbdf = this.zzegm;
        if (zzbdf != null) {
            long currentPosition = (long) zzbdf.getCurrentPosition();
            if (this.zzegr != currentPosition && currentPosition > 0) {
                zzd("timeupdate", Cache.Caches.TIME, String.valueOf(((float) currentPosition) / 1000.0f));
                this.zzegr = currentPosition;
            }
        }
    }

    public final void zzzg() {
        if (this.zzegn && zzzl()) {
            this.zzegi.removeView(this.zzegw);
        }
        if (this.zzegv != null) {
            long elapsedRealtime = zzp.zzky().elapsedRealtime();
            if (this.zzegm.getBitmap(this.zzegv) != null) {
                this.zzegx = true;
            }
            long elapsedRealtime2 = zzp.zzky().elapsedRealtime() - elapsedRealtime;
            if (zzayp.zzxa()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(elapsedRealtime2);
                sb.append("ms");
                zzayp.zzei(sb.toString());
            }
            if (elapsedRealtime2 > this.zzegl) {
                zzayp.zzfe("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzegq = false;
                this.zzegv = null;
                zzabs zzabs = this.zzegj;
                if (zzabs != null) {
                    zzabs.zzh("spinner_jank", Long.toString(elapsedRealtime2));
                }
            }
        }
    }

    public final void zzk(int i, int i2) {
        if (this.zzegq) {
            int max = Math.max(i / ((Integer) zzwm.zzpx().zzd(zzabb.zzcmr)).intValue(), 1);
            int max2 = Math.max(i2 / ((Integer) zzwm.zzpx().zzd(zzabb.zzcmr)).intValue(), 1);
            Bitmap bitmap = this.zzegv;
            if (bitmap == null || bitmap.getWidth() != max || this.zzegv.getHeight() != max2) {
                this.zzegv = Bitmap.createBitmap(max, max2, Bitmap.Config.ARGB_8888);
                this.zzegx = false;
            }
        }
    }

    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.zzegk.resume();
        } else {
            this.zzegk.pause();
            this.zzegs = this.zzegr;
        }
        zzayu.zzeba.post(new zzbdj(this, z));
    }

    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.zzegk.resume();
            z = true;
        } else {
            this.zzegk.pause();
            this.zzegs = this.zzegr;
            z = false;
        }
        zzayu.zzeba.post(new zzbdk(this, z));
    }

    private final boolean zzzl() {
        return this.zzegw.getParent() != null;
    }

    /* access modifiers changed from: private */
    public final void zzd(String str, String... strArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                hashMap.put(str2, str3);
                str2 = null;
            }
        }
        this.zzegh.zza("onVideoEvent", (Map<String, ?>) hashMap);
    }

    private final void zzzm() {
        if (this.zzegh.zzzu() != null && this.zzego && !this.zzegp) {
            this.zzegh.zzzu().getWindow().clearFlags(128);
            this.zzego = false;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzau(boolean z) {
        zzd("windowFocusChanged", "hasWindowFocus", String.valueOf(z));
    }
}
