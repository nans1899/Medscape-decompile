package com.medscape.android.ads;

import android.content.Context;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.medscape.android.ads.proclivity.ProclivityDataModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 52\u00020\u0001:\u00015B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u001e\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0016J\u001c\u0010,\u001a\u00020!2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050.H\u0016J%\u0010/\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0002\u00101J5\u0010/\u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0010\b\u0002\u00100\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000e¢\u0006\u0002\u00103J\u001c\u00104\u001a\u00020!2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050.H\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u00066"}, d2 = {"Lcom/medscape/android/ads/NativeAdAction;", "Lcom/medscape/android/ads/DFPAdAction;", "lContext", "Landroid/content/Context;", "adUnitID", "", "dfpAdLayout", "Landroid/view/View;", "(Landroid/content/Context;Ljava/lang/String;Landroid/view/View;)V", "adLoader", "Lcom/google/android/gms/ads/AdLoader$Builder;", "getAdLoader", "()Lcom/google/android/gms/ads/AdLoader$Builder;", "adsizes", "", "Lcom/google/android/gms/ads/AdSize;", "getAdsizes", "()[Lcom/google/android/gms/ads/AdSize;", "setAdsizes", "([Lcom/google/android/gms/ads/AdSize;)V", "[Lcom/google/android/gms/ads/AdSize;", "pgValue", "", "getPgValue", "()I", "setPgValue", "(I)V", "posValue", "getPosValue", "()Ljava/lang/String;", "setPosValue", "(Ljava/lang/String;)V", "handleDFPAd", "", "dfpAD", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "nativeDFPAdLoadListener", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "makeADRequestAfterBidding", "adRequest", "Lcom/google/android/gms/ads/doubleclick/PublisherAdRequest;", "proclivityData", "", "Lcom/medscape/android/ads/proclivity/ProclivityDataModel;", "makeADRequestWithoutBidding", "screenSpecificMap", "Ljava/util/HashMap;", "prepADWithCombinedRequests", "dfpADsizes", "(Lcom/medscape/android/ads/INativeDFPAdLoadListener;[Lcom/google/android/gms/ads/AdSize;)V", "nativeTemplates", "(Lcom/medscape/android/ads/INativeDFPAdLoadListener;[Lcom/google/android/gms/ads/AdSize;[Ljava/lang/String;)V", "prepareAdForBidding", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NativeAdAction.kt */
public final class NativeAdAction extends DFPAdAction {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_STYLE_CONFIG = "StyleConfig";
    public static final String TEMPLATE_ID_W_STYLES = "11905570";
    private final AdLoader.Builder adLoader;
    private AdSize[] adsizes;
    private int pgValue;
    private String posValue;

    public NativeAdAction(Context context, String str, View view) {
        Intrinsics.checkNotNullParameter(context, "lContext");
        Intrinsics.checkNotNullParameter(str, "adUnitID");
        this.posValue = "";
        this.context = context;
        this.adsSegvar = AdsSegvar.getInstance();
        this.adsSegvar.setGlobalMap(this.context);
        this.adLoader = new AdLoader.Builder(this.context, str);
        this.adLayout = view;
        this.dfpAd = new DFPAd(this.context);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NativeAdAction(Context context, String str, View view, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? null : view);
    }

    public final AdLoader.Builder getAdLoader() {
        return this.adLoader;
    }

    public final AdSize[] getAdsizes() {
        return this.adsizes;
    }

    public final void setAdsizes(AdSize[] adSizeArr) {
        this.adsizes = adSizeArr;
    }

    public final int getPgValue() {
        return this.pgValue;
    }

    public final void setPgValue(int i) {
        this.pgValue = i;
    }

    public final String getPosValue() {
        return this.posValue;
    }

    public final void setPosValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.posValue = str;
    }

    public void prepareAdForBidding(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "screenSpecificMap");
        updateGlobalmap(hashMap);
    }

    public void makeADRequestAfterBidding(PublisherAdRequest publisherAdRequest, List<? extends ProclivityDataModel> list) {
        Intrinsics.checkNotNullParameter(publisherAdRequest, "adRequest");
        Intrinsics.checkNotNullParameter(list, "proclivityData");
        this.adLoader.build().loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, getCombinedBundle(publisherAdRequest, list, this.adsizes)).build());
    }

    public void makeADRequestWithoutBidding(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "screenSpecificMap");
        String str = hashMap.get("pos");
        if (str != null) {
            this.posValue = str;
        }
        updateGlobalmap(hashMap);
        this.adLoader.build().loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, getBundle(createAdRequestWithDetails())).build());
    }

    public static /* synthetic */ void prepADWithCombinedRequests$default(NativeAdAction nativeAdAction, INativeDFPAdLoadListener iNativeDFPAdLoadListener, AdSize[] adSizeArr, int i, Object obj) {
        if ((i & 2) != 0) {
            adSizeArr = null;
        }
        nativeAdAction.prepADWithCombinedRequests(iNativeDFPAdLoadListener, adSizeArr);
    }

    public final void prepADWithCombinedRequests(INativeDFPAdLoadListener iNativeDFPAdLoadListener, AdSize[] adSizeArr) {
        Intrinsics.checkNotNullParameter(iNativeDFPAdLoadListener, "nativeDFPAdLoadListener");
        prepADWithCombinedRequests(iNativeDFPAdLoadListener, adSizeArr, new String[]{"11864416", "11848473", TEMPLATE_ID_W_STYLES});
    }

    public static /* synthetic */ void prepADWithCombinedRequests$default(NativeAdAction nativeAdAction, INativeDFPAdLoadListener iNativeDFPAdLoadListener, AdSize[] adSizeArr, String[] strArr, int i, Object obj) {
        if ((i & 2) != 0) {
            adSizeArr = null;
        }
        nativeAdAction.prepADWithCombinedRequests(iNativeDFPAdLoadListener, adSizeArr, strArr);
    }

    public final void prepADWithCombinedRequests(INativeDFPAdLoadListener iNativeDFPAdLoadListener, AdSize[] adSizeArr, String[] strArr) {
        Intrinsics.checkNotNullParameter(iNativeDFPAdLoadListener, "nativeDFPAdLoadListener");
        if (adSizeArr != null) {
            this.adsizes = adSizeArr;
        }
        if (this.adsizes != null) {
            AdSize[] adSizeArr2 = this.adsizes;
            Intrinsics.checkNotNull(adSizeArr2);
            this.adLoader.forPublisherAdView(new NativeAdAction$prepADWithCombinedRequests$1(this, iNativeDFPAdLoadListener), (AdSize[]) Arrays.copyOf(adSizeArr2, adSizeArr2.length));
            PublisherAdViewOptions.Builder builder = new PublisherAdViewOptions.Builder();
            builder.setAppEventListener(this);
            this.adLoader.withPublisherAdViewOptions(builder.build());
        }
        if (strArr != null) {
            for (String forCustomTemplateAd : strArr) {
                this.adLoader.forCustomTemplateAd(forCustomTemplateAd, new NativeAdAction$prepADWithCombinedRequests$$inlined$let$lambda$1(this, strArr, iNativeDFPAdLoadListener), (NativeCustomTemplateAd.OnCustomClickListener) null);
            }
        }
        this.adLoader.withAdListener(new NativeAdAction$prepADWithCombinedRequests$3(iNativeDFPAdLoadListener)).withNativeAdOptions(new NativeAdOptions.Builder().build());
    }

    /* access modifiers changed from: private */
    public final void handleDFPAd(PublisherAdView publisherAdView, INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        if (Intrinsics.areEqual((Object) publisherAdView.getAdSize(), (Object) DFPAdAction.ADSIZE_1x3)) {
            iNativeDFPAdLoadListener.onAdFailedToLoad(3);
            return;
        }
        handleDFPAdLayout(publisherAdView);
        iNativeDFPAdLoadListener.onAdLoaded(new NativeDFPAD(publisherAdView, (NativeCustomTemplateAd) null, 0, (String) null, (NativeStyleAd) null, (String) null, 62, (DefaultConstructorMarker) null));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\fJ \u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/ads/NativeAdAction$Companion;", "", "()V", "KEY_STYLE_CONFIG", "", "TEMPLATE_ID_W_STYLES", "getNativeStyleFromPosValue", "", "Lcom/medscape/android/ads/NativeStyleAd;", "context", "Landroid/content/Context;", "posValue", "(Landroid/content/Context;Ljava/lang/String;)[Lcom/medscape/android/ads/NativeStyleAd;", "pickRandomNativeStyle", "nativeAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: NativeAdAction.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c3, code lost:
            if (r9.equals(com.medscape.android.Constants.NATIVE_AD_STYLE_CONFIG_TEXT_GRAPHIC) != false) goto L_0x0150;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.medscape.android.ads.NativeStyleAd pickRandomNativeStyle(android.content.Context r7, java.lang.String r8, com.google.android.gms.ads.formats.NativeCustomTemplateAd r9) {
            /*
                r6 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "posValue"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "nativeAd"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = r9.getCustomTemplateId()
                java.lang.String r1 = "StyleConfig"
                java.lang.CharSequence r9 = r9.getText(r1)
                r1 = 0
                if (r9 == 0) goto L_0x0036
                java.lang.String r9 = r9.toString()
                if (r9 == 0) goto L_0x0036
                if (r9 == 0) goto L_0x002e
                java.lang.String r9 = r9.toLowerCase()
                java.lang.String r2 = "(this as java.lang.String).toLowerCase()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r2)
                goto L_0x0037
            L_0x002e:
                java.lang.NullPointerException r7 = new java.lang.NullPointerException
                java.lang.String r8 = "null cannot be cast to non-null type java.lang.String"
                r7.<init>(r8)
                throw r7
            L_0x0036:
                r9 = r1
            L_0x0037:
                r2 = r1
                com.medscape.android.ads.NativeStyleAd[] r2 = (com.medscape.android.ads.NativeStyleAd[]) r2
                java.lang.String r2 = "11905570"
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
                if (r0 == 0) goto L_0x015d
                r0 = r6
                com.medscape.android.ads.NativeAdAction$Companion r0 = (com.medscape.android.ads.NativeAdAction.Companion) r0
                com.medscape.android.ads.NativeStyleAd[] r7 = r0.getNativeStyleFromPosValue(r7, r8)
                int r8 = r7.length
                r0 = 1
                if (r8 <= r0) goto L_0x0150
                r8 = r9
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                r1 = 0
                if (r8 == 0) goto L_0x005c
                int r8 = r8.length()
                if (r8 != 0) goto L_0x005a
                goto L_0x005c
            L_0x005a:
                r8 = 0
                goto L_0x005d
            L_0x005c:
                r8 = 1
            L_0x005d:
                if (r8 != 0) goto L_0x0150
                java.lang.String r8 = "null"
                boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r8)
                r8 = r8 ^ r0
                if (r8 == 0) goto L_0x0150
                if (r9 != 0) goto L_0x006c
                goto L_0x014c
            L_0x006c:
                int r8 = r9.hashCode()
                java.lang.String r2 = "null cannot be cast to non-null type kotlin.Array<T>"
                switch(r8) {
                    case -1095893313: goto L_0x010c;
                    case -439109432: goto L_0x00c7;
                    case -318728404: goto L_0x00bd;
                    case 1692724708: goto L_0x0077;
                    default: goto L_0x0075;
                }
            L_0x0075:
                goto L_0x014c
            L_0x0077:
                java.lang.String r8 = "graphic only"
                boolean r8 = r9.equals(r8)
                if (r8 == 0) goto L_0x014c
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.Collection r8 = (java.util.Collection) r8
                int r9 = r7.length
                r3 = 0
            L_0x0088:
                if (r3 >= r9) goto L_0x00a0
                r4 = r7[r3]
                com.medscape.android.ads.NativeStyleAd r5 = com.medscape.android.ads.NativeStyleAd.GRAPHIC_DRIVER_SMALL_AD
                if (r4 == r5) goto L_0x0097
                com.medscape.android.ads.NativeStyleAd r5 = com.medscape.android.ads.NativeStyleAd.GRAPHIC_DRIVER_LARGE_AD
                if (r4 != r5) goto L_0x0095
                goto L_0x0097
            L_0x0095:
                r5 = 0
                goto L_0x0098
            L_0x0097:
                r5 = 1
            L_0x0098:
                if (r5 == 0) goto L_0x009d
                r8.add(r4)
            L_0x009d:
                int r3 = r3 + 1
                goto L_0x0088
            L_0x00a0:
                java.util.List r8 = (java.util.List) r8
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r9 = r8.isEmpty()
                r9 = r9 ^ r0
                if (r9 == 0) goto L_0x0150
                com.medscape.android.ads.NativeStyleAd[] r7 = new com.medscape.android.ads.NativeStyleAd[r1]
                java.lang.Object[] r7 = r8.toArray(r7)
                if (r7 == 0) goto L_0x00b7
                com.medscape.android.ads.NativeStyleAd[] r7 = (com.medscape.android.ads.NativeStyleAd[]) r7
                goto L_0x0150
            L_0x00b7:
                java.lang.NullPointerException r7 = new java.lang.NullPointerException
                r7.<init>(r2)
                throw r7
            L_0x00bd:
                java.lang.String r8 = "text and graphic"
                boolean r8 = r9.equals(r8)
                if (r8 == 0) goto L_0x014c
                goto L_0x0150
            L_0x00c7:
                java.lang.String r8 = "text and graphic with body"
                boolean r8 = r9.equals(r8)
                if (r8 == 0) goto L_0x014c
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.Collection r8 = (java.util.Collection) r8
                int r9 = r7.length
                r3 = 0
            L_0x00d8:
                if (r3 >= r9) goto L_0x00f0
                r4 = r7[r3]
                com.medscape.android.ads.NativeStyleAd r5 = com.medscape.android.ads.NativeStyleAd.TEXT_AD
                if (r4 == r5) goto L_0x00e7
                com.medscape.android.ads.NativeStyleAd r5 = com.medscape.android.ads.NativeStyleAd.GRAPHIC_DRIVER_LARGE_AD
                if (r4 != r5) goto L_0x00e5
                goto L_0x00e7
            L_0x00e5:
                r5 = 0
                goto L_0x00e8
            L_0x00e7:
                r5 = 1
            L_0x00e8:
                if (r5 == 0) goto L_0x00ed
                r8.add(r4)
            L_0x00ed:
                int r3 = r3 + 1
                goto L_0x00d8
            L_0x00f0:
                java.util.List r8 = (java.util.List) r8
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r9 = r8.isEmpty()
                r9 = r9 ^ r0
                if (r9 == 0) goto L_0x0150
                com.medscape.android.ads.NativeStyleAd[] r7 = new com.medscape.android.ads.NativeStyleAd[r1]
                java.lang.Object[] r7 = r8.toArray(r7)
                if (r7 == 0) goto L_0x0106
                com.medscape.android.ads.NativeStyleAd[] r7 = (com.medscape.android.ads.NativeStyleAd[]) r7
                goto L_0x0150
            L_0x0106:
                java.lang.NullPointerException r7 = new java.lang.NullPointerException
                r7.<init>(r2)
                throw r7
            L_0x010c:
                java.lang.String r8 = "text only"
                boolean r8 = r9.equals(r8)
                if (r8 == 0) goto L_0x014c
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                java.util.Collection r8 = (java.util.Collection) r8
                int r9 = r7.length
                r3 = 0
            L_0x011d:
                if (r3 >= r9) goto L_0x0130
                r4 = r7[r3]
                com.medscape.android.ads.NativeStyleAd r5 = com.medscape.android.ads.NativeStyleAd.TEXT_AD
                if (r4 != r5) goto L_0x0127
                r5 = 1
                goto L_0x0128
            L_0x0127:
                r5 = 0
            L_0x0128:
                if (r5 == 0) goto L_0x012d
                r8.add(r4)
            L_0x012d:
                int r3 = r3 + 1
                goto L_0x011d
            L_0x0130:
                java.util.List r8 = (java.util.List) r8
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r9 = r8.isEmpty()
                r9 = r9 ^ r0
                if (r9 == 0) goto L_0x0150
                com.medscape.android.ads.NativeStyleAd[] r7 = new com.medscape.android.ads.NativeStyleAd[r1]
                java.lang.Object[] r7 = r8.toArray(r7)
                if (r7 == 0) goto L_0x0146
                com.medscape.android.ads.NativeStyleAd[] r7 = (com.medscape.android.ads.NativeStyleAd[]) r7
                goto L_0x0150
            L_0x0146:
                java.lang.NullPointerException r7 = new java.lang.NullPointerException
                r7.<init>(r2)
                throw r7
            L_0x014c:
                com.medscape.android.ads.NativeStyleAd[] r7 = com.medscape.android.ads.NativeStyleAd.values()
            L_0x0150:
                java.util.Random r8 = new java.util.Random
                r8.<init>()
                int r9 = r7.length
                int r8 = r8.nextInt(r9)
                r7 = r7[r8]
                return r7
            L_0x015d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.NativeAdAction.Companion.pickRandomNativeStyle(android.content.Context, java.lang.String, com.google.android.gms.ads.formats.NativeCustomTemplateAd):com.medscape.android.ads.NativeStyleAd");
        }

        private final NativeStyleAd[] getNativeStyleFromPosValue(Context context, String str) {
            if (Intrinsics.areEqual((Object) str, (Object) NativeContentAd.ASSET_LOGO)) {
                return new NativeStyleAd[]{NativeStyleAd.TEXT_AD, NativeStyleAd.GRAPHIC_DRIVER_SMALL_AD};
            } else if (!Intrinsics.areEqual((Object) str, (Object) "2620")) {
                return NativeStyleAd.values();
            } else {
                return new NativeStyleAd[]{NativeStyleAd.TEXT_AD};
            }
        }
    }
}
