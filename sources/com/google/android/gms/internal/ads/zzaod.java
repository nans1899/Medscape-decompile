package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaod extends zzans {
    private final NativeContentAdMapper zzdlu;

    public zzaod(NativeContentAdMapper nativeContentAdMapper) {
        this.zzdlu = nativeContentAdMapper;
    }

    public final zzadw zzsm() {
        return null;
    }

    public final IObjectWrapper zzsn() {
        return null;
    }

    public final String getHeadline() {
        return this.zzdlu.getHeadline();
    }

    public final List getImages() {
        List<NativeAd.Image> images = this.zzdlu.getImages();
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
        return this.zzdlu.getBody();
    }

    public final zzaee zzso() {
        NativeAd.Image logo = this.zzdlu.getLogo();
        if (logo != null) {
            return new zzadq(logo.getDrawable(), logo.getUri(), logo.getScale(), logo.getWidth(), logo.getHeight());
        }
        return null;
    }

    public final String getCallToAction() {
        return this.zzdlu.getCallToAction();
    }

    public final String getAdvertiser() {
        return this.zzdlu.getAdvertiser();
    }

    public final void recordImpression() {
        this.zzdlu.recordImpression();
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        this.zzdlu.handleClick((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        this.zzdlu.trackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzdlu.trackViews((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        this.zzdlu.untrackView((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzdlu.getOverrideImpressionRecording();
    }

    public final boolean getOverrideClickHandling() {
        return this.zzdlu.getOverrideClickHandling();
    }

    public final Bundle getExtras() {
        return this.zzdlu.getExtras();
    }

    public final IObjectWrapper zzue() {
        View adChoicesContent = this.zzdlu.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap(adChoicesContent);
    }

    public final zzyo getVideoController() {
        if (this.zzdlu.getVideoController() != null) {
            return this.zzdlu.getVideoController().zzdv();
        }
        return null;
    }

    public final IObjectWrapper zzuf() {
        View zzadh = this.zzdlu.zzadh();
        if (zzadh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzadh);
    }
}
