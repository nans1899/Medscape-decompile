package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class NativeAppInstallAdMapper extends NativeAdMapper {
    private String zzdsl;
    private String zzerg;
    private List<NativeAd.Image> zzerh;
    private NativeAd.Image zzeri;
    private String zzerj;
    private double zzerk;
    private String zzerl;
    private String zzerm;

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

    public final void setStarRating(double d) {
        this.zzerk = d;
    }

    public final void setStore(String str) {
        this.zzerl = str;
    }

    public final void setPrice(String str) {
        this.zzerm = str;
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

    public final double getStarRating() {
        return this.zzerk;
    }

    public final String getStore() {
        return this.zzerl;
    }

    public final String getPrice() {
        return this.zzerm;
    }
}
