package com.medscape.android.analytics.remoteconfig;

import com.google.android.gms.ads.AdSize;
import com.medscape.android.ads.DFPAdAction;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0002+,B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R6\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0012j\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR\u001a\u0010!\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\"\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "", "()V", "adSizes", "", "Lcom/google/android/gms/ads/AdSize;", "getAdSizes", "()[Lcom/google/android/gms/ads/AdSize;", "setAdSizes", "([Lcom/google/android/gms/ads/AdSize;)V", "[Lcom/google/android/gms/ads/AdSize;", "adUnit", "", "getAdUnit", "()Ljava/lang/String;", "setAdUnit", "(Ljava/lang/String;)V", "customTargeting", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getCustomTargeting", "()Ljava/util/HashMap;", "setCustomTargeting", "(Ljava/util/HashMap;)V", "positionEvery", "", "getPositionEvery", "()I", "setPositionEvery", "(I)V", "positionStart", "getPositionStart", "setPositionStart", "preloadCount", "getPreloadCount", "setPreloadCount", "specialAds", "", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$SpecialAd;", "getSpecialAds", "()Ljava/util/List;", "setSpecialAds", "(Ljava/util/List;)V", "Config", "SpecialAd", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdRemoteConfigModel.kt */
public final class AdRemoteConfigModel {
    private AdSize[] adSizes;
    private String adUnit = "/4312434/profpromomobileapp/medscpnewsmobileapp";
    private HashMap<String, String> customTargeting = new HashMap<>();
    private int positionEvery = 4;
    private int positionStart = 1;
    private int preloadCount = 3;
    private List<SpecialAd> specialAds;

    public AdRemoteConfigModel() {
        AdSize adSize = DFPAdAction.ADSIZE_300x45;
        Intrinsics.checkNotNullExpressionValue(adSize, "DFPAdAction.ADSIZE_300x45");
        AdSize adSize2 = DFPAdAction.ADSIZE_300x61;
        Intrinsics.checkNotNullExpressionValue(adSize2, "DFPAdAction.ADSIZE_300x61");
        AdSize adSize3 = DFPAdAction.ADSIZE_300x76;
        Intrinsics.checkNotNullExpressionValue(adSize3, "DFPAdAction.ADSIZE_300x76");
        AdSize adSize4 = DFPAdAction.ADSIZE_300x91;
        Intrinsics.checkNotNullExpressionValue(adSize4, "DFPAdAction.ADSIZE_300x91");
        AdSize adSize5 = DFPAdAction.ADSIZE_300x106;
        Intrinsics.checkNotNullExpressionValue(adSize5, "DFPAdAction.ADSIZE_300x106");
        AdSize adSize6 = DFPAdAction.ADSIZE_300x121;
        Intrinsics.checkNotNullExpressionValue(adSize6, "DFPAdAction.ADSIZE_300x121");
        AdSize adSize7 = DFPAdAction.ADSIZE_300x136;
        Intrinsics.checkNotNullExpressionValue(adSize7, "DFPAdAction.ADSIZE_300x136");
        AdSize adSize8 = DFPAdAction.ADSIZE_300x50;
        Intrinsics.checkNotNullExpressionValue(adSize8, "DFPAdAction.ADSIZE_300x50");
        AdSize adSize9 = DFPAdAction.ADSIZE_320x50;
        Intrinsics.checkNotNullExpressionValue(adSize9, "DFPAdAction.ADSIZE_320x50");
        AdSize adSize10 = DFPAdAction.ADSIZE_300x400;
        Intrinsics.checkNotNullExpressionValue(adSize10, "DFPAdAction.ADSIZE_300x400");
        AdSize adSize11 = DFPAdAction.ADSIZE_1x3;
        Intrinsics.checkNotNullExpressionValue(adSize11, "DFPAdAction.ADSIZE_1x3");
        AdSize adSize12 = AdSize.MEDIUM_RECTANGLE;
        Intrinsics.checkNotNullExpressionValue(adSize12, "AdSize.MEDIUM_RECTANGLE");
        this.adSizes = new AdSize[]{adSize, adSize2, adSize3, adSize4, adSize5, adSize6, adSize7, adSize8, adSize9, adSize10, adSize11, adSize12};
    }

    public final int getPositionStart() {
        return this.positionStart;
    }

    public final void setPositionStart(int i) {
        this.positionStart = i;
    }

    public final int getPositionEvery() {
        return this.positionEvery;
    }

    public final void setPositionEvery(int i) {
        this.positionEvery = i;
    }

    public final int getPreloadCount() {
        return this.preloadCount;
    }

    public final void setPreloadCount(int i) {
        this.preloadCount = i;
    }

    public final String getAdUnit() {
        return this.adUnit;
    }

    public final void setAdUnit(String str) {
        this.adUnit = str;
    }

    public final HashMap<String, String> getCustomTargeting() {
        return this.customTargeting;
    }

    public final void setCustomTargeting(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.customTargeting = hashMap;
    }

    public final AdSize[] getAdSizes() {
        return this.adSizes;
    }

    public final void setAdSizes(AdSize[] adSizeArr) {
        Intrinsics.checkNotNullParameter(adSizeArr, "<set-?>");
        this.adSizes = adSizeArr;
    }

    public final List<SpecialAd> getSpecialAds() {
        return this.specialAds;
    }

    public final void setSpecialAds(List<SpecialAd> list) {
        this.specialAds = list;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JD\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$SpecialAd;", "", "category", "", "configs", "", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$Config;", "enabled", "", "type", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;)V", "getCategory", "()Ljava/lang/String;", "getConfigs", "()Ljava/util/List;", "getEnabled", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getType", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$SpecialAd;", "equals", "other", "hashCode", "", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdRemoteConfigModel.kt */
    public static final class SpecialAd {
        private final String category;
        private final List<Config> configs;
        private final Boolean enabled;
        private final String type;

        public static /* synthetic */ SpecialAd copy$default(SpecialAd specialAd, String str, List<Config> list, Boolean bool, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = specialAd.category;
            }
            if ((i & 2) != 0) {
                list = specialAd.configs;
            }
            if ((i & 4) != 0) {
                bool = specialAd.enabled;
            }
            if ((i & 8) != 0) {
                str2 = specialAd.type;
            }
            return specialAd.copy(str, list, bool, str2);
        }

        public final String component1() {
            return this.category;
        }

        public final List<Config> component2() {
            return this.configs;
        }

        public final Boolean component3() {
            return this.enabled;
        }

        public final String component4() {
            return this.type;
        }

        public final SpecialAd copy(String str, List<Config> list, Boolean bool, String str2) {
            return new SpecialAd(str, list, bool, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpecialAd)) {
                return false;
            }
            SpecialAd specialAd = (SpecialAd) obj;
            return Intrinsics.areEqual((Object) this.category, (Object) specialAd.category) && Intrinsics.areEqual((Object) this.configs, (Object) specialAd.configs) && Intrinsics.areEqual((Object) this.enabled, (Object) specialAd.enabled) && Intrinsics.areEqual((Object) this.type, (Object) specialAd.type);
        }

        public int hashCode() {
            String str = this.category;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            List<Config> list = this.configs;
            int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
            Boolean bool = this.enabled;
            int hashCode3 = (hashCode2 + (bool != null ? bool.hashCode() : 0)) * 31;
            String str2 = this.type;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            return "SpecialAd(category=" + this.category + ", configs=" + this.configs + ", enabled=" + this.enabled + ", type=" + this.type + ")";
        }

        public SpecialAd(String str, List<Config> list, Boolean bool, String str2) {
            this.category = str;
            this.configs = list;
            this.enabled = bool;
            this.type = str2;
        }

        public final String getCategory() {
            return this.category;
        }

        public final List<Config> getConfigs() {
            return this.configs;
        }

        public final Boolean getEnabled() {
            return this.enabled;
        }

        public final String getType() {
            return this.type;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$Config;", "", "feedType", "", "positionEvery", "", "positionStart", "preloadCount", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getFeedType", "()Ljava/lang/String;", "getPositionEvery", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPositionStart", "getPreloadCount", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel$Config;", "equals", "", "other", "hashCode", "toString", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdRemoteConfigModel.kt */
    public static final class Config {
        private final String feedType;
        private final Integer positionEvery;
        private final Integer positionStart;
        private final Integer preloadCount;

        public static /* synthetic */ Config copy$default(Config config, String str, Integer num, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = config.feedType;
            }
            if ((i & 2) != 0) {
                num = config.positionEvery;
            }
            if ((i & 4) != 0) {
                num2 = config.positionStart;
            }
            if ((i & 8) != 0) {
                num3 = config.preloadCount;
            }
            return config.copy(str, num, num2, num3);
        }

        public final String component1() {
            return this.feedType;
        }

        public final Integer component2() {
            return this.positionEvery;
        }

        public final Integer component3() {
            return this.positionStart;
        }

        public final Integer component4() {
            return this.preloadCount;
        }

        public final Config copy(String str, Integer num, Integer num2, Integer num3) {
            return new Config(str, num, num2, num3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Config)) {
                return false;
            }
            Config config = (Config) obj;
            return Intrinsics.areEqual((Object) this.feedType, (Object) config.feedType) && Intrinsics.areEqual((Object) this.positionEvery, (Object) config.positionEvery) && Intrinsics.areEqual((Object) this.positionStart, (Object) config.positionStart) && Intrinsics.areEqual((Object) this.preloadCount, (Object) config.preloadCount);
        }

        public int hashCode() {
            String str = this.feedType;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            Integer num = this.positionEvery;
            int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
            Integer num2 = this.positionStart;
            int hashCode3 = (hashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
            Integer num3 = this.preloadCount;
            if (num3 != null) {
                i = num3.hashCode();
            }
            return hashCode3 + i;
        }

        public String toString() {
            return "Config(feedType=" + this.feedType + ", positionEvery=" + this.positionEvery + ", positionStart=" + this.positionStart + ", preloadCount=" + this.preloadCount + ")";
        }

        public Config(String str, Integer num, Integer num2, Integer num3) {
            this.feedType = str;
            this.positionEvery = num;
            this.positionStart = num2;
            this.preloadCount = num3;
        }

        public final String getFeedType() {
            return this.feedType;
        }

        public final Integer getPositionEvery() {
            return this.positionEvery;
        }

        public final Integer getPositionStart() {
            return this.positionStart;
        }

        public final Integer getPreloadCount() {
            return this.preloadCount;
        }
    }
}
