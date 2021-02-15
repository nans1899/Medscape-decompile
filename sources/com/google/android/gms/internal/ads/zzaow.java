package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaow extends zzant {
    private final UnifiedNativeAdMapper zzdmf;

    public zzaow(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        this.zzdmf = unifiedNativeAdMapper;
    }

    public final zzadw zzsm() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdmf.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzdmf.getImages();
        ArrayList arrayList = new ArrayList();
        if (images != null) {
            for (NativeAd.Image next : images) {
                arrayList.add(new zzadq(next.getDrawable(), next.getUri(), next.getScale(), next.getWidth(), next.getHeight()));
            }
        }
        return arrayList;
    }

    public final String getBody() {
        return this.zzdmf.getBody();
    }

    public final zzaee zzsl() {
        NativeAd.Image icon = this.zzdmf.getIcon();
        if (icon != null) {
            return new zzadq(icon.getDrawable(), icon.getUri(), icon.getScale(), icon.getWidth(), icon.getHeight());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdmf.getCallToAction();
    }

    public final String getAdvertiser() {
        return this.zzdmf.getAdvertiser();
    }

    public final double getStarRating() {
        if (this.zzdmf.getStarRating() != null) {
            return this.zzdmf.getStarRating().doubleValue();
        }
        return -1.0d;
    }

    public final String getStore() {
        return this.zzdmf.getStore();
    }

    public final String getPrice() {
        return this.zzdmf.getPrice();
    }

    public final zzyo getVideoController() {
        if (this.zzdmf.getVideoController() != null) {
            return this.zzdmf.getVideoController().zzdv();
        }
        return null;
    }

    public final IObjectWrapper zzue() {
        View adChoicesContent = this.zzdmf.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzuf() {
        View zzadh = this.zzdmf.zzadh();
        if (zzadh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzadh);
    }

    public final float getMediaContentAspectRatio() {
        return this.zzdmf.getMediaContentAspectRatio();
    }

    public final float getVideoDuration() {
        return this.zzdmf.getDuration();
    }

    public final float getVideoCurrentTime() {
        return this.zzdmf.getCurrentTime();
    }

    public final IObjectWrapper zzsn() {
        Object zzjx = this.zzdmf.zzjx();
        if (zzjx == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzjx);
    }

    public final Bundle getExtras() {
        return this.zzdmf.getExtras();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdmf.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdmf.getOverrideClickHandling();
    }

    public final void recordImpression() {
        this.zzdmf.recordImpression();
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        this.zzdmf.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdmf.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzdmf.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }
}
