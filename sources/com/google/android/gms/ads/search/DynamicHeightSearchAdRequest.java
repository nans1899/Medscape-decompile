package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.ads.zzyw;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class DynamicHeightSearchAdRequest {
    private final SearchAdRequest zzhlw;

    private DynamicHeightSearchAdRequest(Builder builder) {
        this.zzhlw = builder.zzhlx.build();
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Builder {
        /* access modifiers changed from: private */
        public final SearchAdRequest.Builder zzhlx = new SearchAdRequest.Builder();
        private final Bundle zzhly = new Bundle();

        public final Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzhlx.addNetworkExtras(networkExtras);
            return this;
        }

        public final Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzhlx.addNetworkExtrasBundle(cls, bundle);
            return this;
        }

        public final Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzhlx.addCustomEventExtrasBundle(cls, bundle);
            return this;
        }

        public final Builder setPage(int i) {
            this.zzhly.putString("csa_adPage", Integer.toString(i));
            return this;
        }

        public final Builder setNumber(int i) {
            this.zzhly.putString("csa_number", Integer.toString(i));
            return this;
        }

        public final Builder setAdTest(boolean z) {
            this.zzhly.putString("csa_adtest", z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            return this;
        }

        public final Builder setChannel(String str) {
            this.zzhly.putString("csa_channel", str);
            return this;
        }

        public final Builder setHostLanguage(String str) {
            this.zzhly.putString("csa_hl", str);
            return this;
        }

        public final Builder setLocationColor(String str) {
            this.zzhly.putString("csa_colorLocation", str);
            return this;
        }

        public final Builder setLocationFontSize(int i) {
            this.zzhly.putString("csa_fontSizeLocation", Integer.toString(i));
            return this;
        }

        public final Builder setIsClickToCallEnabled(boolean z) {
            this.zzhly.putString("csa_clickToCall", Boolean.toString(z));
            return this;
        }

        public final Builder setIsLocationEnabled(boolean z) {
            this.zzhly.putString("csa_location", Boolean.toString(z));
            return this;
        }

        public final Builder setIsPlusOnesEnabled(boolean z) {
            this.zzhly.putString("csa_plusOnes", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSellerRatingsEnabled(boolean z) {
            this.zzhly.putString("csa_sellerRatings", Boolean.toString(z));
            return this;
        }

        public final Builder setIsSiteLinksEnabled(boolean z) {
            this.zzhly.putString("csa_siteLinks", Boolean.toString(z));
            return this;
        }

        public final Builder setCssWidth(int i) {
            this.zzhly.putString("csa_width", Integer.toString(i));
            return this;
        }

        public final Builder setFontFamily(String str) {
            this.zzhly.putString("csa_fontFamily", str);
            return this;
        }

        public final Builder setFontFamilyAttribution(String str) {
            this.zzhly.putString("csa_fontFamilyAttribution", str);
            return this;
        }

        public final Builder setFontSizeAnnotation(int i) {
            this.zzhly.putString("csa_fontSizeAnnotation", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeAttribution(int i) {
            this.zzhly.putString("csa_fontSizeAttribution", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDescription(int i) {
            this.zzhly.putString("csa_fontSizeDescription", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeDomainLink(int i) {
            this.zzhly.putString("csa_fontSizeDomainLink", Integer.toString(i));
            return this;
        }

        public final Builder setFontSizeTitle(int i) {
            this.zzhly.putString("csa_fontSizeTitle", Integer.toString(i));
            return this;
        }

        public final Builder setColorAdBorder(String str) {
            this.zzhly.putString("csa_colorAdBorder", str);
            return this;
        }

        public final Builder setColorAdSeparator(String str) {
            this.zzhly.putString("csa_colorAdSeparator", str);
            return this;
        }

        public final Builder setColorAnnotation(String str) {
            this.zzhly.putString("csa_colorAnnotation", str);
            return this;
        }

        public final Builder setColorAttribution(String str) {
            this.zzhly.putString("csa_colorAttribution", str);
            return this;
        }

        public final Builder setColorBackground(String str) {
            this.zzhly.putString("csa_colorBackground", str);
            return this;
        }

        public final Builder setColorBorder(String str) {
            this.zzhly.putString("csa_colorBorder", str);
            return this;
        }

        public final Builder setColorDomainLink(String str) {
            this.zzhly.putString("csa_colorDomainLink", str);
            return this;
        }

        public final Builder setColorText(String str) {
            this.zzhly.putString("csa_colorText", str);
            return this;
        }

        public final Builder setColorTitleLink(String str) {
            this.zzhly.putString("csa_colorTitleLink", str);
            return this;
        }

        public final Builder setAdBorderSelectors(String str) {
            this.zzhly.putString("csa_adBorderSelectors", str);
            return this;
        }

        public final Builder setAdjustableLineHeight(int i) {
            this.zzhly.putString("csa_adjustableLineHeight", Integer.toString(i));
            return this;
        }

        public final Builder setAttributionSpacingBelow(int i) {
            this.zzhly.putString("csa_attributionSpacingBelow", Integer.toString(i));
            return this;
        }

        public final Builder setBorderSelections(String str) {
            this.zzhly.putString("csa_borderSelections", str);
            return this;
        }

        public final Builder setIsTitleUnderlined(boolean z) {
            this.zzhly.putString("csa_noTitleUnderline", Boolean.toString(!z));
            return this;
        }

        public final Builder setIsTitleBold(boolean z) {
            this.zzhly.putString("csa_titleBold", Boolean.toString(z));
            return this;
        }

        public final Builder setVerticalSpacing(int i) {
            this.zzhly.putString("csa_verticalSpacing", Integer.toString(i));
            return this;
        }

        public final Builder setDetailedAttribution(boolean z) {
            this.zzhly.putString("csa_detailedAttribution", Boolean.toString(z));
            return this;
        }

        public final Builder setLongerHeadlines(boolean z) {
            this.zzhly.putString("csa_longerHeadlines", Boolean.toString(z));
            return this;
        }

        public final Builder setAdvancedOptionValue(String str, String str2) {
            this.zzhly.putString(str, str2);
            return this;
        }

        public final DynamicHeightSearchAdRequest build() {
            this.zzhlx.addNetworkExtrasBundle(AdMobAdapter.class, this.zzhly);
            return new DynamicHeightSearchAdRequest(this);
        }

        public final Builder setQuery(String str) {
            this.zzhlx.setQuery(str);
            return this;
        }
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzhlw.getNetworkExtras(cls);
    }

    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzhlw.getNetworkExtrasBundle(cls);
    }

    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzhlw.getCustomEventExtrasBundle(cls);
    }

    public final String getQuery() {
        return this.zzhlw.getQuery();
    }

    public final boolean isTestDevice(Context context) {
        return this.zzhlw.isTestDevice(context);
    }

    /* access modifiers changed from: package-private */
    public final zzyw zzdr() {
        return this.zzhlw.zzdr();
    }
}
