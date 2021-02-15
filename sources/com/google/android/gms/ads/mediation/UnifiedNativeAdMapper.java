package com.google.android.gms.ads.mediation;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class UnifiedNativeAdMapper {
    private Bundle extras = new Bundle();
    private VideoController zzcjt;
    private String zzdsl;
    private String zzerg;
    private List<NativeAd.Image> zzerh;
    private NativeAd.Image zzeri;
    private String zzerj;
    private String zzerl;
    private String zzerm;
    private View zzern;
    private boolean zzero;
    private String zzerq;
    private Double zzerr;
    private View zzers;
    private Object zzert;
    private boolean zzeru;
    private boolean zzerv;
    private float zzerw;

    public float getCurrentTime() {
        return 0.0f;
    }

    public float getDuration() {
        return 0.0f;
    }

    public void handleClick(View view) {
    }

    public void recordImpression() {
    }

    public void trackViews(View view, Map<String, View> map, Map<String, View> map2) {
    }

    public void untrackView(View view) {
    }

    public final void setHeadline(String str) {
        this.zzerg = str;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzerh = list;
    }

    public final void setBody(String str) {
        this.zzdsl = str;
    }

    public final void setIcon(NativeAd.Image image) {
        this.zzeri = image;
    }

    public final void setCallToAction(String str) {
        this.zzerj = str;
    }

    public final void setAdvertiser(String str) {
        this.zzerq = str;
    }

    public final void setStarRating(Double d) {
        this.zzerr = d;
    }

    public final void setStore(String str) {
        this.zzerl = str;
    }

    public final void setPrice(String str) {
        this.zzerm = str;
    }

    public final void zza(VideoController videoController) {
        this.zzcjt = videoController;
    }

    public void setHasVideoContent(boolean z) {
        this.zzero = z;
    }

    public void setAdChoicesContent(View view) {
        this.zzers = view;
    }

    public void setMediaView(View view) {
        this.zzern = view;
    }

    public void setMediaContentAspectRatio(float f) {
        this.zzerw = f;
    }

    public final void zzn(Object obj) {
        this.zzert = obj;
    }

    public final void setExtras(Bundle bundle) {
        this.extras = bundle;
    }

    public final void setOverrideImpressionRecording(boolean z) {
        this.zzeru = z;
    }

    public final void setOverrideClickHandling(boolean z) {
        this.zzerv = z;
    }

    public final String getHeadline() {
        return this.zzerg;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzerh;
    }

    public final String getBody() {
        return this.zzdsl;
    }

    public final NativeAd.Image getIcon() {
        return this.zzeri;
    }

    public final String getCallToAction() {
        return this.zzerj;
    }

    public final String getAdvertiser() {
        return this.zzerq;
    }

    public final Double getStarRating() {
        return this.zzerr;
    }

    public final String getStore() {
        return this.zzerl;
    }

    public final String getPrice() {
        return this.zzerm;
    }

    public final VideoController getVideoController() {
        return this.zzcjt;
    }

    public boolean hasVideoContent() {
        return this.zzero;
    }

    public View getAdChoicesContent() {
        return this.zzers;
    }

    public final View zzadh() {
        return this.zzern;
    }

    public float getMediaContentAspectRatio() {
        return this.zzerw;
    }

    public final Object zzjx() {
        return this.zzert;
    }

    public final Bundle getExtras() {
        return this.extras;
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzeru;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzerv;
    }
}
