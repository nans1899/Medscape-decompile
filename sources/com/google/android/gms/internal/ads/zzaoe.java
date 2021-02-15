package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaoe extends zzann {
    private final NativeAppInstallAdMapper zzdlv;

    public zzaoe(NativeAppInstallAdMapper nativeAppInstallAdMapper) {
        this.zzdlv = nativeAppInstallAdMapper;
    }

    public final zzadw zzsm() {
        return null;
    }

    public final IObjectWrapper zzsn() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdlv.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzdlv.getImages();
        if (images == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (NativeAd.Image next : images) {
            arrayList.add(new zzadq(next.getDrawable(), next.getUri(), next.getScale(), next.getWidth(), next.getHeight()));
        }
        return arrayList;
    }

    public final String getBody() {
        return this.zzdlv.getBody();
    }

    public final zzaee zzsl() {
        NativeAd.Image icon = this.zzdlv.getIcon();
        if (icon != null) {
            return new zzadq(icon.getDrawable(), icon.getUri(), icon.getScale(), icon.getWidth(), icon.getHeight());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdlv.getCallToAction();
    }

    public final double getStarRating() {
        return this.zzdlv.getStarRating();
    }

    public final String getStore() {
        return this.zzdlv.getStore();
    }

    public final String getPrice() {
        return this.zzdlv.getPrice();
    }

    public final void recordImpression() {
        this.zzdlv.recordImpression();
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        this.zzdlv.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        this.zzdlv.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdlv.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzdlv.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdlv.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdlv.getOverrideClickHandling();
    }

    public final Bundle getExtras() {
        return this.zzdlv.getExtras();
    }

    public final zzyo getVideoController() {
        if (this.zzdlv.getVideoController() != null) {
            return this.zzdlv.getVideoController().zzdv();
        }
        return null;
    }

    public final IObjectWrapper zzue() {
        View adChoicesContent = this.zzdlv.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final IObjectWrapper zzuf() {
        View zzadh = this.zzdlv.zzadh();
        if (zzadh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzadh);
    }
}
