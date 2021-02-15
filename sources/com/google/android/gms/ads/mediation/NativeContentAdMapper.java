package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class NativeContentAdMapper extends NativeAdMapper {
    private String zzdsl;
    private String zzerg;
    private List<NativeAd.Image> zzerh;
    private String zzerj;
    private NativeAd.Image zzerp;
    private String zzerq;

    public final void setHeadline(String str) {
        this.zzerg = str;
    }

    public final void setImages(List<NativeAd.Image> list) {
        this.zzerh = list;
    }

    public final void setBody(String str) {
        this.zzdsl = str;
    }

    public final void setLogo(NativeAd.Image image) {
        this.zzerp = image;
    }

    public final void setCallToAction(String str) {
        this.zzerj = str;
    }

    public final void setAdvertiser(String str) {
        this.zzerq = str;
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

    public final NativeAd.Image getLogo() {
        return this.zzerp;
    }

    public final String getCallToAction() {
        return this.zzerj;
    }

    public final String getAdvertiser() {
        return this.zzerq;
    }
}
